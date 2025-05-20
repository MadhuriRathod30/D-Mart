#os
FROM openjdk:17-jdk-alpine

#set working dir
WORKDIR /app

#copy jar to workdir
COPY target/OrderService-0.0.1-SNAPSHOT.jar app.jar

#Expose port
EXPOSE 8081

#defile executable command
ENTRYPOINT ("java", "-jar" , "app.jar")
