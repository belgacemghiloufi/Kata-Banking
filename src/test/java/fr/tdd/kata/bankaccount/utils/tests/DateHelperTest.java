package fr.tdd.kata.bankaccount.utils.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import fr.tdd.kata.bankaccount.utils.DateHelper;

public class DateHelperTest {
	
	private static final String TODAY = "24/10/2022";

	@Test
	public void 
	should_return_todays_date_in_dd_MM_YYYY_format() {
		DateHelper dateHelper = new TestableDateHelper();
		String today = dateHelper.getTodayAsString();
		assertThat(today, is(TODAY));
	}
	
	private class TestableDateHelper extends DateHelper {
		@Override
		public LocalDate getToday() {
			return LocalDate.of(2022, 10, 24);
		}
	}
}
