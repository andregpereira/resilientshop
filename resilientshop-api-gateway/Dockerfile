FROM eclipse-temurin:17-jdk-alpine
LABEL authors="André Garcia"
WORKDIR /app
COPY target/*.jar /app/resilientshop-api-gateway.jar
ENTRYPOINT ["java","-jar","resilientshop-api-gateway.jar"]
EXPOSE 8763