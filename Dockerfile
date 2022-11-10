#cmd : docker build -t token-service .
FROM eclipse-temurin:8-jdk-alpine
EXPOSE 9099
VOLUME /tmp
COPY target/*.jar token-service.jar
ENTRYPOINT ["java","-jar","/token-service.jar"]