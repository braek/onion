package be.koder.library.test.mock;

import be.koder.library.domain.book.Book;
import be.koder.library.domain.book.BookRepository;
import be.koder.library.domain.book.BookSnapshot;
import be.koder.library.queries.book.BookArchive;
import be.koder.library.queries.book.BookArchiveListItem;
import be.koder.library.vocabulary.book.BookId;
import be.koder.library.vocabulary.book.Isbn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MockBookRepository implements BookRepository, BookArchive {

    private final Map<BookId, BookSnapshot> data = new HashMap<>();

    @Override
    public Optional<Book> getById(BookId id) {
        if (data.containsKey(id)) return Optional.of(Book.fromSnapshot(data.get(id)));
        return Optional.empty();
    }

    @Override
    public void save(Book book) {
        final BookSnapshot snapshot = book.takeSnapshot();
        data.put(snapshot.id(), snapshot);
    }

    @Override
    public List<BookArchiveListItem> listBooks() {
        return data.values().stream().map(it -> new BookArchiveListItem(
                it.id(),
                it.title(),
                it.isbn(),
                it.author()
        )).toList();
    }

    public void clear() {
        data.clear();
    }

    @Override
    public boolean isExistingIsbn(Isbn isbn) {
        return data.values().stream().anyMatch(it -> it.isbn().equals(isbn));
    }
}