package fr.tdd.kata.bankaccount;

import java.math.BigDecimal;

import fr.tdd.kata.bankaccount.adapters.Console;
import fr.tdd.kata.bankaccount.adapters.ConsoleStatementPrinter;
import fr.tdd.kata.bankaccount.adapters.InMemoryTransactionRepository;
import fr.tdd.kata.bankaccount.domain.model.Account;
import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;
import fr.tdd.kata.bankaccount.utils.DateHelper;

public class HexagonalArchitectureBankingKata {
	
	public static void main(String[] args) {
		
		Console console = new Console();
		DateHelper helper = new DateHelper();
		StatementPrinter printer = new ConsoleStatementPrinter(console);
		TransactionRepository repository = new InMemoryTransactionRepository(helper);
		Account account = new Account(repository, printer);
		
		account.deposit(new BigDecimal(1000.00));
		account.withdraw(new BigDecimal(100.00));
		account.deposit(new BigDecimal(500.00));
		
		account.printStatement();
		
	}
}
