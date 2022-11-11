FROM ubuntu:20.04

RUN apt-get -y update && apt-get install -y tzdata
RUN apt-get install -y openjdk-11-jdk
COPY ./target/kata-academy-third-block-0.0.1-SNAPSHOT.jar /home/web-app.jar

ENTRYPOINT ["java", "-jar", "/home/web-app.jar"]

EXPOSE 8081