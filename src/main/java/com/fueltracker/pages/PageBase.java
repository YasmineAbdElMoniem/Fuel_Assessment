package com.fueltracker.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    public PageBase(WebDriver driver) {
        PageBase.driver = driver;
    }

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @Step("Wait For Element To Be Clickable And Visible")
    public void visibilityWaitForElementToBeClickable(By element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    @Step("Click On Element")
    protected void clickElement(By element) {
        visibilityWaitForElementToBeClickable(element);
        driver.findElement(element).click();
    }

    @Step("Send The Text")
    protected void sendText(By element, String text) {
        visibilityWaitForElementToBeClickable(element);
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    @Step("Scroll By Visible Text")
    protected void scrollByVisibleText(String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        WebElement element = driver.findElement(By.xpath("//*[ text() = '" + text + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}