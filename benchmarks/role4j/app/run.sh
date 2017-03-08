#!/bin/sh
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home
JVM_ARGS="-d64 -Xms1024m -Xmx4048m"
mvn compile
mvn exec:exec \
  -Dexec.args="$JVM_ARGS -cp %classpath Harness BankBenchmark 1 100"
