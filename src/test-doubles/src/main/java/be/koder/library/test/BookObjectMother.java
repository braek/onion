package be.koder.library.test;

import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public enum BookObjectMother {

    INSTANCE;

    public final BookSnapshot harryPotterAndTheChamberofSecrets = new BookSnapshot(
            BookId.createNew(),
            "Harry Potter and the Chamber of Secrets",
            Isbn.fromString("9781408855669"),
            "J. K. Rowling"
    );
}