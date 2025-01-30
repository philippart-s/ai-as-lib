#!/bin/bash

# Set some environment variables to use AI Endpoints
source ../.env

# Compile before launching
mvn clean compile
clear

bat -P -r 12: $(basename "$0")

# Call the chatbot with "My name is Stéphane." && "Do you remember what is my name?"
mvn -q -e exec:java -Dexec.useMavenLogger=false -Dexec.quietLogs=true -Dexec.mainClass="fr.wilda.ai.chatbot.MemoryStreamingChatbot"