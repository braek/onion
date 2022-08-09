package be.koder.library.vocabulary.book;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public final class Isbn {

    private final String value;

    private Isbn(final String str) {
        final String sanitized = Optional.ofNullable(str)
                .map(String::trim)
                .map(String::toUpperCase)
                .orElse(null);
        final Pattern regex = Pattern.compile("^978\\d{10}$");
        if (sanitized == null || !regex.matcher(sanitized).matches()) {
            throw new InvalidIsbnException(sanitized);
        }
        this.value = sanitized;
    }

    public static Isbn create(final String str) {
        return new Isbn(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Isbn isbn = (Isbn) o;
        return Objects.equals(value, isbn.value);
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