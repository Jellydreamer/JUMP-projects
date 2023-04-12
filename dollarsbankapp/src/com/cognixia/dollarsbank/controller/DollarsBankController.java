package dollarsbank.controller;
import java.util.Scanner;

import org.w3c.dom.Text;

import dollarsbank.utility.TextHelper;
import dollarsbank.model.Account;
import dollarsbank.model.Bank;

public class DollarsBankController {
    
    Bank dollarsBank;
    
    public DollarsBankController(){
        this.dollarsBank = new Bank();
        runMenu();
    }

    public void runMenu(){
        boolean quit = false;
        boolean loggedIn = false;
        int currentAccountPin = 0000;
        Scanner myObj = new Scanner(System.in);
        do {
            while (loggedIn == false) {
                TextHelper.topperLine();
                System.out.println(TextHelper.colorTextCyan("Enter a Valid Choice (1 > Transaction 2 > Open New Account 3 > Quit)"));
                System.out.println(TextHelper.colorTextYellow("Please enter selection: "));
                TextHelper.bottomLine();
                int choice = myObj.nextInt();

                switch (choice) {
                    
                    case 1:
                        //Enter Pin 
                        TextHelper.topperLine();
                        System.out.println(TextHelper.colorTextCyan("Please enter valid account pin: "));
                        int pin = myObj.nextInt();
                        if (dollarsBank.getAccountDatabase().containsKey(pin)) {
                            loggedIn = true;
                            currentAccountPin = pin;
                            System.out.println("Account Logged In!");
                        } else {
                            System.out.println("No such account found!");
                        }
                        TextHelper.bottomLine();
                        break;
                    
                    case 2:
                        //Create new account
                        TextHelper.topperLine();
                        System.out.println(TextHelper.colorTextCyan("Please enter a new valid account pin (####) : "));
                        int newPin = myObj.nextInt();
                        System.out.println(TextHelper.colorTextCyan("Please enter a starting value for the account: $"));
                        int startingAmount = myObj.nextInt();
                        myObj.nextLine();
                        System.out.println(TextHelper.colorTextCyan("Please enter a username for the account: $"));
                        String username = myObj.nextLine();
                        if (dollarsBank.getAccountDatabase().containsKey(newPin)) {
                            System.out.println("Error! Pin already exists, restart process");
                            break;
                        } else {
                            System.out.println(TextHelper.colorTextGreen("Valid Account Created!"));
                            currentAccountPin = newPin;
                            dollarsBank.addAccount(newPin, new Account(username, newPin, startingAmount));
                            loggedIn = true;
                            dollarsBank.getAccount(newPin).writeAccountFile();
                        }
                        TextHelper.bottomLine();
                        break;

                    case 3:
                        //Quit
                        System.exit(0);
                }
            }
            
            System.out.println(TextHelper.colorTextCyan("Welcome User! Transaction Menu: "));
            System.out.println(TextHelper.colorTextYellow("Enter [1] : Account Balance Check"));
            System.out.println(TextHelper.colorTextYellow("Enter [2] : Print Transactions"));
            System.out.println(TextHelper.colorTextYellow("Enter [3] : Update Pin"));
            System.out.println(TextHelper.colorTextYellow("Enter [4] : Withdraw Amount"));
            System.out.println(TextHelper.colorTextYellow("Enter [5] : Deposit Amount"));
            System.out.println(TextHelper.colorTextRed("Enter [6] : Logout"));
            int newChoice = myObj.nextInt();
            
            switch (newChoice) {
                case 1:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    System.out.println(TextHelper.colorTextGreen("Your Balance: " + dollarsBank.getAccount(currentAccountPin).getBalance() + "$"));
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;
                    
                case 2:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    System.out.println(dollarsBank.getAccount(currentAccountPin).getTransactions());
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;

                case 3:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    System.out.println("Please enter new pin {####} : ");
                    int replacePin = myObj.nextInt();
                    dollarsBank.getAccount(currentAccountPin).setPin(replacePin);
                    Account saveSlot = dollarsBank.getAccount(currentAccountPin);
                    dollarsBank.getAccountDatabase().remove(currentAccountPin);
                    dollarsBank.getAccountDatabase().put(replacePin, saveSlot);
                    currentAccountPin = replacePin;
                    System.out.println(TextHelper.colorTextGreen("Pin changed!"));
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;

                case 4:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    System.out.println("Please Enter Amount to Withdraw: ");
                    
                    int withdrawAmount = myObj.nextInt();
                    dollarsBank.getAccount(currentAccountPin).withdrawBalance(withdrawAmount);
                    System.out.println(TextHelper.colorTextGreen("New Balance: " + dollarsBank.getAccount(currentAccountPin).getBalance() + "$"));
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;
                
                case 5:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    System.out.println("Please Enter Amount to Deposit: ");
                    int depositAmount = myObj.nextInt();
                    dollarsBank.getAccount(currentAccountPin).depositBalance(depositAmount);
                    System.out.println(TextHelper.colorTextGreen("New Balance: " + dollarsBank.getAccount(currentAccountPin).getBalance() + "$"));
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;
                
                case 6:
                    TextHelper.lineBarrier();
                    TextHelper.blankLine();
                    loggedIn = false;
                    currentAccountPin = 0;
                    System.out.println(TextHelper.colorTextGreen("Logged Out!"));
                    TextHelper.blankLine();
                    TextHelper.lineBarrier();
                    break;    
            }

        } while (quit == false);
    }

}
