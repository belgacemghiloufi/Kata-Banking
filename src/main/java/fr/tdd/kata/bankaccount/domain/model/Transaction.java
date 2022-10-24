package fr.tdd.kata.bankaccount.domain.model;

import java.math.BigDecimal;

public class Transaction {

	private String date;
	private BigDecimal amount;
	private OperationType operationType;

	public Transaction(String date, BigDecimal amount, OperationType operationType) {
		this.date = date;
		this.amount = amount;
		this.operationType = operationType;
	}

	public String getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (operationType != other.operationType) {
			return false;
		}
		return true;
	}

}
