package be.koder.library.api.book;

public interface AddBook {
    void addBook(String title, String isbn, String author, AddBookPresenter presenter);
}