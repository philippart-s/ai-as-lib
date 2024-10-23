#!/bin/bash

bat -P -r 8: ./01-chatbot-langchain.sh

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot
python blocking-chatbot-langchain.py

