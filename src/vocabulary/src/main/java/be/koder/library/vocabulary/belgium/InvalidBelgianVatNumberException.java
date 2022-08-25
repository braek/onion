package be.koder.library.vocabulary.belgium;

public final class InvalidBelgianVatNumberException extends RuntimeException {
    public InvalidBelgianVatNumberException(String str) {
        super(String.format("This string '%s' is not a valid Belgian VAT number.", str));
    }
}