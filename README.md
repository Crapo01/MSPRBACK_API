# MSPRBACK_cms_reworked
java project, maven  
## spring initializer:

    Lombok 
    Spring Web 
    MySQL Driver SQL
    Spring Data JPA SQL

## application.properties

https://stackoverflow.com/questions/71518442/cannot-load-driver-class-jdbcmysql-localhost3306-mydatabase


spring.application.name=secured_api

spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://localhost:3306/"db_name"
spring.datasource.username= "db_username here"
spring.datasource.password= "db_password here"  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= true


//auto create tables  
spring.jpa.hibernate.ddl-auto=update

## ADDED VALIDATION

    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

## ADDED SPRING SECURITY JWT

SOURCE: 

for exceptions handling:  
https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac

for security implementation:  
https://www.bezkoder.com/spring-boot-jwt-authentication/  

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
    </dependency>

