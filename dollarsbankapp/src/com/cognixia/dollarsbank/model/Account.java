package dollarsbank.model;
import java.util.ArrayList;
import java.util.Objects;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  
import dollarsbank.utility.TextHelper;
 
public class Account {
    
    String username;
    int balance = 0;
    int pin = 0;
    ArrayList<String> transactions;

    public Account(int pin) {
        this.balance = 0;
        this.username = "New User";
        this.pin = pin;
        this.transactions = new ArrayList<String>();
        this.transactions.add("Account Created");
    }

    public Account(int pin, int balance) {
        this.balance = balance;
        this.username = "New User";
        this.pin = pin;
        this.transactions = new ArrayList<String>();
        this.transactions.add("Account Created");
    }

    public Account(String username, int pin, int balance) {
        this.balance = balance;
        this.username = username;
        this.pin = pin;
        this.transactions = new ArrayList<String>();
        this.transactions.add("Account Created");
    }

    public void writeAccountFile(){
        try {
            FileWriter myWriter = new FileWriter(this.username + ".txt");
            myWriter.write(this.toString());
            myWriter.close();
            System.out.println(TextHelper.colorTextYellow("Successfully wrote to the file."));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void depositBalance(int amount) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Only Positive Numbers & no Letters Please!"); 
            }   
            this.balance += amount;
            this.transactions.add(("Deposited: +" + amount + "$"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void withdrawBalance(int amount) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Only Positive Numbers & no Letters Please!"); 
            }   
            this.balance -= amount;
            this.transactions.add(("Withdrew: +" + amount + "$"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    public int getBalance() {
        this.transactions.add(("Checked Balance in Account"));
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
        this.transactions.add(("Changed Pin"));
    }

    public ArrayList<String> getTransactions() {
        this.transactions.add(("Checked History"));
        return this.transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return balance == account.balance && pin == account.pin && Objects.equals(transactions, account.transactions);
    }

    @Override
    public String toString() {
        return "{" +
            " balance='" + getBalance() + "'" +
            ", pin='" + getPin() + "'" +
            ", transactions='" + getTransactions() + "'" +
            "}";
    }




}
