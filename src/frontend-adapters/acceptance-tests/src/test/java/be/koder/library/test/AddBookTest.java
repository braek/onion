package be.koder.library.test;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.mutators.book.AddBookMutator;
import be.koder.library.vocabulary.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given an API to add Books to library")
public class AddBookTest {

    private final MockBookRepository bookRepository = new MockBookRepository();
    private final AddBook addBook = new AddBookMutator(bookRepository);

    @Nested
    @DisplayName("when Book added")
    class TestHappyFlow implements AddBookPresenter {

        private final String title = "Harry Potter and the Philosopher's Stone";
        private final String isbn = "0-7475-3269-9";
        private final String author = "J. K. Rowling";
        private boolean addedCalled;
        private BookId bookId;

        @BeforeEach
        void setup() {
            addBook.addBook(title, isbn, author, this);
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
    }
}