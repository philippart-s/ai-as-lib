#!/bin/bash

bat -P -r 11: $(basename "$0")

# Set some environment variables to use AI Endpoints
source ../.env

# Launch Quarkus in dev mode
quarkus dev -Denable-native-access=ALL-UNNAMED -Dsun-misc-unsafe-memory-access=allow
