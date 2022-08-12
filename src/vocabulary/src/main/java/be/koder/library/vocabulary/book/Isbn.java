package be.koder.library.vocabulary.book;

import java.util.Optional;
import java.util.regex.Pattern;

public record Isbn(String value) {

    public Isbn {
        final String sanitized = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
        final Pattern regex = Pattern.compile("^978\\d{10}$");
        if (sanitized == null || !regex.matcher(sanitized).matches()) {
            throw new InvalidIsbnException(value);
        }
        value = sanitized;
    }

    @Override
    public String toString() {
        return value;
    }
}