package com.theoriest.greendaybank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/* ======================================================================
   MODEL CLASSES (same as original BankingApp.java)
   ====================================================================== */

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

    public String getName() { return name; }
    public BigDecimal getCash() { return cash; }
    public void setCash(BigDecimal cash) { this.cash = cash; }
    public BigDecimal getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(BigDecimal savingsBalance) { this.savingsBalance = savingsBalance; }
    public BigDecimal getInvestmentBalance() { return investmentBalance; }
    public void setInvestmentBalance(BigDecimal investmentBalance) { this.investmentBalance = investmentBalance; }
    public Map<String, BigDecimal> getFunds() { return funds; }
}

class Customer extends User {
    public Customer(String name) { super(name); }
}

/* ======================================================================
   BANKING LOGIC (business rules extracted from the console app,
   with all Scanner/System.out I/O removed so the GUI can call them
   directly and get back a plain result string)
   ====================================================================== */

class BankingService {

    static void applyInterestAndGains(User user) {
        BigDecimal savingsInterest = user.getSavingsBalance().multiply(new BigDecimal("0.01"));
        user.setSavingsBalance(user.getSavingsBalance().add(savingsInterest).setScale(2, RoundingMode.HALF_UP));

        Map<String, BigDecimal> funds = user.getFunds();
        funds.put("LOW_RISK", funds.get("LOW_RISK").multiply(new BigDecimal("1.02")).setScale(2, RoundingMode.HALF_UP));
        funds.put("MEDIUM_RISK", funds.get("MEDIUM_RISK").multiply(new BigDecimal("1.05")).setScale(2, RoundingMode.HALF_UP));
        funds.put("HIGH_RISK", funds.get("HIGH_RISK").multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP));
    }

    static String depositMoney(User user, String input) {
        try {
            BigDecimal amount = new BigDecimal(input.trim());
            if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getCash().compareTo(amount) >= 0) {
                user.setCash(user.getCash().subtract(amount));
                user.setSavingsBalance(user.getSavingsBalance().add(amount));
                return "Deposit of $" + amount + " was successful!";
            } else if (user.getCash().compareTo(amount) < 0) {
                return "Insufficient cash balance.";
            } else {
                return "Amount must be greater than zero.";
            }
        } catch (NumberFormatException e) {
            return "Invalid amount entered. Amount must be a number.";
        }
    }

    static String withdrawMoney(User user, String input) {
        try {
            BigDecimal amount = new BigDecimal(input.trim());
            if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getSavingsBalance().compareTo(amount) >= 0) {
                user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                user.setCash(user.getCash().add(amount));
                return "Withdrawal of $" + amount + " successful.";
            } else {
                return "Amount to withdraw must be equal or less than balance.";
            }
        } catch (NumberFormatException e) {
            return "Invalid input format.";
        }
    }

    static String sendMoney(User user, User recipient, String amountInput) {
        if (recipient == null) return "Recipient not found.";
        if (recipient == user) return "You can not send yourself money from your own account.";
        try {
            BigDecimal amount = new BigDecimal(amountInput.trim());
            if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getSavingsBalance().compareTo(amount) >= 0) {
                user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                recipient.setSavingsBalance(recipient.getSavingsBalance().add(amount));
                return "$" + amount + " sent to " + recipient.getName();
            } else {
                return "You can not send an amount greater than the balance in your savings account.";
            }
        } catch (NumberFormatException e) {
            return "Amount can only be a number.";
        }
    }

    static String investInFunds(User user, String fundChoice, String amountInput) {
        String choice = fundChoice.toUpperCase().trim();
        if (!user.getFunds().containsKey(choice)) {
            return "Invalid fund selected.";
        }
        try {
            BigDecimal amount = new BigDecimal(amountInput.trim());
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return "Amount must be greater than zero.";
            }
            if (user.getInvestmentBalance().compareTo(amount) >= 0) {
                user.setInvestmentBalance(user.getInvestmentBalance().subtract(amount));
                BigDecimal currentFundBalance = user.getFunds().get(choice);
                user.getFunds().put(choice, currentFundBalance.add(amount));
                return "Successfully invested $" + amount + " in " + choice + " fund.";
            } else {
                return "Insufficient funds in Investment account. Please transfer funds to Investment account first.";
            }
        } catch (NumberFormatException e) {
            return "Invalid amount entered.";
        }
    }

    static String transferBetweenAccounts(User user, String direction, String amountInput) {
        try {
            BigDecimal amount = new BigDecimal(amountInput.trim());
            if (direction.equals("SAVINGS_TO_INVESTMENT")) {
                if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getSavingsBalance().compareTo(amount) >= 0) {
                    user.setSavingsBalance(user.getSavingsBalance().subtract(amount));
                    user.setInvestmentBalance(user.getInvestmentBalance().add(amount));
                    return "You have successfully transferred $" + amount + " to investment account.";
                } else {
                    return "Insufficient funds in savings account.";
                }
            } else if (direction.equals("INVESTMENT_TO_SAVINGS")) {
                if (amount.compareTo(BigDecimal.ZERO) > 0 && user.getInvestmentBalance().compareTo(amount) >= 0) {
                    user.setInvestmentBalance(user.getInvestmentBalance().subtract(amount));
                    user.setSavingsBalance(user.getSavingsBalance().add(amount));
                    return "You have successfully transferred $" + amount + " to savings account.";
                } else {
                    return "Insufficient funds in investment account. Check balance and try again.";
                }
            } else {
                return "Invalid choice selection.";
            }
        } catch (NumberFormatException e) {
            return "Invalid input format.";
        }
    }

    static String withdrawAllInvestments(User user) {
        BigDecimal totalFunds = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : user.getFunds().entrySet()) {
            totalFunds = totalFunds.add(entry.getValue());
            entry.setValue(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        }
        user.setInvestmentBalance(user.getInvestmentBalance().add(totalFunds));
        return "All investments totaling $" + totalFunds.setScale(2, RoundingMode.HALF_UP)
                + " were deposited in your investment account.";
    }
}

/* ======================================================================
   GUI
   ====================================================================== */

public class BankingAppGUI extends JFrame {

    // Palette
    private static final Color BG = new Color(244, 247, 245);
    private static final Color PRIMARY = new Color(27, 94, 32);      // deep green
    private static final Color PRIMARY_DARK = new Color(17, 66, 21);
    private static final Color ACCENT = new Color(76, 175, 80);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color TEXT_DARK = new Color(33, 37, 41);
    private static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 24);
    private static final Font FONT_HEADING = new Font("SansSerif", Font.BOLD, 16);
    private static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 14);

    private final Map<String, User> users = new HashMap<>();
    private User currentUser;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    // Login components
    private JComboBox<String> userSelector;

    // Dashboard components
    private JLabel welcomeLabel;
    private JLabel cashValue, savingsValue, investmentValue, lowRiskValue, mediumRiskValue, highRiskValue;
    private JTextArea activityLog;

    public BankingAppGUI() {
        super("Green Day Banking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 560);
        setMinimumSize(new Dimension(680, 520));
        setLocationRelativeTo(null);

        users.put("Alice", new Customer("Alice"));
        users.put("Bob", new Customer("Bob"));
        users.put("Charlie", new Customer("Charlie"));
        users.put("Diana", new Customer("Diana"));

        cardPanel.setBackground(BG);
        cardPanel.add(buildLoginPanel(), "LOGIN");
        cardPanel.add(buildDashboardPanel(), "DASHBOARD");

        setContentPane(cardPanel);
        cardLayout.show(cardPanel, "LOGIN");
    }

    /* ---------------------- LOGIN PANEL ---------------------- */

    private JPanel buildLoginPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 220), 1, true),
                new EmptyBorder(36, 44, 36, 44)));

        JLabel title = new JLabel("\uD83C\uDF3F Green Day Banking");
        title.setFont(FONT_TITLE);
        title.setForeground(PRIMARY_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Sign in to manage your accounts");
        subtitle.setFont(FONT_BODY);
        subtitle.setForeground(new Color(110, 110, 110));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(new EmptyBorder(6, 0, 24, 0));

        userSelector = new JComboBox<>(users.keySet().toArray(new String[0]));
        userSelector.setFont(FONT_BODY);
        userSelector.setMaximumSize(new Dimension(260, 34));
        userSelector.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginBtn = styledButton("Log In", PRIMARY);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(e -> doLogin());

        card.add(title);
        card.add(subtitle);
        card.add(userSelector);
        card.add(Box.createVerticalStrut(20));
        card.add(loginBtn);

        outer.add(card);
        return outer;
    }

    private void doLogin() {
        String selected = (String) userSelector.getSelectedItem();
        if (selected == null || !users.containsKey(selected)) {
            JOptionPane.showMessageDialog(this, "Please select a valid user.", "Login failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        currentUser = users.get(selected);
        welcomeLabel.setText("Welcome, " + currentUser.getName() + "!");
        activityLog.setText("");
        logActivity("Logged in as " + currentUser.getName() + ".");
        refreshBalances();
        cardLayout.show(cardPanel, "DASHBOARD");
    }

    /* ---------------------- DASHBOARD PANEL ---------------------- */

    private JPanel buildDashboardPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));

        // ---- Header ----
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(FONT_TITLE);
        welcomeLabel.setForeground(PRIMARY_DARK);
        JButton logoutBtn = styledButton("Logout", new Color(90, 96, 92));
        logoutBtn.addActionListener(e -> doLogout());
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);
        header.setBorder(new EmptyBorder(0, 0, 16, 0));

        // ---- Balances card ----
        JPanel balancesCard = new JPanel(new GridLayout(2, 3, 14, 14));
        balancesCard.setBackground(CARD_BG);
        balancesCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 220), 1, true),
                new EmptyBorder(18, 18, 18, 18)));

        cashValue = new JLabel();
        savingsValue = new JLabel();
        investmentValue = new JLabel();
        lowRiskValue = new JLabel();
        mediumRiskValue = new JLabel();
        highRiskValue = new JLabel();

        balancesCard.add(balanceTile("Cash", cashValue));
        balancesCard.add(balanceTile("Savings", savingsValue));
        balancesCard.add(balanceTile("Investment", investmentValue));
        balancesCard.add(balanceTile("Low Risk Fund", lowRiskValue));
        balancesCard.add(balanceTile("Medium Risk Fund", mediumRiskValue));
        balancesCard.add(balanceTile("High Risk Fund", highRiskValue));

        // ---- Actions ----
        JPanel actions = new JPanel(new GridLayout(4, 2, 10, 10));
        actions.setBackground(BG);
        actions.setBorder(new EmptyBorder(16, 0, 16, 0));

        actions.add(actionButton("Apply Interest / Gains", ACCENT, e -> {
            BankingService.applyInterestAndGains(currentUser);
            logActivity("Applied interest and investment gains.");
            refreshBalances();
        }));
        actions.add(actionButton("Deposit Money", PRIMARY, e -> handleDeposit()));
        actions.add(actionButton("Withdraw Money", PRIMARY, e -> handleWithdraw()));
        actions.add(actionButton("Send Money", PRIMARY, e -> handleSendMoney()));
        actions.add(actionButton("Invest in Funds", PRIMARY, e -> handleInvest()));
        actions.add(actionButton("Transfer Between Accounts", PRIMARY, e -> handleTransfer()));
        actions.add(actionButton("Withdraw All Investments", PRIMARY, e -> {
            String result = BankingService.withdrawAllInvestments(currentUser);
            logActivity(result);
            refreshBalances();
        }));
        actions.add(actionButton("Refresh", new Color(90, 96, 92), e -> refreshBalances()));

        // ---- Activity log ----
        activityLog = new JTextArea(8, 20);
        activityLog.setEditable(false);
        activityLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        activityLog.setBackground(new Color(250, 250, 250));
        activityLog.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane logScroll = new JScrollPane(activityLog);
        logScroll.setBorder(BorderFactory.createTitledBorder("Activity Log"));

        JPanel center = new JPanel(new BorderLayout(0, 12));
        center.setBackground(BG);
        center.add(balancesCard, BorderLayout.NORTH);
        center.add(actions, BorderLayout.CENTER);
        center.add(logScroll, BorderLayout.SOUTH);

        root.add(header, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        return root;
    }

    private JPanel balanceTile(String label, JLabel valueLabel) {
        JPanel tile = new JPanel();
        tile.setLayout(new BoxLayout(tile, BoxLayout.Y_AXIS));
        tile.setBackground(CARD_BG);
        JLabel l = new JLabel(label);
        l.setFont(FONT_BODY);
        l.setForeground(new Color(110, 110, 110));
        valueLabel.setFont(FONT_HEADING);
        valueLabel.setForeground(TEXT_DARK);
        tile.add(l);
        tile.add(valueLabel);
        return tile;
    }

    private JButton actionButton(String text, Color color, java.awt.event.ActionListener listener) {
        JButton btn = styledButton(text, color);
        btn.addActionListener(listener);
        return btn;
    }

    private JButton styledButton(String text, Color color) {
        RoundButton btn = new RoundButton(text, color, color.darker());
        btn.setFont(FONT_BUTTON);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    /**
     * A JButton that paints its own background directly instead of relying on
     * the platform look-and-feel (some L&Fs, e.g. macOS Aqua, ignore
     * setBackground()/setForeground() on plain JButtons, which is why the
     * text can end up unreadable against a default grey background).
     */
    private static class RoundButton extends JButton {
        private final Color baseColor;
        private final Color hoverColor;

        RoundButton(String text, Color baseColor, Color hoverColor) {
            super(text);
            this.baseColor = baseColor;
            this.hoverColor = hoverColor;
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    setBackground(hoverColor);
                    repaint();
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    setBackground(baseColor);
                    repaint();
                }
            });
            setBackground(baseColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? hoverColor.darker() : getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private void doLogout() {
        logActivity("Logged out.");
        currentUser = null;
        cardLayout.show(cardPanel, "LOGIN");
    }

    /* ---------------------- ACTION HANDLERS ---------------------- */

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to deposit to savings account:", "Deposit", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        String result = BankingService.depositMoney(currentUser, input);
        logActivity(result);
        refreshBalances();
    }

    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw from savings account:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        String result = BankingService.withdrawMoney(currentUser, input);
        logActivity(result);
        refreshBalances();
    }

    private void handleSendMoney() {
        java.util.List<String> others = new java.util.ArrayList<>();
        for (String name : users.keySet()) {
            if (!name.equals(currentUser.getName())) others.add(name);
        }
        String recipientName = (String) JOptionPane.showInputDialog(this, "Send money to:", "Send Money",
                JOptionPane.PLAIN_MESSAGE, null, others.toArray(), others.isEmpty() ? null : others.get(0));
        if (recipientName == null) return;

        String amountInput = JOptionPane.showInputDialog(this, "How much would you like to send to " + recipientName + "?", "Send Money", JOptionPane.PLAIN_MESSAGE);
        if (amountInput == null) return;

        String result = BankingService.sendMoney(currentUser, users.get(recipientName), amountInput);
        logActivity(result);
        refreshBalances();
    }

    private void handleInvest() {
        String[] fundOptions = {"LOW_RISK", "MEDIUM_RISK", "HIGH_RISK"};
        String fundChoice = (String) JOptionPane.showInputDialog(this, "Choose a fund to invest in:", "Invest in Funds",
                JOptionPane.PLAIN_MESSAGE, null, fundOptions, fundOptions[0]);
        if (fundChoice == null) return;

        String amountInput = JOptionPane.showInputDialog(this, "Enter amount to invest in " + fundChoice + ":", "Invest in Funds", JOptionPane.PLAIN_MESSAGE);
        if (amountInput == null) return;

        String result = BankingService.investInFunds(currentUser, fundChoice, amountInput);
        logActivity(result);
        refreshBalances();
    }

    private void handleTransfer() {
        String[] directions = {"Savings \u2192 Investment", "Investment \u2192 Savings"};
        String directionChoice = (String) JOptionPane.showInputDialog(this, "Choose transfer direction:", "Transfer Between Accounts",
                JOptionPane.PLAIN_MESSAGE, null, directions, directions[0]);
        if (directionChoice == null) return;

        String amountInput = JOptionPane.showInputDialog(this, "Enter the amount to transfer:", "Transfer Between Accounts", JOptionPane.PLAIN_MESSAGE);
        if (amountInput == null) return;

        String direction = directionChoice.equals(directions[0]) ? "SAVINGS_TO_INVESTMENT" : "INVESTMENT_TO_SAVINGS";
        String result = BankingService.transferBetweenAccounts(currentUser, direction, amountInput);
        logActivity(result);
        refreshBalances();
    }

    /* ---------------------- HELPERS ---------------------- */

    private void refreshBalances() {
        if (currentUser == null) return;
        cashValue.setText("$" + currentUser.getCash());
        savingsValue.setText("$" + currentUser.getSavingsBalance());
        investmentValue.setText("$" + currentUser.getInvestmentBalance());
        lowRiskValue.setText("$" + currentUser.getFunds().get("LOW_RISK"));
        mediumRiskValue.setText("$" + currentUser.getFunds().get("MEDIUM_RISK"));
        highRiskValue.setText("$" + currentUser.getFunds().get("HIGH_RISK"));
    }

    private void logActivity(String message) {
        activityLog.append(message + "\n");
        activityLog.setCaretPosition(activityLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new BankingAppGUI().setVisible(true);
        });
    }
}
