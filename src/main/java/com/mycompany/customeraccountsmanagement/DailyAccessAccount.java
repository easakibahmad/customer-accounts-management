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

    // Dummy implementation of applyMonthlyInterest()
    @Override
    public void applyMonthlyInterest() {
        System.out.println("applyMonthlyInterest() - Method Under Development");
    }

    // Dummy implementation of withdraw()
    @Override
    public void withdraw(double amount) {
        System.out.println("withdraw() - Method Under Development");
    }

    // Dummy implementation of deposit()
    @Override
    public void deposit(double amount) {
        System.out.println("deposit() - Method Under Development");
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
