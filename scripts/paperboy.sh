#!/bin/bash

clear

export JAVA_HOME=/usr/lib/jvm/java-8-oracle


if [ $1 = "build" ]; then
	mvn clean compile assembly:single

elif [ $1 = "test" ]; then
	java -cp target/Paperboy-1-jar-with-dependencies.jar pb.main.Main $@

fi 


echo "execution finished"
