# 🛒 Spree Commerce UI Automation

Automated UI testing framework for the [Spree Commerce demo store](https://demo.spreecommerce.org), using:

- 🧪 [Playwright](https://playwright.dev/java/) with Java
- 🍀 [Cucumber](https://cucumber.io/) for BDD
- ✅ [TestNG](https://testng.org/) for test orchestration
- 📦 [Maven](https://maven.apache.org/) for dependency management
- 📊 [ExtentReports](https://extentreports.com/) for rich HTML reports
- ✨ [![Build](https://github.com/lolence21/SpreeCommerce/actions/workflows/ci.yml/badge.svg)](https://github.com/lolence21/SpreeCommerce/actions/workflows/ci.yml)

---

## 💻 Development Environment

This project was developed using:

- **IDE**: IntelliJ IDEA (Community/Ultimate Edition)
- **Language**: Java 21
- **Build Tool**: Maven
- **Automation Framework**: Playwright with Cucumber, TestNG
- **Reporting**: ExtentReports
- **Logging**: SLF4J with Log4j2


## ✨ Features Tested

- Navigate to the Spree Commerce demo store. 
- Click on the user icon and Sign Up as a new user from the registration page from
   the side menu. (Log out if needed)
- Log in with the newly registered user credentials. 
- Browse products and open a product detail page. 
- Add the product to cart. 
- Go to the cart and verify the product details (name, quantity, price). 
- Proceed to checkout and complete the following:
   ○ Add a shipping address.
   ○ Select a shipping method.
   ○ Verify the different delivery and pricing options.
   ○ Select a payment method. (Kindly refer test card details on the checkout)
   ○ Complete the order.
- Verify the order confirmation page is shown with an order number and success
   message.

---

## 🧱 Project Structure
```bash
    src
    ├── .github
    │ └── workflows # ci.yml for pipeline run
    ├── reports # Screenshots and HTML reports
    ├── logs # app.log
    ├── main
    │ └── java
    │ └── com.example.spreeCommerce.interfaces # Interface Class for Page Objects
    │ └── com.example.spreeCommerce.pages # Page Objects
    │ └── com.example.spreeCommerce.utils # Helpers (TestContext, EmailGenerator, ConfigReader)
    ├── test
    │ ├── java
    │ │ └── com.example.spreeCommerce.runner # TestNG Runner class
    │ │ └── com.example.spreeCommerce.steps # Cucumber Step Definitions
    │ │ └── com.example.spreeCommerce.utils # ExtentManager Class for Reports
    │ ├── resources
    │ │ └── config # config.properties
    │ │ └── features # Cucumber .feature file
    │ │ └── configcucumber.properties # Cucumber config
    │ │ └── log4j2.xml # logger format
    │ │ └── testing-spree-commerce.xml # xml for ci.yml run call
    ├── .gitignore
    ├── pom.xml
    └── ReadMe.md
```
---

## 🔧 Prerequisites

| Tool      | Version Recommended             |
|-----------|---------------------------------|
| Java      | 21                              |
| Maven     | 3.2.5                           |
| IntelliJ  | (with Maven & Cucumber plugins) |
| Git       | Installed                       |

---

## ⚙️ Setup Instructions

1. **Clone the repository**
```bash
    git clone https://github.com/lolence21/SpreeCommerce.git
    cd SpreeCommerce
```
2. **Compile Maven Dependencies**
```bash
    mvn clean compile
```
3. **Update Configuration**
 - located in resources/config/config.properties
```properties
    browser = chrome
    headless = false
    url = https://demo.spreecommerce.org/
```

---

## 🧪 Running Tests
```bash
    mvn clean test -DsuiteXmlFile="src/test/resources/testng-spree-commerce.xml"
    or
    mvn clean test
```

---

## 📊 Reports
   - After execution, view the Extent Report:
```bash
    reports/
```

---

## 📝 Logs & Debugging
```bash
    logs/
```
---

## 📝 Configuration Details
    - config.properties supports: 
```bash
    logs/
```
