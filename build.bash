set -e
cd "$(dirname "$0")"
mkdir -p bin
find ./ -name *.java | javac -d bin -classpath "lib/antlr-4.7-complete.jar" @/dev/stdin