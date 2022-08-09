package be.koder.library.test.objectmother;

import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public enum BookObjectMother {

    INSTANCE;

    public final BookSnapshot harryPotterAndTheChamberOfSecrets = new BookSnapshot(
            BookId.create(),
            "Harry Potter and the Chamber of Secrets",
            ISBN.create("9781408855669"),
            "J. K. Rowling"
    );
}