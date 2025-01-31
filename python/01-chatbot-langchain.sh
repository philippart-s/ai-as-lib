#!/bin/bash

# Set some environment variables to use AI Endpoints
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Call the chatbot with "What is OVHcloud?"
python blocking-chatbot-langchain.py

