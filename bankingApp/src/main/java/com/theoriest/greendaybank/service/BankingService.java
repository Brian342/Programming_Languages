package com.theoriest.greendaybank.service;

import com.theoriest.greendaybank.exception.InvalidAmountException;
import com.theoriest.greendaybank.model.Fund;
import com.theoriest.greendaybank.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankingService {
    private final Scanner scanner;
    private final Map<String, User> users;

    public BankingService() {
        this.scanner = new Scanner(System.in);
        this.users = new LinkedHashMap<>();
        users.put("Alice", new User("Alice"));
        users.put("Bob", new User("Bob"));
        users.put("Charlie", new User("Charlie"));
        users.put("Diana", new User("Diana"));
    }

    public void run() {
        try {
            boolean appRunning = true;

            while (appRunning) {
                User currentUser = handleLogin();
                if (currentUser == null) {
                    // EOF reached during login
                    break;
                }

                boolean sessionActive = true;
                while (sessionActive) {
                    printMenu();
                    
                    if (!scanner.hasNextLine()) {
                        sessionActive = false;
                        appRunning = false;
                        break;
                    }

                    String choiceInput = scanner.nextLine().trim();
                    int choice;
                    try {
                        choice = Integer.parseInt(choiceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                    }

                    switch (choice) {
                        case 1 -> showBalance(currentUser);
                        case 2 -> depositMoney(currentUser);
                        case 3 -> withdrawMoney(currentUser);
                        case 4 -> sendMoney(currentUser);
                        case 5 -> investInFunds(currentUser);
                        case 6 -> transferBetweenAccounts(currentUser);
                        case 7 -> withdrawAllInvestments(currentUser);
                        case 8 -> {
                            System.out.println("Goodbye " + currentUser.getName() + " Thank you for using Green Banking App!");
                            sessionActive = false;
                        }
                        case 9 -> {
                            sessionActive = false;
                            appRunning = false;
                            System.out.println("Thank you for using our Green Day Banking app. Bye!");
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        } catch (NoSuchElementException e) {
            // Graceful fallback if EOF is triggered unexpectedly without hasNextLine catching it
        }finally {
            System.out.flush();
        }
    }

    private User handleLogin() {
        while (true) {
            System.out.print("Enter your name to login: ");
            System.out.flush();

            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();
            if (users.containsKey(input)) {
                User user = users.get(input);
                System.out.println("Welcome, " + user.getName() + "!");
                System.out.println();
                return user;
            } else {
                System.out.println("User not found. Please try again.");
            }
        }
    }

    private void printMenu() {
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
        System.out.flush();
    }

    private void showBalance(User user) {
        user.getSavingsAccount().applyInterest();
        user.getInvestmentAccount().applyGains();

        System.out.println("Savings account balance: $" + user.getSavingsAccount().getBalance().setScale(2, RoundingMode.HALF_UP));
        System.out.println("Investment account balance:");
        System.out.println("* Not Invested: $" + user.getInvestmentAccount().getBalance().setScale(2, RoundingMode.HALF_UP));
        System.out.println();
    }

    private void depositMoney(User user) {
        System.out.print("Enter amount to deposit to savings account: $");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        String input = scanner.nextLine().trim();

        try {
            BigDecimal amount = new BigDecimal(input);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Deposit failed: amount must be positive");
            } else if (user.getCash().compareTo(amount) < 0) {
                System.out.println("Deposit failed: Insufficient cash on hand");
            } else {
                user.setCash(user.getCash().subtract(amount));
                user.getSavingsAccount().deposit(amount);
                System.out.println("Deposit successful.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format.");
        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private void withdrawMoney(User user) {
        System.out.print("Enter amount to withdraw from savings account: $");
        System.out.flush();

        if (!scanner.hasNextLine()) return;

        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            user.getSavingsAccount().withdraw(amount);
            user.setCash(user.getCash().add(amount));
            System.out.println("Withdrawal successful.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format.");
        } catch (InvalidAmountException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private void sendMoney(User user) {
        System.out.println("Available recipients:");
        for (String registered : users.keySet()) {
            System.out.println(registered + "\t");
        }
        System.out.println();

        System.out.print("Enter recipient's name: ");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        String recipientName = scanner.nextLine().trim();

        if (recipientName.isEmpty()) return;
        if (!users.containsKey(recipientName)) {
            System.out.println("User not found. You can only send money to registered users");
            return;
        }
        if (users.get(recipientName) == user) {
            System.out.println("You can not send yourself money from your own account");
            return;
        }

        System.out.print("How much would you like to send to " + recipientName + ": $");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            user.getSavingsAccount().withdraw(amount);
            users.get(recipientName).getSavingsAccount().deposit(amount);
            System.out.println("$" + amount.setScale(2, RoundingMode.HALF_UP) + " sent to " + recipientName);
        } catch (NumberFormatException e) {
            System.out.println("Amount can only be a number");
        } catch (InvalidAmountException e) {
            System.out.println("User can not send an amount greater than the balance in their savings account");
        }
    }

    private void investInFunds(User user) {
        System.out.println("Available funds:\nLOW_RISK\nMEDIUM_RISK\nHIGH_RISK\n");

        System.out.print("Enter fund to invest in: ");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        String fundChoiceStr = scanner.nextLine().trim().toUpperCase();

        Fund fundChoice;
        try {
            fundChoice = Fund.valueOf(fundChoiceStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fund selected.");
            return;
        }

        System.out.print("Enter amount to invest: $");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            user.getInvestmentAccount().invest(fundChoice, amount);
            System.out.println("Successfully Invested $" + amount.setScale(2, RoundingMode.HALF_UP) + " in " + fundChoice + " Fund");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered");
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void transferBetweenAccounts(User user) {
        System.out.println("1. Transfer from savings to investment");
        System.out.println("2. Transfer from investment to savings");
        System.out.print("Enter your choice: ");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        String choice = scanner.nextLine().trim();

        if (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Invalid choice selection. Try again");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        System.out.flush();

        if (!scanner.hasNextLine()) return;
        try {
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
            if (choice.equals("1")) {
                user.getSavingsAccount().withdraw(amount);
                user.getInvestmentAccount().deposit(amount);
                System.out.println("You have successfully transferred $" + amount.setScale(2, RoundingMode.HALF_UP) + " to investment account.");
            } else {
                user.getInvestmentAccount().withdraw(amount);
                user.getSavingsAccount().deposit(amount);
                System.out.println("You have successfully transferred $" + amount.setScale(2, RoundingMode.HALF_UP) + " to savings account.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format.");
        } catch (InvalidAmountException e) {
            if (choice.equals("1")) {
                System.out.println("Insufficient funds in savings account.");
            } else {
                System.out.println("Insufficient funds in investment account. Check balance and try again.");
            }
        }
    }

    private void withdrawAllInvestments(User user) {
        BigDecimal totalWithdrawn = user.getInvestmentAccount().withdrawAllInvestments();
        System.out.println("All investments totaling $" + totalWithdrawn.setScale(2, RoundingMode.HALF_UP) + " were deposited in your investment account.");
    }
}