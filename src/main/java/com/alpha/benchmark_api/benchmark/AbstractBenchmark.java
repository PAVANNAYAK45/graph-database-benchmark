package com.alpha.benchmark_api.benchmark;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

import com.alpha.benchmark_api.metrics.MetricsCalculator;
import com.alpha.benchmark_api.model.BenchmarkResult;

public abstract class AbstractBenchmark {

    protected final Driver driver;
    protected final MetricsCalculator metricsCalculator;

    protected static final int WARMUP = 5;
    protected static final int ITERATIONS = 20;

    public AbstractBenchmark(
            Driver driver,
            MetricsCalculator metricsCalculator) {

        this.driver = driver;
        this.metricsCalculator = metricsCalculator;
    }

    public BenchmarkResult execute() {

        System.out.println("--------------------------------");
        System.out.println(getBenchmarkName() + " Benchmark Started");
        System.out.println("--------------------------------");

        try (Session session = driver.session()) {

            // Warmup
            for (int i = 0; i < WARMUP; i++) {
                runQuery(session);
            }

            List<Long> executionTimes = new ArrayList<>();

            for (int i = 1; i <= ITERATIONS; i++) {

                long start = System.nanoTime();

                runQuery(session);

                long end = System.nanoTime();

                long time = (end - start) / 1_000_000;

                executionTimes.add(time);

                System.out.println("Iteration " + i + " : " + time + " ms");
            }

            return metricsCalculator.calculate(
                    getBenchmarkName(),
                    executionTimes,
                    ITERATIONS
            );
        }
    }

    protected abstract void runQuery(Session session);

    protected abstract String getBenchmarkName();
}