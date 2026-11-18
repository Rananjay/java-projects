package com.va.Week4.Lab2.RananjayChauhan_F2026;

import java.util.Scanner;

public class MainDriver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Array for three BankAccount objects
        BankAccount[] accounts = new BankAccount[3];

        System.out.println("======================================");
        System.out.println("       WELCOME TO SIMPLE RC BANK");
        System.out.println("======================================");

        // Create three bank account objects
        for (int i = 0; i < accounts.length; i++) {

            System.out.println("\nCreate Account " + (i + 1));

            boolean accountCreated = false;

            while (!accountCreated) {

                try {
                    System.out.print(
                            "Enter account number (at least 9 digits): "
                    );
                    long accountNumber = Long.parseLong(
                            scanner.nextLine()
                    );

                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.nextLine();

                    System.out.print("Enter opening balance: $");
                    double openingBalance = Double.parseDouble(
                            scanner.nextLine()
                    );

                    accounts[i] = new BankAccount(
                            accountNumber,
                            accountHolderName,
                            openingBalance
                    );

                    accountCreated = true;

                    System.out.println(
                            "Account created successfully!"
                    );
                    accounts[i].displayAccountInformation();

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Error: Account number and balance "
                                    + "must be valid numeric values."
                    );

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );

                } finally {

                    System.out.println(
                            "Final Account creation attempt completed."
                    );
                }
            }
        }

        // Deposit and withdraw once for every account
        for (int i = 0; i < accounts.length; i++) {

            System.out.println("\n======================================");
            System.out.println(
                    "Transactions for "
                            + accounts[i].getAccountHolderName()
            );
            System.out.println(
                    "Account Number: "
                            + accounts[i].getAccountNumber()
            );
            System.out.println("======================================");

            processDeposit(scanner, accounts[i]);
            processWithdrawal(scanner, accounts[i]);

            System.out.println("\nFinal Account Information:");
            accounts[i].displayAccountInformation();
        }

        System.out.println("\n======================================");
        System.out.println("All transactions have been successfully completed.");
        System.out.println("Thank you for using Simple RC Bank!");
        System.out.println("======================================");

        scanner.close();
    }

    // Deposit transaction method
    public static void processDeposit(
            Scanner scanner,
            BankAccount account) {

        boolean depositCompleted = false;

        while (!depositCompleted) {

            try {
                System.out.print("\nEnter deposit amount: $");

                double depositAmount = Double.parseDouble(
                        scanner.nextLine()
                );

                account.deposit(depositAmount);
                depositCompleted = true;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: Please enter a valid numeric amount."
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Deposit failed: " + e.getMessage()
                );

            } finally {

                System.out.println(
                        "Deposit transaction processed."
                );
            }
        }
    }

    // Withdrawal transaction method
    public static void processWithdrawal(
            Scanner scanner,
            BankAccount account) {

        boolean withdrawalCompleted = false;

        while (!withdrawalCompleted) {

            try {
                System.out.print("\nEnter withdrawal amount: $");

                double withdrawalAmount = Double.parseDouble(
                        scanner.nextLine()
                );

                account.withdraw(withdrawalAmount);
                withdrawalCompleted = true;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: Please enter a valid numeric amount."
                );

            } catch (InsufficientFundsException e) {

                System.out.println(
                        "Withdrawal failed: " + e.getMessage()
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Withdrawal failed: " + e.getMessage()
                );

            } finally {

                System.out.println(
                        "Withdrawal transaction processed."
                );
            }
        }
    }
}