# Deltahouse

A rewrite of an old project called [DeltaPi](https://github.com/vehagn/DeltaPi) for trading tokens for refreshments at
the student office.

## Getting started

Download Java 21 from e.g. [Azul](https://www.azul.com/downloads/?version=java-21-lts&package=jdk#zulu),
or using your favourite package manager. [SDKMan](https://sdkman.io/) is also an alternative.

To start the application using an in-memory H2 database run

```shell
./gradlew bootRun --args='--spring.profiles.active=local-h2'
```

The application will be available at [localhost:8080](http://localhost:8080).

Open API (swagger) documentation is available
at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).