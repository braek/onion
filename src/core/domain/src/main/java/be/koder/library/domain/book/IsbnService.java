package be.koder.library.domain.book;

import be.koder.library.vocabulary.book.Isbn;

public interface IsbnService {
    boolean exists(Isbn isbn);
}