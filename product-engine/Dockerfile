FROM openjdk:22-ea-17-slim

WORKDIR /app

COPY /build/libs/*.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "app.jar"]