package com.mycompany.customeraccountsmanagement;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The CustomerList class manages customers and their accounts, providing functionality 
 * to load data, find customers and accounts by ID, and generate reports for the stored data.
 */
public class CustomerList {
    // HashMaps to store customers and account objects
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Account> accounts = new HashMap<>();

    /**
     * Loads sample customer and account data.
     * This method simulates loading data, where customers and accounts are created and added
     * to respective HashMaps.
     */
    public void loadCustomerData() {
        // Create customers
        Customer cust1 = new Customer("C0001", "John Smith", "0412345678", "j.smith@cqumail.com");
        Customer cust2 = new Customer("C0002", "Mary Brown", "0412456789", "m.brown@cqumail.com");
        Customer cust3 = new Customer("C0003", "Peter Green", "0412567890", "p.green@cqumail.com");
        Customer cust4 = new Customer("C0004", "John Terry", "0412534567", "j.terry@cqumail.com");

        // Add them to the HashMap of customers
        customers.put("C0001", cust1);
        customers.put("C0002", cust2);
        customers.put("C0003", cust3);
        customers.put("C0004", cust4);

        // Create accounts
        Account acct1 = new HomeLoanAccount("HL0001", "C0001", 0.0005, 800000, 30, "01/01/2024");
        accounts.put("HL0001", acct1);
        Account acct2 = new HomeLoanAccount("HL0002", "C0003", 0.0005, 600000, 20, "01/03/2024");
        accounts.put("HL0002", acct2);

        Account acct3 = new GoalSaverAccount("GS0001", "C0002", 0.0075, 2000);
        accounts.put("GS0001", acct3);
        Account acct4 = new GoalSaverAccount("GS0002", "C0003", 0.0075, 10000);
        accounts.put("GS0002", acct4);

        Account acct5 = new DailyAccessAccount("DA0001", "C0003", 0.0025, 400);
        accounts.put("DA0001", acct5);
        Account acct6 = new DailyAccessAccount("DA0002", "C0002", 0.0025, 1000);
        accounts.put("DA0002", acct6);
        Account acct7 = new DailyAccessAccount("DA0003", "C0001", 0.0025, 3000);
        accounts.put("DA0003", acct7);
        Account acct8 = new DailyAccessAccount("DA0004", "C0004", 0.0025, 3000);
        accounts.put("DA0004", acct8);

        // Add accounts to customers
        cust1.addAccount(acct1);
        cust1.addAccount(acct7);
        cust2.addAccount(acct3);
        cust2.addAccount(acct6);
        cust3.addAccount(acct2);
        cust3.addAccount(acct4);
        cust3.addAccount(acct5);
        cust4.addAccount(acct8);
    }

    /**
     * Finds a customer by ID.
     *
     * @param id The unique ID of the customer.
     * @return The Customer object corresponding to the given ID, or null if not found.
     */
    public Customer findCustomer(String id) {
        return customers.get(id);  // Returns the customer object or null if not found
    }

    /**
     * Finds an account by ID.
     *
     * @param id The unique ID of the account.
     * @return The Account object corresponding to the given ID, or null if not found.
     */
    public Account findAccount(String id) {
        return accounts.get(id);  // Returns the account object or null if not found
    }

    /**
     * Placeholder for applying monthly interest to all accounts.
     * This method is to be implemented in later phases.
     */
    public void applyMonthlyInterest() {
        System.out.println("applyMonthlyInterest() - Method Under Development");
    }

    /**
     * Generates a report of all customers and their accounts, sorted by customer ID.
     * The report is written to a text file named "ReportForDate_dd_MM_yyyy.txt".
     */
    public void generateReports() {
        try {
            // Get the current date and format it for the report name
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
            String reportName = "ReportForDate_" + dateFormat.format(date) + ".txt";

            // Create a file writer to write the report
            FileWriter writer = new FileWriter(reportName);

            // Get the date string for the report heading
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);

            // Use a TreeMap to order the customers by customer ID
            TreeMap<String, Customer> sortedCustomers = new TreeMap<>(customers);

            // Loop through all customers, sorted by customer ID
            for (Map.Entry<String, Customer> entry : sortedCustomers.entrySet()) {
                Customer customer = entry.getValue();

                // Write customer details
                writer.write(String.format("Date: %s\tCustomer ID: %s\tName: %s\n", dateString, customer.getCustomerID(), customer.getName()));

                // Write details for each account of the customer
                for (Account account : customer.getAccounts()) {
                    writer.write(String.format("\nAccount ID: %s\tType: %s\n", account.getAccountID(), account.getType()));
                    writer.write(account.getAccountDetailsForReport());  // Get detailed account information
                    writer.write("\n");
                }

                // Write a separator between customers
                writer.write("\n====================================================================\n");
            }

            // Close the writer after finishing
            writer.close();

            // Display a success message in the messagesArea (assuming it's a TextArea in your GUI)
            System.out.println("Report generated: " + reportName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
