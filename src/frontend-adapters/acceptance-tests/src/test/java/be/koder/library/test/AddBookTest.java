package be.koder.library.test;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given an API to add Books to library")
public class AddBookTest {

    @Nested
    @DisplayName("when Book added")
    class TestHappyFlow implements AddBookPresenter {

        private final BookSnapshot book = BookObjectMother.INSTANCE.harryPotterAndTheChamberofSecrets;
        private boolean addedCalled;
        private BookId bookId;

        @BeforeEach
        void setup() {
            TestApplicationContext.INSTANCE.addBook.addBook(book.title(), book.isbn().toString(), book.author(), this);
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
            fail("Should not be called");
        }
    }

    @Nested
    @DisplayName("when Book added with invalid ISBN")
    class TestInvalidIsbn implements AddBookPresenter {

        private final String invalidIsbn = "0123456789";
        private boolean invalidIsbnCalled;

        @BeforeEach
        void setup() {
            TestApplicationContext.INSTANCE.addBook.addBook(
                    BookObjectMother.INSTANCE.harryPotterAndTheChamberofSecrets.title(),
                    invalidIsbn,
                    BookObjectMother.INSTANCE.harryPotterAndTheChamberofSecrets.author(),
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
            fail("Should not be called");
        }

        @Override
        public void invalidIsbn() {
            invalidIsbnCalled = true;
        }
    }
}