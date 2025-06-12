#!/bin/bash

# Set some environment variables to use AI Endpoints
source ../.env

# To deactivate warning messages from Maven su to JDK24 usage (see https://issues.apache.org/jira/browse/MNG-8760 & https://github.com/google/guice/issues/1901)
export MAVEN_OPTS="--enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow"

# Compile before launching
mvn clean compile
clear

bat -P -r 15: $(basename "$0")

# Call the chatbot with "I love Java"
mvn -e -q exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.ai.nlp.SentimentsAnalysis"