#!/bin/sh

JVM_ARGS="-d64 -Xms1024m -Xmx4048m"
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/

ant jar

java \
  $JVM_ARGS -jar build/rop-benchmark.jar \
  BankBenchmark 1 1500
