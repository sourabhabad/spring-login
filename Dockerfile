FROM openjdk:8
ADD target/login-spring.jar login-spring.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "login-spring.jar"]