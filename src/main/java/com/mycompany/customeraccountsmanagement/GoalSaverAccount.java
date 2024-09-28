package com.mycompany.customeraccountsmanagement;

public class GoalSaverAccount extends Account {
    // Instance variables
    private double balance;
    private double startOfMonthBalance;
    private double interestEarned;

    // Constructor
    public GoalSaverAccount(String id, String custId, double rate, double balance) {
        super(id, custId, "Goal Saver", rate);  // Call the constructor of the superclass (Account)
        this.balance = balance;
        this.startOfMonthBalance = balance;  // Set startOfMonthBalance to initial balance
        this.interestEarned = 0.0;  // Default interest earned is 0
    }

    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();

        // Add specific goal saver account details
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Start of Month Balance: $%.2f%n", startOfMonthBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Properly calculate annual interest rate

        return sb.toString();
    }

    // Implement applyMonthlyInterest() method
    @Override
    public void applyMonthlyInterest() {
        // Check if balance is greater than or equal to startOfMonthBalance + 500
        if (balance >= startOfMonthBalance + 500) {
            // Calculate interest
            interestEarned = balance * getMonthlyInterestRate();
            balance += interestEarned;  // Add the interest to the balance
        } else {
            interestEarned = 0;  // No interest earned if condition is not met
        }

        // Reset startOfMonthBalance to the new balance after applying interest
        startOfMonthBalance = balance;
    }
    
    // Deposit money into the account
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Balance updated.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraw money from the account (ensure no overdrawing)
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdraw successful. Balance updated.");
        } else if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
    
    @Override
    public String getAccountDetailsForReport() {
        StringBuilder sb = new StringBuilder();

        // Add specific goal saver account details
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Balance at start of month: $%.2f%n", startOfMonthBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Interest Rate: %.4f%n", getMonthlyInterestRate() * 100)); // Properly calculate annual interest rate

        return sb.toString();
    }
}
