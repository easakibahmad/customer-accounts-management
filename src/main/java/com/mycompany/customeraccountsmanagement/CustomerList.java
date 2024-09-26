package com.mycompany.customeraccountsmanagement;

import java.util.HashMap;

public class CustomerList {
    // HashMaps to store customers and account objects
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Account> accounts = new HashMap<>();

    // Method to load customer and account data (based on Appendix A)
    public void loadCustomerData() {
        // Create three customers
        Customer cust1 = new Customer("C0001", "John Smith", "0412345678", "j.smith@cqumail.com");
        Customer cust2 = new Customer("C0002", "Mary Brown", "0412456789", "m.brown@cqumail.com");
        Customer cust3 = new Customer("C0003", "Peter Green", "0412567890", "p.green@cqumail.com");

        // Add them to the HashMap of customers
        customers.put("C0001", cust1);
        customers.put("C0002", cust2);
        customers.put("C0003", cust3);

        // create 7 accounts and add them to the HashMap of accounts
        Account acct1 = new HomeLoanAccount("HL0001", "C0001",  0.005, 800000, 30, "01/01/2024" );
        accounts.put("HL0001", acct1);
        Account acct2 = new HomeLoanAccount("HL0002", "C0003", 0.005, 600000, 20, "01/03/2024" );
        accounts.put("HL0002", acct2);
        
        Account acct3 = new GoalSaverAccount("GS0001", "C0002", 0.0050, 2000 );
        accounts.put("GS0001", acct3);
        Account acct4 = new GoalSaverAccount("GS0002", "C0003", 0.0050, 10000 );
        accounts.put("GS0002", acct4);
        
        Account acct5 = new DailyAccessAccount("DA0001", "C0003", 0.0025, 400 );
        accounts.put("DA0001", acct5);
        Account acct6 = new DailyAccessAccount("DA0002", "C0002", 0.0025, 1000 );
        accounts.put("DA0002", acct6);
        Account acct7 = new DailyAccessAccount("DA0003", "C0001", 0.0025, 3000 );
        accounts.put("DA0003", acct7);


        // Add accounts to the respective customers
        cust1.addAccount(acct1);
        cust1.addAccount(acct7);

        cust2.addAccount(acct3);
        cust2.addAccount(acct6);

        cust3.addAccount(acct2);
        cust3.addAccount(acct4);
        cust3.addAccount(acct5);
    }

    // Method to find a customer by ID
    public Customer findCustomer(String id) {
        return customers.get(id);  // Returns the customer object or null if not found
    }

    // Method to find an account by ID
    public Account findAccount(String id) {
        return accounts.get(id);  // Returns the account object or null if not found
    }

    // Placeholder for the applyInterestToAll method (to be implemented in later phases)
    public void applyMonthlyInterest() {
        System.out.println("applyMonthlyInterest() - Method Under Development");
    }

    // Placeholder for the generateReports method (to be implemented in later phases)
    public void generateReports() {
        System.out.println("generateReports() - Method Under Development");
    }
}
