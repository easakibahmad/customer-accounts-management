package com.mycompany.customeraccountsmanagement;

public class DailyAccessAccount extends Account {
    // Instance variables
    private double balance;
    private double minimum;
    private double interestEarned;

    // Constructor
    public DailyAccessAccount(String id, String custId, double rate, double balance) {
        super(id, custId, "Daily Access", rate);
        this.balance = balance;
        this.minimum = balance;  // Set minimum to initial balance
    }

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

    @Override
    public void applyMonthlyInterest() {
        // Calculate the interest earned on the current balance
        double interest = minimum * getMonthlyInterestRate();  // Interest calculated on minimum balance
        interestEarned = interest;  // Store the interest earned for this month

        // Add the interest to the balance
        balance += interest;

        // Reset the minimum balance for the next month to the current balance
        minimum = balance;
    }

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
    
    @Override
    public String getAccountDetailsForReport() {
        StringBuilder sb = new StringBuilder();

        // Add specific daily access account details
        sb.append(String.format("Account Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Monthly Balance: $%.2f%n", minimum));
        sb.append(String.format("Last Interest Earned: $%.4f%n", interestEarned));
        sb.append(String.format("Interest Rate: %.4f%n", getMonthlyInterestRate() * 100)); // Annual interest rate

        return sb.toString();
    }

    // Getters for the balance and minimum (optional, based on future use)
    public double getBalance() {
        return balance;
    }

    public double getMinimumBalance() {
        return minimum;
    }

    public double getInterestEarned() {
        return interestEarned;
    }
}
