import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Project: Bank Account Management System (Task 01)
 * Author: Akram
 * Email: Akram@gmail.com
 * Description: A professional banking application using HashMap for O(1) efficiency,
 * Regex for strict validation, and String formatting for clean currency output.
 * Updated for universal Java compatibility.
 */

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Rs. " + String.format("%,.2f", amount) + " deposited successfully.");
            System.out.println("New Balance: Rs. " + String.format("%,.2f", balance));
        } else {
            System.out.println(" Invalid amount! Deposit must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Rs. " + String.format("%,.2f", amount) + " withdrawn successfully.");
            System.out.println("New Balance: Rs. " + String.format("%,.2f", balance));
        } else if (amount > balance) {
            System.out.println(" Insufficient Balance!");
        } else {
            System.out.println(" Invalid withdrawal amount!");
        }
    }

    public void displayAccountInfo() {
        System.out.println("-------------------------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Current Balance: Rs. " + String.format("%,.2f", balance));
        System.out.println("-------------------------------------------------");
    }
}

public class BankManagementSystem {
    private static Map<String, Account> accountsDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        System.out.println("=========================================");
        System.out.println("  Welcome to the Bank Management System  ");
        System.out.println("=========================================");

        while (isRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Account Balance");
            System.out.println("5. View All Accounts (Admin)");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid choice! Please enter a number between 1-6.");
                continue;
            }

            // Updated switch statement for universal Java compatibility
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewAllAccounts();
                    break;
                case 6:
                    System.out.println("Thank you for using our system. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println(" Invalid option. Try again.");
            }
        }
    }

    private static double getValidDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid amount (numbers only).");
            }
        }
    }

    private static void createAccount() {
        String accNo;
        while (true) {
            System.out.print("Enter Account Number (Digits only): ");
            accNo = scanner.nextLine().trim();
            if (accNo.matches("\\d+")) {
                if (accountsDatabase.containsKey(accNo)) {
                    System.out.println(" Account Number already exists!");
                } else {
                    break;
                }
            } else {
                System.out.println(" Invalid! Use digits only.");
            }
        }

        String name;
        while (true) {
            System.out.print("Enter Account Holder Name (Alphabets only): ");
            name = scanner.nextLine().trim();
            if (name.matches("^[a-zA-Z\\s]+$")) {
                break;
            } else {
                System.out.println(" Invalid! Use alphabets only.");
            }
        }

        double initialDeposit = getValidDoubleInput("Enter Initial Deposit Amount: ");
        accountsDatabase.put(accNo, new Account(accNo, name, initialDeposit));
        System.out.println(" Account created successfully!");
    }

    private static void performDeposit() {
        System.out.print("Enter Account Number: ");
        String inputAccNo = scanner.nextLine().trim();
        Account acc = accountsDatabase.get(inputAccNo);
        if (acc != null) {
            acc.deposit(getValidDoubleInput("Enter Deposit Amount: "));
        } else {
            System.out.println(" Account not found!");
        }
    }

    private static void performWithdraw() {
        System.out.print("Enter Account Number: ");
        String inputAccNo = scanner.nextLine().trim();
        Account acc = accountsDatabase.get(inputAccNo);
        if (acc != null) {
            acc.withdraw(getValidDoubleInput("Enter Withdrawal Amount: "));
        } else {
            System.out.println(" Account not found!");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String inputAccNo = scanner.nextLine().trim();
        Account acc = accountsDatabase.get(inputAccNo);
        if (acc != null) {
            acc.displayAccountInfo();
        } else {
            System.out.println(" Account not found!");
          
        }
    }

    private static void viewAllAccounts() {
        if (accountsDatabase.isEmpty()) {
            System.out.println("No accounts registered.");
        } else {
            for (Account acc : accountsDatabase.values()) {
                acc.displayAccountInfo();
              
            }
          
        }
    }
  }
