#!/bin/bash

echo "Installing Java"
apt-get update
apt-get install -y openjdk-11-jdk

echo "Running build.sh"
mvn clean package
echo "Build completed"
