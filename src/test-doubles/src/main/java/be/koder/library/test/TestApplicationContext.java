package be.koder.library.test;

import be.koder.library.api.book.AddBook;
import be.koder.library.mutators.book.AddBookMutator;

public enum TestApplicationContext {

    INSTANCE;

    private final MockEventPublisher eventPublisher = new MockEventPublisher();
    private final MockBookRepository bookRepository = new MockBookRepository();

    public final AddBook addBook = new AddBookMutator(bookRepository, eventPublisher);
}