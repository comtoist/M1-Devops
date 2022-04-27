FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version
COPY pom.xml /usr/src/datafram-app/pom.xml
WORKDIR /usr/src/datafram-app
RUN mvn -B package --file pom.xml
CMD ["java", "-jar", "./datafram-app.jar"]
