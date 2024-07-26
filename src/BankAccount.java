import java.util.Scanner;

class BankAccount {
    private String name;
    private double balance;
    private int accountNumber;
    private static Scanner scanner = new Scanner(System.in);

    public BankAccount(String name, double balance, int accountNumber) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        System.out.print("What is the name for the account? ");
        this.name = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = scanner.nextDouble();
        scanner.nextLine(); // consume newline
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void printInfo() {
        System.out.println(this.name + "'s account balance: " + this.balance);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    public void transfer(BankAccount toAccount, double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            toAccount.deposit(amount);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}