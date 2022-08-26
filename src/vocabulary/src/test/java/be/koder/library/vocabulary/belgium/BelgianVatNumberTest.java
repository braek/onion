package be.koder.library.vocabulary.belgium;

import be.koder.library.vocabulary.belgium.exception.InvalidBelgianVatNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Given a domain primitive called 'Belgian VAT Number'")
class BelgianVatNumberTest {

    @ParameterizedTest
    @DisplayName("when valid 'Belgian VAT Number' constructed")
    @ValueSource(strings = {
            "be0222   333\n\n\n\r\t\t\s444",
            "BE0333444555",
            "be0333444555",
            "BE1666777888"
    })
    void testValidBelgianVatNumber(String str) {
        var vatNumber = new BelgianVatNumber(str);
        assertThat(vatNumber).isNotNull();
        assertThat(vatNumber.toString().length()).isEqualTo(15);
    }

    @ParameterizedTest
    @DisplayName("when invalid 'Belgian VAT Number' constructed")
    @NullSource
    @ValueSource(strings = {
            "+3211445566",
            "batman@gothamcity.com",
            "BE2333444555",
            "BE3666777888",
            "NL0666777888"
    })
    void testInvalidBelgianVatNumber(final String str) {
        assertThrows(InvalidBelgianVatNumberException.class, () -> new BelgianVatNumber(str));
    }
}