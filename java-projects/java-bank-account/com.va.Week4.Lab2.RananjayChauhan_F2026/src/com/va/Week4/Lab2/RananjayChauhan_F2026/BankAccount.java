package com.va.Week4.Lab2.RananjayChauhan_F2026;


public class BankAccount {

	private long accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(
            long accountNumber,
            String accountHolderName,
            double balance) {

        // Account number must be at least 9 digits
        if (accountNumber < 100000000L) {
            throw new IllegalArgumentException(
                    "Account number must contain at least 9 digits."
            );
        }

        // Account holder name cannot be blank
        if (accountHolderName == null
                || accountHolderName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Account holder name cannot be blank."
            );
        }

        // Initial balance cannot be negative
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Opening balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName.trim();
        this.balance = roundToTwoDecimals(balance);
    }

    // Deposit money
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance = balance + amount;
        balance = roundToTwoDecimals(balance);

        System.out.printf(
                "Deposit successful! New balance: $%.2f%n",
                balance
        );
    }

    // Withdraw money
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available balance: $"
                            + String.format("%.2f", balance)
            );
        }

        balance = balance - amount;
        balance = roundToTwoDecimals(balance);

        System.out.printf(
                "Withdrawal successful! New balance: $%.2f%n",
                balance
        );
    }

    // Getter for account number
    public long getAccountNumber() {
        return accountNumber;
    }

    // Getter for account holder name
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Round money values to two decimal places
    private double roundToTwoDecimals(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

    // Display account information
    public void displayAccountInformation() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Current Balance: $%.2f%n", balance);
    }

}