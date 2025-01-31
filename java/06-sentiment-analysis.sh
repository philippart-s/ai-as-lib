#!/bin/bash

# Set some environment variables to use AI Endpoints
source ../.env

# Compile before launching
mvn clean compile
clear

bat -P -r 12: $(basename "$0")

# Call the chatbot with "I love Java"
mvn -e -q exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.ai.nlp.SentimentsAnalysis"