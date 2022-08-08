package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public record BookSnapshot(
        BookId id,
        String title,
        Isbn isbn,
        String author
) {
}