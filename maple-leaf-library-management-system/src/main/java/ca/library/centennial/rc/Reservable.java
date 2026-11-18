package ca.library.centennial.rc;


/**
 * Interface for objects that can reserve a book.
 * Demonstrates interface-based behavior as an alternative to multiple class inheritance.
 */
public interface Reservable {
    void reserveBook(String bookId);
}