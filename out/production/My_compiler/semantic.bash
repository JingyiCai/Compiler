#!/bin/bash 

set -e
cd "$(dirname "$0")"
cat > program.in
java -classpath ./lib/antlr-4.7-complete.jar:./bin My.Main program.in
