package be.koder.library.vocabulary.belgium;

import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

public final class BelgianVatNumber {

    private final String value;

    public BelgianVatNumber(final String str) {
        final String sanitized = ofNullable(str)
                .map(String::trim)
                .map(String::toUpperCase)
                .map(it -> it.replaceAll("[^BE\\d]", ""))
                .orElse(null);
        final Pattern regex = Pattern.compile("^BE(0|1)\\d{9}$");
        if (sanitized == null || !regex.matcher(sanitized).matches()) {
            throw new InvalidBelgianVatNumberException(str);
        }
        this.value = String.format("%s %s.%s.%s",
                sanitized.substring(0, 2),
                sanitized.substring(2, 6),
                sanitized.substring(6, 9),
                sanitized.substring(9, 12)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelgianVatNumber belgianVatNumber = (BelgianVatNumber) o;
        return Objects.equals(value, belgianVatNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}