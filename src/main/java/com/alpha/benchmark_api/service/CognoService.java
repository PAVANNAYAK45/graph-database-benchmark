package com.alpha.benchmark_api.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

@Service
public class CognoService {

    private final Driver driver;

    public CognoService(Driver driver) {
        this.driver = driver;
    }

    public void testConnection() {

        try (Session session = driver.session()) {

            int value = session.run("RETURN 1 AS value")
                    .single()
                    .get("value")
                    .asInt();

            System.out.println("================================");
            System.out.println("CognoDB Connected Successfully");
            System.out.println("Query Result : " + value);
            System.out.println("================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}