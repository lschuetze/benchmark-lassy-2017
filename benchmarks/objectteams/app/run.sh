#!/bin/sh

OTRE_MIN=lib/otre_min.jar
OTREDYN_AGENT=lib/otredyn_agent.jar

ant jar -lib lib/ecotj-R-2.5.0-201606070953.jar

PROFILE=-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=filename=${PROF_FILE_NAME},settings=profiling-expensive -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=.

JVM_ARGS="-d64 -Xms1024m -Xmx4048m"

#mx -p $GRAAL_HOME vm -Xbootclasspath/a:$OTRE_MIN -javaagent:$OTREDYN_AGENT \
#    $JVM_ARGS -jar objectteams-benchmark.jar \
#    BankBenchmark 2000

/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/bin/java \
  -Xbootclasspath/a:$OTRE_MIN -javaagent:$OTREDYN_AGENT \
  $JVM_ARGS -jar build/objectteams-benchmark.jar \
  BankBenchmark 1 1500
