FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/incidents-1.0.0.jar /app/incidents.jar
CMD ["java","-jar","incidents.jar"]