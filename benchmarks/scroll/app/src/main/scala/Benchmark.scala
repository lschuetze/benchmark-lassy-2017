abstract class Benchmark {

  def benchmark() : Object
  def verifyResult(result : Object) : Boolean
  def setUp(innerIterations : Int) : Boolean

  def innerBenchmarkLoop(innerIterations : Int) : Boolean = {
    for (i <- 1 to innerIterations) {
      if (!verifyResult(benchmark())) {
        return false
      }
    }
    return true
  }
}
