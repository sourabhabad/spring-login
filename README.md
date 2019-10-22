## Spring Security Login Tutorial

1. `mvn clean`
2. `mvn clean install`
3. Go to the target folder and `java -jar login-0.0.1-SNAPSHOT.jar`


- `http://localhost:8080/registration`
- `http://localhost:8080/login`

#### Spring Boot on Docker connecting to MySQL Docker container


1. `docker pull mysql:5.6`
2. `docker run --name mysql-sql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=login -e MYSQL_USER=root -e MYSQL_PASSWORD=password -d mysql:5.6`
3. Change in application.properties to `spring.datasource.url = jdbc:mysql://mysql-sql:3306/login`
4. Create a Dockerfile for creating a docker image from the Spring Boot Application <br>
`FROM openjdk:8 `<br>`
ADD target/login-spring.jar login-spring.jar `<br>`
EXPOSE 8086 `<br>`
ENTRYPOINT ["java", "-jar", "login-spring.jar"]`
5. Using the Dockerfile create the Docker image. From the directory of Dockerfile <br>
  `docker build . -t login-spring`
6. `docker run -p 8086:8086 --name spring-docker --link spring-sql:mysql -d login-spring`
                                       

#### Important docker command 

1. `docker logs <mysql container>` to view logs of mysql
2. `docker logs <spring container>` to view logs of sprin-boot
