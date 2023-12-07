FROM openjdk:22-ea-17-slim

WORKDIR /app

COPY /build/libs/*.jar app.jar

EXPOSE 8084

EXPOSE 9094

CMD ["java", "-jar", "app.jar"]