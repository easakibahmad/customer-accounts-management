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
    }

    // Dummy implementation of getAccountDetails() method
    @Override
    public String getAccountDetails() {
        return "Method Name Under Development";
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
