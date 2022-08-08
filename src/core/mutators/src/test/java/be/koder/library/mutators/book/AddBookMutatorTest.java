package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBookPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a mutator to add Books to library")
class AddBookMutatorTest {

    private final AddBookMutator addBookMutator = new AddBookMutator();

    @Nested
    @DisplayName("when Book added")
    class TestHappy implements AddBookPresenter {

        private final String title = "Harry Potter and the Philosopher's Stone";
        private final String isbn = "0-7475-3269-9";
        private final String author = "J. K. Rowling";
        private boolean addedCalled;

        @BeforeEach
        void setup() {
            addBookMutator.execute(new AddBookCommand(title, isbn, author), this);
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