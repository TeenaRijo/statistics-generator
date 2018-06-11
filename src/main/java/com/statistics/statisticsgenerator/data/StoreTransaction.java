package com.statistics.statisticsgenerator.data;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.DoubleStream;

import com.google.common.collect.Multimaps;
import com.google.common.collect.TreeMultimap;
import com.statistics.statisticsgenerator.entity.Statistics;
import com.statistics.statisticsgenerator.entity.TransactionEvent;

/**
 * @author Teena
 * 
 *  A singleton class which holds the data structure and performs
 *         several operations on it
 */
public class StoreTransaction {

	private static StoreTransaction storeTransaction;

	//private static TreeMap<Long, Double> transactionMap = new TreeMap<Long, Double>();
	private static TreeMultimap<Long, Double> transactionMap = TreeMultimap.create();

	private StoreTransaction() {
	}

	public static StoreTransaction getInstance() {
		initObject();
		return storeTransaction;
	}

	private static void initObject() {
		synchronized (StoreTransaction.class) {
			if (storeTransaction == null) {
				storeTransaction = new StoreTransaction();
			}
		}
	}

	/**
	 * @param transactionEvent
	 *            To store the transaction in the map
	 */
	public void storeTransaction(TransactionEvent transactionEvent) {
		transactionMap.put(transactionEvent.getTimestamp(), transactionEvent.getAmount());
	}

	/**
	 * @return Statistics retrieves the Statistics for last 60 seconds
	 */
	public Statistics getStatisticsInLastSixtySeconds() {
		Statistics statistics;
		Long lastSixtySeconds = System.currentTimeMillis() - 60000L;
		System.out.println("size:"+transactionMap.size());
		if (transactionMap != null && !transactionMap.isEmpty()) {
			Long floorTimestamp = (transactionMap.keySet().floor(lastSixtySeconds)) != null
					? transactionMap.keySet().floor(lastSixtySeconds) : 0L;
			// Removing Transactions older than 60s, if there are more than one value
			if (!floorTimestamp.equals(0L) && !transactionMap.keySet().first().equals(floorTimestamp)) {
				transactionMap.asMap().subMap(transactionMap.keySet().first(), true, floorTimestamp, true).clear();
			}

			DoubleSummaryStatistics doubleSummaryStatistics = transactionMap.entries().parallelStream()
					.map(entry -> entry.getValue()).flatMapToDouble((x) -> DoubleStream.of(x.doubleValue()))
					.summaryStatistics();
			statistics = new Statistics();
			statistics.setAvg(doubleSummaryStatistics.getAverage());
			statistics.setCount(doubleSummaryStatistics.getCount());
			statistics.setMax(doubleSummaryStatistics.getMax());
			statistics.setMin(doubleSummaryStatistics.getMin());
			statistics.setSum(doubleSummaryStatistics.getSum());
		} else {
			statistics = new Statistics(0d, 0d, 0d, 0d, 0L);
		}
		return statistics;
	}

}
