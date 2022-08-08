package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public final class Book {

    private BookId id;
    private String title;
    private Isbn isbn;
    private String author;

    public Book(BookId id, String title, Isbn isbn, String author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public static Book createNew(String title, Isbn isbn, String author) {
        return new Book(BookId.createNew(), title, isbn, author);
    }

    public static Book fromSnapshot(BookSnapshot snapshot) {
        return new Book(snapshot.id(), snapshot.title(), snapshot.isbn(), snapshot.author());
    }

    public BookSnapshot takeSnapshot() {
        return new BookSnapshot(id, title, isbn, author);
    }
}