FROM eclipse-temurin:21-jre-alpine
LABEL authors="André Garcia"
WORKDIR /app
COPY target/*.jar /app/resilientshop-api-gateway.jar
ENTRYPOINT ["java","-jar","resilientshop-api-gateway.jar"]
EXPOSE 8763