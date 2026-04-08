import java.util.*;

class BankAccount {
    int accountNumber;
    String username;
    double balance;

    public BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return accountNumber + ". " + username + " – Balance: " + balance;
    }
}

class Task6 {
    static LinkedList<BankAccount> accounts = new LinkedList<>(); // Task 1 [cite: 28]
    static Stack<String> transactionHistory = new Stack<>();       // Task 3 [cite: 41]
    static Queue<String> billQueue = new LinkedList<>();           // Task 4 [cite: 52]
    static Queue<BankAccount> accountRequests = new LinkedList<>(); // Task 5 [cite: 59]

    static Scanner scanner = new Scanner(System.in);
    static int accountIdCounter = 1;

    public static void main(String[] args) {
        runTask6Demo();

        boolean running = true;
        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
            System.out.println("4 – Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": bankMenu(); break;
                case "2": atmMenu(); break;
                case "3": adminMenu(); break;
                case "4":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default: System.out.println("Invalid option! Please try again.");
            }
        }
    }


    static void bankMenu() {
        System.out.println("\n--- Welcome to the Bank ---");
        System.out.println("1 - Submit account opening request");
        System.out.println("2 - Deposit money");
        System.out.println("3 - Withdraw money");
        System.out.println("4 - Back to main menu");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter username for new account: ");
                String name = scanner.nextLine();
                System.out.print("Enter initial deposit: ");
                double initial = Double.parseDouble(scanner.nextLine());
                accountRequests.add(new BankAccount(accountIdCounter++, name, initial));
                System.out.println("Request submitted to Admin queue.");
                break;
            case "2":
                performTransaction("Deposit");
                break;
            case "3":
                performTransaction("Withdraw");
                break;
        }
    }


    static void atmMenu() {
        System.out.println("\n--- ATM ---");
        System.out.print("Enter your username: ");
        String name = scanner.nextLine();
        BankAccount account = findAccount(name);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.println("1 - Balance enquiry");
        System.out.println("2 - Withdraw");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.println("Current Balance: " + account.balance);
        } else if (choice.equals("2")) {
            System.out.print("Amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= account.balance) {
                account.balance -= amount;
                transactionHistory.push("Withdraw " + amount + " from " + name);
                System.out.println("New balance: " + account.balance);
            } else {
                System.out.println("Insufficient funds!");
            }
        }
    }


    static void adminMenu() {
        System.out.println("\n--- Admin Panel ---");
        System.out.println("1 - Process Account Requests (Queue)");
        System.out.println("2 - Process Bill Payments (Queue)");
        System.out.println("3 - View All Accounts (LinkedList)");
        System.out.println("4 - Undo Last Transaction (Stack)");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                if (!accountRequests.isEmpty()) {
                    BankAccount req = accountRequests.poll();
                    accounts.add(req);
                    System.out.println("Account approved for: " + req.username);
                } else {
                    System.out.println("No pending requests.");
                }
                break;
            case "2":
                if (!billQueue.isEmpty()) {
                    System.out.println("Processing: " + billQueue.poll());
                } else {
                    System.out.println("No bills. Adding test bills (Electricity, Internet)...");
                    billQueue.add("Electricity Bill");
                    billQueue.add("Internet Bill");
                }
                break;
            case "3":
                System.out.println("Accounts List:");
                for (BankAccount acc : accounts) System.out.println(acc);
                break;
            case "4":
                if (!transactionHistory.isEmpty()) {
                    System.out.println("Last action: " + transactionHistory.peek());
                    System.out.println("Undoing: " + transactionHistory.pop() + " [Removed]");
                } else {
                    System.out.println("History is empty.");
                }
                break;
        }
    }

    // Вспомогательные методы
    static void runTask6Demo() {
        System.out.println("Task 6: Physical Data Structure (Array) Demo [cite: 67-70]");
        BankAccount[] fixedArray = new BankAccount[3];
        fixedArray[0] = new BankAccount(101, "Ali", 150000);
        fixedArray[1] = new BankAccount(102, "Sara", 220000);
        fixedArray[2] = new BankAccount(103, "Omar", 50000);
        for (BankAccount b : fixedArray) {
            System.out.println(b);
            accounts.add(b);
        }
        System.out.println("--------------------------------------");
    }

    static BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) return acc;
        }
        return null;
    }

    static void performTransaction(String type) {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        BankAccount account = findAccount(name);
        if (account != null) {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (type.equals("Deposit")) {
                account.balance += amount;
                transactionHistory.push("Deposit " + amount + " to " + name); // Task 3 [cite: 43]
            } else {
                if (amount <= account.balance) {
                    account.balance -= amount;
                    transactionHistory.push("Withdraw " + amount + " from " + name); // Task 3 [cite: 44]
                } else {
                    System.out.println("Not enough money!");
                    return;
                }
            }
            System.out.println("Success. New balance: " + account.balance);
        } else {
            System.out.println("User not found.");
        }
    }
}