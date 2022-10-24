package fr.tdd.kata.bankaccount.domain.ports;

import java.util.List;

import fr.tdd.kata.bankaccount.domain.model.Transaction;

public interface StatementPrinter {

	void print(List<Transaction> transactions);
}
