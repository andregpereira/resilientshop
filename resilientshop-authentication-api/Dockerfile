FROM eclipse-temurin:21-jre-alpine
LABEL authors="André Garcia"
WORKDIR /app
COPY target/*.jar /app/resilientshop-authentication.jar
ENTRYPOINT ["java","-jar","resilientshop-authentication.jar"]