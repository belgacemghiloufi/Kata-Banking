package fr.tdd.kata.bankaccount.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account;

	@Before
	public void initialise() {
		account = new Account();
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

}
