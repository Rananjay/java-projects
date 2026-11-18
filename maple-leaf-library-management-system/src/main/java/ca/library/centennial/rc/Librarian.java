package ca.library.centennial.rc;
/**
 * Represents a librarian.
 * Demonstrates: method overriding (calculateMonthlyCompensation, displayStaffDetails),
 * method overloading (calculateLateFee), and final fields.
 *
 * Compensation = monthly salary + ($2.00 per book processed).
 */
public class Librarian extends LibraryStaff implements Reportable, Reservable {
    public final int dailyIssueLimit;
    private int booksProcessed;

    public Librarian(String personId, String name, String phoneNumber,
                     String staffId, String department, double monthlySalary,
                     int dailyIssueLimit, int booksProcessed) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary);

        if (dailyIssueLimit < 0) {
            throw new IllegalArgumentException("Daily issue limit cannot be negative.");
        }
        if (booksProcessed < 0) {
            throw new IllegalArgumentException("Books processed cannot be negative.");
        }

        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;
    }

    public void recordBookProcessed() {
        this.booksProcessed++;
        System.out.println("✓ Book processed. Total books processed by " + staffId + ": " + booksProcessed);
    }

    public int getBooksProcessed() {
        return booksProcessed;
    }

    // Overloaded late-fee method #1: uses default rate of $1.00 per overdue day.
    public double calculateLateFee(int daysOverdue) {
        return calculateLateFee(daysOverdue, 1.0);
    }

    // Overloaded late-fee method #2: uses the supplied daily rate.
    public double calculateLateFee(int daysOverdue, double dailyRate) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Days overdue cannot be negative.");
        }
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("Daily rate must be greater than zero.");
        }
        return daysOverdue * dailyRate;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + (booksProcessed * 2.0);
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Daily Issue Limit: %-17d│%n", dailyIssueLimit);
        System.out.printf("│ Books Processed  : %-17d│%n", booksProcessed);
        System.out.println("└─────────────────────────────────────┘");
    }

    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    // --- Reportable interface ---
    @Override
    public void generateReport() {
        System.out.println("\n──────── LIBRARIAN REPORT ────────");
        System.out.println("Staff ID    : " + staffId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.println("Books Processed : " + booksProcessed);
        System.out.println("Monthly Compensation : $" + String.format("%.2f", calculateMonthlyCompensation()));
        System.out.println("──────────────────────────────────");
    }

    // --- Reservable interface ---
    @Override
    public void reserveBook(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            System.out.println("✗ Reservation failed: Book ID cannot be empty.");
            return;
        }
        System.out.println("✓ Book reserved successfully by Librarian " + staffId + ": " + bookId.trim());
    }

    @Override
    public String toString() {
        return "Librarian{staffId='" + staffId + "', booksProcessed=" + booksProcessed
                + ", " + super.toString() + "}";
    }
}