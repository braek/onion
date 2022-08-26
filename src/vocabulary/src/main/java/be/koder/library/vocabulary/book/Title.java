package be.koder.library.vocabulary.book;

import be.koder.library.vocabulary.book.exception.InvalidTitleException;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public final class Title {

    private final String value;

    public Title(String value) {
        final String sanitized = ofNullable(value)
                .map(String::trim)
                .orElse(null);
        if (sanitized == null || sanitized.length() > 50) {
            throw new InvalidTitleException(sanitized);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
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