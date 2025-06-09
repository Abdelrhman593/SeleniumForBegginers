package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionPage extends BasePage {

    private By addButtonLocator  = By.id("add_btn");
    private By row2InputFieldLocator  = By.xpath("//div[@id='row2']/input");
    private By row2saveButtonLocator  = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By SuccessMsgLocator = By.id("confirmation");
    private By editButton = By.id("edit_btn");
    private By row1InputFieldLocator = By.xpath("//*[@id='row1']/input");
    private By row1saveButtonLocator = By.xpath("//div[@id='row1']/button[@name='Save']");
    private By instructionMsgLocator = By.id("instructions");

    public ExceptionPage(WebDriver driver) {
        super(driver);
    }
    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exception/");
    }
    public void clickAddButton(){
        driver.findElement(addButtonLocator).click();
    }
    public String getConfirmationMessage(){
        WebElement confirmationMessageElement = waitForElementToBeDisplayed(SuccessMsgLocator);
        return confirmationMessageElement.getText();
    }
    public boolean isRowTwoDisplayedAfterWait(){
        return waitForIsDisplayed(row2InputFieldLocator);
    }
    public void clickEditButton(){
        driver.findElement(editButton).click();
    }
    public String getInstructionMessage(){
        return waitForElementToBeDisplayed(instructionMsgLocator).getText();
    }
    public void enterFoodInRow2(String name){
        driver.findElement(row2InputFieldLocator).sendKeys(name);
    }
    public void clickSaveButtonInRow2(){
        driver.findElement(row2saveButtonLocator).click();
    }
    public String getSuccessMessage(){
        return waitForElementToBeDisplayed(SuccessMsgLocator).getText();
    }
    public void clearRow1InputField(){
        driver.findElement(row1InputFieldLocator).clear();
    }
    public void saveRow1InputField(){
        driver.findElement(row1saveButtonLocator).click();
    }
    public void enterFoodInRow1(String name){
        driver.findElement(row1InputFieldLocator).sendKeys(name);
    }
    public void findInstructionMessage(){
        waitForElementToBeDisplayed(instructionMsgLocator);
    }
    public boolean isInstructionMessageDisplayed(){
        return waitForIsDisplayed(instructionMsgLocator);
    }
    public boolean isInstructionMessageHiddenAfterWait(){
        return waitForIsHidden(instructionMsgLocator);
    }


}
