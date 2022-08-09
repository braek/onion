package be.koder.library.vocabulary.book;

public final class InvalidISBNException extends RuntimeException {
    public InvalidISBNException(final String isbn) {
        super(String.format("This string %s is not a valid ISBN.", isbn));
    }
}