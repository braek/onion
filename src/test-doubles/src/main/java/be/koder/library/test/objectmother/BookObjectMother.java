package be.koder.library.test.objectmother;

import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.ISBN;

public enum BookObjectMother {

    INSTANCE;

    public final BookSnapshot harryPotterAndTheChamberOfSecrets = new BookSnapshot(
            BookId.generate(),
            "Harry Potter and the Chamber of Secrets",
            new ISBN("9781408855669"),
            "J. K. Rowling"
    );

    public final BookSnapshot harryPotterPrisonerOfAzkaban = new BookSnapshot(
            BookId.generate(),
            "Prisoner of Azkaban",
            new ISBN("9780747542155"),
            "J. K. Rowling"
    );
}