#FROM gradle:8.1.1-jdk17 as build-image
#WORKDIR app
#COPY --chown=gradle:gradle build.gradle .
#RUN gradle --no-daemon test
#
#COPY --chown=gradle:gradle src ./src
##
## Build the application.
##
#RUN gradle --no-daemon build
#RUN pwd
#RUN ls build/libs/

#FROM eclipse-temurin:17
#ARG JAR_FILE=app/build/libs/gateway-0.0.1-SNAPSHOT.jar
##COPY ${JAR_FILE} gateway-0.0.1-SNAPSHOT.jar
#COPY --from=build-image ${JAR_FILE} gateway-0.0.1-SNAPSHOT.jar
#
#ENTRYPOINT ["java", "-jar", "/gateway-0.0.1-SNAPSHOT.jar"]


FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} pagos-0.0.1-SNAPSHOT.jar
ADD  ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/pagos-0.0.1-SNAPSHOT.jar"]