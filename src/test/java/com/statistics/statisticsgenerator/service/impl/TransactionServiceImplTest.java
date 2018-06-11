package com.statistics.statisticsgenerator.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.statistics.statisticsgenerator.data.StoreTransaction;
import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;
import com.statistics.statisticsgenerator.util.Util;

/**
 * Test class for <TransactionServiceImpl>
 * @author Teena
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {
	
	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@MockBean
	StoreTransaction storeTransaction;
	
	/**
	 * Test for addTransactions() method- for successful saving
	 */
	@Test
	public void testAddTransactionsForSuccessfulSaving(){
		TransactionEvent transactionEvent1 = getTransactionEvent(24,1);
		boolean flag = transactionServiceImpl.addTransactions(transactionEvent1);
		assertEquals(true, flag);
	}
	/**
	 * Test for addTransactions() method -for unsuccessful saving
	 */
	@Test
	public void testaddTransactionsForUnsuccessfulSaving(){
		TransactionEvent transactionEvent1 = getTransactionEvent(24,70);
		boolean flag = transactionServiceImpl.addTransactions(transactionEvent1);
		assertEquals(false, flag);
	}
	/**
	 * Test for getStatisticsOfLastSixtySeconds() method 
	 */
	@Test
	public void testGetStatisticsOfLastSixtySeconds(){
		
		Statistics statistics = new Statistics(10.0, 5.0, 5.0, 5.0, 2L);
		Mockito.doReturn(statistics).when(storeTransaction).getStatisticsInLastSixtySeconds();
		Statistics statisticsResponse=transactionServiceImpl.getStatisticsOfLastSixtySeconds();
		assertNotNull(statisticsResponse);
		
	}
	/**
	 * method to populate a TransactionEvent
	 */
	private TransactionEvent getTransactionEvent(double amount, long timeInSeconds){
        TransactionEvent transactionEvent = new TransactionEvent(Util.timeBeforeSomeSeconds(timeInSeconds), amount);
        return transactionEvent;
	}

}
