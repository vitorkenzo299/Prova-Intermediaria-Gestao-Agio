FROM amazoncorretto:21

COPY target/prova-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
