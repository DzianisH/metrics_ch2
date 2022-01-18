##HOW TO BUILD
./gradlew clean build

docker build -t dzianish/metrics_app:latest .

##HOW TO RUN
cd metrics_demo_app

docker-compose up -d

##HOW TO STOP
cd metrics_demo_app

docker-compose down
