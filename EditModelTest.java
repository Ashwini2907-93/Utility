package tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EditModelPage;
import pages.LoginPage;

public class EditModelTest extends BaseClass {

    LoginPage loginPage;
    EditModelPage editModelPage;

    @Test
    public void testLoginAndEditModel() throws InterruptedException {
        loginPage = new LoginPage(driver);
        editModelPage = new EditModelPage(driver);

        // Perform login
        loginPage.clickLoginWithMicrosoft();
        Thread.sleep(3000);
        loginPage.enterEmail(username);
        Thread.sleep(3000);
        loginPage.clickNext();
        Thread.sleep(3000);
        loginPage.enterPassword(password);
        Thread.sleep(3000);
        loginPage.clickSignIn();
        Thread.sleep(4000);
        loginPage.clickYes();
        Thread.sleep(5000);

        // Navigate to Models module
        editModelPage.goToModelsModule();
        Thread.sleep(5000);

        // Click on the model in the data table (to edit)
        editModelPage.clickModelInTable();
        Thread.sleep(5000);

        // Confirm action by clicking 'Yes' in the confirmation dialog
        editModelPage.clickConfirmationYes();
        Thread.sleep(5000);

        // Scroll and click the "Edit" button to allow editing
        editModelPage.clickEditButton();
        Thread.sleep(3000);

        // Edit the fields in sequence
        editModelPage.clearAndSetFrequencyPenalty("0.6");
        editModelPage.clearAndSetPresencePenalty("0.7");
        editModelPage.clearAndSetSensitivity("0.9");
        editModelPage.clearAndSetCostLimit("800");
        editModelPage.clearAndSetBaseCostInputTokens("0.06");
        editModelPage.clearAndSetBaseCostOutputTokens("0.07");
        editModelPage.clearAndSetMaxTokenLength("2200");
        editModelPage.clearAndSetTemperature("1.0");
        editModelPage.clearAndSetTopP("1");

        Thread.sleep(3000);

        // Save changes and handle confirmation message
        editModelPage.saveChanges();
        Thread.sleep(3000);

        // Verify the displayed confirmation message
        Assert.assertTrue(editModelPage.isConfirmationMessageDisplayed(), "No confirmation message was displayed.");

        if (editModelPage.isModelUpdatedSuccessfully()) {
            System.out.println("Model updated successfully.");
        } else {
            System.out.println("No changes are found.");
        }

        // Click the "OK" button on the confirmation message
        editModelPage.clickConfirmationOkButton();
        Thread.sleep(3000);

        // Return to Models module to add new records
        editModelPage.goToModelsModule();
        Thread.sleep(3000);
        editModelPage.clickModelInTable();
        Thread.sleep(3000);

        // Add a new record of type "Project"
        editModelPage.clickAddNewRecord();
        editModelPage.selectType("project");
        editModelPage.selectRelatedName("DummyProject");
        editModelPage.setUserDefinedInputTokens("100");
        editModelPage.setUserDefinedOutputTokens("50");
        editModelPage.setApiKey("sk-proj-84t4g79CFca2KYkl65tBT3BlbkFJT58qbAx6okl7RSWhiG9K");
        editModelPage.saveNewRecord();
        Thread.sleep(3000);

        // Add a new record of type "Department"
        editModelPage.goToModelsModule();
        Thread.sleep(3000);
        editModelPage.clickModelInTable();
        Thread.sleep(3000);
        editModelPage.clickAddNewRecord();
        editModelPage.selectType("department");
        editModelPage.selectRelatedName("Security Department");
        editModelPage.setUserDefinedInputTokens("200");
        editModelPage.setUserDefinedOutputTokens("100");
        editModelPage.setApiKey("sk-dept-85t5g80DGdb3KYlm75uCU4CrlALJM69kbBx7pl8QTWiJ8L");
        editModelPage.saveNewRecord();
        Thread.sleep(3000);
    }
}
