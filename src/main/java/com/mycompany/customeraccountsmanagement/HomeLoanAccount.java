package com.mycompany.customeraccountsmanagement;

public class HomeLoanAccount extends Account {
    // Instance variables
    private final double originalLoan;
    private int duration;  // Loan duration in months
    private String startDate;
    private double amountOwing;
    private double interestCharged;

    // Constructor
    public HomeLoanAccount(String id, String custId, double rate, double loan, int duration, String startDate) {
        super(id, custId, "Home Loan", rate);  // Call the constructor of the superclass (Account)
        this.originalLoan = loan;
        this.amountOwing = loan;  // Initially, the amount owing is the full loan amount
        this.duration = duration;
        this.startDate = startDate;
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

    @Override
    public void applyMonthlyInterest() {
        // Calculate the interest based on the amount owing and the monthly interest rate
        double interest = amountOwing * getMonthlyInterestRate();
        interestCharged = interest;  // Store the interest charged for this month

        // Add the interest to the amount owing
        amountOwing += interest;
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            // Reduce the amount owing by the deposit amount
            amountOwing -= amount;
            
            // Ensure the amount owing doesn't go below zero
            if (amountOwing < 0) {
                amountOwing = 0;
            }
            System.out.println("Deposit successful. Amount owing updated.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        // Home loans cannot be withdrawn from, so this method does nothing
        System.out.println("Withdrawals are not allowed for Home Loan accounts.");
    }
    
    @Override
    public String getAccountDetailsForReport() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Interest Rate: %.4f%n", getMonthlyInterestRate() * 100));
        sb.append(String.format("Monthly Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Original Loan Amount: $%.2f%n", originalLoan));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        sb.append(String.format("Duration of Loan: %d years%n", duration));

        return sb.toString();
    }
}

