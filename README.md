[![Build Status](https://travis-ci.org/NicoleCarpenter/JavaHttpServer.svg?branch=master)](https://travis-ci.org/NicoleCarpenter/JavaHttpServer)

# Java Http Server

This is an HTTP server built with Java. External applications can connect to the server by using the jar file hosted on [Clojars](https://clojars.org).

## Dependencies

* [Java](https://java.com/en/download/)

## Running the Server

The server can be run using applications built to the required specifications of the server. In order to use the server in your project that uses Gradle, the Clojars repo will have to be included in your `build.gradle` file

```
repositories {
   maven { url 'https://clojars.org/repo' }
}

```

You will also have to include the [jar](https://clojars.org/org.clojars.ncarpenter/java-http-server) in your dependencies

```
dependencies {
  compile 'org.clojars.ncarpenter:java-http-server:1.0-SNAPSHOT'
}
```

## Running the Tests

In order to run the tests, [JUnit](http://junit.org/junit4/) should be included as a dependency. In your build file, you can include the `mavenCentral()` repo in your repositories section, which will allow you to add junit in the dependencies section

```
testCompile 'junit:junit:4.11'
```

This project encorporates Gradle as a build tool. Gradle provides a wrapper feature which allows you to run Gradle commands without having Gradle installed by using the `gradlew` command when a wrapper is present.

To run the unit tests from the root directory, type

```
./gradlew test
```