FROM eclipse-temurin:17-jdk-alpine
LABEL authors="André Garcia"
WORKDIR /app
COPY target/*.jar /app/resilientshop-authentication.jar
ENTRYPOINT ["java","-jar","resilientshop-authentication.jar"]
