#!/usr/bin/env bash

# TODO - ensure `target/classes` exists (creating it if it doesn't)
if [ ! -d target/classes ] ; then
	mkdir -p target/classes
fi
# TODO - compile all the Java files within the project and output them into `target/classes`
javac -d ./target/classes -classpath ./src/main/java/ ./src/main/java/edu/nyu/cs9053/homework2/*.java 
