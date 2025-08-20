Here’s a sample **README.md** file tailored for your **Hotel Booking Automation project** with **Selenium, TestNG, Extent Reports, and GitHub**. You can extend it further as your project grows.

---

# 🏨 Hotel Booking Automation Framework

This is a **Test Automation Framework** built using **Java, Selenium WebDriver, TestNG, and Extent Reports** for automating test cases of the [Adactin Hotel Application](http://adactinhotelapp.com/index.php). The project follows a **Page Object Model (POM)** design pattern and integrates with **GitHub** for version control.

---

## 🚀 Features

* Automated end-to-end test cases for hotel booking workflows.
* Page Object Model (POM) design pattern for maintainability.
* TestNG framework for test execution and reporting.
* Extent Reports for detailed HTML reports with screenshots on failures.
* Configurable test data through `Config.properties`.
* Driver management via `DriverFactory`.
* Chrome options configured to disable password and save card popups.

---

## 📂 Project Structure

```
HotelBookingAutomation/
│── src/
│   ├── base/                 # BaseTest and driver setup
│   ├── pages/                # Page Object classes
│   ├── tests/                # Test classes
│   ├── utilities/            # ConfigReader, DriverFactory, etc.
│── test-output/              # TestNG + Extent Reports
│── pom.xml                   # Maven dependencies
│── README.md                 # Project documentation
```

---

## ⚙️ Prerequisites

* Java 11 or higher
* Maven
* TestNG plugin installed in Eclipse/IntelliJ
* Chrome Browser & ChromeDriver (managed by DriverFactory)
* Git (for version control)

---

## 📦 Dependencies

The project uses the following key dependencies (managed in `pom.xml`):

* **Selenium WebDriver**
* **TestNG**
* **Extent Reports**
* **WebDriverManager** (for managing drivers, if added later)

---

## ▶️ How to Run Tests

1. Clone the repository:

   ```bash
   git clone https://github.com/vijaya-raghupathi/HotelAutomationProject.git
   ```

2. Navigate to the project folder:

   ```bash
   cd hotel-booking-automation
   ```

3. Run the tests using Maven:

   ```bash
   mvn clean test
   ```

4. View the Extent Report:
   Open `test-output/ExtentReport.html` in a browser.

---

## 📸 Screenshots on Failure

* When a test fails, a screenshot is automatically captured.
* The screenshot is attached in the **Extent Report** under the failed test.

---

## 🔮 Future Enhancements

* Add support for multiple browsers (Firefox, Edge).
* Integrate with **Jenkins** for CI/CD pipeline.
* Add **Allure Reports** for enhanced visualization.
* Parameterize tests using **DataProvider** with Excel/CSV input.
* Integrate with **Cucumber (BDD)** for behavior-driven testing.

---

## 👩‍💻 Author

**Vijaya Gopi**
📌 Automation Tester | Passionate about Test Automation & CI/CD

---

✨ This framework is designed to be scalable, reusable, and extendable for any web-based automation testing project.
