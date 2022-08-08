package be.koder.library.domain.book;

import be.koder.library.domain.Event;
import be.koder.library.vocabulary.book.BookId;

public record BookAdded(BookId bookId) implements Event {
}