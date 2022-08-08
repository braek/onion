package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.EventPublisher;
import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookAdded;
import be.koder.library.domain.book.BookRepository;
import be.koder.library.mutators.Mutator;
import be.koder.library.vocabulary.book.BookId;

public final class AddBookMutator implements AddBook, Mutator<AddBookCommand, AddBookPresenter> {

    private final BookRepository bookRepository;
    private final EventPublisher eventPublisher;

    public AddBookMutator(BookRepository bookRepository, EventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void addBook(String title, String isbn, String author, AddBookPresenter presenter) {
        execute(new AddBookCommand(title, isbn, author), presenter);
    }

    @Override
    public void execute(AddBookCommand command, AddBookPresenter presenter) {
        final Book book = Book.createNew(command.title(), command.isbn(), command.author());
        bookRepository.save(book);
        final BookId bookId = book.takeSnapshot().id();
        eventPublisher.publish(new BookAdded(bookId));
        presenter.added(bookId);
    }
}