#!/bin/bash
BASE_DIR=$(cd $(dirname $0)/..; pwd)
CLASSPATH="$BASE_DIR/conf:$BASE_DIR/lib/*"
java -cp "$CLASSPATH" org.opchan.PulsarProducer "$@"
