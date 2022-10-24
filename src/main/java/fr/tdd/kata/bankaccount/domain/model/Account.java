package fr.tdd.kata.bankaccount.domain.model;

import java.math.BigDecimal;

import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;

public class Account {

	private BigDecimal balance = BigDecimal.ZERO;
	private TransactionRepository transactionRepository;
	private StatementPrinter statementPrinter;

	public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
		this.transactionRepository = transactionRepository;
		this.statementPrinter = statementPrinter;
	}

	public void deposit(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not deposit a negative amount: %s", amount));
		balance = balance.add(amount);
		transactionRepository.addTransaction(amount, OperationType.DEPOSIT);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void withdraw(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not withdraw a negative amount: %s", amount));
		if (balance.compareTo(amount) < 0)
			throw new IllegalArgumentException(String.format("Should not withdraw amount %s more than balance %s", amount, balance));
		balance = balance.subtract(amount);
		transactionRepository.addTransaction(amount, OperationType.WITHDRAWAL);
	}

	public void printStatement() {
		statementPrinter.print(transactionRepository.getTransactions());
	}

}
