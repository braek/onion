package be.koder.library.mutators.book;

import be.koder.library.api.AddBook;
import be.koder.library.api.AddBookPresenter;
import be.koder.library.mutators.Mutator;

public final class AddBookMutator implements AddBook, Mutator<AddBookCommand, AddBookPresenter> {

    @Override
    public void addBook(String title, String isbn, String author, AddBookPresenter presenter) {
        execute(new AddBookCommand(title, isbn, author), presenter);
    }

    @Override
    public void execute(AddBookCommand command, AddBookPresenter presenter) {
        // TODO: implement this one
    }
}