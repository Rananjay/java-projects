package ca.library.centennial.rc;
/**
 * Represents a senior librarian.
 * Demonstrates: MULTILEVEL inheritance (Person -> LibraryStaff -> Librarian -> SeniorLibrarian),
 * method overriding, and final fields.
 *
 * Compensation = monthly salary + ($2.00 per book processed) + seniority allowance.
 */
public class SeniorLibrarian extends Librarian {
    public final String specialization;
    public final double seniorityAllowance;

    public SeniorLibrarian(String personId, String name, String phoneNumber,
                           String staffId, String department, double monthlySalary,
                           int dailyIssueLimit, int booksProcessed,
                           String specialization, double seniorityAllowance) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary,
               dailyIssueLimit, booksProcessed);

        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty.");
        }
        if (seniorityAllowance < 0) {
            throw new IllegalArgumentException("Seniority allowance cannot be negative.");
        }

        this.specialization = specialization.trim();
        this.seniorityAllowance = seniorityAllowance;
    }

    @Override
    public double calculateMonthlyCompensation() {
        // Base librarian compensation (salary + $2/book) plus seniority allowance.
        return super.calculateMonthlyCompensation() + seniorityAllowance;
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Specialization : %-17s│%n", specialization);
        System.out.printf("│ Seniority Allow: $%-17.2f│%n", seniorityAllowance);
        System.out.println("└─────────────────────────────────────┘");
    }

    @Override
    public String getRoleDescription() {
        return "Senior Librarian (" + specialization + ")";
    }

    @Override
    public String toString() {
        return "SeniorLibrarian{specialization='" + specialization + "', seniorityAllowance=$"
                + seniorityAllowance + ", " + super.toString() + "}";
    }
}