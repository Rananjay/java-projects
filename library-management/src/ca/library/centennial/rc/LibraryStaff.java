package ca.library.centennial.rc;

/**
 * Abstract class representing library staff.
 * Demonstrates: MULTILEVEL inheritance (Person -> LibraryStaff -> Librarian),
 * HIERARCHICAL inheritance base (Librarian and LibraryAssistant extend this),
 * abstract method, static counter, and final fields.
 */
public abstract class LibraryStaff extends Person {
    // Final staff-specific fields.
    public final String staffId;
    public final String department;
    public final double monthlySalary;

    // Static counter for staff members only.
    private static int staffCount = 0;

    public LibraryStaff(String personId, String name, String phoneNumber,
                        String staffId, String department, double monthlySalary) {
        super(personId, name, phoneNumber);

        if (staffId == null || staffId.trim().isEmpty()) {
            throw new IllegalArgumentException("Staff ID cannot be empty.");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Monthly salary cannot be negative.");
        }

        this.staffId = staffId.trim();
        this.department = department.trim();
        this.monthlySalary = monthlySalary;
        staffCount++;
    }

    /** Returns the total number of LibraryStaff objects created. */
    public static int getStaffCount() {
        return staffCount;
    }

    /** Abstract compensation method — each staff type calculates differently. */
    public abstract double calculateMonthlyCompensation();

    /** Displays staff-specific details. */
    public void displayStaffDetails() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         STAFF DETAILS               │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Staff ID    : %-25s│%n", staffId);
        System.out.printf("│ Department  : %-25s│%n", department);
        System.out.printf("│ Monthly Sal.: $%-23.2f│%n", monthlySalary);
        System.out.printf("│ Compensation: $%-23.2f│%n", calculateMonthlyCompensation());
        System.out.println("└─────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return "LibraryStaff{staffId='" + staffId + "', department='" + department
                + "', monthlySalary=$" + monthlySalary + ", " + super.toString() + "}";
    }
}