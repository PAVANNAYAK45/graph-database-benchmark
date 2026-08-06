package com.alpha.benchmark_api.benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.metrics.MetricsCalculator;
import com.alpha.benchmark_api.model.BenchmarkResult;

@Component
public class TraversalBenchmark extends AbstractBenchmark {
	public TraversalBenchmark(
	        Driver driver,
	        MetricsCalculator metricsCalculator) {

	    super(driver, metricsCalculator);
	}

	@Override
	protected void runQuery(Session session) {

		session.run("""
				MATCH (u:User)-[:CONNECTED]->(v:User)
				RETURN u,v
				LIMIT 1000
				""").list();

	}

	@Override
	protected String getBenchmarkName() {
		return "Traversal";
	}

}