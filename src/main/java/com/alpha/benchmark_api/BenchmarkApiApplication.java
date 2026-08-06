package com.alpha.benchmark_api;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alpha.benchmark_api.dataset.DatasetDownloader;
import com.alpha.benchmark_api.dataset.DatasetExtractor;
import com.alpha.benchmark_api.dataset.DatasetParser;
import com.alpha.benchmark_api.loader.CognoDatasetLoader;
import com.alpha.benchmark_api.manager.BenchmarkManager;
import com.alpha.benchmark_api.model.Edge;
import com.alpha.benchmark_api.service.CognoService;

@SpringBootApplication
public class BenchmarkApiApplication implements CommandLineRunner {

	private final BenchmarkManager benchmarkManager;

	public BenchmarkApiApplication(BenchmarkManager benchmarkManager) {
		this.benchmarkManager = benchmarkManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(BenchmarkApiApplication.class, args);
	}

	@Override
	public void run(String... args) {

		benchmarkManager.execute();

	}
}