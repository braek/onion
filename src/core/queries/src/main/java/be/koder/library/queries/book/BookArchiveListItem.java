package be.koder.library.queries.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public record BookArchiveListItem(
        BookId id,
        String title,
        ISBN isbn,
        String author
) {
}