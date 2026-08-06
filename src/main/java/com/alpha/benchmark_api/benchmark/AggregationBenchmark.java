package com.alpha.benchmark_api.benchmark;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.metrics.MetricsCalculator;

@Component
public class AggregationBenchmark extends AbstractBenchmark {

	public AggregationBenchmark(
	        Driver driver,
	        MetricsCalculator metricsCalculator) {

	    super(driver, metricsCalculator);
	}

    @Override
    protected void runQuery(Session session) {

        session.run("""
                MATCH (u:User)-[:CONNECTED]->(v)
                RETURN count(*) AS totalConnections
                """).list();

    }

    @Override
    protected String getBenchmarkName() {
        return "Aggregation";
    }
}