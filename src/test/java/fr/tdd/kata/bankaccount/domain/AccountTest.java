package fr.tdd.kata.bankaccount.domain;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.ports.StatementPrinter;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

	private static final String TODAY = "22/10/2022";
	private Account account;
	@Mock private TransactionRepository transactionRepository;
	@Mock private StatementPrinter statementPrinter;

	@Before
	public void initialise() {
		account = new Account(transactionRepository, statementPrinter);
	}

	@Test  
	public void 
	should_increase_balance_when_deposit_is_made() {
		BigDecimal amount = new BigDecimal(100.00);
		account.deposit(amount);
		assertThat(account.getBalance(), is(amount));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void
	should_handle_negative_deposit_amount() {
		BigDecimal negativeAmount = new BigDecimal(-100.00);
		account.deposit(negativeAmount);
	}
	
	@Test
	public void
	should_decrease_balance_when_withdrawal_is_made() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		BigDecimal withdrawAmount = new BigDecimal(50.00);
		BigDecimal leftAmount = new BigDecimal(50.00);
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
		assertThat(account.getBalance(), is(leftAmount));
	}
	
	
	@Test(expected = IllegalArgumentException.class) 
	public void
	should_handle_negative_withdraw_amount() {
		BigDecimal negativeAmount = new BigDecimal(-100.00);
		account.withdraw(negativeAmount);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void
	should_handle_withdrawing_more_than_balance() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		BigDecimal withdrawAmount = new BigDecimal(150.00);
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
	}
	
	@Test
	public void
	should_print_a_statement_containing_all_transactions() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		List<Transaction> transactions = Arrays.asList(new Transaction(TODAY,  depositAmount, OperationType.DEPOSIT));
		given(transactionRepository.getTransactions()).willReturn(transactions);
		account.printStatement();
		verify(statementPrinter).print(transactions);
	}

}
