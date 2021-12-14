FROM gradle:7.3.1-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN printenv
RUN gradle build --no-daemon --scan -Dhttp.proxyHost=192.168.0.150 -Dhttp.proxyPort=8080 -Dhttps.proxyHost=192.168.0.150 -Dhttps.proxyPort=8080

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/kafka-consumer-1.0.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
