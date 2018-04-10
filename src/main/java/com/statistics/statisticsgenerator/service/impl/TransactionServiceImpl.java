package com.statistics.statisticsgenerator.service.impl;

import org.springframework.stereotype.Service;

import com.statistics.statisticsgenerator.data.StoreTransaction;
import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;
import com.statistics.statisticsgenerator.service.TransactionService;
import com.statistics.statisticsgenerator.util.Util;

/**
 * @author Teena
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/*
	 * This method adds the transaction to the map if it's timestamp within the
	 * last 60 seconds
	 */
	@Override
	public boolean addTransactions(TransactionEvent transaction) {
		boolean flag = false;
		if (transaction == null || transaction.getAmount() == null || transaction.getTimestamp() == null
				|| !Util.isWithinLastSixtySeconds(transaction.getTimestamp())) {
			return flag;
		} else {
			StoreTransaction.getInstance().storeTransaction(transaction);
			flag = true;
		}

		return flag;
	}

	/*
	 * This method retrieves the statistics of last sixty seconds
	 *
	 */
	@Override
	public Statistics getStatisticsOfLastSixtySeconds() {
		Statistics statistics = StoreTransaction.getInstance().getStatisticsInLastSixtySeconds();
		return statistics;
	}

}
