package com.alpha.benchmark_api.dataset;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatasetExtractor {

    @Value("${dataset.directory}")
    private String datasetDirectory;

    @Value("${dataset.filename}")
    private String compressedFile;

    public void extract() {

        try {

            String extractedFile =
                    compressedFile.replace(".gz", "");

            FileInputStream fis =
                    new FileInputStream(
                            datasetDirectory + "/" + compressedFile);

            GZIPInputStream gis =
                    new GZIPInputStream(fis);

            FileOutputStream fos =
                    new FileOutputStream(
                            datasetDirectory + "/" + extractedFile);

            byte[] buffer = new byte[8192];

            int length;

            while ((length = gis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);

            }

            gis.close();
            fos.close();
            fis.close();

            System.out.println("--------------------------------");
            System.out.println("Dataset Extracted Successfully");
            System.out.println("--------------------------------");

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}