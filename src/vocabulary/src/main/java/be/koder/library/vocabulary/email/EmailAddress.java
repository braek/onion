package be.koder.library.vocabulary.email;

import org.apache.commons.validator.routines.EmailValidator;

import static java.util.Optional.ofNullable;

public record EmailAddress(String value) {

    public EmailAddress {
        final String sanitized = ofNullable(value)
                .map(String::trim)
                .map(String::toLowerCase)
                .orElse(null);
        if (!EmailValidator.getInstance().isValid(sanitized)) {
            throw new InvalidEmailAddressException(value);
        }
        value = sanitized;
    }

    @Override
    public String toString() {
        return value;
    }
}