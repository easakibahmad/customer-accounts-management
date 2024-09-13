package com.mycompany.customeraccountsmanagement;

public class DailyAccessAccount extends Account {
    // Instance variables
    private double balance;
    private double minimum;
    private double interestEarned;

    // Constructor

    public DailyAccessAccount(String id, String custId, double rate, double balance) {
        super(id, custId, "DailyAccessAccount", rate);
        this.balance = balance;
    }

    // Dummy implementation of getAccountDetails() method
    @Override
    public String getAccountDetails() {
        return "Method Name Under Development";  // For now, return this message
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
