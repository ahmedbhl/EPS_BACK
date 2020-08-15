# [BACK_END] - Educational Personal Space 

A learning platform designed to provide educators, administrators and learners with a single robust, secure and integrated system to create personalised learning environments.

[![github](https://badgen.net/badge/icon/github?icon=github&label)](https://github.com/ahmedbhl/EPS_BACK)
[![Java](https://badgen.net/badge/Java/version%208/red)](https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html)
[![Spring](https://badgen.net/badge/Spring%20Boot/v%202.0.6/green)](https://start.spring.io/)
[![Hibernate](https://badgen.net/badge/Hibernate/version%205.2/orange)](https://hibernate.org/)
[![Liquibase](https://badgen.net/badge/Liquibase/v%203.5.5/cyan)](https://www.liquibase.org/get-started)
[![MySQL](https://badgen.net/badge/MySQL/version%205/orange)](https://www.mysql.com)
[![DropBox](https://badgen.net/badge/DropBox/version%202.1.1/blue)](https://www.dropbox.com/developers/documentation/http/documentation)
[![heroku](https://badgen.net/badge/Deploy/Heroku/blue)](https://heroku.com/deploy)
[![Demo](https://badgen.net/badge/Demo/ClicMe/green)](https://eps-project-319b4.firebaseapp.com/)

## Development

### Install the project and Running Locally

Make sure you have Java and Maven installed. Also, install the Heroku CLI.

```console
$ git clone https://github.com/ahmedbhl/EPS_BACK.git
$ cd EPS_BACK
$ mvn clean install
$ heroku local:start
```
Navigate to `http://localhost:8080/swagger-ui.html` or `http://localhost:5000/swagger-ui.html` for see all the documentation of the apis. The app will automatically reload if you change any of the source files due the DevTools.

## Deploying to Heroku

To deploy this for free on Heroku, click this button:

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

Then set the `BACKEND_URL` variable in our example apps to your Heroku URL (it'll be in the format https://epscedro-back.herokuapp.com/).

Happy testing!

## Dependencies

The following open source projects were used for the development of this service:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Spring Security](https://spring.io/projects/spring-security)
- [Hibenrate](https://hibernate.org/)
- [Liquibase](https://www.liquibase.org/get-started)
- [Swagger 2](https://swagger.io/)
- [DropBox](https://www.dropbox.com/developers/documentation/http/documentation)
- [heroku](https://heroku.com/deploy)
- [Lombok](https://projectlombok.org/)


## More details

For more information about the application and to see the front-End part, you can click on this link : [EPS_Front](https://github.com/ahmedbhl/EPS_FRONT).

![screenshot](https://github.com/ahmedbhl/EPS_BACK/blob/master/swagger.png?raw=true)
