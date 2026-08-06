package com.alpha.benchmark_api.metrics;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.model.BenchmarkResult;

@Component
public class MetricsCalculator {

    public BenchmarkResult calculate(
            String benchmarkName,
            List<Long> executionTimes,
            int iterations) {

        Collections.sort(executionTimes);

        BenchmarkResult result = new BenchmarkResult();

        result.setBenchmarkName(benchmarkName);

        result.setIterations(iterations);

        result.setMinimumTime(executionTimes.get(0));

        result.setMaximumTime(
                executionTimes.get(executionTimes.size() - 1));

        double average =
                executionTimes.stream()
                        .mapToLong(Long::longValue)
                        .average()
                        .orElse(0);

        result.setAverageTime(average);

        result.setP50(
                executionTimes.get(executionTimes.size() / 2));

        result.setP95(
                executionTimes.get(
                        (int) (executionTimes.size() * 0.95)));

        double totalTime =
                executionTimes.stream()
                        .mapToLong(Long::longValue)
                        .sum();

        result.setThroughput(
                iterations / (totalTime / 1000.0));

        return result;

    }

}