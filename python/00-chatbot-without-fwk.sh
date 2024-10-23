#!/bin/bash

bat -P -r 8: ./00-chatbot-langchain.sh

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot
python blocking-chatbot-without-fwk.py