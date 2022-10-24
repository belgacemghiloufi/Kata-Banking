package fr.tdd.kata.bankaccount.adapters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.model.Transaction;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;
import fr.tdd.kata.bankaccount.utils.DateHelper;

public class InMemoryTransactionRepository implements TransactionRepository {

	private List<Transaction> transactions = new ArrayList<>();
	private DateHelper dateHelper;

	public InMemoryTransactionRepository(DateHelper dateHelper) {
		this.dateHelper = dateHelper;
	}

	@Override
	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions );
	}

	@Override
	public void addTransaction(BigDecimal amount, OperationType operationType) {
		Transaction transaction = new Transaction(dateHelper.getTodayAsString(), amount, operationType);
		transactions.add(transaction);
	}


}
