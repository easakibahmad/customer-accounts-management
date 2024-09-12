package com.mycompany.customeraccountsmanagement;

import java.util.HashMap;

public class CustomerList {
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Account> accounts = new HashMap<>();

    public CustomerList() {
        loadCustomerData();  // Load initial data (see Appendix A of your assignment)
    }

    // Load hardcoded customers and accounts data
    public void loadCustomerData() {
        // Add sample customers and accounts, see Appendix A for detailed data
        Customer cust1 = new Customer("C0001", "John Smith", "0412345678", "j.smith@example.com");
        Account acc1 = new HomeLoanAccount("HL0001", "C0001", 0.0005, 800000, 30, "01/01/2024");
        cust1.addAccount(acc1);
        customers.put("C0001", cust1);
        accounts.put("HL0001", acc1);

        // Add more customers and accounts similarly
    }

    // Find customer by ID
    public Customer findCustomer(String customerID) {
        return customers.get(customerID);
    }

    // Find account by ID
    public Account findAccount(String accountID) {
        return accounts.get(accountID);
    }
}
