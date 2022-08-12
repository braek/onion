package io.koder.library.email;

public final class InvalidEmailAddressException extends RuntimeException {
    public InvalidEmailAddressException(String str) {
        super(String.format("This string %s is not a valid ISBN.", str));
    }
}