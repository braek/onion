package be.koder.library.test.data;

import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

public enum BookObjectMother {

    INSTANCE;

    public final BookSnapshot harryPotterAndTheChamberOfSecrets = new BookSnapshot(
            BookId.create(),
            "Harry Potter and the Chamber of Secrets",
            Isbn.create("9781408855669"),
            "J. K. Rowling"
    );
}