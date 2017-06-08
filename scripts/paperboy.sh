#!/bin/bash

clear

export JAVA_HOME=/usr/lib/jvm/java-8-oracle


if [ $1 = "build" ]; then
	mvn clean compile assembly:single

elif [ $1 = "test" ]; then
	java -cp target/Lazarus-1.0-jar-with-dependencies.jar lazarus.misc.teste $@

fi 


echo "execution finished"
