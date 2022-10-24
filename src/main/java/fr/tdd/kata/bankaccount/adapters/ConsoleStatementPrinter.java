package fr.tdd.kata.bankaccount.adapters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

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
		
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		
		AtomicReference<BigDecimal> runningBalance = new AtomicReference<BigDecimal>(new BigDecimal(0.00));
		
		Function<Transaction, String> transactionToStatementLineConverter = transaction -> {
			var amount = (transaction.getOperationType() == OperationType.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate();
			String statementLine = transaction.getOperationType()
															   + " | " + 
															   transaction.getDate() 
															   + " | " + 
															   decimalFormat.format(amount)
															   + " | " + 
															   decimalFormat.format(runningBalance.accumulateAndGet(amount, (u,v) -> u.add(v))); 
			return statementLine;};
			
			transactions.stream().map(transactionToStatementLineConverter).forEach(console::printLine);
		
	}

}
