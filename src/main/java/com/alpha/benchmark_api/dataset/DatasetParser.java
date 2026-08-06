package com.alpha.benchmark_api.dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alpha.benchmark_api.model.Edge;

@Component
public class DatasetParser {

    @Value("${dataset.directory}")
    private String directory;

    public List<Edge> parse() {

        List<Edge> edges = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(directory + "/wiki-Vote.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] values = line.split("\\s+");

                long source = Long.parseLong(values[0]);
                long destination = Long.parseLong(values[1]);

                edges.add(new Edge(source, destination));

            }

            reader.close();

            System.out.println("--------------------------------");
            System.out.println("Dataset Parsed Successfully");
            System.out.println("Total Edges : " + edges.size());
            System.out.println("--------------------------------");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return edges;
    }
}