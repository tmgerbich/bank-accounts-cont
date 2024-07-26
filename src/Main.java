import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int accountNumberCounter = 1; // to auto-generate account numbers

        System.out.println("Hello world! Welcome to CTAC Bank!");
        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == -1) {
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter your account number: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    BankAccount account = findAccount(accountNumber);
                    if (account == null) {
                        System.out.println("Account not found.");
                    } else {
                        mainMenu(account);
                    }
                    break;
                case 2:
                    BankAccount newAccount = new BankAccount(accountNumberCounter++);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully. Your account number is " + newAccount.getAccountNumber());
                    mainMenu(newAccount);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void mainMenu(BankAccount account) {
        System.out.println("Hello " + account.getName() + "!");
        while (true) {
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    account.printInfo();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    account.withdraw(withdrawAmount);
                    System.out.println("Withdrawal successful.");
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 4:
                    System.out.print("Please enter the account number to transfer to: ");
                    int transferAccountNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    BankAccount transferAccount = findAccount(transferAccountNumber);
                    if (transferAccount == null) {
                        System.out.println("Account doesn't exist");
                    } else {
                        System.out.print("Please enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        account.transfer(transferAccount, transferAmount);
                        System.out.println("Transfer successful.");
                        System.out.println("The name on the account is: " + account.getName() + " and they have a balance of $" + account.getBalance());
                        System.out.println("The name on the account is: " + transferAccount.getName() + " and they have a balance of $" + transferAccount.getBalance());
                    }
                    break;
                case 0:
                    System.out.println("Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
