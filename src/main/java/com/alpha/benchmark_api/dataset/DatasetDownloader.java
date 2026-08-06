package com.alpha.benchmark_api.dataset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatasetDownloader {

    @Value("${dataset.url}")
    private String datasetUrl;

    @Value("${dataset.directory}")
    private String datasetDirectory;

    @Value("${dataset.filename}")
    private String datasetFilename;

    public void downloadDataset() {

        try {

            File directory = new File(datasetDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            File dataset = new File(directory, datasetFilename);

            if (dataset.exists()) {

                System.out.println("--------------------------------");
                System.out.println("Dataset already exists.");
                System.out.println(dataset.getAbsolutePath());
                System.out.println("--------------------------------");

                return;
            }

            System.out.println("Downloading dataset...");

            URL url = new URL(datasetUrl);

            try (InputStream input = url.openStream();
                 FileOutputStream output = new FileOutputStream(dataset)) {

                byte[] buffer = new byte[8192];

                int bytesRead;

                while ((bytesRead = input.read(buffer)) != -1) {

                    output.write(buffer, 0, bytesRead);

                }

            }

            System.out.println("--------------------------------");
            System.out.println("Dataset downloaded successfully.");
            System.out.println(dataset.getAbsolutePath());
            System.out.println("--------------------------------");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}