package be.koder.library.test.mock;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.vocabulary.book.BookId;

public final class MockAddBookPresenter implements AddBookPresenter {

    private BookId bookId;

    @Override
    public void added(BookId bookId) {
        this.bookId = bookId;
    }

    @Override
    public void invalidIsbn() {
    }

    @Override
    public void existingIsbn() {
    }

    public BookId getBookId() {
        return bookId;
    }
}