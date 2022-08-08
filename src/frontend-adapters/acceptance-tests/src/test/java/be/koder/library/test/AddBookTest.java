package be.koder.library.test;

import be.koder.library.api.AddBook;
import be.koder.library.api.AddBookPresenter;
import be.koder.library.mutators.book.AddBookMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given an API to add Books to library")
public class AddBookTest {

    private final AddBook addBook = new AddBookMutator();

    @Nested
    @DisplayName("when valid Book added")
    class TestHappyFlow implements AddBookPresenter {

        private final String bookTitle = "Harry Potter and the Philosopher's Stone";
        private final String bookIsbn = "0-7475-3269-9";
        private final String bookAuthor = "J. K. Rowling";
        private boolean addedCalled;

        @BeforeEach
        void setup() {
            addBook.addBook(bookTitle, bookIsbn, bookAuthor, this);
        }

        @Test
        @DisplayName("it should provide feedback")
        void feedbackProvided() {
            assertTrue(addedCalled);
        }

        @Override
        public void added() {
            addedCalled = true;
        }
    }
}