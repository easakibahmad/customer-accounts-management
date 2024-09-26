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
        super(id, custId, "Home Loan", rate);  // Call the constructor of the superclass (Account)
        this.originalLoan = originalLoan;
        this.amountOwing = originalLoan;  // Initially, the amount owing is the full loan amount
        this.duration = duration;
        this.startDate = startDate;
        this.amountOwing = originalLoan;  // Initially, the amount owing is the original loan
    }
    
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100));
        sb.append(String.format("Monthly Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Original Loan Amount: $%.2f%n", originalLoan));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        sb.append(String.format("Duration of Loan: %d years%n", duration));

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

