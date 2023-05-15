FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} microservicios-0.0.1-SNAPSHOT.jar
ADD  ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/microservicios-0.0.1-SNAPSHOT.jar"]