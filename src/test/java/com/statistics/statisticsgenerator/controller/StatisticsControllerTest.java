/**
 * 
 */
package com.statistics.statisticsgenerator.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;
import com.statistics.statisticsgenerator.service.TransactionService;
import com.statistics.statisticsgenerator.util.Util;

/**
 * Test class for <StatisticsController>
 * @author Teena
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsControllerTest {
	
	@Autowired
	StatisticsController statisticsController;
	
	@MockBean
	TransactionService transactionService;
	
	/**
	 * Test for saveTransactions() method for a successful save
	 */
	@Test
	public void testSaveTransactions(){
		 TransactionEvent transaction = getTransactionEvent(100.0,2);
		Mockito.doReturn(true).when(transactionService).addTransactions(transaction);
		ResponseEntity<TransactionEvent> response = statisticsController.saveTransactions(transaction);
		assertEquals(201,response.getStatusCode().value());
	}
	/**
	 * Test for saveTransactions() method for an unsuccessful save
	 */
	@Test
	public void testSaveTransactionsForOldTransactions(){
		 TransactionEvent transaction = getTransactionEvent(100.0,2);
		Mockito.doReturn(false).when(transactionService).addTransactions(transaction);
		ResponseEntity<TransactionEvent> response = statisticsController.saveTransactions(transaction);
		assertEquals(204,response.getStatusCode().value());
	}
	
	/**
	 * Test for getTransactions() method 
	 */
	@Test
	public void testGetTransactions(){
		Statistics statistics = new Statistics(10.0, 5.0, 5.0, 5.0, 2L);
		Mockito.doReturn(statistics).when(transactionService).getStatisticsOfLastSixtySeconds();
		ResponseEntity<Statistics> response = statisticsController.getTransactions();
		assertEquals(200,response.getStatusCode().value());
	}
	/**
	 * method to populate a TransactionEvent
	 */
	private TransactionEvent getTransactionEvent(double amount, long timeInSeconds){
        TransactionEvent transactionEvent = new TransactionEvent(Util.timeBeforeSomeSeconds(timeInSeconds), amount);
        return transactionEvent;
	}
	
}
