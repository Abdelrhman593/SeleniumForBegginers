package com.practicetestautomation.tests.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")

    public void setup(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
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
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
        logger.info("Browser is Closed");
    }
    @Test
    public void noSuchElementExceptionsTests(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        logger.info("Starting ExceptionsTests");
        logger.info("Locating Add Button");
        WebElement addButton = driver.findElement(By.id("add_btn"));
        logger.info("Clicking Add Button");
        addButton.click();

        WebElement InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));

        Assert.assertTrue(InputField.isDisplayed());
        logger.info("InputField is displayed");
        logger.info("ExceptionsTests Passed");
    }
    @Test
    public void TimeoutExceptionTests(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        logger.info("Starting ExceptionsTests");
        logger.info("Locating Add Button");
        WebElement addButton = driver.findElement(By.id("add_btn"));
        logger.info("Clicking Add Button");
        addButton.click();

        WebElement InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));

        Assert.assertTrue(InputField.isDisplayed());
        logger.info("InputField is displayed");
        logger.info("ExceptionsTests Passed");
    }

    @Test
    public void elementNotInteractableExceptionTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        //Click Add Button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebElement InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));
        Assert.assertTrue(InputField.isDisplayed());

        //Click Save Button
        InputField.sendKeys("Burger");
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
        saveButton.click();

        WebElement SuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        WebElement SaveConfirmMsg = driver.findElement(By.id("confirmation"));
        Assert.assertTrue(SaveConfirmMsg.isDisplayed());
        Assert.assertEquals(SaveConfirmMsg.getText(), "Row 2 was saved");
    }
    @Test
    public void invalidElementStateExceptionTests(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_btn")));
        editButton.click();
        WebElement row1InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row1\"]/input")));
        Assert.assertTrue(row1InputField.isDisplayed());
        row1InputField.clear();
        row1InputField.sendKeys("Burger");
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@name='Save']"));
        saveButton.click();
        WebElement SaveConfirmMsg = driver.findElement(By.id("confirmation"));
        Assert.assertTrue(SaveConfirmMsg.isDisplayed());
        Assert.assertEquals(SaveConfirmMsg.getText(), "Row 1 was saved");

    }
}
