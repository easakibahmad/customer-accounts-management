package com.mycompany.customeraccountsmanagement;

import java.util.ArrayList;

/**
 * The Customer class represents a customer with multiple accounts.
 * It provides methods to manage and retrieve account information, such as 
 * navigating between accounts, adding accounts, and handling the current account index.
 */
public class Customer {
    private String customerID;  // Unique customer identifier
    private String name;  // Customer's name
    private String phone;  // Customer's phone number
    private String email;  // Customer's email address
    private int currentAccount;  // Index of the currently selected account
    private ArrayList<Account> accounts;  // List of accounts for this customer

    /**
     * Constructor to initialize a customer with basic details.
     *
     * @param id    Unique customer ID
     * @param name  Customer's name
     * @param phone Customer's phone number
     * @param email Customer's email address
     */
    public Customer(String id, String name, String phone, String email) {
        this.customerID = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();  // Initialize the accounts list
        this.currentAccount = 0;  // Start with the first account as the default
    }

    /**
     * Gets the customer ID.
     *
     * @return the unique customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Gets the customer's name.
     *
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the customer's email address.
     *
     * @return the customer's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the current account based on the current account index.
     *
     * @return the current Account object, or null if no accounts exist
     */
    public Account getCurrentAccount() {
        if (!accounts.isEmpty() && currentAccount >= 0 && currentAccount < accounts.size()) {
            return accounts.get(currentAccount);  // Return the account at the current index
        }
        return null;  // Return null if no account exists
    }

    /**
     * Gets the list of all accounts for this customer.
     *
     * @return an ArrayList of accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Converts the customer's details to a string format.
     *
     * @return a string representation of the customer
     */
    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", name=" + name + ", phone=" + phone + ", email=" + email + ", currentAccount=" + currentAccount + ", accounts=" + accounts + '}';
    }

    /**
     * Gets the number of accounts the customer has.
     *
     * @return the number of accounts
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Sets the current account based on the account ID.
     * This allows navigating to a specific account by its ID.
     *
     * @param accountID the ID of the account to set as the current account
     */
    public void setCurrentAccount(String accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountID().equals(accountID)) {
                currentAccount = i;
                break;
            }
        }
    }

    /**
     * Gets the first account in the list of accounts.
     * This also sets the current account to the first account.
     *
     * @return the first account in the list, or null if no accounts exist
     */
    public Account getFirstAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = 0;
            return accounts.get(currentAccount);
        }
        return null;
    }

    /**
     * Gets the next account in the list, cycling back to the first account if at the end.
     *
     * @return the next account in the list, or null if no accounts exist
     */
    public Account getNextAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = (currentAccount + 1) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null;
    }

    /**
     * Gets the previous account in the list, cycling to the last account if at the beginning.
     *
     * @return the previous account in the list, or null if no accounts exist
     */
    public Account getPreviousAccount() {
        if (!accounts.isEmpty()) {
            currentAccount = (currentAccount - 1 + accounts.size()) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null;
    }

    /**
     * Adds a new account to the customer's account list.
     *
     * @param account the new account to be added
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Placeholder method to apply monthly interest to the customer's accounts.
     * The actual implementation comes in a later phase.
     */
    public void applyMonthlyInterest() {
        // Implementation will be provided in a later phase
    }

    /**
     * Placeholder method to generate a report for the customer.
     * The actual implementation comes in a later phase.
     */
    public void generateReport() {
        // Implementation will be provided in a later phase
    }
}
