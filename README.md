# Selenium Test Automation Framework

## Tech Stack
- Java 21
- Selenium 4
- Cucumber
- TestNG
- Maven
- Allure-2.37.0 Reporting

## How to Run
mvn clean test

## Pre-Requisites
- Install Java version: 21.0.10
    Set Environment Variabale for JAVA_HOME example: C:\Program Files\Java\jdk-21.0.10
    Add to PATH Environment Variable: %JAVA_HOME%\bin
- Install Apache Maven 3.9.12
    Set Environment Variabale for MAVEN_HOME example: C:\Users\...\apache-maven-3.9.12\bin
    Add to PATH Environment Variable: %MAVEN_HOME%
- Add Maven certificate (repo.maven.apache.org) to keystore
    keytool -import -noprompt -trustcacerts -alias maven -file "C:\...\repo.maven.apache.org.crt" -keystore "C:\...\Java\jdk-21.0.10\lib\security\cacerts" -storepass changeit

## Allure Reporting
- Pre-Requisites
    Download Allure-2.37.0
    Add to PATH Environment Variable: C:\...\allure-2.37.0\bin
- Clean results folder (if needed)
    del allure-results\.
- Run test
    mvn clean test
- Generate report
    allure serve