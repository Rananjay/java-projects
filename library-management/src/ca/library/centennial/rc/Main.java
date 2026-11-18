package ca.library.centennial.rc;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application — demo entry point for the Library Management System.
 * Demonstrates: object creation, validation, overloaded methods,
 * static counters, runtime polymorphism, and both interfaces.
 */
public class Main {

    public static void main(String[] args) {
        printHeader("MAPLE LEAF PUBLIC LIBRARY — MANAGEMENT SYSTEM DEMO");

        // 1. Register at least two members (overloaded registerMember methods).
        System.out.println("\n>>> REGISTERING MEMBERS");
        Member member1 = Member.registerMember("P001", "Alice Chen", "416-555-0101",
                                               "M1001", 28, "Regular");
        Member member2 = Member.registerMember("P002", "Ben Patel", "416-555-0102",
                                               "M1002", 35, "Premium");
        // Demonstrate the overloaded basic registration (defaults to "Regular").
        Member member3 = Member.registerMember("P003", "Cara Lewis", "416-555-0103",
                                               "M1003", 19);

        // Demonstrate an unsuccessful registration (validation).
        System.out.println("\n>>> UNSUCCESSFUL REGISTRATION ATTEMPT");
        try {
            Member.registerMember("P004", "Invalid", "416-555-0104",
                                  "M1004", -5, "Regular");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Registration failed: " + e.getMessage());
        }

        // 2. Create one librarian, one senior librarian, one library assistant.
        System.out.println("\n>>> CREATING STAFF");
        Librarian librarian = new Librarian("P010", "Diana Reyes", "416-555-0110",
                                            "S2001", "Circulation", 4500.00, 10, 25);
        SeniorLibrarian seniorLibrarian = new SeniorLibrarian("P011", "Ethan Wong", "416-555-0111",
                                                              "S2002", "Reference", 6000.00,
                                                              15, 40, "Digital Archives", 500.00);
        LibraryAssistant assistant = new LibraryAssistant("P012", "Fiona Gomez", "416-555-0112",
                                                          "S2003", "Shelving", 3200.00,
                                                          "Evening", 300.00);

        // 3. Display member and staff details.
        System.out.println("\n>>> MEMBER DETAILS");
        member1.displayBasicDetails();
        member2.displayBasicDetails();
        member3.displayBasicDetails();

        System.out.println("\n>>> STAFF DETAILS");
        librarian.displayStaffDetails();
        seniorLibrarian.displayStaffDetails();
        assistant.displayStaffDetails();

        // 4. Calculate late fees using overloaded methods.
        System.out.println("\n>>> LATE FEE CALCULATIONS");
        System.out.println("Librarian " + librarian.staffId + " late fee calculations:");
        System.out.printf("   7 days overdue @ default $1.00/day = $%.2f%n",
                          librarian.calculateLateFee(7));
        System.out.printf("   7 days overdue @ $1.50/day       = $%.2f%n",
                          librarian.calculateLateFee(7, 1.50));

        // Demonstrate validation on late fee.
        System.out.println("\n>>> UNSUCCESSFUL LATE FEE ATTEMPT");
        try {
            librarian.calculateLateFee(-3, 1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Late fee calculation failed: " + e.getMessage());
        }

        // 5. Calculate monthly compensation for each staff category.
        System.out.println("\n>>> MONTHLY COMPENSATION");
        System.out.printf("   Librarian       : $%.2f%n", librarian.calculateMonthlyCompensation());
        System.out.printf("   Senior Librarian: $%.2f%n", seniorLibrarian.calculateMonthlyCompensation());
        System.out.printf("   Library Assistant: $%.2f%n", assistant.calculateMonthlyCompensation());

        // 6. Display static counters.
        System.out.println("\n>>> STATIC COUNTERS");
        System.out.println("   Total Person objects created : " + Person.getPersonCount());
        System.out.println("   Total LibraryStaff created   : " + LibraryStaff.getStaffCount());

        // 7. Runtime polymorphism using LibraryStaff references.
        System.out.println("\n>>> RUNTIME POLYMORPHISM (LibraryStaff references)");
        List<LibraryStaff> staffList = new ArrayList<>();
        staffList.add(librarian);
        staffList.add(seniorLibrarian);
        staffList.add(assistant);

        for (LibraryStaff staff : staffList) {
            System.out.println("   " + staff.getRoleDescription()
                               + " -> Compensation: $"
                               + String.format("%.2f", staff.calculateMonthlyCompensation()));
        }

        // 8. Demonstrate both interfaces (Reportable and Reservable).
        System.out.println("\n>>> INTERFACE DEMONSTRATION");
        System.out.println("--- Reportable (generateReport) ---");
        librarian.generateReport();
        assistant.generateReport();

        System.out.println("\n--- Reservable (reserveBook) ---");
        librarian.reserveBook("BK-101");
        assistant.reserveBook(""); // invalid reservation to show validation

        // 9. Outstanding fees example.
        System.out.println("\n>>> OUTSTANDING FEES");
        member1.addOutstandingFees(librarian.calculateLateFee(10));
        System.out.printf("   Member %s outstanding fees: $%.2f%n",
                          member1.memberNumber, member1.getOutstandingFees());
        member1.clearOutstandingFees();

        printFooter();
    }

    private static void printHeader(String title) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         " + padRight(title, 38) + "║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void printFooter() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║         END OF LIBRARY MANAGEMENT DEMO          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}