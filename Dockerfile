FROM openjdk:11-jre-slim
VOLUME /tmp
MAINTAINER Nikita Ermolovsky
COPY src/main/docker/wrapper.sh .
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]