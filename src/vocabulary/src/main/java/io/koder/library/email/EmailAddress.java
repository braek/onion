package io.koder.library.email;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public final class EmailAddress {

    private final String value;

    public EmailAddress(final String str) {
        final String sanitized = ofNullable(str)
                .map(String::trim)
                .map(String::toLowerCase)
                .orElse(null);
        if (EmailValidator.getInstance().isValid(sanitized)) {
            this.value = sanitized;
            return;
        }
        throw new InvalidEmailAddressException(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}