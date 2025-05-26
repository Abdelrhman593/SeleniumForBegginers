package com.practicetestautomation.tests.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
@Test(groups = {"Positive", "regression", "smoke"})
    public void testLoginFunctionality(){
        // Open page
        WebDriver driver = new FirefoxDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        // Type username student into Username field
        WebElement userNameInput = driver.findElement(By.id("username"));
        userNameInput.sendKeys("student");
        //userNameInput.sendKeys("Wrong_Student");

        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Password123");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));
        // Verify button Log out is displayed on the new page
        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        //WebElement logoutButton = driver.findElement(By.linkText("Log in"));
        Assert.assertTrue(logoutButton.isDisplayed());
        //quit the page after done
        driver.quit();
    }
    @Test(groups = {"negative", "regression"})
    public void IncorrectUsernameTest(){
        // Test case 2: Negative username test
        // Open page
        System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        // Type username incorrectUser into Username field
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("incorrectUser");
        //usernameInputField.sendKeys("student");
        // Type password Password123 into Password field
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("Password123");
        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        // Verify error message text is Your username is invalid!
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        driver.quit();
    }

    @Parameters({"username","password","expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){

        //Test case 3: Negative password test
        // Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        // Type username student into Username field
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys(username);
        // Type password incorrectPassword into Password field
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys(password);
        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        // Verify error message text is Your password is invalid!
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        driver.quit();

    }
}
