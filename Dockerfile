FROM --platform=linux/x86_64 eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=*.jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
