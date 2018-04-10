
package com.statistics.statisticsgenerator.service;


import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;

/**
 * @author Teena
 * The service layer which helps to save the transactions 
 * and the fetch statistics
 */
public interface TransactionService {
	
	public boolean addTransactions(TransactionEvent transaction);
	
	public Statistics getStatisticsOfLastSixtySeconds();

	

}	
