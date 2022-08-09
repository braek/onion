package be.koder.library.test;

import be.koder.library.api.book.BookListItem;
import be.koder.library.test.util.TestApplicationContext;
import be.koder.library.test.mock.MockAddBookPresenter;
import be.koder.library.test.objectmother.BookObjectMother;
import be.koder.library.vocabulary.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Given an API to list Books")
class ListBooksTest {

    @Nested
    @DisplayName("when it is requested")
    class TestHappyFlow {

        private final List<BookListItem> items = new ArrayList<>();
        private BookId bookId;

        @BeforeEach
        void setup() {
            var book = BookObjectMother.INSTANCE.harryPotterAndTheChamberOfSecrets;
            var presenter = new MockAddBookPresenter();
            TestApplicationContext.INSTANCE.clear();
            TestApplicationContext.INSTANCE.addBook.addBook(book.title(), book.isbn().toString(), book.author(), presenter);
            bookId = presenter.getBookId();
            items.addAll(TestApplicationContext.INSTANCE.listBooks.listBooks());
        }

        @Test
        @DisplayName("it should return items")
        void itemsReturned() {
            assertThat(items).hasSize(1);
            assertThat(items.get(0).id()).isEqualTo(bookId);
        }
    }
}