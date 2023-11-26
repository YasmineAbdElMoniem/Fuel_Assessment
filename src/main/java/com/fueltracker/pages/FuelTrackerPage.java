package com.fueltracker.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuelTrackerPage extends PageBase {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'hh:mma");
    String formattedDateTime = currentDateTime.format(formatter);

    public FuelTrackerPage(WebDriver driver) {
        super(driver);
    }

    // Form Fields Locators
    By carNumberField = By.xpath("//input[@name='carNumber']");
    By fuelInLitersField = By.xpath("//input[@name='fuelInLiters']");
    By fuelCostField = By.xpath("//input[@name='fuelCost']");
    By fuelTypeField = By.xpath("//input[@name='fuelType']");
    By dateTimeField = By.xpath("//input[@name='dateAndTime']");
    By customerCompanyIDField = By.xpath("//input[@name='companyId']");
    By editButton = By.xpath("//button[@type='submit']");

    // List Of Text Fields
    By listOfFields = By.xpath("//input[@type='text']");

    // Table
    By tableColumnsNames = By.xpath("//tr//th");
    By tableColumnsValues = By.xpath("//tr//td");

    @Step("Enter Car Number: {carNumber}")
    public FuelTrackerPage enterCarNumber(int carNumber) {
        sendText(carNumberField, String.valueOf(carNumber));
        return this;
    }

    @Step("Enter Fuel In Liters: {fuelInLiters}")
    public FuelTrackerPage enterFuelInLiters(double fuelInLiters) {
        sendText(fuelInLitersField, String.valueOf(fuelInLiters));
        return this;
    }

    @Step("Enter Fuel Cost: {fuelCost}")
    public FuelTrackerPage enterFuelCost(double fuelCost) {
        sendText(fuelCostField, String.valueOf(fuelCost));
        return this;
    }

    @Step("Enter Fuel Type: {fuelType}")
    public FuelTrackerPage enterFuelType(String fuelType) {
        sendText(fuelTypeField, fuelType);
        return this;
    }

    @Step("Enter Date and Time: {formattedDateTime}")
    public FuelTrackerPage enterDateTime() {
        sendText(dateTimeField, formattedDateTime);
        return this;
    }

    @Step("Enter Customer Company ID: {customerCompanyID}")
    public FuelTrackerPage enterCustomerCompanyID(int customerCompanyID) {
        sendText(customerCompanyIDField, String.valueOf(customerCompanyID));
        return this;
    }

    @Step("Click Edit Button")
    public FuelTrackerPage clickEditButton() {
        clickElement(editButton);
        return this;
    }

    @Step("Check Validation Message Appears for Every Text Field")
    public Boolean checkValidationMessageAppearForEveryTextField() {
        List<WebElement> inputFieldsList = driver.findElements(listOfFields);
        for (WebElement inputField : inputFieldsList) {
            performFieldValidation(inputField);
        }
        return true;
    }

    @Step("Perform Field Validation for Input Field")
    private void performFieldValidation(WebElement inputField) {
        clickEditButton();
        String validationMessage = inputField.getAttribute("validationMessage");
        if (!validationMessage.contains("Please fill out this field")) {
            throw new RuntimeException("Validation message not as expected for the field.");
        }
        inputField.click();
        inputField.clear();
        inputField.sendKeys("text");
        clickEditButton();
    }

    @Step("Check Date Time Field Validation")
    public Boolean checkDateTimeFieldValidation() {
        String tooltip = driver.findElement(dateTimeField).getAttribute("validationMessage");
        return tooltip.contains("Please fill out this field");
    }

    @Step("Check the Record Is Added Successfully")
    public Boolean checkTheRecordIsAddedSuccessfully(double fuelInGallonsToCheck, double fuelCostToCheck, int companyIdToCheck) {
        boolean recordFound = false;
        scrollByVisibleText("Delete");
        // Get All  Column Names
        List<WebElement> columnsNames = driver.findElements(tableColumnsNames);

        // Iterate through columns Names
        for (int i = 0; i <= columnsNames.size(); i++) {
            // Get all values for each column name
            List<WebElement> columnValues = driver.findElements(tableColumnsValues);

            String fuelInGallons = columnValues.get(1).getText();
            String fuelCost = columnValues.get(2).getText();
            String companyId = columnValues.get(5).getText();

            // Check if the columns value match the expected values
            if (Double.parseDouble(fuelInGallons) == fuelInGallonsToCheck &&
                    Double.parseDouble(fuelCost) == fuelCostToCheck &&
                    Integer.parseInt(companyId) == companyIdToCheck) {
                recordFound = true;
            }
        }
        return recordFound;
    }
}
