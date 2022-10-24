package fr.tdd.kata.bankaccount.domain.ports;

import java.util.List;

import fr.tdd.kata.bankaccount.domain.Transaction;

public interface StatementPrinter {

	void print(List<Transaction> transactions);
}
