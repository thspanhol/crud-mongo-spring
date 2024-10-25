FROM gradle AS build

COPY src/ app/src
COPY build.gradle /app

WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:21

COPY --from=build /app/build/libs/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
