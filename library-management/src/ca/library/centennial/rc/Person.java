package ca.library.centennial.rc;
/**
 * Abstract base class representing any person in the library system.
 * Demonstrates: abstract class, final instance fields, static field/counter,
 * abstract method, and toString().
 */
public abstract class Person {
    // Final identity/contact fields — set once at construction, never changed.
    public final String personId;
    public final String name;
    public final String phoneNumber;

    // Static counter shared across all Person subclasses.
    private static int personCount = 0;

    /**
     * Constructs a Person and increments the static counter on success.
     */
    public Person(String personId, String name, String phoneNumber) {
        if (personId == null || personId.trim().isEmpty()) {
            throw new IllegalArgumentException("Person ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        this.personId = personId.trim();
        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
        personCount++;
    }

    /** Returns the total number of Person objects created so far. */
    public static int getPersonCount() {
        return personCount;
    }

    /** Prints basic identity details in a user-friendly format. */
    public void displayBasicDetails() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         PERSON DETAILS              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf("│ Person ID   : %-25s│%n", personId);
        System.out.printf("│ Name        : %-25s│%n", name);
        System.out.printf("│ Phone       : %-25s│%n", phoneNumber);
        System.out.println("└─────────────────────────────────────┘");
    }

    /** Abstract method — every subclass must describe its role. */
    public abstract String getRoleDescription();

    /** String representation of the person. */
    @Override
    public String toString() {
        return "Person{personId='" + personId + "', name='" + name
                + "', phoneNumber='" + phoneNumber + "', role='" + getRoleDescription() + "'}";
    }
}