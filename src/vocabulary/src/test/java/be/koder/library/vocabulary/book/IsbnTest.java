package be.koder.library.vocabulary.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Given a class to create ISBNs")
class IsbnTest {

    @Nested
    @DisplayName("when valid ISBN created")
    class TestValidIsbn {

        @ParameterizedTest
        @ValueSource(strings = {
                "9782223334445",
                "9780009998888",
                "9781117774444",
                "\r\n\t9781117774444\r\n\t"
        })
        @DisplayName("it should succeed")
        void creationSucceeded(String str) {
            var isbn = new Isbn(str);
            assertThat(isbn.toString()).isEqualTo(str.trim());
            assertThat(isbn.value()).isEqualTo(str.trim());
        }
    }

    @Nested
    @DisplayName("when invalid ISBN created")
    class TestInvalidIsbn {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {
                "abc",
                "123",
                "0002223334445",
                "The Dark Knight",
                "batman@gothamcity.com",
                "The Legend of Zelda: Ocarina of Time",
                "De GVR",
                "AD1400080001001234567890"
        })
        @DisplayName("it should throw exception")
        void exceptionThrown(String str) {
            assertThrows(InvalidIsbnException.class, () -> new Isbn(str));
        }
    }
}