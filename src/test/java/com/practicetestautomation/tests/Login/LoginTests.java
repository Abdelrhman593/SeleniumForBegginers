package com.practicetestautomation.tests.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests  {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")

    public void setup(@Optional("chrome") String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running Test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warning("Configuration for "+ browser +" is missing, so Running Test in Chrome By default");
                driver = new ChromeDriver();
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
        logger.info("Browser is Closed");
    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFunctionality() {
        logger.info ("Starting testLoginFunctionality");
        // Type username student into Username field
        logger.info ("Typing username student");
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("student");

        // Type password Password123 into Password field
        logger.info ("Typing password Password123");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Password123");

        // Push Submit button
        logger.info ("Clicking Submit button");
        WebElement submitButton = driver.findElement(By.id("submit"));
        //WebElement submitButton = driver.findElement(By.xpath("//button"));
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info ("Verifing Login Functionality");
        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        logger.info ("Verifying Login Message");
        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));
        logger.info ("Login Message is displayed");

        logger.info ("Verifying Log out Button");
        // Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
        logger.info ("Log out Button is displayed");

        logger.info("Test Login Functionality Passed");
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        logger.info("Starting negativeLoginTest");
        logger.info("Username: " + username);
        // Type username incorrectUser into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);

        logger.info("Typing Password");
        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        logger.info("clicking submit button");
        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Verifing Login Functionality");

        logger.info("Verifying expected error message: " + expectedErrorMessage);
        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        logger.info("error message is displayed");

        logger.info("Verifying error message text");
        // Verify error message text is Your username is invalid!
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        logger.info("error message text is correct");

        logger.info("Test negativeLoginTest Passed");

    }
}