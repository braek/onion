package be.koder.library.test.presenter;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.vocabulary.book.BookId;

public final class TestAddBookPresenter implements AddBookPresenter {

    private BookId bookId;

    @Override
    public void added(BookId bookId) {
        this.bookId = bookId;
    }

    @Override
    public void invalidIsbn() {
    }

    public BookId getBookId() {
        return bookId;
    }
}