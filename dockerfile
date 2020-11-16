FROM openjdk:8-jdk-alpine

WORKDIR /stockSimApi-build

COPY . /stockSimApi-build

RUN ls -al

RUN chmod +x gradlew

RUN ./gradlew build

WORKDIR /stockSimApi

RUN cp /stockSimApi-build/build/libs/*.jar /stockSimApi/app.jar

RUN rm -rf /stockSimApi-build

RUN ls -al

ENTRYPOINT [ "java", "-jar", "/stockSimApi/app.jar" ]
