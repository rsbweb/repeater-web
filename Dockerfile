FROM openjdk:17-jdk-slim
COPY jar/repeater-web-0.0.1-SNAPSHOT.jar repeater-web-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/repeater-web-0.0.1-SNAPSHOT.jar"]