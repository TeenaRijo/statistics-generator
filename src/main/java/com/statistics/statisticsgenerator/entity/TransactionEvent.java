package com.statistics.statisticsgenerator.entity;

/**
 * @author Teena
 *POJO that holds the transaction event 
 */
public class TransactionEvent {
	
	private Long timestamp;
	private Double amount;

	public TransactionEvent() {

	}

	public TransactionEvent(Long timestamp, Double amount) {
		this.timestamp = timestamp;
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [timestamp=" + timestamp + ", amount=" + amount + "]";
	}

}
