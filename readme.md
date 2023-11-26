# Selenium Test Script for Fuel Tracker Form

## Overview

This Selenium test script is designed to verify the basic functionality of the Fuel Tracker form on a webpage. The
script includes form field validation and form submission.

## Script Structure

The test script is organized using the Page Object Model (POM) and Fluent Design patterns

## Test Cases

1. Verify Validation Messages for Every Text Field
   (checkValidationMessageAppearForEveryTextField):
    - Enters invalid data into each text field.
    - Clicks the "Save" button.
    - Verifies that validation messages appear for each text field.
2. Verify Date Time Field Validation      
   (checkDateTimeFieldValidation):
    - Leaves the Date and Time field empty.
    - Clicks the "Save" button.
    - Verifies that the validation message for the Date and Time field appears.
3. Add and Verify a Record is Added Successfully
   (checkTheRecordIsAddedSuccessfully):
    - Enters valid data into each form field.
    - Clicks the "Save" button.
    - Verifies that the record is added successfully.

**How to Run**

1. Clone this repository to your local machine.
2. Set up your Java environment with Selenium WebDriver.
3. Open the project in your preferred Java IDE.
4. Navigate to the test class for the FuelTracker scenario (e.g., FuelTrackerTest.java) and run the test methods.

**Dependencies**

- Java
- Selenium WebDriver
- Selenium Manager
- TestNG (for test execution)
- Java Faker
- Allure Report
- Maven
