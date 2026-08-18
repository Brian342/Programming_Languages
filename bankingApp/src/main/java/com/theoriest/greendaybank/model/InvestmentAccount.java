package com.theoriest.greendaybank.model;

import com.theoriest.greendaybank.exception.InvalidAmountException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class InvestmentAccount extends Account {
    private final Map<Fund, BigDecimal> investments;

    public InvestmentAccount() {
        super();
        this.investments = new EnumMap<>(Fund.class);
        for (Fund fund : Fund.values()) {
            this.investments.put(fund, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        }
    }

    public Map<Fund, BigDecimal> getInvestments() {
        return investments;
    }

    public void invest(Fund fund, BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
        if (getBalance().compareTo(amount) < 0) {
            throw new InvalidAmountException("Insufficient funds in Investment account. Please transfer funds to Investment account first.");
        }
        withdraw(amount);
        BigDecimal currentFundBalance = investments.get(fund);
        investments.put(fund, currentFundBalance.add(amount).setScale(2, RoundingMode.HALF_UP));
    }

    public void applyGains() {
        for (Fund fund : Fund.values()) {
            BigDecimal current = investments.get(fund);
            if (current != null && current.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal gain = current.multiply(fund.getRate());
                investments.put(fund, current.add(gain).setScale(2, RoundingMode.HALF_UP));
            }
        }
    }

    public BigDecimal withdrawAllInvestments() {
        BigDecimal totalWithdrawn = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (Fund fund : Fund.values()) {
            BigDecimal amount = investments.get(fund);
            if (amount != null) {
                totalWithdrawn = totalWithdrawn.add(amount);
                investments.put(fund, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
            }
        }
        try {
            deposit(totalWithdrawn);
        } catch (InvalidAmountException ignored) {
        }
        return totalWithdrawn;
    }
}