FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version
WORKDIR /usr/src/datafram-app
RUN mvn package
CMD ["java", "-jar", "./datafram-app.jar"]
