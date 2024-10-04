package com.mycompany.customeraccountsmanagement;

/**
 * Abstract class representing a bank account.
 * This class provides the blueprint for various account types, such as Home Loan, Daily Access, and Goal Saver.
 * It defines common properties like account ID, customer ID, account type, and monthly interest rate.
 * Subclasses are required to implement the abstract methods for account-specific functionality.
 */
public abstract class Account {
    // Instance variables
    protected String accountID;  // Unique identifier for the account
    protected String type;  // Type of account (e.g., Home Loan, Daily Access)
    protected double monthlyInterestRate;  // Monthly interest rate applicable to the account
    protected String customerID;  // Customer who owns the account

    /**
     * Constructor to initialize the common properties of an account.
     *
     * @param acctID  Unique identifier for the account
     * @param custID  Unique identifier for the customer owning the account
     * @param type    Type of account (e.g., Home Loan, Daily Access)
     * @param rate    Monthly interest rate applicable to the account
     */
    public Account(String acctID, String custID, String type, double rate) {
        this.accountID = acctID;
        this.customerID = custID;
        this.type = type;
        this.monthlyInterestRate = rate;
    }

    /**
     * Returns the account ID.
     *
     * @return the unique account ID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Returns the type of the account.
     *
     * @return the type of account (e.g., Home Loan, Daily Access)
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the monthly interest rate of the account.
     *
     * @return the monthly interest rate
     */
    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    /**
     * Returns the customer ID who owns the account.
     *
     * @return the customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Abstract method to get account details.
     * Subclasses must implement this method to provide specific account details.
     *
     * @return a string representing account details
     */
    public abstract String getAccountDetails();

    /**
     * Abstract method to apply monthly interest to the account.
     * Subclasses must implement this method to handle interest application.
     */
    public abstract void applyMonthlyInterest();

    /**
     * Abstract method to deposit a specified amount into the account.
     * Subclasses must implement this method to handle deposit operations.
     *
     * @param amount the amount to be deposited
     */
    public abstract void deposit(double amount);

    /**
     * Abstract method to withdraw a specified amount from the account.
     * Subclasses must implement this method to handle withdrawal operations.
     *
     * @param amount the amount to be withdrawn
     */
    public abstract void withdraw(double amount);
    
    /**
     * Abstract method to get detailed account information for reports.
     * Subclasses must implement this method to format account information for reports.
     *
     * @return a string representing account details for reporting
     */
    public abstract String getAccountDetailsForReport();
}
