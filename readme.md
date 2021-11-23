### How to use this spring-boot project

- Install packages with `./gradlew build`
- Run `./gradlew bootRun` for starting the application (or use your IDE)
- Run `./gradlew test` to run the test for the application

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What I did to this project
- Refactoring the project to a Gradle base project
- Updated the readme file about  
- Add web layer test to the project
- Setup security config for the project
- Replaced system output with logger

#### My experience in Java

- I have 4 years experience in Java and I started to use Spring Boot alone with kotlin from last year.
- I'm not an expert in Spring Boot.But I'm be able to fix bug and resolve problem by document reading and source code analyzing. 
- I know Spring Boot to some extends and can it to meet project requirement.
