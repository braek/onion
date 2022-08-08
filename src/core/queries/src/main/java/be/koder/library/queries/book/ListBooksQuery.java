package be.koder.library.queries.book;

import be.koder.library.api.book.BookListItem;
import be.koder.library.api.book.ListBooks;

import java.util.List;

public final class ListBooksQuery implements ListBooks {

    private final BookArchive bookArchive;

    public ListBooksQuery(BookArchive bookArchive) {
        this.bookArchive = bookArchive;
    }

    @Override
    public List<BookListItem> listBooks() {
        return bookArchive.listBooks().stream().map(it -> new BookListItem(
                it.id(),
                it.title(),
                it.isbn(),
                it.author()
        )).toList();
    }
}