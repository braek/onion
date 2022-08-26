package be.koder.library.mutators.book;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.AddBookPresenter;
import be.koder.library.domain.EventPublisher;
import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookAdded;
import be.koder.library.domain.book.BookRepository;
import be.koder.library.domain.book.IsbnService;
import be.koder.library.mutators.Mutator;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;
import be.koder.library.vocabulary.book.exception.InvalidIsbnException;

public final class AddBookMutator implements AddBook, Mutator<AddBookCommand, AddBookPresenter> {

    private final BookRepository bookRepository;
    private final IsbnService isbnService;
    private final EventPublisher eventPublisher;

    public AddBookMutator(BookRepository bookRepository, IsbnService isbnService, EventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.isbnService = isbnService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void addBook(String title, String isbn, String author, AddBookPresenter presenter) {
        execute(new AddBookCommand(title, isbn, author), presenter);
    }

    @Override
    public void execute(AddBookCommand command, AddBookPresenter presenter) {
        try {
            final Isbn isbn = new Isbn(command.isbn());
            if (isbnService.exists(isbn)) {
                presenter.existingIsbn();
                return;
            }
            final Book book = Book.create(command.title(), isbn, command.author());
            final BookId bookId = book.takeSnapshot().id();
            bookRepository.save(book);
            eventPublisher.publish(new BookAdded(bookId));
            presenter.added(bookId);
        } catch (InvalidIsbnException e) {
            presenter.invalidIsbn();
        }
    }
}