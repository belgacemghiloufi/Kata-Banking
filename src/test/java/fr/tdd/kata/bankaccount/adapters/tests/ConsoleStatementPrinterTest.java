package fr.tdd.kata.bankaccount.adapters.tests;

import static  fr.tdd.kata.bankaccount.adapters.ConsoleStatementPrinter.STATEMENT_HEADER;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.tdd.kata.bankaccount.adapters.Console;
import fr.tdd.kata.bankaccount.adapters.ConsoleStatementPrinter;
import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.model.Transaction;
import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleStatementPrinterTest {
	
	private static final String TODAY = "24/10/2022";
	private StatementPrinter statementPrinter;
	@Mock private Console console;
	
	@Before
	public void initialise() {
		statementPrinter = new ConsoleStatementPrinter(console);
	}
	
	@Test
	public void
	print_header_even_when_there_are_no_transactions() {
		statementPrinter.print(emptyList());
		verify(console).printLine(STATEMENT_HEADER);
	}
	
	@Test
	public void 
	print_all_transactions() {

		Transaction deposit_1 = new Transaction(TODAY, new BigDecimal(1000.00), OperationType.DEPOSIT);
		Transaction withdrawal  = new Transaction(TODAY, new BigDecimal(100.00), OperationType.WITHDRAWAL);
		Transaction deposit_2 = new Transaction(TODAY, new BigDecimal(500.00), OperationType.DEPOSIT);
		List<Transaction> transactionList = Arrays.asList(deposit_1, withdrawal, deposit_2);

		statementPrinter.print(transactionList);

		verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
		verify(console).printLine("DEPOSIT | 24/10/2022 | 1000.00 | 1000.00");
		verify(console).printLine("WITHDRAWAL | 24/10/2022 | -100.00 | 900.00");
		verify(console).printLine("DEPOSIT | 24/10/2022 | 500.00 | 1400.00");
	}
}
