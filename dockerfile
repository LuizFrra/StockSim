FROM openjdk:11.0-jdk-slim as build

WORKDIR /stockSimApi-build

COPY . /stockSimApi-build

RUN ls -al

RUN chmod +x gradlew

RUN ./gradlew build

FROM openjdk:11-jre-slim as run

WORKDIR /stockSimApi

COPY --from=build /stockSimApi-build/build/libs/*.jar /stockSimApi/app.jar

RUN ls -al

ENTRYPOINT [ "java", "-jar", "/stockSimApi/app.jar" ]
