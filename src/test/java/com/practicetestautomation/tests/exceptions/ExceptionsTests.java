package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionPage;
import com.practicetestautomation.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionsTests extends BaseTest {

    @Test
    public void noSuchElementExceptionsTests(){
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        Assert.assertTrue(exceptionPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");
    }
    @Test
    public void TimeoutExceptionTests(){
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        Assert.assertTrue(exceptionPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");
    }
    @Test
    public void elementNotInteractableExceptionTest(){
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        exceptionPage.isRowTwoDisplayedAfterWait();
        exceptionPage.enterFoodInRow2("Burger");
        exceptionPage.clickSaveButtonInRow2();
        Assert.assertEquals(exceptionPage.getSuccessMessage(), "Row 2 was saved","Message is not expected)");
    }
    @Test
    public void invalidElementStateExceptionTests(){
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickEditButton();
        exceptionPage.clearRow1InputField();
        exceptionPage.enterFoodInRow1("Fateer");
        exceptionPage.saveRow1InputField();
        Assert.assertEquals(exceptionPage.getSuccessMessage(), "Row 1 was saved","Message is not expected)");
    }

    @Test
    public void staleElementReferenceExceptionTests(){
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.findInstructionMessage();
        exceptionPage.clickAddButton();
        Assert.assertTrue(exceptionPage.isInstructionMessageHiddenAfterWait(),"Instruction Message is not hidden");
    }
}
