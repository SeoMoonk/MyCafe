FROM openjdk:17-jdk
LABEL authors="seomoon"
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} mycafe.war
ENTRYPOINT ["java", "-jar", "mycafe.jar"]