FROM openjdk:6
MAINTAINER Lars Schütze <lars.schuetze@tu-dresden.de>

ENV JCOP_HOME /usr/src/app/lib

COPY . /usr/src/app
WORKDIR /usr/src/app

# Build the application
RUN \
  java -d64 -jar -ea lib/jcop.jar\
   -target 1.5 -source 1.5 -classpath lib -log -verbose\
   -sourcepath src -d bin Harness
