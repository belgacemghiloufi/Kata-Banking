package fr.tdd.kata.bankaccount.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
	
	private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	public String getTodayAsString() {
		return getToday().format(DD_MM_YYYY);
	}

	protected LocalDate getToday() {
		return LocalDate.now();
	}

}
