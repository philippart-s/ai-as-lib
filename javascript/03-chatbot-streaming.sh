#!/bin/bash

bat -P -r 8: ./03-chatbot-streaming.sh

# Set some environment variables to use AI Endpoints
source ../.env

# Call the chatbot
npm start --silent