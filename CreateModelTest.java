package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ModelModulePage;

public class CreateModelTest extends BaseClass {

    LoginPage loginPage;
    ModelModulePage modelModulePage;

    @Test
    public void testLoginAndNavigateToCreateModel() throws InterruptedException {
        // Initialize the login and model module page objects
        loginPage = new LoginPage(driver);
        modelModulePage = new ModelModulePage(driver);

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
        modelModulePage.goToModelsModule();
        Thread.sleep(3000);

        modelModulePage.clickCreateNewModel();
        Thread.sleep(3000);

        // Confirm by clicking 'Yes'
        modelModulePage.clickConfirmationYes();
        Thread.sleep(3000);

        // Select "Open Source" model type
        modelModulePage.selectOpenSourceModel();
        Thread.sleep(3000);

        // Select the model from the dropdown
        try {
            modelModulePage.selectModelFromDropdown("llama2:latest");
        } catch (Exception e) {
            // Retry with a different model if the previous one already exists
            modelModulePage.selectModelFromDropdown("GPT-NeoX");
        }

        // Save the model
        modelModulePage.clickSaveModel();
        Thread.sleep(3000);

        // Click 'OK' to confirm after model creation
        modelModulePage.clickConfirmationOk();
        Thread.sleep(3000);
    }
}
