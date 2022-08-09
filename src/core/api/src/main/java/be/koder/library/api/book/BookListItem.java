package be.koder.library.api.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public record BookListItem(
        BookId id,
        String title,
        ISBN isbn,
        String author
) {
}