package be.koder.library.queries.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public record BookArchiveListItem(
        BookId id,
        String title,
        Isbn isbn,
        String author
) {
}