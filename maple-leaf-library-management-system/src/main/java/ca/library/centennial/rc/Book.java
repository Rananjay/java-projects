package ca.library.centennial.rc;

/**
 * Represents a library book.
 * Optional extension: supports availability tracking and reservations.
 */
public class Book {
    public final String isbn;
    public final String title;
    public final String author;
    private boolean available;

    public Book(String isbn, String title, String author) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }

        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "', author='" + author
                + "', available=" + available + "}";
    }
}
