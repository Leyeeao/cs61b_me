/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    private int balance;
    /** Savings account --mt */
    public Account parentAccount;

    /** two-argument constructor --mt */
    public Account(int balance, Account savingsAccount) {
        this.balance = balance;
        this.parentAccount = savingsAccount;
    }

    /** Initialize an account with the given balance. */
    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }

    /** Returns the balance for the current account. */
    public int getBalance() {
        return balance;
    }

    /** Deposits amount into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract amount from the account if possible. If subtracting amount
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        // TODO

        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
            return false;
        } else if (balance < amount) {
            if (this.parentAccount == null) {
                System.out.println("Insufficient funds");
                return false;

            }else {
                amount = amount - this.balance;
                this.balance = 0;
                return this.parentAccount.withdraw(amount);
            }
        } else {
            balance = balance - amount;
            return true;
        }
    }

    /**
     * Merge account other into this account by removing all money from other
     * and depositing it into this account.
     */
    public void merge(Account other) {
        // TODO
        this.balance = this.balance + other.balance;
        other.balance = 0;

    }
}
