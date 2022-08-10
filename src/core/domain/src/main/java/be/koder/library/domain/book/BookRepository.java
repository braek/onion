package be.koder.library.domain.book;

import be.koder.library.domain.Repository;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public interface BookRepository extends Repository<BookId, Book> {
    boolean isExistingIsbn(Isbn isbn);
}