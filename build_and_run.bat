CALL ./gradlew clean build
CALL docker build -t dash39/metrics_app:latest .

CALL cd metrics-demo-env
CALL docker-compose up -d
CALL cd ..