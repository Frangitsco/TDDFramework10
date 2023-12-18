# Appium Page Object Model Framework

## Overview
This framework is built for automating mobile applications using Appium with the Page Object Model design pattern. It's set up to test the Sauce Lab's demo mobile app.

## Technologies and Tools
- **Eclipse**: IDE for development
- **Appium**: Mobile Automation library
- **Maven**: Build automation tool
- **Java**: Programming language
- **TestNG**: Test Management library
- **JSON/Excel**: For test data management
- **XML**: For managing static text
- **Git**: For version control

## Framework Best Practices
- Code reusability and readability
- Scalability (supports multiple test classes)
- Use of explicit waits
- Abstraction layers for UI commands and test data
- Exception handling
- Parameterization with TestNG and config properties

## Key Features
- **Programmatic Start of Appium Server**: The framework is configured to start the Appium server programmatically, enhancing the ease of running tests without manual intervention.
- **Test Cases**:
  1. Login with wrong username and right password
  2. Login with correct username and wrong password
  3. Login with correct credentials