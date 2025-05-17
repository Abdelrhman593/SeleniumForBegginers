package com.practicetestautomation.tests.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTests {
    @Test
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
    @Test
    public void IncorrectPasswordTest(){

        //Test case 3: Negative password test
        // Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        // Type username student into Username field
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("student");
        // Type password incorrectPassword into Password field
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("<PASSWORD>");
        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        // Verify error message text is Your password is invalid!
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        driver.quit();

    }
}
