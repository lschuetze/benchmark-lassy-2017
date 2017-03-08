#!/bin/sh

JVM_ARGS="-d64 -Xms1024m -Xmx4048m"
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/

JCOP_HOME=lib/

docker run --rm -v "$PWD":/usr/src/myapp -w /usr/src/myapp openjdk:6\
  java -d64 -jar -ea lib/jcop.jar\
  -classpath lib -verbose -log\
  -sourcepath src BankBenchmark

# java $JVM_ARGS -classpath $JCOP_HOME/aspectjweaver.jar:bin\
#   -Djava.system.class.loader=org.aspectj.weaver.loadtime.WeavingURLClassLoader\
#   -Daj.class.path=%ASPECTPATH%:%CLASSPATH%\
#   -Daj.aspect.path=%ASPECTPATH%\
#   Harness BankBenchmark 1 1500
