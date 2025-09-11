package library;

import java.util.ArrayList;
import java.util.List;

public class Systems {
    private List<Book> libbooks;

    public Systems() {
        libbooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        libbooks.add(book);
    }

    public Book findBookbyId(String BookId) {
        for (Book book : libbooks) {
            if (book.getBookId().equals(BookId)) {
                return book;
            }
        }
        return null;
    }

    public boolean issueableBook(String BookId) {
        Book book = findBookbyId(BookId);
        return (book != null && book.issue());
    }

    public boolean returnBook(String BookId) {
        Book book = findBookbyId(BookId);
        return (book != null && book.returnItem());
    }

    public boolean checkAvailable(String BookId) {
        Book book = findBookbyId(BookId);
        return (book != null && book.isAvailable());
    }

    public void displayAllBooks() {
        for (Book book : libbooks) {
            System.out.println(book);
        }
    }
}