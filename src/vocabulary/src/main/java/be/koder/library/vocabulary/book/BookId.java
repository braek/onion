package be.koder.library.vocabulary.book;

import java.util.Objects;
import java.util.UUID;

public final class BookId {

    private final UUID value;

    private BookId(UUID value) {
        this.value = value;
    }

    public static BookId create() {
        return new BookId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return Objects.equals(value, bookId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
