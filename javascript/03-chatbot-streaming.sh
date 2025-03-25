#!/bin/bash

clear

bat -P -r 10: $(basename "$0")

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot with "What is OVHcloud?"
npm start --silent