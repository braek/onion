package be.koder.library.vocabulary.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Given a class to create ISBNs")
class ISBNTest {

    @Nested
    @DisplayName("when valid ISBN created")
    class TestValidISBN {

        @Test
        @DisplayName("it should succeed")
        void creationSucceeded() {
            var isbn = ISBN.create("9782223334445");
            assertThat(isbn).isNotNull();
            assertThat(isbn.toString()).isEqualTo("9782223334445");
        }
    }

    @Nested
    @DisplayName("when invalid ISBN created")
    class TestInvalidISBN {

        @Test
        @DisplayName("it should throw exception")
        void exceptionThrown() {
            assertThrows(InvalidISBNException.class, () -> ISBN.create("abc"));
        }
    }
}