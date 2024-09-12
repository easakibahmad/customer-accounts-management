package com.mycompany.customeraccountsmanagement;

public abstract class Account {
    protected String accountID;
    protected String type;
    protected double monthlyInterestRate;
    protected String customerID;

    public Account(String acctID, String custID, String type, double rate) {
        this.accountID = acctID;
        this.customerID = custID;
        this.type = type;
        this.monthlyInterestRate = rate;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getType() {
        return type;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public String getCustomerID() {
        return customerID;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getAccountDetails();

    public abstract void applyMonthlyInterest();

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);
}

