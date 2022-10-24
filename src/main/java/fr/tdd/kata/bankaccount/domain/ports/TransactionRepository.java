package fr.tdd.kata.bankaccount.domain.ports;

import java.math.BigDecimal;
import java.util.List;

import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.model.Transaction;

public interface TransactionRepository {

	List<Transaction> getTransactions();

	void addTransaction(BigDecimal amount, OperationType operationType);

}
