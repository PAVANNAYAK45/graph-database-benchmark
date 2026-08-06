package com.alpha.benchmark_api.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfig {

    @Value("${cognodb.uri}")
    private String uri;

    @Value("${cognodb.username}")
    private String username;

    @Value("${cognodb.password}")
    private String password;

    @Bean
    public Driver driver() {

        System.out.println("================================");
        System.out.println("Creating CognoDB Driver");
        System.out.println("URI      : " + uri);
        System.out.println("USERNAME : " + username);
        System.out.println("================================");

        return GraphDatabase.driver(
                uri,
                AuthTokens.basic(username, password)
        );
    }
}