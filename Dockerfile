FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/*.jar /app/algafood.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "algafood.jar"]
