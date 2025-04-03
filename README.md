# This project was generated by [spring initializr](https://start.spring.io/index.html)

- **Spring Boot**: 3.4.4 (*pom.xml*)
```xml
<parent>
    <version>3.4.4</version>
</parent>
```
- **Group**: hu.hazazs (*pom.xml*)
```xml
<groupId>hu.hazazs</groupId>
```
- **Artifact**: rest-api (*also defines the project's root folder*)  (*pom.xml*)
```xml
<artifactId>rest-api</artifactId>
```
- **Name**: RestApi (*RestApiApplication.java*)
```java
@SpringBootApplication
public class RestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
```
- **Description**: Demo project for Spring Boot with REST API  (*pom.xml*)
```xml
<description>Demo project for Spring Boot with REST API</description>
```
- **Package name**: hu.hazazs.rest.api (*RestApiApplication.java*)
```java
package hu.hazazs.rest.api;
```
- **Packaging**: Jar (*this is the default, so there is no explicit attribute in the ```pom.xml```*)
- **Java**: 17  (*pom.xml*)
```xml
<properties>
    <java.version>17</java.version>
</properties>
```

# Before the first commit

Since the files in the generated project come with LF line ending by default, we need to do some settings before the first commit.

### Setting up IntelliJ

Navigate to **Settings...** (*Ctrl+Alt+S*) → **Editor** → **Code Style**, and on the **General** tab change **Line separator** to
```Unix and macOS (\n)```
.

### Setting up Git

- Configure Git to ensure line endings in files you checkout are correct for Unix and macOS.
```bash
git config --global core.autocrlf false
```
- Remove ```.gitattributes``` from the project root folder

We also need to add the following lines to ```.gitignore```
- mvnw
- mvnw.cmd
- /.mvn