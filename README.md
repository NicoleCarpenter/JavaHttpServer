[![Build Status](https://travis-ci.org/NicoleCarpenter/JavaHttpServer.svg?branch=master)](https://travis-ci.org/NicoleCarpenter/JavaHttpServer)

# Java Http Server

This is an HTTP server built with Java, adhering to FitNess [Cob Spec](https://github.com/8thlight/cob_spec) acceptance criteria. 

## Dependencies

* [Java](https://java.com/en/download/)

## Running the Server

From your desired file location, clone the repo

```
git clone git@github.com:NicoleCarpenter/JavaHttpServer.git

```

Then `cd` into the application's root directory

```
cd JavaHttpServer.git
``` 

From there, to run the application, 

```
java -jar <path to application/build/libs/JavaHttpServer.jar>
```

Additionally, flags for port number `-p` and root directory `-d` can be added to customize these options. The default port is `5000` and the default root is `public`. Here is an example implementing these options

```
java -jar <path to application/build/libs/JavaHttpServer.jar> -p 4000 -d alternateDirectory
```

## Running the Tests

This project encorporates Gradle as a build tool. Gradle provides a wrapper feature which allows you to run Gradle commands without having Gradle installed by using the `gradlew` command when a wrapper is present.

To run the tests from the root directory, type

```
./gradlew test
```