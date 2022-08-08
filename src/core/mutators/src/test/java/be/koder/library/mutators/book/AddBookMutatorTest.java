package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookAdded;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.test.MockBookRepository;
import be.koder.library.test.MockEventPublisher;
import be.koder.library.vocabulary.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a mutator to add Books to library")
class AddBookMutatorTest {

    private final MockBookRepository bookRepository = new MockBookRepository();
    private final MockEventPublisher eventPublisher = new MockEventPublisher();
    private final AddBookMutator addBookMutator = new AddBookMutator(bookRepository, eventPublisher);

    @Nested
    @DisplayName("when Book added")
    class TestHappyFlow implements AddBookPresenter {

        private final String title = "Harry Potter and the Philosopher's Stone";
        private final String isbn = "0-7475-3269-9";
        private final String author = "J. K. Rowling";
        private boolean addedCalled;
        private BookId bookId;
        private BookSnapshot book;

        @BeforeEach
        void setup() {
            addBookMutator.execute(new AddBookCommand(title, isbn, author), this);
            book = bookRepository.get(bookId)
                    .map(Book::takeSnapshot)
                    .orElseThrow();
        }

        @Test
        @DisplayName("it should be saved")
        void bookSaved() {
            assertThat(book.id()).isEqualTo(bookId);
            assertThat(book.title()).isEqualTo(title);
            assertThat(book.isbn()).isEqualTo(isbn);
            assertThat(book.author()).isEqualTo(author);
        }

        @Test
        @DisplayName("it should publish event")
        void eventPublished() {
            assertThat(eventPublisher.getLastEvent()).hasValueSatisfying(it -> {
                assertThat(it).isInstanceOf(BookAdded.class);
                var event = (BookAdded) it;
                assertThat(event.bookId()).isEqualTo(bookId);
            });
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