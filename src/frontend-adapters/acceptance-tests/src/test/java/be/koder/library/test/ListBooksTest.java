package be.koder.library.test;

import be.koder.library.api.book.BookListItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Given an API to list Books")
class ListBooksTest {

    @Nested
    @DisplayName("when it is requested")
    class TestHappyFlow {

        private final List<BookListItem> items = emptyList();

        @BeforeEach
        void setup() {
            var book = BookObjectMother.INSTANCE.harryPotterAndTheChamberofSecrets;
            var presenter = new MockAddBookPresenter();
            TestApplicationContext.INSTANCE.addBook.addBook(book.title(), book.isbn().toString(), book.author(), presenter);
            items.addAll(TestApplicationContext.INSTANCE.listBooks.listBooks());
        }

        @Test
        @DisplayName("it should return items")
        void itemsReturned() {
            assertThat(items).isNotEmpty();
        }
    }
}