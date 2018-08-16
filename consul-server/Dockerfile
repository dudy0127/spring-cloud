FROM openjdk:latest
EXPOSE 8501
RUN mkdir /opt/application
COPY ./target/consul-server-0.0.1-SNAPSHOT.jar /opt/application
CMD ["java","-jar","/opt/application/consul-server-0.0.1-SNAPSHOT.jar"]