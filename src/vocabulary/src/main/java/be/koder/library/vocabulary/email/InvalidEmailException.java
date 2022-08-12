package be.koder.library.vocabulary.email;

public final class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String str) {
        super(String.format("This string %s is not a valid ISBN.", str));
    }
}