FROM openjdk:8-jdk-alpine
COPY ./target/spring-boot-nodep-mybatis-example-3.1.0.1-SNAPSHOT-exec.jar /usr/src/example/
WORKDIR /usr/src/example
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-nodep-mybatis-example-3.1.0.1-SNAPSHOT-exec.jar"]