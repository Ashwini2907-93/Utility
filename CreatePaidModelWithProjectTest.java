package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.PaidModelModulePage;
import pages.LoginPage;

public class CreatePaidModelWithProjectTest extends BaseClass {

    LoginPage loginPage;
    PaidModelModulePage paidModelModulePage;

    @Test
    public void testLoginAndCreatePaidModelWithApi() throws InterruptedException {
        // Initialize the login and paid model module page objects
        loginPage = new LoginPage(driver);
        paidModelModulePage = new PaidModelModulePage(driver);

        // Perform login
        loginPage.clickLoginWithMicrosoft();
        Thread.sleep(3000);
        loginPage.enterEmail(username);  // Using username from BaseClass
        Thread.sleep(3000);

        loginPage.clickNext();
        Thread.sleep(3000);

        loginPage.enterPassword(password);  // Using password from BaseClass
        Thread.sleep(3000);

        loginPage.clickSignIn();
        Thread.sleep(3000);

        loginPage.clickYes();
        Thread.sleep(3000);

        // Navigate to Models module and click 'Create New Model'
        paidModelModulePage.goToModelsModule();
        Thread.sleep(3000);

        paidModelModulePage.clickCreateNewModel();
        Thread.sleep(3000);

        // Confirm the action by clicking 'Yes'
        paidModelModulePage.clickConfirmationYes();
        Thread.sleep(3000);

        // Select the model (gpt-4o initially)
        paidModelModulePage.selectModel();
        Thread.sleep(3000);

        // Check for "Model already exists" error and select another model (Google-PaLM-2)
        paidModelModulePage.handleModelAlreadyExists();
        Thread.sleep(3000);

        // Enter the API key
        String apiKey = "your-api-key-here";
        paidModelModulePage.enterApiKey(apiKey);
        Thread.sleep(3000);

        // Click 'Test' button
        paidModelModulePage.clickTestButton();
        Thread.sleep(3000);

        // Click 'Save Model' button
        paidModelModulePage.clickSaveModel();
        Thread.sleep(3000);

        // Click 'OK' after saving the model
        paidModelModulePage.clickConfirmationOk();
        Thread.sleep(3000);
    }
}
