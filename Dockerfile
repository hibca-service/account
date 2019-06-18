FROM openjdk:8
ADD target/account-0.0.1.jar account-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","account-0.0.1.jar"]
