# java-selenium-locators

A simple Java + Selenium project to practice and demonstrate different types of web element locators.

## Features
- Examples of Selenium locators: id, name, className, cssSelector, xpath, linkText, partialLinkText, tagName.
- TestNG-based test execution.
- Organized test classes for each page.

## Requirements
- Java 21+
- Maven
- Chrome browser & ChromeDriver

## 📂 Project Structure
ECommerceSeleniumDemo/
├── pom.xml
├── README.md
├── src
│ ├── main
│ │ └── java
│ │ └── pages
│ │ ├── LoginPage.java
│ │ ├── ProductsPage.java
│ │ ├── CartPage.java
│ │ └── CheckoutPage.java
│ └── test
│ └── java
│ └── tests
│ └── E2ETest.java