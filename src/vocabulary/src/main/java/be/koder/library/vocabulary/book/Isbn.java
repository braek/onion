package be.koder.library.vocabulary.book;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public final class Isbn {

    private final String value;

    private Isbn(final String str) {
        final String sanitized = sanitize(str);
        final Pattern regex = Pattern.compile("^\\d{13}$");
        if (sanitized == null || !regex.matcher(sanitized).matches()) {
            throw new InvalidIsbnException(sanitized);
        }
        this.value = String.format(
                "%s-%s-%s-%s-%s",
                sanitized.substring(0, 3),
                sanitized.substring(3, 5),
                sanitized.substring(5, 10),
                sanitized.substring(10, 12),
                sanitized.charAt(12)
        );
    }

    private String sanitize(final String str) {
        return Optional.ofNullable(str)
                .map(String::trim)
                .map(String::toUpperCase)
                .orElse(null);
    }

    public static Isbn fromString(final String str) {
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