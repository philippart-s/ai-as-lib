#!/bin/bash

bat -P -r 8: $(basename "$0")

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot with "What is OVHcloud?"
python blocking-chatbot-without-fwk.py