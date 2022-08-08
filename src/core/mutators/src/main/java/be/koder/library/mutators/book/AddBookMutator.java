package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookRepository;
import be.koder.library.mutators.Mutator;

public final class AddBookMutator implements AddBook, Mutator<AddBookCommand, AddBookPresenter> {

    private final BookRepository bookRepository;

    public AddBookMutator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(String title, String isbn, String author, AddBookPresenter presenter) {
        execute(new AddBookCommand(title, isbn, author), presenter);
    }

    @Override
    public void execute(AddBookCommand command, AddBookPresenter presenter) {
        final Book book = Book.create(command.title(), command.isbn(), command.author());
        bookRepository.save(book);
        presenter.added(book.takeSnapshot().id());
    }
}