package be.koder.library.vocabulary.book;

public final class InvalidIsbnException extends RuntimeException {
    public InvalidIsbnException(final String str) {
        super(String.format("This string %s is not a valid ISBN.", str));
    }
}