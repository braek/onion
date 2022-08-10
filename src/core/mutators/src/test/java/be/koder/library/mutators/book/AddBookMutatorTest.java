package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookAdded;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.test.mock.MockAddBookPresenter;
import be.koder.library.test.mock.MockBookRepository;
import be.koder.library.test.mock.MockEventPublisher;
import be.koder.library.test.objectmother.BookObjectMother;
import be.koder.library.test.util.TestUtil;
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

        private final BookSnapshot book = BookObjectMother.INSTANCE.harryPotterPrisonerOfAzkaban;
        private BookId bookId;
        private BookSnapshot savedBook;

        @BeforeEach
        void setup() {
            addBookMutator.execute(new AddBookCommand(book.title(), book.isbn().toString(), book.author()), this);
            savedBook = bookRepository.getById(bookId)
                    .map(Book::takeSnapshot)
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
            assertNotNull(bookId);
        }

        @Override
        public void added(BookId bookId) {
            this.bookId = bookId;
        }

        @Override
        public void invalidIsbn() {
            TestUtil.INSTANCE.fail();
        }

        @Override
        public void existingIsbn() {
            TestUtil.INSTANCE.fail();
        }
    }

    @Nested
    @DisplayName("when Book added with invalid ISBN")
    class TestInvalidIsbn implements AddBookPresenter {

        private final String invalidIsbn = "1408855669";
        private boolean invalidIsbnCalled;

        @BeforeEach
        void setup() {
            addBookMutator.execute(new AddBookCommand(
                    BookObjectMother.INSTANCE.harryPotterPrisonerOfAzkaban.title(),
                    invalidIsbn,
                    BookObjectMother.INSTANCE.harryPotterPrisonerOfAzkaban.author()
            ), this);
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
            this.invalidIsbnCalled = true;
        }

        @Override
        public void existingIsbn() {
            TestUtil.INSTANCE.fail();
        }
    }

    @Nested
    @DisplayName("when Book added with existing ISBN")
    class TestExistingIsbn implements AddBookPresenter {

        private final BookSnapshot book = BookObjectMother.INSTANCE.harryPotterAndTheChamberOfSecrets;
        private boolean existingIsbnCalled;

        @BeforeEach
        void setup() {
            addBookMutator.execute(new AddBookCommand(
                    book.title(),
                    book.isbn().toString(),
                    book.author()
            ), new MockAddBookPresenter());
            addBookMutator.execute(new AddBookCommand(
                    book.title(),
                    book.isbn().toString(),
                    book.author()
            ), this);
        }

        @Test
        @DisplayName("it should provide feedback")
        void feedbackProvided() {
            assertTrue(existingIsbnCalled);
        }

        @Override
        public void added(BookId bookId) {
            TestUtil.INSTANCE.fail();
        }

        @Override
        public void invalidIsbn() {
            TestUtil.INSTANCE.fail();
        }

        @Override
        public void existingIsbn() {
            this.existingIsbnCalled = true;
        }
    }
}