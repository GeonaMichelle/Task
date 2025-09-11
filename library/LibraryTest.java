package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("201", "Data Structures", "Robert Lafore");
    }

    @Test
    public void testInitialAvailability() {
        assertTrue(book.isAvailable(), "Book should be available when first created");
    }

    @Test
    public void testIssueBook() {
        assertTrue(book.issue(), "First issue should succeed");
        assertFalse(book.issue(), "Second issue should fail since already issued");
    }

    @Test
    public void testReturnBook() {
        book.issue(); // first issue
        assertTrue(book.returnItem(), "Return should succeed after issue");
        assertFalse(book.returnItem(), "Second return should fail since already returned");
    }

    @Test
    public void testBookDetails() {
        assertEquals("201", book.getBookId());
        assertEquals("Data Structures", book.BookTitle());
        assertEquals("Robert Lafore", book.BookAuthor());
    }

    @Test
    public void testToStringFormat() {
        String output = book.toString();
        assertTrue(output.contains("201"));
        assertTrue(output.contains("Data Structures"));
        assertTrue(output.contains("Robert Lafore"));
    }
}
