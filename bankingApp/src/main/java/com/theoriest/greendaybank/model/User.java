package com.theoriest.greendaybank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class User {
    private final String name;
    private BigDecimal cash;
    private final SavingsAccount savingsAccount;
    private final InvestmentAccount investmentAccount;

    public User(String name) {
        this.name = name;
        this.cash = new BigDecimal("1000.00").setScale(2, RoundingMode.HALF_UP);
        this.savingsAccount = new SavingsAccount();
        this.investmentAccount = new InvestmentAccount();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash.setScale(2, RoundingMode.HALF_UP);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public InvestmentAccount getInvestmentAccount() {
        return investmentAccount;
    }
}