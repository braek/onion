package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public final class Book {

    private final BookId id;
    private final String title;
    private final ISBN isbn;
    private final String author;

    private Book(BookId id, String title, ISBN isbn, String author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public BookSnapshot takeSnapshot() {
        return new BookSnapshot(id, title, isbn, author);
    }

    public static Book create(String title, ISBN isbn, String author) {
        return new Book(BookId.generate(), title, isbn, author);
    }

    public static Book fromSnapshot(BookSnapshot snapshot) {
        return new Book(snapshot.id(), snapshot.title(), snapshot.isbn(), snapshot.author());
    }
}