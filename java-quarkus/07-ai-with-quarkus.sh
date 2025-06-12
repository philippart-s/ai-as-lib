#!/bin/bash

bat -P -r 11: $(basename "$0")

# Set some environment variables to use AI Endpoints
source ../.env

# To deactivate warning messages from Maven su to JDK24 usage (see https://issues.apache.org/jira/browse/MNG-8760 & https://github.com/google/guice/issues/1901)
export MAVEN_OPTS="--enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow"

# Launch Quarkus in dev mode
quarkus dev -Denable-native-access=ALL-UNNAMED -Dsun-misc-unsafe-memory-access=allow
