package be.koder.library.test;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.api.book.BookListItem;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.test.data.BookObjectMother;
import be.koder.library.test.util.TestUtil;
import be.koder.library.vocabulary.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given an API to add Books to library")
public class AddBookTest {

    @Nested
    @DisplayName("when Book added")
    class TestHappyFlow implements AddBookPresenter {

        private final BookSnapshot book = BookObjectMother.INSTANCE.harryPotterAndTheChamberOfSecrets;
        private boolean addedCalled;
        private BookId bookId;
        private BookListItem savedBook;

        @BeforeEach
        void setup() {
            TestApplicationContext.INSTANCE.clear();
            TestApplicationContext.INSTANCE.addBook.addBook(book.title(), book.isbn().toString(), book.author(), this);
            savedBook = TestApplicationContext.INSTANCE.listBooks.listBooks().stream()
                    .filter(it -> it.id().equals(bookId))
                    .findFirst()
                    .orElseThrow();
        }

        @Test
        @DisplayName("it should be saved")
        void bookSaved() {
            assertThat(savedBook.id()).isEqualTo(bookId);
            assertThat(savedBook.title()).isEqualTo(book.title());
            assertThat(savedBook.isbn()).isEqualTo(book.isbn());
            assertThat(savedBook.author()).isEqualTo(book.author());
        }

        @Test
        @DisplayName("it should provide feedback")
        void feedbackProvided() {
            assertTrue(addedCalled);
            assertNotNull(bookId);
        }

        @Override
        public void added(BookId bookId) {
            this.addedCalled = true;
            this.bookId = bookId;
        }

        @Override
        public void invalidIsbn() {
            TestUtil.INSTANCE.fail();
        }
    }

    @Nested
    @DisplayName("when Book added with invalid ISBN")
    class TestInvalidISBN implements AddBookPresenter {

        private final String invalidIsbn = "0123456789";
        private boolean invalidIsbnCalled;

        @BeforeEach
        void setup() {
            TestApplicationContext.INSTANCE.addBook.addBook(
                    BookObjectMother.INSTANCE.harryPotterAndTheChamberOfSecrets.title(),
                    invalidIsbn,
                    BookObjectMother.INSTANCE.harryPotterAndTheChamberOfSecrets.author(),
                    this
            );
        }

        @Test
        @DisplayName("it should provide feedback")
        void feedbackProvided() {
            assertTrue(invalidIsbnCalled);
        }

        @Override
        public void added(BookId bookId) {
            TestUtil.INSTANCE.fail();
        }

        @Override
        public void invalidIsbn() {
            invalidIsbnCalled = true;
        }
    }
}