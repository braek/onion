package be.koder.library.mutators.book;

import be.koder.library.mutators.Command;

public record AddBookCommand(
        String title,
        String isbn,
        String author
) implements Command {
}
