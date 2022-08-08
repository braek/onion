package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;

public record BookSnapshot(
        BookId id,
        String title,
        String isbn,
        String author
) {
}