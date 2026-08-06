package com.alpha.benchmark_api.model;

import java.util.Collections;
import java.util.List;

public class BenchmarkResult {

    private String benchmarkName;

    private double averageTime;

    private long minimumTime;

    private long maximumTime;

    private long p50;

    private long p95;

    private double throughput;

    private int iterations;

	public String getBenchmarkName() {
		return benchmarkName;
	}

	public void setBenchmarkName(String benchmarkName) {
		this.benchmarkName = benchmarkName;
	}

	public double getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(double averageTime) {
		this.averageTime = averageTime;
	}

	public long getMinimumTime() {
		return minimumTime;
	}

	public void setMinimumTime(long minimumTime) {
		this.minimumTime = minimumTime;
	}

	public long getMaximumTime() {
		return maximumTime;
	}

	public void setMaximumTime(long maximumTime) {
		this.maximumTime = maximumTime;
	}

	public long getP50() {
		return p50;
	}

	public void setP50(long p50) {
		this.p50 = p50;
	}

	public long getP95() {
		return p95;
	}

	public void setP95(long p95) {
		this.p95 = p95;
	}

	public double getThroughput() {
		return throughput;
	}

	public void setThroughput(double throughput) {
		this.throughput = throughput;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	private BenchmarkResult calculateStatistics(List<Long> times) {

	    long min = Collections.min(times);

	    long max = Collections.max(times);

	    long average = (long) times.stream()
	            .mapToLong(Long::longValue)
	            .average()
	            .orElse(0);

	    BenchmarkResult result = new BenchmarkResult();

	    result.setBenchmarkName(getBenchmarkName());
	    result.setAverageTime(average);
	    result.setMinimumTime(min);
	    result.setMaximumTime(max);
	    result.setIterations(iterations);

	    return result;
	}

}