package fr.tdd.kata.bankaccount.adapters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.model.Transaction;
import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;


public class ConsoleStatementPrinter implements StatementPrinter {
	
	public static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
	private Console console;

	public ConsoleStatementPrinter(Console console) {
		this.console = console;
	}

	@Override
	public void print(List<Transaction> transactions) {
		console.printLine(STATEMENT_HEADER);
		AtomicReference<BigDecimal> runningBalance = new AtomicReference<BigDecimal>(new BigDecimal(0.00));
	    transactions.stream()
							.map(transaction ->transactionToStatementLineConverter(transaction, runningBalance))
							.forEach(console::printLine);
	}
	
	private String transactionToStatementLineConverter(Transaction transaction, AtomicReference<BigDecimal> runningBalance) {
		var amount = (transaction.getOperationType() == OperationType.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate();
		return transaction.getOperationType() 
				   + " | " +
				   transaction.getDate()
				   + " | " +
				   formatDecimal(amount)
				   + " | " +
				   formatDecimal(runningBalance.accumulateAndGet(amount, (u,v) -> u.add(v)));
	}
	
	private String formatDecimal(BigDecimal amount) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return decimalFormat.format(amount);
	}

}
