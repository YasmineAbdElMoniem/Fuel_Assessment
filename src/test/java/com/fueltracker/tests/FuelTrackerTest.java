package com.fueltracker.tests;

import com.fueltracker.pages.FuelTrackerPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class FuelTrackerTest extends TestBase {

    @Test(priority = 1, description = "Verify validation messages for every text field")
    @Description("This test checks if validation messages appear for every text field on the Fuel Tracker page.")
    public void checkValidationMessageAppearForEveryTextField() {
        boolean verifyValidationMessageAppearForEveryTextField = new FuelTrackerPage(driver)
                .checkValidationMessageAppearForEveryTextField();
        doAssertEqual(verifyValidationMessageAppearForEveryTextField, true);
        softAssertion.assertAll();
    }

    @Test(priority = 2, description = "Verify date time field validation")
    @Description("This test checks the validation message for the date time field on the Fuel Tracker page.")
    public void checkDateTimeFieldValidation() {
        boolean verifyDateTimeFieldValidation = new FuelTrackerPage(driver)
                .checkDateTimeFieldValidation();
        doAssertEqual(verifyDateTimeFieldValidation, true);
        softAssertion.assertAll();
    }

    @Test(priority = 3, description = "Add and verify a record is added successfully")
    @Description("This test adds a record to the Fuel Tracker page and verifies if it is added successfully.")
    public void checkTheRecordIsAddedSuccessfully() {
        int carNumber = new Faker().number().numberBetween(10, 90);
        double fuelInLiters = new Faker().number().randomDouble(1, 1, 100);
        double fuelCost = new Faker().number().randomDouble(1, 100, 1000);
        String fuelType = new Faker().name().title();
        int customerCompanyID = new Faker().number().randomDigit();

        boolean checkTheRecordIsAddedSuccessfully = new FuelTrackerPage(driver)
                .enterCarNumber(carNumber)
                .enterFuelInLiters(fuelInLiters)
                .enterFuelCost(fuelCost)
                .enterFuelType(fuelType)
                .enterDateTime()
                .enterCustomerCompanyID(customerCompanyID)
                .clickEditButton()
                .checkTheRecordIsAddedSuccessfully(fuelInLiters, fuelCost, customerCompanyID);
        doAssertEqual(checkTheRecordIsAddedSuccessfully, true);
        softAssertion.assertAll();
    }
}
