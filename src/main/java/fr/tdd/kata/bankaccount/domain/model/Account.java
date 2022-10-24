package fr.tdd.kata.bankaccount.domain.model;

import java.math.BigDecimal;

import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;

public class Account {

	private TransactionRepository transactionRepository;
	private StatementPrinter statementPrinter;

	public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
		this.transactionRepository = transactionRepository;
		this.statementPrinter = statementPrinter;
	}

	public void deposit(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not deposit a negative amount: %s", amount));
		transactionRepository.addTransaction(amount, OperationType.DEPOSIT);
	}

	public BigDecimal getBalance() {
		return transactionRepository
				  .getTransactions()
				  .stream()
				  .map(transaction -> (transaction.getOperationType() == OperationType.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate())
				  .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void withdraw(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not withdraw a negative amount: %s", amount));
		if (getBalance().compareTo(amount) < 0)
			throw new IllegalArgumentException(String.format("Should not withdraw amount %s more than balance %s", amount, getBalance()));
		transactionRepository.addTransaction(amount, OperationType.WITHDRAWAL);
	}

	public void printStatement() {
		statementPrinter.print(transactionRepository.getTransactions());
	}

}
