package be.koder.library.test.context;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.ListBooks;
import be.koder.library.mutators.book.AddBookMutator;
import be.koder.library.queries.book.ListBooksQuery;
import be.koder.library.test.mock.MockBookRepository;
import be.koder.library.test.mock.MockEventPublisher;

public enum TestApplicationContext {

    INSTANCE;

    private final MockEventPublisher eventPublisher = new MockEventPublisher();
    private final MockBookRepository bookRepository = new MockBookRepository();

    public final AddBook addBook = new AddBookMutator(bookRepository, bookRepository, eventPublisher);
    public final ListBooks listBooks = new ListBooksQuery(bookRepository);

    public void clear() {
        eventPublisher.clear();
        bookRepository.clear();
    }
}