package com.alpha.benchmark_api.loader;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.model.Edge;

@Component
public class CognoDatasetLoader {

    private static final int BATCH_SIZE = 1000;

    private final Driver driver;

    public CognoDatasetLoader(Driver driver) {
        this.driver = driver;
    }

    public void loadDataset(List<Edge> edges) {

        Instant start = Instant.now();

        try (Session session = driver.session()) {

            System.out.println("--------------------------------");
            System.out.println("Clearing Existing Graph...");
            System.out.println("--------------------------------");

            session.run("MATCH (n) DETACH DELETE n");

            for (int i = 0; i < edges.size(); i += BATCH_SIZE) {

                int end = Math.min(i + BATCH_SIZE, edges.size());

                List<Edge> batch = edges.subList(i, end);

                Map<String, Object> params = new HashMap<>();

                params.put("edges", batch.stream().map(edge -> {

                    Map<String, Object> map = new HashMap<>();

                    map.put("source", edge.getSource());
                    map.put("destination", edge.getDestination());

                    return map;

                }).toList());

                session.run("""
                        UNWIND $edges AS edge

                        MERGE (a:User {id: edge.source})

                        MERGE (b:User {id: edge.destination})

                        MERGE (a)-[:CONNECTED]->(b)
                        """, params);

                System.out.println("Inserted : " + end + " / " + edges.size());

            }

        }

        Instant end = Instant.now();

        System.out.println("--------------------------------");
        System.out.println("Dataset Loaded Successfully");
        System.out.println("Loading Time : "
                + Duration.between(start, end).toMillis()
                + " ms");
        System.out.println("--------------------------------");

    }
    public void verifyDataset() {

        try (Session session = driver.session()) {

            long nodeCount = session.run("""
                    MATCH (n)
                    RETURN count(n) AS count
                    """)
                    .single()
                    .get("count")
                    .asLong();

            long relationshipCount = session.run("""
                    MATCH ()-[r]->()
                    RETURN count(r) AS count
                    """)
                    .single()
                    .get("count")
                    .asLong();

            System.out.println("--------------------------------");
            System.out.println("Verification");
            System.out.println("Nodes         : " + nodeCount);
            System.out.println("Relationships : " + relationshipCount);
            System.out.println("--------------------------------");

        }

    }

}