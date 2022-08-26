package be.koder.library.vocabulary.book.exception;

public final class InvalidTitleException extends RuntimeException {
    public InvalidTitleException(final String str) {
        super(String.format("This string %s is not a valid title.", str));
    }
}