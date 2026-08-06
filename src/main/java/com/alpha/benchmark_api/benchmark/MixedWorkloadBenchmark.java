package com.alpha.benchmark_api.benchmark;

import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.metrics.MetricsCalculator;
import com.alpha.benchmark_api.util.NodeIdProvider;

@Component
public class MixedWorkloadBenchmark extends AbstractBenchmark {

    private final NodeIdProvider nodeIdProvider;

    public MixedWorkloadBenchmark(
            Driver driver,
            MetricsCalculator metricsCalculator,
            NodeIdProvider nodeIdProvider) {

        super(driver, metricsCalculator);

        this.nodeIdProvider = nodeIdProvider;
    }

    @Override
    protected void runQuery(Session session) {

        long nodeId = nodeIdProvider.randomNode();

        // Lookup
        session.run("""
                MATCH (u:User {id:$id})
                RETURN u
                """,
                Map.of("id", nodeId)).list();

        // Traversal
        session.run("""
                MATCH (u:User {id:$id})-[:CONNECTED]->(v)
                RETURN v
                LIMIT 100
                """,
                Map.of("id", nodeId)).list();

        // Aggregation
        session.run("""
                MATCH (:User)-[:CONNECTED]->()
                RETURN count(*) AS total
                """).list();

    }

    @Override
    protected String getBenchmarkName() {
        return "Mixed Workload";
    }

}