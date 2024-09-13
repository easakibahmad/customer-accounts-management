package com.mycompany.customeraccountsmanagement;

public class HomeLoanAccount extends Account {
    // Instance variables
    private final double originalLoan;
    private int duration;  // Loan duration in months
    private String startDate;
    private double amountOwing;
    private double interestCharged;

    // Constructor
    public HomeLoanAccount(String id, String custId, double rate, double originalLoan, int duration, String startDate) {
        super(id, custId, "HomeLoanAccount", rate);  // Call the constructor of the superclass (Account)
        this.originalLoan = originalLoan;
        this.duration = duration;
        this.startDate = startDate;
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

