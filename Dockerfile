FROM openjdk:17-jdk
ARG WAR_FILE=*.war
ADD ${WAR_FILE} mycafe.war
ENTRYPOINT ["java", "-jar", "mycafe.war"]