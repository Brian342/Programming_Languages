package com.theoriest.greendaybank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account {
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.01");

    public void applyInterest() {
        BigDecimal interest = getBalance().multiply(INTEREST_RATE);
        setBalance(getBalance().add(interest).setScale(2, RoundingMode.HALF_UP));
    }
}