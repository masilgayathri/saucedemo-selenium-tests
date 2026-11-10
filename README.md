# SauceDemo UI tests (Selenium, TestNG, Java)

Automated UI tests for [SauceDemo](https://www.saucedemo.com), built with Selenium WebDriver, TestNG and Java, using the Page Object Model.

## Prerequisites

| Tool | Version used |
|---|---|
| Java JDK | 25.0.5 (Eclipse Temurin). JDK 17 or later should work |
| Apache Maven | 3.10.0 |
| Google Chrome | 156.0.8078.80 |
| Selenium WebDriver | 4.27.0 (downloaded by Maven) |
| TestNG | 7.10.2 (downloaded by Maven) |
| Operating system | Windows 11 |

You do not need to download ChromeDriver. Selenium Manager (part of Selenium 4) finds the right driver for your Chrome version automatically. An internet connection is needed.

## How to run the tests

Open a terminal in the project folder (the folder that contains `pom.xml`) and run:

```
mvn clean test
```

To run one test class only:

```
mvn clean test -Dtest=LoginTest
```

In IntelliJ IDEA, you can also right-click `testng.xml` and select Run.

## Where to find the results

- The console shows the number of tests run, passed and failed.
- HTML report: `target/surefire-reports/index.html`
- Summary report: `target/surefire-reports/emailable-report.html`

The `target` folder is created when the tests run.

## What is automated

| Test class | What it checks | Test case |
|---|---|---|
| LoginTest | Valid login: page title, URL, product list displayed and 6 products shown | TC-01 |
| SelectItemTest | Name, description and price on the product page match the Products page. Add to cart changes the button to Remove, updates the cart badge and adds the item to the cart | TC-07 |
| EndToEndPurchaseTest | Login, add items, review the cart, enter checkout details, check the overview (items, payment and shipping information, item total, tax and total), finish and check the order confirmation | TC-13, TC-14 |
| SortProductsTest | Price (low to high) sorts all 6 products in ascending price order | TC-05 |
| CartUpdateCheckoutTest | Add 6 items, remove 2, check the cart and badge show 4, then complete checkout with the correct totals | TC-08, TC-13 |

The first three are the required tests. The last two are extra tests I added.

## Project structure

```
src/test/java/com/saucedemo
  base/BaseTest.java       Opens a new browser before each test and closes it after
  pages/                   One class per page (Page Object Model)
  tests/                   The 5 test classes
  utils/TestData.java      URLs, test user, checkout data and expected values
testng.xml                 Test suite
pom.xml                    Dependencies and build settings
```

## Design choices

- Page Object Model: locators and page actions are kept in page classes. If the UI changes, the fix is made in one place.
- Explicit waits only (WebDriverWait in BasePage). No Thread.sleep and no implicit waits.
- Each test opens its own browser session, so every test can run on its own and in any order.
- Assertions check values, not just clicks (for example, the tax and total are calculated and compared).
- standard_user is used for all automated tests.

## Assumptions

- SauceDemo has no requirements document. The 8% tax rate was worked out from the values shown on the overview page. It is kept in `TestData.TAX_RATE`, so it can be changed in one place.