package com.mycompany.customeraccountsmanagement;

import java.util.ArrayList;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private int currentAccount;  // Index of the current account
    private ArrayList<Account> accounts;

    public Customer(String customerID, String name, String phone, String email, int currentAccount, ArrayList<Account> accounts) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.currentAccount = currentAccount;
        this.accounts = accounts;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getCurrentAccount() {
        return currentAccount;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", name=" + name + ", phone=" + phone + ", email=" + email + ", currentAccount=" + currentAccount + ", accounts=" + accounts + '}';
    }
    
    // Get number of accounts
    public int getNumberOfAccounts() {
        return accounts.size();
    }
    
    // Set the current account by ID
    public void setCurrentAccount(String accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountID().equals(accountID)) {
                currentAccount = i;
                break;
            }
        }
    }
    
    // Get the first account in the list
    public Account getFirstAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = 0;
            return accounts.get(currentAccount);
        }
        return null;
    }
    
    // Get next account (with cycling behavior)
    public Account getNextAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = (currentAccount + 1) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null;
    }
    
    // Get previous account (with cycling behavior)
    public Account getPreviousAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = (currentAccount - 1 + accounts.size()) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null;
    }
    
    // Adding an account to the customer
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    
    // Declaration for applyMonthlyInterest (implementation comes in a later phase)
    public void applyMonthlyInterest(){
    }

    // Declaration for generateReport (implementation comes in a later phase)
    public void generateReport(){
    }
}
