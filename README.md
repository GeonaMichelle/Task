I have created a simple Library Management System in Java with these parts:

Package:

All your classes are inside the package library.

This helps organize related classes together.

Core Classes & Interface:

Issueable (interface) → defines common operations for items that can be issued/returned.

Book (class) → represents a single book that implements Issueable.

Systems (class) → represents the library system that manages multiple books.

JUnit Test (Testing Setup):

LibraryTest → uses JUnit 5 to test the Book class functionality.

So overall, your project has:

Library items (Book)

System to manage them (Systems)

Tests to verify behavior (LibraryTest)

🔹 Code Explanation
1. Issueable (interface)
package library;

public interface Issueable {
    boolean issue();
    boolean returnItem();
    boolean isAvailable();
}


Defines three methods any library item must have:

issue() → try to issue (borrow) an item.

returnItem() → return an item.

isAvailable() → check if item is free for borrowing.


2. Book (class)
package library;

public class Book implements Issueable {
    private String BookId;
    private String BookTitle;
    private String BookAuthor;
    private boolean Isissued;


Represents a single book with attributes:

BookId (unique ID like ISBN)

BookTitle (name of book)

BookAuthor (author name)

Isissued (true/false → whether issued)

Constructor:

public Book(String BookId, String BookTitle, String BookAuthor) {
    this.BookId = BookId;
    this.BookTitle = BookTitle;
    this.BookAuthor = BookAuthor;
    this.Isissued = false;  // newly created book is available
}


Creates a book and marks it as not issued initially.

Getters:

public String getBookId() { return BookId; }
public String BookTitle() { return BookTitle; }
public String BookAuthor() { return BookAuthor; }


Allow retrieval of book details.

Issue/Return Logic (from Issueable):

@Override
public boolean issue() {
    if (!Isissued) {
        Isissued = true;
        return true;   // book successfully issued
    }
    return false;      // already issued
}

@Override
public boolean returnItem() {
    if (Isissued) {
        Isissued = false;
        return true;   // return successful
    }
    return false;      // book wasn’t issued
}

@Override
public boolean isAvailable() {
    return !Isissued;
}


String Representation:

@Override
public String toString() {
    return "Book{" +
            "isbn='" + BookId + '\'' +
            ", title='" + BookTitle + '\'' +
            ", author='" + BookAuthor + '\'' +
            ", issued=" + Isissued +
            '}';
}


Helps print book details in readable format.

3. Systems (class)
package library;

import java.util.ArrayList;
import java.util.List;

public class Systems {
    private List<Book> libbooks;

    public Systems() {
        libbooks = new ArrayList<>();
    }


Represents the library system itself.

Uses a list of books (libbooks) to store available books.

Key Methods:

public void addBook(Book book) {
    libbooks.add(book);
}


Adds a book to library.

public Book findBookbyId(String BookId) {
    for (Book book : libbooks) {
        if (book.getBookId().equals(BookId)) {
            return book;
        }
    }
    return null;
}


Finds book using BookId.

public boolean issueableBook(String BookId) {
    Book book = findBookbyId(BookId);
    return (book != null && book.issue());
}


Issues book if it exists and is available.

public boolean returnBook(String BookId) {
    Book book = findBookbyId(BookId);
    return (book != null && book.returnItem());
}


Returns book by ID.

public boolean checkAvailable(String BookId) {
    Book book = findBookbyId(BookId);
    return (book != null && book.isAvailable());
}


Checks if a book is available.

public void displayAllBooks() {
    for (Book book : libbooks) {
        System.out.println(book);
    }
}


Prints all books.

4. LibraryTest (JUnit Test Class)
package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


Uses JUnit 5 framework for testing.

Setup:

private Book book;

@BeforeEach
public void setUp() {
    book = new Book("201", "Data Structures", "Robert Lafore");
}


Runs before each test. Creates a fresh book.

Tests:

@Test
public void testInitialAvailability() {
    assertTrue(book.isAvailable(), "Book should be available when first created");
}


Ensures a new book is available.

@Test
public void testIssueBook() {
    assertTrue(book.issue(), "First issue should succeed");
    assertFalse(book.issue(), "Second issue should fail since already issued");
}


Tests issuing logic.

@Test
public void testReturnBook() {
    book.issue();
    assertTrue(book.returnItem(), "Return should succeed after issue");
    assertFalse(book.returnItem(), "Second return should fail since already returned");
}


Tests returning logic.

@Test
public void testBookDetails() {
    assertEquals("201", book.getBookId());
    assertEquals("Data Structures", book.BookTitle());
    assertEquals("Robert Lafore", book.BookAuthor());
}


Tests getters.

@Test
public void testToStringFormat() {
    String output = book.toString();
    assertTrue(output.contains("201"));
    assertTrue(output.contains("Data Structures"));
    assertTrue(output.contains("Robert Lafore"));
}


Tests if toString() contains correct info.

🔹 Summary

Issueable → contract for borrowable items.

Book → implements issue/return logic.

Systems → manages multiple books (add, issue, return, check availability).

LibraryTest → unit tests verifying book behavior.
