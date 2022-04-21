## System requirement

- JDK >= 16
- Docker >= 19
- Docker Compose >= 1.25

## Available Scripts

```
docker-compose -f docker-compose-dev-storage-postgres.yml up
```

Ups storage

```
./mvnw -f webapp-pdb-s1/pom.xml spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
```

Runs backend app

```
./mvnw clean package -DskipTests
docker build -f docker/run.Dockerfile --build-arg JAR="webapp-pdb-s1/target/tge22-webapp-pdb-s1-0.0.1-SNAPSHOT.jar" -t tge22-webapp-pdb-s1:SNAPSHOT .
```

Builds backend app image

```
docker-compose -f docker-compose-dev-tge22-webapp-mdb-s1.yml up
docker-compose -f docker-compose-dev-tge22-webapp-pdb-s1.yml up
```

Ups storages and backend apps

> Next - open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
