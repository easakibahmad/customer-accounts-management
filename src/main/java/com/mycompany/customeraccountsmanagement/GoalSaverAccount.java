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

    // Dummy implementation of applyMonthlyInterest()
    @Override
    public void applyMonthlyInterest() {
        System.out.println("applyMonthlyInterest() - Method Under Development");
    }
    
    // Dummy implementation of deposit()
    @Override
    public void deposit(double amount) {
        System.out.println("deposit() - Method Under Development");
    }

    // Dummy implementation of withdraw()
    @Override
    public void withdraw(double amount) {
        System.out.println("withdraw() - Method Under Development");
    }
}
