package be.koder.library.vocabulary.book;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public final class ISBN {

    private final String value;

    public ISBN(final String str) {
        final String sanitized = Optional.ofNullable(str)
                .map(String::trim)
                .orElse(null);
        final Pattern regex = Pattern.compile("^978\\d{10}$");
        if (sanitized == null || !regex.matcher(sanitized).matches()) {
            throw new InvalidISBNException(sanitized);
        }
        this.value = sanitized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISBN isbn = (ISBN) o;
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