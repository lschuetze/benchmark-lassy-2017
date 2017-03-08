object Harness extends App {
  def processArguments(args : Array[String]) : Run = {

    val run = new Run(args(0));

    if (args.length > 1) {
      run.setIterations(args(1).toInt)
    if (args.length > 2) {
        run.setInnerIterations(args(2).toInt)
      }
    }
    run
  }

  def printUsage() = {
    println("Harness [benchmark] [num-iterations [inner-iter]]");
    println();
    println("  benchmark      - benchmark class name ");
    println("  iterations     - number of times to execute benchmark, default: 1");
    println("  inner-iter     - number of times the benchmark is executed in an inner loop, ");
    println("                   which is measured in total, default: 1");
  }

  if (args.length < 2) {
    printUsage()
    System.exit(1)
  }

  val run = processArguments(args)
  run.runBenchmark
  run.printTotal
}
