#os
FROM openjdk:17-openjdk-alpine

#working dir
WORKDIR /app

#COPY jar file
COPY target/PickingService-0.0.1-SNAPSHOT.jar app.jar

#expose port
EXPOSE 8084

#command to run jar file
ENTRYPOINT ("java" , "-jar" , "app.jar")