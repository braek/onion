package be.koder.library.vocabulary.book.exception;

public final class InvalidAuthorException extends RuntimeException {
    public InvalidAuthorException(String str) {
        super(String.format("This string %s is not a valid author.", str));
    }
}