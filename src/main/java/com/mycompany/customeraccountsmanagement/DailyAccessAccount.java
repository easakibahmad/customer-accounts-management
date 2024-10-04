package com.mycompany.customeraccountsmanagement;

/**
 * The DailyAccessAccount class represents a daily access bank account.
 * It extends the abstract Account class and provides implementations for methods
 * related to balance, deposits, withdrawals, and monthly interest application.
 */
public class DailyAccessAccount extends Account {
    // Instance variables
    private double balance;  // Current balance in the account
    private double minimum;  // Minimum balance in the account for the current month
    private double interestEarned;  // Interest earned during the last interest application

    /**
     * Constructor to initialize a Daily Access account with an initial balance.
     *
     * @param id      Unique account ID
     * @param custId  Customer ID owning the account
     * @param rate    Monthly interest rate
     * @param balance Initial account balance
     */
    public DailyAccessAccount(String id, String custId, double rate, double balance) {
        super(id, custId, "Daily Access", rate);
        this.balance = balance;
        this.minimum = balance;  // Set minimum to initial balance
    }

    /**
     * Returns the account details as a string.
     *
     * @return a string representation of the daily access account details
     */
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();

        // Add specific daily access account details
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Monthly Balance: $%.2f%n", minimum));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual interest rate

        return sb.toString();
    }

    /**
     * Applies monthly interest to the account balance based on the minimum balance of the month.
     * Interest is calculated and added to the balance, then the minimum balance is reset.
     */
    @Override
    public void applyMonthlyInterest() {
        // Calculate the interest earned on the minimum balance
        double interest = minimum * getMonthlyInterestRate();  // Interest calculated on minimum balance
        interestEarned = interest;  // Store the interest earned for this month

        // Add the interest to the balance
        balance += interest;

        // Reset the minimum balance for the next month to the current balance
        minimum = balance;
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

            // Check if the current balance is lower than the minimum balance and update if necessary
            if (balance < minimum) {
                minimum = balance;
            }

            System.out.println("Deposit successful. Balance updated.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount to be withdrawn
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;

            // Check if the current balance is lower than the minimum balance and update if necessary
            if (balance < minimum) {
                minimum = balance;
            }

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

        // Add specific daily access account details for report
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Monthly Balance: $%.2f%n", minimum));
        sb.append(String.format("Last Interest Earned: $%.4f%n", interestEarned));
        sb.append(String.format("Interest Rate: %.4f%n", getMonthlyInterestRate() * 100)); // Annual interest rate

        return sb.toString();
    }

    // Getters for the balance and minimum (optional, based on future use)
    
    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the minimum balance recorded for the month.
     *
     * @return the minimum balance for the current month
     */
    public double getMinimumBalance() {
        return minimum;
    }

    /**
     * Returns the interest earned during the last interest application.
     *
     * @return the last interest earned
     */
    public double getInterestEarned() {
        return interestEarned;
    }
}
