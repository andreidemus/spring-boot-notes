#Notes

Spring Boot demo application for managing text notes:
- frontend: [Angular](https://angularjs.org/) powered SPA
- backend: [Spring Boot](https://projects.spring.io/spring-boot/) powered REST endpoints
- database: [H2](http://www.h2database.com/) (in-memory, contains sample data)


##Building
To build, simply run `./mvnw clean package` from source root.

Or, if you have Maven installed, you may build with command `mvn clean package`


##Running
- Run command `java -jar target/notes-0.0.1-SNAPSHOT.jar`
- Then open in browser [http://localhost:8080](http://localhost:8080)

Default port 8080 may be changed with `--server.port` command line argument:

`java -jar  target/notes-0.0.1-SNAPSHOT.jar --server.port=9090`


##Logs
Logs are stored in `notes.log` in working directory.

