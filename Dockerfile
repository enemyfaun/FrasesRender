FROM eclipse-temurin:25-jdk-alpine
LABEL authors="Gerardo"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8084

ENTRYPOINT ["java","-jar","top","-b","app.jar"]