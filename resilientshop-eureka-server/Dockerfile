FROM eclipse-temurin:17-jre-alpine
LABEL authors="André Garcia"
WORKDIR /app
COPY target/*.jar /app/resilientshop-eureka-server.jar
ENTRYPOINT ["java","-jar","resilientshop-eureka-server.jar"]
EXPOSE 8761
