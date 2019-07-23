# REST API Automation testing. REST Assured java.
* Setting up REST ASSURED environment for automation
* REST Assured methods
* REST Assured validations
* Methods to validate Json and xpath responses
* Optimizing scripts to Framwork standards
* Logging feature to log responses
* Migrating project to TESTNG framework
* Building Maven setup as build management tool 
* Creating BAT file to run whole project on single click
## What is Rest Assured? 
REST Assured is a Java DSL for simplifying testing of REST based services built on top 
of HTTP Builder. It supports `POST`, `GET`, `PUT`, `DELETE`, `OPTIONS`, `PATCH` and `HEAD` 
requests and can be used to validate and verify the response of these requests. 
## Creating a Project
You will need somewhere for your project to reside, create a directory somewhere and start a shell in that directory. 

<i><b> On your command line, execute the following Maven goal:</b></i>

    mvn archetype:generate \
        -DgroupId=com.mycompany.app \
        -DartifactId=my-app \
        -DarchetypeArtifactId=maven-archetype-quickstart \
        -DarchetypeVersion=1.4 \
        -DinteractiveMode=false

<i><b> Under this directory you will notice the following standard project structure.</b></i>

    my-app
    |-- pom.xml
    `-- src
        |-- main
        |   `-- java
        |       `-- com
        |           `-- mycompany
        |               `-- app
        |                   `-- App.java
        `-- test
            `-- java
                `-- com
                    `-- mycompany
                        `-- app
                            `-- AppTest.java
                            
The src/main/java directory contains the project source code, the src/test/java directory contains the test source, 
and the pom.xml file is the project's Project Object Model, or POM.

### The POM

The `pom.xml` file is the core of a project's configuration in Maven. 
It is a single configuration file that contains the majority of information required to build a project in just the way you want. 
The <b>POM</b> is huge and can be daunting in its complexity, but it is not necessary to understand all of the intricacies just yet to use it effectively. 

<b><i>This project's POM is:</b></i>
```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>com.mycompany.app</groupId>
      <artifactId>my-app</artifactId>
      <version>1.0-SNAPSHOT</version> 
      <properties>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
      </properties>
      <dependencies>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
        </dependency>
      </dependencies>
    </project>
```