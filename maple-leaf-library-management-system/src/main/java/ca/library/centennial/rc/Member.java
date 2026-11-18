package ca.library.centennial.rc;
/**
 * Represents a library member.
 * Demonstrates: SINGLE inheritance (Member extends Person), final fields,
 * method overloading (registerMember), input validation, and instance fields.
 */
public class Member extends Person {
    // Final member-specific fields.
    public final String memberNumber;
    public final int age;
    public final String membershipType;

    private double outstandingFees;

    // Overloaded registration methods:
    // 1. Basic registration without a membership type (defaults to "Regular").
    public static Member registerMember(String personId, String name, String phoneNumber,
                                        String memberNumber, int age) {
        return registerMember(personId, name, phoneNumber, memberNumber, age, "Regular");
    }

    // 2. Registration with an explicit membership type.
    public static Member registerMember(String personId, String name, String phoneNumber,
                                        String memberNumber, int age, String membershipType) {
        // Validate required fields.
        if (memberNumber == null || memberNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Member number cannot be empty.");
        }
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120.");
        }
        if (membershipType == null || membershipType.trim().isEmpty()) {
            throw new IllegalArgumentException("Membership type cannot be empty.");
        }

        Member member = new Member(personId, name, phoneNumber,
                memberNumber.trim(), age, membershipType.trim());
        System.out.println("✓ Member registered successfully: " + member.memberNumber);
        return member;
    }

    private Member(String personId, String name, String phoneNumber,
                   String memberNumber, int age, String membershipType) {
        super(personId, name, phoneNumber);
        this.memberNumber = memberNumber;
        this.age = age;
        this.membershipType = membershipType;
        this.outstandingFees = 0.0;
    }

    /** Adds late fees to the member's outstanding balance. */
    public void addOutstandingFees(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Fee amount cannot be negative.");
        }
        this.outstandingFees += amount;
    }

    /** Clears the outstanding fees. */
    public void clearOutstandingFees() {
        this.outstandingFees = 0.0;
        System.out.println("✓ Outstanding fees cleared for member " + memberNumber);
    }

    public double getOutstandingFees() {
        return outstandingFees;
    }

    @Override
    public String getRoleDescription() {
        return "Library Member (" + membershipType + ")";
    }

    @Override
    public void displayBasicDetails() {
        super.displayBasicDetails();
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         MEMBER DETAILS              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Member No.  : %-25s│%n", memberNumber);
        System.out.printf("│ Age         : %-25d│%n", age);
        System.out.printf("│ Membership  : %-25s│%n", membershipType);
        System.out.printf("│ Outstanding : $%-24.2f│%n", outstandingFees);
        System.out.println("└─────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return "Member{memberNumber='" + memberNumber + "', age=" + age
                + ", membershipType='" + membershipType + "', outstandingFees=$" + outstandingFees
                + ", " + super.toString() + "}";
    }
}