package fr.tdd.kata.bankaccount.adapters.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.tdd.kata.bankaccount.adapters.InMemoryTransactionRepository;
import fr.tdd.kata.bankaccount.domain.model.OperationType;
import fr.tdd.kata.bankaccount.domain.model.Transaction;
import fr.tdd.kata.bankaccount.domain.ports.TransactionRepository;
import fr.tdd.kata.bankaccount.utils.DateHelper;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTransactionRepositoryTest {

	private static final String TODAY = "24/10/2022";

	private TransactionRepository transactionRepository;
	@Mock private DateHelper dateHelper;

	@Before
	public void initialise() {
		given(dateHelper.getTodayAsString()).willReturn(TODAY);
		transactionRepository = new InMemoryTransactionRepository(dateHelper);
	}

	@Test
	public void 
	should_create_a_transaction() {
		BigDecimal amount = new BigDecimal(100.00);
		transactionRepository.addTransaction(amount, OperationType.DEPOSIT);
		List<Transaction> transactions = transactionRepository.getTransactions();
		Transaction transaction = new Transaction(TODAY, amount, OperationType.DEPOSIT);
		assertThat(transactions.size(), is(1));
		assertThat(transactions.get(0), is(transaction));
	}
	
}
