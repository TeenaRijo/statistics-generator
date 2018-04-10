package com.statistics.statisticsgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;
import com.statistics.statisticsgenerator.service.TransactionService;

/**
 * @author Teena
 *
 *The rest controller
 */
@RestController
@RequestMapping("/api")
public class StatisticsController {
	
	@Autowired
	TransactionService transactionService;

	@RequestMapping(path="/transactions",method=RequestMethod.POST,consumes="application/json")	
	public ResponseEntity<TransactionEvent> saveTransactions(@RequestBody TransactionEvent transaction){
		if( ! transactionService.addTransactions(transaction)){
			return ResponseEntity.status(204).build();
		}
		return ResponseEntity.status(201).build();
		
	}
	
	
	@GetMapping("/statistics")
	public ResponseEntity<Statistics> getTransactions()
	{
		Statistics statistics=transactionService.getStatisticsOfLastSixtySeconds();
		
		ResponseEntity<Statistics> response = new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
		return response;
	}
}
