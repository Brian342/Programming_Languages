import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class User {
    protected String name;
    protected BigDecimal cash;
    protected BigDecimal savingsBalance;
    protected BigDecimal investmentBalance;
    protected Map<String, BigDecimal> funds;
    
    public User(String name) {
        this.name = name;
        this.cash = new BigDecimal("1000.00");
        this.savingsBalance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.investmentBalance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.funds = new HashMap<>();
        this.funds.put("LOW_RISK", BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        this.funds.put("MEDIUM_RISK", BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        this.funds.put("HIGH_RISK", BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }
    
    public String getName() {
        return name;
    }
    
    public BigDecimal getCash() {
        return cash;
    }
    
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
    
    public BigDecimal getSavingsBalance() {
        return savingsBalance;
    }
    
    public void setSavingsBalance(BigDecimal savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
    
    public BigDecimal getInvestmentBalance() {
        return investmentBalance;
    }
    
    public void setInvestmentBalance(BigDecimal investmentBalance) {
        this.investmentBalance = investmentBalance;
    }
    
    public Map<String, BigDecimal> getFunds() {
        return funds;
    }
}

class Customer extends User {
    public Customer(String name) {
        super(name);
    }
}

public class BankingApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();
        
        // Initialize the 4 required users
        users.put("Alice", new Customer("Alice"));
        users.put("Bob", new Customer("Bob"));
        users.put("Charlie", new Customer("Charlie"));
        users.put("Diana", new Customer("Diana"));
        
        boolean running = true;
        
        while (running) {
            // Login State
            
            User currentUser = null;
            while (currentUser == null) {
                // Ask for the user who wants to login
                System.out.print("Enter your name to login: ");
                if (!scanner.hasNextLine()) {
                    running = false;
                    break;
                }
                String input = scanner.nextLine().trim();
                if (users.containsKey(input)) {
                    currentUser = users.get(input);
                    // Print welcome message for the current user.
                    System.out.println("Welcome, " + currentUser.getName() + "!");
                } else {
                    // Handle invalid username gracefully
                    System.out.println("User not found. Please try again.");
                }
            }
            
            if (!running) {
                break;
            }
            
            // Session Active Loop
            boolean sessionActive = true;
            while (sessionActive) {
                printMenu();
                if (!scanner.hasNextLine()) {
                    sessionActive = false;
                    running = false;
                    break;
                }
                String choiceStr = scanner.nextLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(choiceStr);
                    if(choice < 1 || choice > 9){
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        // Show balance & apply interest/gains
                        applyInterestAndGains(currentUser);
                        showBalance(currentUser);
                        break;
                    case 2:
                        // Deposit money (Cash -> Savings)
                        depositMoney(currentUser, scanner);
                        break;
                    case 3:
                        // Withdraw money (Savings -> Cash)
                        System.out.println(withdrawMoney(currentUser, scanner));
                        break;
                    case 4:
                        // Send money to a person
                        System.out.println(sendMoney(currentUser, users, scanner));
                        break;
                    case 5:
                        // Invest in funds
                        investInFunds(currentUser, scanner);
                        break;
                    case 6:
                        // Transfer between accounts (Savings <-> Investment)
                        System.out.println(transferBetweenAccounts(currentUser, scanner));
                        break;
                    case 7:
                        // Withdraw all investments
                        System.out.println(withdrawAllInvestments(currentUser));
                        break;
                    case 8:
                        // Logout
                        System.out.println("You have been logged out.");
                        sessionActive = false;
                        break;
                    case 9:
                        // Exit (Gracefully without System.exit)
                        sessionActive = false;
                        running = false;
                        System.out.print("Thank you for using our banking app. Goodbye!");
                        System.out.println();
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("");
        System.out.println("--- Banking App Menu ---");
        System.out.println("1. Show balance");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Send money to a person");
        System.out.println("5. Invest in funds");
        System.out.println("6. Transfer between accounts");
        System.out.println("7. Withdraw all investments");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void applyInterestAndGains(User user) {
        // Savings 1% interest
        BigDecimal savingsInterest = user.getSavingsBalance().multiply(new BigDecimal("0.01"));
        user.setSavingsBalance(user.getSavingsBalance().add(savingsInterest).setScale(2, RoundingMode.HALF_UP));
        
        // Funds appreciation: LOW_RISK (2%), MEDIUM_RISK (5%), HIGH_RISK (10%)
        Map<String, BigDecimal> funds = user.getFunds();
        funds.put("LOW_RISK", funds.get("LOW_RISK").multiply(new BigDecimal("1.02")).setScale(2, RoundingMode.HALF_UP));
        funds.put("MEDIUM_RISK", funds.get("MEDIUM_RISK").multiply(new BigDecimal("1.05")).setScale(2, RoundingMode.HALF_UP));
        funds.put("HIGH_RISK", funds.get("HIGH_RISK").multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP));
    }
    
    private static void showBalance(User user) {
        System.out.println("Savings account balance: $" + user.getSavingsBalance());
        System.out.println("Investment account balance:");

        System.out.println("* Not Invested: $" + user.getInvestmentBalance());
    
        // Get all funds and check if any have been invested
        Map<String, BigDecimal> funds = user.getFunds();

        // Maintain a consistent key order matching the fund creation order
    String[] fundKeys = {"LOW_RISK", "MEDIUM_RISK", "HIGH_RISK"};
    for (String key : fundKeys) {
        BigDecimal balance = funds.get(key);
        if (balance != null && balance.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("* " + key + ": $" + balance);
        }
    }
}
    
    // Brian's Section
    private static void depositMoney(User user, Scanner scanner) {
        System.out.print("Enter amount to deposit to savings account: $");
        String input = scanner.nextLine().trim();
        
        try {
            BigDecimal amount = new BigDecimal(input);
            if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getCash().compareTo(amount) >= 0) {
                user.setCash(user.getCash().subtract(amount));
                user.setSavingsBalance(user.getSavingsBalance().add(amount));
                System.out.println("Deposit successful.");
            } else if (user.getCash().compareTo(amount) < 0) {
                System.out.println("Deposit failed: Insufficient cash on hand");
            } else {
                System.out.println("Deposit failed: amount must be positive");
            }
        } catch (NumberFormatException e) {}
    }
    
    private static String withdrawMoney(User user, Scanner scanner) {
        System.out.print("Enter amount to withdraw from savings account: $");
        
        if (!scanner.hasNextLine()) {
            return "Amount to withdraw can not be empty";
        }
        
        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getSavingsBalance().compareTo(amount) >= 0) {
                user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                user.setCash(user.getCash().add(amount));
                return "Withdrawal successful.";
            } else if (user.getSavingsBalance().compareTo(amount) < 0) {
                return "Withdrawal failed: Insufficient funds";
            } else {
                return "Withdrawal failed: amount must be positive";
            }
        } catch (NumberFormatException ignored) {
            return "Invalid input format";
        }
    }
    
    private static String sendMoney(User user, Map<String, User> users, Scanner scanner) {
        System.out.println("Available recipients:");
        
        // Sort available users alphabetically for consistent output order
        java.util.List<String> sortedUsernames = new java.util.ArrayList<>(users.keySet());
        java.util.Collections.sort(sortedUsernames);
        
        for (String registered : sortedUsernames) {
            if (registered.equals(user.getName())) {
                continue;
            } else {
                System.out.println(registered);
            }
        }
        
        // Ask for recipient and store the value
        System.out.print("Enter recipient's name: ");
        
        // validate recipient
        if (!scanner.hasNextLine()) return "Recipient can not be blank";
        String recipientName = scanner.nextLine().trim();
        
        if (recipientName.isEmpty()) return "Recipient can not be blank";
        
        if (!users.containsKey(recipientName)) return "Invalid recipient.";
        
        if (users.get(recipientName) == user) return " You can not send yourself money from your own account";
        
        // Ask for amount and store the value
        System.out.print("Enter amount to send: $");
        if (!scanner.hasNextLine()) return "Amount can not be empty";
        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            
            // Check if amount is positive first to match the expected error message
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return "Failed to send money: amount must be positive";
            }
            
            // Check for sufficient funds next
            if (user.getSavingsBalance().compareTo(amount) >= 0) {
                user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                User recipient = users.get(recipientName);
                recipient.setSavingsBalance(recipient.getSavingsBalance().add(amount));
                return "Sent $" + amount.setScale(0, RoundingMode.FLOOR) + " to " + recipientName;
            } else {
                return "Failed to send money: Insufficient funds";
            }
        } catch (NumberFormatException ignored) {
            return "Amount can only be a number";
        }
    }
    
    // Brian's Section
    private static void investInFunds(User user, Scanner scanner) {
        String choice;
        
        System.out.println("Available funds:\n" + //
                        "LOW_RISK\n" + //
                        "MEDIUM_RISK\n" + //
                        "HIGH_RISK");
        
        System.out.print("Enter fund to invest in: ");

        if (!scanner.hasNextLine()) return;  

        choice = scanner.nextLine().toUpperCase().trim();

        if(!user.getFunds().containsKey(choice)){
            System.out.println("Invalid fund.");
            return;
        }
        
        System.out.print("Enter amount to invest: $");
        if(!scanner.hasNextLine()) return;

        String value = scanner.nextLine().trim();
        
        try{
            BigDecimal amount = new BigDecimal(value);
            if(amount.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Failed to invest: amount must be positive");
                return;
            }
            // checking if user has sufficient funds in their Investment Account
            // or move directly from Savings
            if(user.getInvestmentBalance().compareTo(amount) >= 0){
                user.setInvestmentBalance(user.getInvestmentBalance().subtract(amount));
                BigDecimal currentFundBalance = user.getFunds().get(choice);
                user.getFunds().put(choice, currentFundBalance.add(amount));
                System.out.println("Successfully invested $" + amount + " in " + choice + " fund");
            }else{
                System.out.println("Failed to invest: Insufficient funds");
            }
            
        }catch (NumberFormatException e){
            System.out.println("Invalid amount entered");
            
        }
    }
    
    private static String transferBetweenAccounts(User user, Scanner scanner) {
        System.out.println("1. Transfer from savings to investment");
        System.out.println("2. Transfer from investment to savings");
        System.out.print("Enter your choice: ");
        
        if (!scanner.hasNextLine()) return "";
        String choice = scanner.nextLine().trim();

        System.out.print("Enter amount to transfer: $");

        if(!scanner.hasNextLine()) return "";
        String amountStr = scanner.nextLine().trim();
        
        if (!choice.equals("1") && !choice.equals("2")) {
            return "Invalid choice.";
        }
                
        try {
            BigDecimal amount = new BigDecimal(amountStr);

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return "Transfer failed: amount must be positive";
            }
            
            if (choice.equals("1")) {
            if (user.getSavingsBalance().compareTo(amount) >= 0) {
                user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                user.setInvestmentBalance(user.getInvestmentBalance().add(amount));
                return "Successfully transferred $" + amount.setScale(0, RoundingMode.FLOOR) + " to investment account.";
            } else {
                return "Transfer failed: Insufficient funds";
            }
        } else {
            if (user.getInvestmentBalance().compareTo(amount) >= 0) {
                user.setInvestmentBalance(user.getInvestmentBalance().subtract(amount));
                user.setSavingsBalance(user.getSavingsBalance().add(amount));
                return "Successfully transferred $" + amount.setScale(0, RoundingMode.FLOOR) + " to savings account.";
            } else {
                return "Transfer failed: Insufficient funds";
            }
        }
    } catch (NumberFormatException e) {
        return "Invalid input format.";
    
    }
}
    
    private static String withdrawAllInvestments(User user) {
        BigDecimal totalFunds = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : user.getFunds().entrySet()) {
            totalFunds = totalFunds.add(entry.getValue());
            entry.setValue(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        }
        user.setInvestmentBalance(user.getInvestmentBalance().add(totalFunds));
        
        return "All investments have been withdrawn and added to your investment account balance.";
    }
}
