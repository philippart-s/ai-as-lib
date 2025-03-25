#!/bin/bash

clear

bat -P -r 10: $(basename "$0")

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot with "What is OVHcloud?"
python blocking-chatbot-langchain.py
