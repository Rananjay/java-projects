package ca.library.centennial.rc;
/**
 * Represents a library assistant.
 * Demonstrates: HIERARCHICAL inheritance (LibraryAssistant extends LibraryStaff,
 * alongside Librarian), method overriding, and final fields.
 *
 * Compensation = monthly salary + shift allowance.
 */
public class LibraryAssistant extends LibraryStaff implements Reportable, Reservable {
    public final String shiftType;
    public final double shiftAllowance;

    public LibraryAssistant(String personId, String name, String phoneNumber,
                            String staffId, String department, double monthlySalary,
                            String shiftType, double shiftAllowance) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary);

        if (shiftType == null || shiftType.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift type cannot be empty.");
        }
        if (shiftAllowance < 0) {
            throw new IllegalArgumentException("Shift allowance cannot be negative.");
        }

        this.shiftType = shiftType.trim();
        this.shiftAllowance = shiftAllowance;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + shiftAllowance;
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Shift Type    : %-17s│%n", shiftType);
        System.out.printf("│ Shift Allowance: $%-15.2f│%n", shiftAllowance);
        System.out.println("└─────────────────────────────────────┘");
    }

    @Override
    public String getRoleDescription() {
        return "Library Assistant (" + shiftType + " shift)";
    }

    // --- Reportable interface ---
    @Override
    public void generateReport() {
        System.out.println("\n──────── LIBRARY ASSISTANT REPORT ────────");
        System.out.println("Staff ID        : " + staffId);
        System.out.println("Name            : " + name);
        System.out.println("Department      : " + department);
        System.out.println("Shift Type      : " + shiftType);
        System.out.println("Shift Allowance : $" + String.format("%.2f", shiftAllowance));
        System.out.println("Monthly Compensation : $" + String.format("%.2f", calculateMonthlyCompensation()));
        System.out.println("──────────────────────────────────────────");
    }

    // --- Reservable interface ---
    @Override
    public void reserveBook(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            System.out.println("✗ Reservation failed: Book ID cannot be empty.");
            return;
        }
        System.out.println("✓ Book reserved successfully by Library Assistant " + staffId + ": " + bookId.trim());
    }

    @Override
    public String toString() {
        return "LibraryAssistant{shiftType='" + shiftType + "', shiftAllowance=$"
                + shiftAllowance + ", " + super.toString() + "}";
    }
}