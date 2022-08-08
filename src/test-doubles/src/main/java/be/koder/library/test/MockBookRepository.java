package be.koder.library.test;

import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookRepository;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.vocabulary.book.BookId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class MockBookRepository implements BookRepository {

    private final Map<BookId, BookSnapshot> data = new HashMap<>();

    @Override
    public Optional<Book> get(BookId id) {
        if (data.containsKey(id)) {
            return Optional.of(Book.fromSnapshot(data.get(id)));
        }
        return Optional.empty();
    }

    @Override
    public void save(Book book) {
        final BookSnapshot snapshot = book.takeSnapshot();
        data.put(snapshot.id(), snapshot);
    }
}
