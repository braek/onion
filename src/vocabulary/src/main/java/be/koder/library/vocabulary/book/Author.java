package be.koder.library.vocabulary.book;

import be.koder.library.vocabulary.book.exception.InvalidAuthorException;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public final class Author {

    private final String value;

    public Author(String value) {
        final String sanitized = ofNullable(value)
                .map(String::trim)
                .orElse(null);
        if (sanitized == null || sanitized.length() > 50) {
            throw new InvalidAuthorException(sanitized);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author title = (Author) o;
        return Objects.equals(value, title.value);
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