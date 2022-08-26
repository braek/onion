package be.koder.library.vocabulary.email;

import be.koder.library.vocabulary.email.exception.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

import static java.util.Optional.ofNullable;

public record Email(String value) {

    public Email {
        final String sanitized = ofNullable(value)
                .map(String::trim)
                .map(String::toLowerCase)
                .orElse(null);
        if (!EmailValidator.getInstance().isValid(sanitized)) {
            throw new InvalidEmailException(value);
        }
        value = sanitized;
    }

    @Override
    public String toString() {
        return value;
    }
}