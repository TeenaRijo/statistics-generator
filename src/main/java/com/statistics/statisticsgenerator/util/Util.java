package com.statistics.statisticsgenerator.util;

import java.time.Instant;

/**
 * @author Teena
 *
 */
public class Util {

		/**
		 * @param timestamp
		 * @return boolean
		 * to check whether a given transaction is within last 60 seconds
		 */
		public static boolean isWithinLastSixtySeconds(Long timestamp) {
			
			return timestamp <= System.currentTimeMillis() && 
					(System.currentTimeMillis()-timestamp) <= 60000 ;
		}
		
		/**
		 * method to generate time before a given time(seconds)
		 * @param time
		 * @return
		 */
		public static long timeBeforeSomeSeconds(long time){
			return Instant.now().minusSeconds(time).toEpochMilli();
	}

	}

