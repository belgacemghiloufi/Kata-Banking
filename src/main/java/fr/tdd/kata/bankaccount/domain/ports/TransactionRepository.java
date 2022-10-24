package fr.tdd.kata.bankaccount.domain.ports;

import java.util.List;

import fr.tdd.kata.bankaccount.domain.Transaction;

public interface TransactionRepository {

	List<Transaction> getTransactions();

}
