package com.mycompany.customeraccountsmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerList {
    // HashMaps to store customer and account objects
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Account> accounts = new HashMap<>();

    // Method to load customer and account data (Appendix A data)
    public void loadCustomerData() {
        // Creating an empty ArrayList to store accounts for each customer

        // Customer 1 with two accounts
        ArrayList<Account> accounts1 = new ArrayList<>();
        Customer cust1 = new Customer("C001", "John Doe", "0412345678", "johndoe@example.com", 0, accounts1);
        Account acc1 = new DailyAccessAccount("A001", "C001", "Daily Access", 0.02, 1000.0);
        Account acc2 = new GoalSaverAccount("A002", "C001", "Goal Saver", 0.05, 5000.0);
        cust1.addAccount(acc1);
        cust1.addAccount(acc2);
        customers.put(cust1.getCustomerID(), cust1);
        accounts.put(acc1.getAccountID(), acc1);
        accounts.put(acc2.getAccountID(), acc2);

        // Customer 2 with two accounts
        ArrayList<Account> accounts2 = new ArrayList<>();
        Customer cust2 = new Customer("C002", "Jane Smith", "0412345679", "janesmith@example.com", 0, accounts2);
        Account acc3 = new HomeLoanAccount("A003", "C002", "Home Loan", 0.03, 200000.0, 30, "01/01/2022");
        Account acc4 = new GoalSaverAccount("A004", "C002", "Goal Saver", 0.05, 7000.0);
        cust2.addAccount(acc3);
        cust2.addAccount(acc4);
        customers.put(cust2.getCustomerID(), cust2);
        accounts.put(acc3.getAccountID(), acc3);
        accounts.put(acc4.getAccountID(), acc4);

        // Customer 3 with two accounts
        ArrayList<Account> accounts3 = new ArrayList<>();
        Customer cust3 = new Customer("C003", "Bob Johnson", "0412345680", "bobjohnson@example.com", 0, accounts3);
        Account acc5 = new DailyAccessAccount("A005", "C003", "Daily Access", 0.02, 3000.0);
        Account acc6 = new HomeLoanAccount("A006", "C003", "Home Loan", 0.03, 150000.0, 25, "01/01/2023");
        cust3.addAccount(acc5);
        cust3.addAccount(acc6);
        customers.put(cust3.getCustomerID(), cust3);
        accounts.put(acc5.getAccountID(), acc5);
        accounts.put(acc6.getAccountID(), acc6);
    }

    // Method to find a customer by ID
    public Customer findCustomer(String id) {
        return customers.get(id);  // Returns the customer object or null if not found
    }

    // Method to find an account by ID
    public Account findAccount(String id) {
        return accounts.get(id);  // Returns the account object or null if not found
    }

    // The applyInterestToAll() method will be implemented in a later phase
    public void applyInterestToAll() {
        System.out.println("applyInterestToAll() - Method Under Development");
    }

    // The generateReports() method will be implemented in a later phase
    public void generateReports() {
        System.out.println("generateReports() - Method Under Development");
    }
}
