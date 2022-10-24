package fr.tdd.kata.bankaccount.domain;

import java.math.BigDecimal;

public class Account {

	private BigDecimal balance = BigDecimal.ZERO;

	public void deposit(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not deposit a negative amount: %s", amount));
		balance = balance.add(amount);
	}

	public BigDecimal getBalance() {
		return balance;
	}

}
