FROM openjdk:22-ea-17-slim

WORKDIR /app

COPY /build/libs/*.jar app.jar

EXPOSE 8086

EXPOSE 9096

CMD ["java", "-jar", "app.jar"]