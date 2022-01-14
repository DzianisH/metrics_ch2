#!/bin/bash

./gradlew clean build
docker build -t dash39/metrics_app:latest .

cd metrics-demo-env
docker-compose up -d
cd ..
