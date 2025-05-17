package com.practicetestautomation.tests.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostiveLoginTests {
@Test
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
}
