package be.koder.library.test;

import be.koder.library.api.book.AddBook;
import be.koder.library.api.book.ListBooks;
import be.koder.library.mutators.book.AddBookMutator;
import be.koder.library.queries.book.ListBooksQuery;
import be.koder.library.test.event.InMemoryEventPublisher;
import be.koder.library.test.repository.InMemoryBookRepository;

public enum TestApplicationContext {

    INSTANCE;

    private final InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();
    private final InMemoryBookRepository bookRepository = new InMemoryBookRepository();

    public final AddBook addBook = new AddBookMutator(bookRepository, eventPublisher);
    public final ListBooks listBooks = new ListBooksQuery(bookRepository);

    public void clear() {
        eventPublisher.clear();
        bookRepository.clear();
    }
}