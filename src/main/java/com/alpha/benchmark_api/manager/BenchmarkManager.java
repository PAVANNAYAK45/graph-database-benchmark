package com.alpha.benchmark_api.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alpha.benchmark_api.benchmark.AggregationBenchmark;
import com.alpha.benchmark_api.benchmark.LookupBenchmark;
import com.alpha.benchmark_api.benchmark.MixedWorkloadBenchmark;
import com.alpha.benchmark_api.benchmark.TraversalBenchmark;
import com.alpha.benchmark_api.dataset.DatasetDownloader;
import com.alpha.benchmark_api.dataset.DatasetExtractor;
import com.alpha.benchmark_api.dataset.DatasetParser;
import com.alpha.benchmark_api.loader.CognoDatasetLoader;
import com.alpha.benchmark_api.metrics.MetricsCalculator;
import com.alpha.benchmark_api.model.BenchmarkResult;
import com.alpha.benchmark_api.model.Edge;
import com.alpha.benchmark_api.service.CognoService;
import com.alpha.benchmark_api.util.NodeIdProvider;

@Service
public class BenchmarkManager {

	private final CognoService cognoService;
	private final DatasetDownloader datasetDownloader;
	private final DatasetExtractor datasetExtractor;
	private final DatasetParser datasetParser;
	private final CognoDatasetLoader cognoDatasetLoader;
	private final TraversalBenchmark traversalBenchmark;
	private final NodeIdProvider nodeIdProvider;
	private final LookupBenchmark lookupBenchmark;
	private final AggregationBenchmark aggregationBenchmark;
	private final MixedWorkloadBenchmark mixedWorkloadBenchmark;
	private final MetricsCalculator metricsCalculator;

	public BenchmarkManager(
	        CognoService cognoService,
	        DatasetDownloader datasetDownloader,
	        DatasetExtractor datasetExtractor,
	        DatasetParser datasetParser,
	        CognoDatasetLoader cognoDatasetLoader,
	        TraversalBenchmark traversalBenchmark,
	        LookupBenchmark lookupBenchmark,
	        NodeIdProvider nodeIdProvider,MetricsCalculator metricsCalculator,AggregationBenchmark aggregationBenchmark,MixedWorkloadBenchmark mixedWorkloadBenchmark) {

	    this.cognoService = cognoService;
	    this.datasetDownloader = datasetDownloader;
	    this.datasetExtractor = datasetExtractor;
	    this.datasetParser = datasetParser;
	    this.cognoDatasetLoader = cognoDatasetLoader;
	    this.traversalBenchmark = traversalBenchmark;
	    this.lookupBenchmark = lookupBenchmark;
	    this.nodeIdProvider = nodeIdProvider;
	    this.aggregationBenchmark=aggregationBenchmark;
	    this.mixedWorkloadBenchmark=mixedWorkloadBenchmark;
	    this.metricsCalculator=metricsCalculator;
	}
	public void execute() {

	    System.out.println("========================================");
	    System.out.println("Graph Database Benchmark Started");
	    System.out.println("========================================");

	    // Connect to CognoDB
	    cognoService.testConnection();

	    // Prepare dataset (Run only if required)
	    datasetDownloader.downloadDataset();

	    datasetExtractor.extract();

	    List<Edge> edges = datasetParser.parse();

	    nodeIdProvider.initialize(edges);

	    // Uncomment only when loading is required
	    // cognoDatasetLoader.loadDataset(edges);

	    cognoDatasetLoader.verifyDataset();

	    // Execute Benchmarks
	    List<BenchmarkResult> results = new ArrayList<>();

	    results.add(traversalBenchmark.execute());

	    results.add(lookupBenchmark.execute());

	    results.add(aggregationBenchmark.execute());

	    results.add(mixedWorkloadBenchmark.execute());

	    // Print Results
	    System.out.println();
	    System.out.println("============= RESULTS =============");

	    for (BenchmarkResult result : results) {
	        System.out.println(result);
	    }

	    System.out.println("========================================");
	    System.out.println("Benchmark Completed");
	    System.out.println("========================================");
	}
	

}