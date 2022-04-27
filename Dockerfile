FROM adoptopenjdk/openjdk13:jdk-13.0.1_9-slim
WORKDIR /usr/src/datafram-app
RUN mvn compile
RUN mvn package
CMD ["java", "-jar", "./datafram-app.jar"]
