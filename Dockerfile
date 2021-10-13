FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash
ARG mariaPw # you could give this a default value as well
ENV SPRING_DATASOURCE_PASSWORD=${mariaPw}
ARG JAR_FILE=build/libs/gatewayDemo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]