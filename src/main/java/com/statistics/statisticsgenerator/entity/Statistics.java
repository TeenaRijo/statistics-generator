package com.statistics.statisticsgenerator.entity;

/**
 * @author Teena
 * 
 *         POJO that holds the Statistic details
 *
 */
public class Statistics {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public Statistics() {

		this.sum = 0.0;
		this.avg = 0.0;
		this.max = 0.0;
		this.min = 0.0;
		this.count = 0L;

	}

	public Statistics(Double sum, Double avg, Double max, Double min, long count) {
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	/**
	 * @return the sum
	 */
	public Double getSum() {
		return sum;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

	/**
	 * @return the avg
	 */
	public Double getAvg() {
		return avg;
	}

	/**
	 * @param avg
	 *            the avg to set
	 */
	public void setAvg(Double avg) {
		this.avg = avg;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

}
