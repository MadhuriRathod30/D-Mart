
#Lightweight Java Image
FROM openjdk:17-jdk-alpine

#Set the working directory inside the container
WORKDIR /app

#Copy the jar file from your local machine to container
COPY target/AssociateService-0.0.1-SNAPSHOT.jar app.jar

#Expose the port your spring boot app runs upon
EXPOSE 8082

#Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
