package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.BookId;

public final class Book {

    private BookId id;
    private String title;
    private String isbn;
    private String author;

    public Book(BookId id, String title, String isbn, String author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public static Book create(String title, String isbn, String author) {
        return new Book(BookId.create(), title, isbn, author);
    }

    public static Book fromSnapshot(BookSnapshot snapshot) {
        return new Book(snapshot.id(), snapshot.title(), snapshot.isbn(), snapshot.author());
    }

    public BookSnapshot takeSnapshot() {
        return new BookSnapshot(id, title, isbn, author);
    }
}