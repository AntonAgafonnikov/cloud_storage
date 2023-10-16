FROM openjdk:17-jdk-slim-buster

EXPOSE 8080

COPY target/cloud_storage-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]