package be.koder.library.api.book;

import be.koder.library.vocabulary.book.BookId;

public interface AddBookPresenter {

    void added(BookId bookId);

    void invalidIsbn();
}