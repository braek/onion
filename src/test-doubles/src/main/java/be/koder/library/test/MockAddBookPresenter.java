package be.koder.library.test;

import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.vocabulary.book.BookId;

public final class MockAddBookPresenter implements AddBookPresenter {

    @Override
    public void added(BookId bookId) {

    }

    @Override
    public void invalidIsbn() {

    }
}