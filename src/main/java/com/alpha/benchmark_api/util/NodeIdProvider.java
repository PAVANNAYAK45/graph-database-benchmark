package com.alpha.benchmark_api.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.model.Edge;

@Component
public class NodeIdProvider {

    private final List<Long> nodeIds = new ArrayList<>();

    private final Random random = new Random();

    public void initialize(List<Edge> edges) {

        Set<Long> unique = new HashSet<>();

        for (Edge edge : edges) {

            unique.add(edge.getSource());
            unique.add(edge.getDestination());

        }

        nodeIds.clear();

        nodeIds.addAll(unique);

        System.out.println("--------------------------------");
        System.out.println("Unique Nodes : " + nodeIds.size());
        System.out.println("--------------------------------");

    }

    public long randomNode() {

        return nodeIds.get(

                random.nextInt(nodeIds.size())

        );

    }

}