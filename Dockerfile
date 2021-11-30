FROM openjdk:8
LABEL serviceName="datalake"
COPY ./target/dataIngress-0.1.0.jar /root/dataIngress-0.1.0.jar
WORKDIR /root
EXPOSE 8080
CMD ["java", "-jar", "dataIngress-0.1.0.jar"]