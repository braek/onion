package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public record BookSnapshot(
        BookId id,
        String title,
        ISBN isbn,
        String author
) {
}