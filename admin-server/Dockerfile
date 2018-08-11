FROM openjdk:latest
EXPOSE 8700
RUN mkdir /opt/application
COPY ./target/adminserver-0.0.1-SNAPSHOT.jar /opt/application
CMD ["java","-jar","/opt/application/adminserver-0.0.1-SNAPSHOT.jar"]