package library;

public class Book implements Issueable {
    private String BookId;
    private String BookTitle;
    private String BookAuthor;
    private boolean Isissued;

    public Book(String BookId, String BookTitle, String BookAuthor) {
        this.BookId = BookId;
        this.BookTitle = BookTitle;
        this.BookAuthor = BookAuthor;
        this.Isissued = false;
    }

    public String getBookId() {
        return BookId;
    }

    public String BookTitle() {
        return BookTitle;
    }

    public String BookAuthor() {
        return BookAuthor;
    }

    @Override
    public boolean issue() {
        if (!Isissued) {
            Isissued = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnItem() {
        if (Isissued) {
            Isissued = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isAvailable() {
        return !Isissued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + BookId + '\'' +
                ", title='" + BookTitle + '\'' +
                ", author='" + BookAuthor + '\'' +
                ", issued=" + Isissued +
                '}';
    }
}