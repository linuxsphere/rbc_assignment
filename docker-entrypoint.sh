#!/bin/bash

set -e
echo "Starting application"
java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar  -Djdk.tls.client.protocols="TLSv1.2" -Dhttps.protocols="TLSv1.2"