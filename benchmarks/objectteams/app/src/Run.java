public final class Run {
	private final String name;
	private final Class<? extends Benchmark> benchmarkSuite;
	private String optionalArgument;
	private int iterations;
	private int innerIterations;
	private long total;

	public Run(final String name) {
		this.name = name;
		benchmarkSuite = getSuiteFromName(name);
		iterations = 1;
		innerIterations = 1;
		optionalArgument = "";
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Benchmark> getSuiteFromName(final String name) {
		try {
			return (Class<? extends Benchmark>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void runBenchmark() {
		System.out.println("Starting " + name + " benchmark ...");

		try {
			doRuns(benchmarkSuite.newInstance());
		} catch (ReflectiveOperationException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		reportBenchmark();

		System.out.println();
	}

	private void measure(final Benchmark bench) {
		long startTime = System.nanoTime();
		if (!bench.innerBenchmarkLoop(innerIterations)) {
			throw new RuntimeException("Benchmark failed");
		}
		long endTime = System.nanoTime();
		long runTime = (endTime - startTime) / (long) 1000.0;

		printResult(runTime);

		total += runTime;
	}

	private void doRuns(final Benchmark bench) {
		for (int i = 0; i < iterations; ++i) {
			bench.setUp(innerIterations);
			bench.setUpOptional(optionalArgument);
			measure(bench);
		}
	}

	private void printResult(final long runTime) {
		System.out.println(name + ": iterations=1 runtime: " + runTime + "us");
	}

	private void reportBenchmark() {
		System.out.println(name + ": iterations=" + iterations + " average: " + (total / iterations) + "us total: "
				+ total + "us\n");
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setInnerIterations(int innerIterations) {
		this.innerIterations = innerIterations;
	}

	public void setOptionalArgument(String optionalArgument) {
		this.optionalArgument = optionalArgument;
	}

	public void printTotal() {
		System.out.println("Total Runtime: " + total + "us");
	}
}
