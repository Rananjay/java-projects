package testcases;

import ca.library.centennial.rc.Librarian;
import ca.library.centennial.rc.LibraryStaff;
import ca.library.centennial.rc.Member;
import ca.library.centennial.rc.Person;

/**
 * Test cases covering registration, validation, late fees, compensation, and static counters.
 */
public class AssignmentTest {
    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        // Test 1: Successful member registration increments person count.
        total++;
        try {
            int before = Person.getPersonCount();
            Member m = Member.registerMember("T001", "Test User", "416-555-0001",
                                             "MT001", 25, "Regular");
            if (Person.getPersonCount() == before + 1 && m.memberNumber.equals("MT001")) {
                System.out.println("PASS Test 1: Member registration + person count");
                passed++;
            } else {
                System.out.println("FAIL Test 1");
            }
        } catch (Exception e) {
            System.out.println("FAIL Test 1: " + e.getMessage());
        }

        // Test 2: Invalid age is rejected.
        total++;
        try {
            Member.registerMember("T002", "Bad Age", "416-555-0002",
                                  "MT002", 150, "Regular");
            System.out.println("FAIL Test 2: Invalid age accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS Test 2: Invalid age rejected");
            passed++;
        }

        // Test 3: Late fee with default rate.
        total++;
        Librarian lib = new Librarian("T010", "Librarian", "416-555-0010",
                                      "ST010", "Circulation", 4000.0, 10, 20);
        double fee = lib.calculateLateFee(5);
        if (fee == 5.0) {
            System.out.println("PASS Test 3: Default late fee $1/day");
            passed++;
        } else {
            System.out.println("FAIL Test 3: expected 5.0, got " + fee);
        }

        // Test 4: Late fee with custom daily rate.
        total++;
        double feeCustom = lib.calculateLateFee(5, 2.0);
        if (feeCustom == 10.0) {
            System.out.println("PASS Test 4: Custom late fee $2/day");
            passed++;
        } else {
            System.out.println("FAIL Test 4: expected 10.0, got " + feeCustom);
        }

        // Test 5: Librarian compensation = salary + $2 * books processed.
        total++;
        double expected = 4000.0 + (20 * 2.0);
        if (lib.calculateMonthlyCompensation() == expected) {
            System.out.println("PASS Test 5: Librarian compensation");
            passed++;
        } else {
            System.out.println("FAIL Test 5: expected " + expected + ", got "
                               + lib.calculateMonthlyCompensation());
        }

     // Test 6: Check counters in a fresh run of AssignmentTest.
        total++;

        int persons = Person.getPersonCount();
        int staff = LibraryStaff.getStaffCount();

        if (persons == 2 && staff == 1) {
            System.out.println(
                "PASS Test 6: Static counters (persons=" + persons
                + ", staff=" + staff + ")"
            );
            passed++;
        } else {
            System.out.println(
                "FAIL Test 6: Expected persons=2 and staff=1, but got persons="
                + persons + " and staff=" + staff
            );
        }
    }
}