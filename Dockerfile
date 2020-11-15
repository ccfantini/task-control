FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/itau-task-control-0.0.1.jar
WORKDIR /app
ENTRYPOINT java -jar itau-task-control-0.0.1.jar