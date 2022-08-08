package be.koder.library.api.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public record BookListItem(
        BookId id,
        String title,
        Isbn isbn,
        String author
) {}