package be.koder.library.vocabulary.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Given a class to create e-mail addresses")
public class EmailAddressTest {

    @Nested
    @DisplayName("when valid e-mail address created")
    class TestValidEmailAddress {

        @ParameterizedTest
        @ValueSource(strings = {
                "BATMAN@GOTHAMCITY.COM",
                "joker@arkhamcity.com",
                "\r\n\tTWO-face@gothamCITY.com\r\n\t"
        })
        @DisplayName("it should succeed")
        void creationSucceeded(String str) {
            var email = new EmailAddress(str);
            assertThat(email).isNotNull();
            assertThat(email.toString()).isEqualTo(str.trim().toLowerCase());
        }
    }

    @Nested
    @DisplayName("when invalid e-mail address created")
    class TestInvalidEmailAddress {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {
                "abc",
                "123",
                "0002223334445",
                "The Dark Knight",
                "The Legend of Zelda: Ocarina of Time",
                "De GVR",
                "AD1400080001001234567890"
        })
        @DisplayName("it should throw exception")
        void exceptionThrown(String str) {
            assertThrows(InvalidEmailAddressException.class, () -> new EmailAddress(str));
        }
    }
}