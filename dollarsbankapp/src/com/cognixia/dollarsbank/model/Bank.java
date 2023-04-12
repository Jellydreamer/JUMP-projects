package dollarsbank.model;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {
    
    // Database to be filled with Accounts, parsed by key value of their pin number
    Map<Integer, Account> accountDatabase;

    public Bank(){
        this.accountDatabase = new HashMap<Integer, Account>();
    }

    public void addAccount(int pin, Account newAccount){
        this.accountDatabase.put(pin, newAccount);
    }

    public Map<Integer,Account> getAccountDatabase() {
        return this.accountDatabase;
    }

    public Account getAccount(int pin){
        return this.accountDatabase.get(pin);
    }

    public void setAccountDatabase(Map<Integer,Account> accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountDatabase);
    }

    @Override
    public String toString() {
        return "{" +
            " accountDatabase='" + getAccountDatabase() + "'" +
            "}";
    }

}
