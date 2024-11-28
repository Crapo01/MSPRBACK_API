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
spring.datasource.url=jdbc:mysql://localhost:3306/"db name"
spring.datasource.username= "db username here"
spring.datasource.password= "db password here"  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= true


//auto create tables  
spring.jpa.hibernate.ddl-auto=update