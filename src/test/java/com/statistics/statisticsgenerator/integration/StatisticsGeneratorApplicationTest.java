/**
 * 
 */
package com.statistics.statisticsgenerator.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.statistics.statisticsgenerator.StatisticsGeneratorApplication;
import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;
import com.statistics.statisticsgenerator.util.Util;

import org.junit.Assert;
import org.junit.Before;

/**
 * This method tests the application
 * @author Teena
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StatisticsGeneratorApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
public class StatisticsGeneratorApplicationTest {
	
	 	@Value("${local.server.port}")
	    private int port;


	    private URL base;
	    private TestRestTemplate restTemplate;
		
		
		@Before
		public void setUp() throws MalformedURLException
		{
			 this.base = new URL("http://localhost:" + port + "/api/");
			 restTemplate = new TestRestTemplate();
		}
		
		/**
		 * Testing the Get transaction rest end point for 
		 */
		@Test
	    public void testGetTransactions() {
			
						
	        ResponseEntity<Statistics> response = restTemplate.getForEntity(base+ "/statistics", Statistics.class);
	        Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
	        
	    }
		/**
		 * Testing the save endpoint,Transaction within last sixty seconds
		 */
		@Test
	    public void testSaveTransactionsForCurrentTime() {
			
						
	        TransactionEvent transactionEvent = getTransactionEvent(100.0,2);
	        ResponseEntity<TransactionEvent> response = restTemplate.postForEntity(base+ "/transactions", transactionEvent,TransactionEvent.class);
	        
	        Assert.assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	        
	    }
		
		/**
		 * Testing the save endpoint,Transaction before last sixty seconds
		 */
		@Test
	    public void testSaveTransactionsBeforeLastSixtySeconds() {
			
			TransactionEvent transactionEvent = getTransactionEvent(100.0,70);
	        ResponseEntity<TransactionEvent> response = restTemplate.postForEntity(base+ "/transactions", transactionEvent,TransactionEvent.class);
	        
	        Assert.assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	        
	    }
		
		/**
		 * Testing the whole functionality,Transaction before last sixty seconds
		 */
		@Test
	    public void testFunctionality() throws InterruptedException {
			
						
	        TransactionEvent transactionEvent1 = getTransactionEvent(24,1);
	        TransactionEvent transactionEvent2 = getTransactionEvent(28,70);
	        TransactionEvent transactionEvent3 = getTransactionEvent(28,4);
	        TransactionEvent transactionEvent4 = getTransactionEvent(30,58);
	        
	        ResponseEntity<TransactionEvent> response1 = restTemplate.postForEntity(base+ "/transactions", transactionEvent1,TransactionEvent.class);
	        ResponseEntity<TransactionEvent> response2 = restTemplate.postForEntity(base+ "/transactions", transactionEvent2,TransactionEvent.class);
	        ResponseEntity<Statistics> response = restTemplate.getForEntity(base+ "/statistics", Statistics.class);
	        
	        Assert.assertEquals(1,response.getBody().getCount().intValue());
	        
	        ResponseEntity<TransactionEvent> response3 = restTemplate.postForEntity(base+ "/transactions", transactionEvent3,TransactionEvent.class);
	        ResponseEntity<TransactionEvent> response4 = restTemplate.postForEntity(base+ "/transactions", transactionEvent4,TransactionEvent.class);
	        Thread.sleep(5000);
	        ResponseEntity<Statistics> response5 = restTemplate.getForEntity(base+ "/statistics", Statistics.class);
	        
	        Assert.assertEquals(3,response5.getBody().getCount().intValue());
	        
	    }
		/**
		 * method to populate a TransactionEvent
		 */
		private TransactionEvent getTransactionEvent(double amount, long timeInSeconds){
	        TransactionEvent transactionEvent = new TransactionEvent(Util.timeBeforeSomeSeconds(timeInSeconds), amount);
	        return transactionEvent;
		}


}
