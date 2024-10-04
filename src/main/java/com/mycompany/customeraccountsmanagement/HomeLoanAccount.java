package com.mycompany.customeraccountsmanagement;

/**
 * The HomeLoanAccount class represents a home loan account.
 * It extends the abstract Account class and provides implementations
 * for managing the loan amount, applying monthly interest, and handling deposits.
 */
public class HomeLoanAccount extends Account {
    // Instance variables
    private final double originalLoan;  // Original loan amount
    private int duration;  // Loan duration in months
    private String startDate;  // Start date of the loan
    private double amountOwing;  // Current amount owing on the loan
    private double interestCharged;  // Last charged interest

    /**
     * Constructor to initialize a Home Loan account with loan details.
     *
     * @param id        Unique account ID
     * @param custId    Customer ID owning the account
     * @param rate      Monthly interest rate
     * @param loan      Original loan amount
     * @param duration  Loan duration in months
     * @param startDate Start date of the loan
     */
    public HomeLoanAccount(String id, String custId, double rate, double loan, int duration, String startDate) {
        super(id, custId, "Home Loan", rate);  // Call the constructor of the superclass (Account)
        this.originalLoan = loan;
        this.amountOwing = loan;  // Initially, the amount owing is the full loan amount
        this.duration = duration;
        this.startDate = startDate;
    }

    /**
     * Returns the account details as a string.
     *
     * @return a string representation of the home loan account details
     */
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate calculation
        sb.append(String.format("Monthly Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Original Loan Amount: $%.2f%n", originalLoan));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        sb.append(String.format("Duration of Loan: %d years%n", duration));

        return sb.toString();
    }

    /**
     * Applies monthly interest to the loan. The interest is calculated
     * based on the current amount owing and the monthly interest rate.
     * The calculated interest is added to the amount owing.
     */
    @Override
    public void applyMonthlyInterest() {
        // Calculate the interest based on the amount owing and the monthly interest rate
        double interest = amountOwing * getMonthlyInterestRate();
        interestCharged = interest;  // Store the interest charged for this month

        // Add the interest to the amount owing
        amountOwing += interest;
    }

    /**
     * Deposits a specified amount to reduce the loan amount owing.
     * Ensures that the amount owing does not go below zero.
     *
     * @param amount the amount to be deposited
     */
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

    /**
     * Withdrawals are not allowed for Home Loan accounts.
     * This method does nothing and outputs a message to the console.
     *
     * @param amount the amount to be withdrawn (unused in Home Loan accounts)
     */
    @Override
    public void withdraw(double amount) {
        // Home loans cannot be withdrawn from, so this method does nothing
        System.out.println("Withdrawals are not allowed for Home Loan accounts.");
    }

    /**
     * Returns account details formatted for report generation.
     *
     * @return a string representation of the account details for reports
     */
    @Override
    public String getAccountDetailsForReport() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Interest Rate: %.4f%%%n", getMonthlyInterestRate() * 100));  // Monthly rate * 100 for percentage
        sb.append(String.format("Monthly Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Original Loan Amount: $%.2f%n", originalLoan));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        sb.append(String.format("Duration of Loan: %d years%n", duration));

        return sb.toString();
    }
}
