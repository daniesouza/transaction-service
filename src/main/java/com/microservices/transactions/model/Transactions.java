package com.microservices.transactions.model;

import javax.validation.constraints.NotNull;

public class Transactions {

	@NotNull(message = "amount não pode ser nulo!")
	private Double amount;

	@NotNull(message = "timestamp não pode ser nulo!")
	private Long timestamp;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
