# Zippopotam API Automation Framework

A Java-based API automation framework developed to test the Zippopotam.us postal code API.

This project was created as part of a technical coding assessment and demonstrates API automation practices including reusable API clients, data-driven testing, response validation, JSON schema validation, POJO deserialization, logging, and automated test execution.

## Application Under Test

Zippopotam.us provides location information based on country and postal code.

### Endpoint

GET

`https://api.zippopotam.us/{country}/{postal-code}`

### Parameters

| Parameter | Description | Example |
|---|---|---|
| Country | ISO country code | `us` |
| Postal Code | Postal/ZIP code supported by the country | `90210` |

---

## Tech Stack

- Java 21
- Maven
- REST Assured
- TestNG
- Jackson
- JSON Schema Validator
- Log4j2
- Allure
- IntelliJ IDEA

---

## Framework Features

- REST API automation using REST Assured
- Reusable API client architecture
- Page Object-style separation for API components
- Data-driven testing using TestNG `@DataProvider`
- Positive and negative API scenarios
- JSON response validation
- JSON Schema contract validation
- POJO-based response deserialization with Jackson
- Response status and content-type validation
- Response field validation
- Logging with Log4j2
- Allure test reporting
- Maven-based test execution
- TestNG suite configuration
- Clean separation of tests, clients, models, test data, and configuration

---

## Project Structure

```text
Zippopotam-Task
│
├── pom.xml
├── testng.xml
├── README.md
├── .gitignore
│
├── src
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── BaseTest.java
│       │   │
│       │   ├── clients
│       │   │   └── ZippopotamClient.java
│       │   │
│       │   ├── data
│       │   │   └── ZippopotamTestData.java
│       │   │
│       │   ├── models
│       │   │   ├── Place.java
│       │   │   └── ZippopotamResponse.java
│       │   │
│       │   └── tests
│       │       ├── ZippopotamPositiveTest.java
│       │       ├── ZippopotamNegativeTest.java
│       │       └── ZippopotamContractTest.java
│       │
│       └── resources
│           ├── schemas
│           │   └── zippopotam-response-schema.json
│           ├── allure.properties
│           ├── config.properties
│           └── log4j2.xml
│
└── target/