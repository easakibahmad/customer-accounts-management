package com.mycompany.customeraccountsmanagement;

/**
 * The GoalSaverAccount class represents a goal saver bank account.
 * It extends the abstract Account class and provides implementations
 * for balance management, deposits, withdrawals, and monthly interest application.
 */
public class GoalSaverAccount extends Account {
    // Instance variables
    private double balance;  // Current balance of the account
    private double startOfMonthBalance;  // Balance at the start of the month
    private double interestEarned;  // Interest earned during the last interest application

    /**
     * Constructor to initialize a Goal Saver account with an initial balance.
     *
     * @param id      Unique account ID
     * @param custId  Customer ID owning the account
     * @param rate    Monthly interest rate
     * @param balance Initial account balance
     */
    public GoalSaverAccount(String id, String custId, double rate, double balance) {
        super(id, custId, "Goal Saver", rate);  // Call the constructor of the superclass (Account)
        this.balance = balance;
        this.startOfMonthBalance = balance;  // Set startOfMonthBalance to initial balance
        this.interestEarned = 0.0;  // Default interest earned is 0
    }

    /**
     * Returns the account details as a string.
     *
     * @return a string representation of the goal saver account details
     */
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

    /**
     * Applies monthly interest to the account balance if the current balance
     * is at least $500 more than the start of month balance.
     * Updates the interest earned and the balance.
     */
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
    
    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to be deposited
     */
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Balance updated.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws a specified amount from the account if sufficient balance exists.
     *
     * @param amount the amount to be withdrawn
     */
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
    
    /**
     * Returns account details formatted for report generation.
     *
     * @return a string representation of the account details for reports
     */
    @Override
    public String getAccountDetailsForReport() {
        StringBuilder sb = new StringBuilder();

        // Add specific goal saver account details for report
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Balance at start of month: $%.2f%n", startOfMonthBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Interest Rate: %.4f%n", getMonthlyInterestRate() * 100)); // Properly calculate annual interest rate

        return sb.toString();
    }
}
