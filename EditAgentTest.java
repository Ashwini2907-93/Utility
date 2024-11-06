package tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EditAgentPage;
import pages.LoginPage;

public class EditAgentTest extends BaseClass {

    private LoginPage loginPage;
    private EditAgentPage editAgentPage;

    @BeforeClass
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        editAgentPage = new EditAgentPage(driver);
    }

    // Test case for editing the agent name, description, and conditional model selection
    @Test
    public void testEditAgentNameDescriptionAndModel() throws InterruptedException {
        performLogin(); // Perform login

        // Navigate through the flow to edit the agent
        editAgentPage.goToAgentsModule();
        editAgentPage.selectAgentFromDropdown("613"); // Using the value attribute for DummyTestAgent
        editAgentPage.openProfileTab();
        Thread.sleep(3000);

        // Click Edit button in Profile tab
        editAgentPage.clickEditButtonProfile();
        Thread.sleep(3000);

        // Edit agent name and description
        editAgentPage.editAgentName("DummyAgentTest");
        editAgentPage.updateAgentDescription("This is a new agent description.");
        Thread.sleep(3000);

        // Save and confirm update success message
        editAgentPage.clickSaveButton();
        Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Data update confirmation message not displayed.");
        editAgentPage.clickOkButton(); // Close confirmation dialog
        Thread.sleep(3000);

        // Click Next to navigate to the Configuration tab
        editAgentPage.clickNextButton();
        Thread.sleep(3000);

        // Click the Edit button in the Configuration tab
        editAgentPage.clickEditButtonConfiguration();
        Thread.sleep(3000);

        // Attempt to save without making changes and check for "No Changes Found" message
        editAgentPage.clickSaveButton();
        Thread.sleep(3000);

        // Conditional check for changes
        if (editAgentPage.isNoChangesFound()) {
            // If "No Changes Found" is displayed, click OK and skip model selection
            editAgentPage.clickOkButtonNoChanges();
            System.out.println("No changes detected; skipping model selection and proceeding to Knowledge Base tab.");
        } else {
            // If changes are required, select the model and save again
            editAgentPage.selectModelFromDropdown("dolphin-mixtral:latest");
            editAgentPage.clickSaveButton();
            Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Model selection confirmation message not displayed.");
            editAgentPage.clickOkButton(); // Close confirmation dialog after saving model
        }

        // Directly click on the Knowledge Base tab
        editAgentPage.clickKnowledgeBaseTabDirectly();
        Thread.sleep(3000);

     // Knowledge Base tab actions: Edit, select environment, select portal, and save
        editAgentPage.clickEditButtonKnowledgeBase();
        Thread.sleep(3000);
        editAgentPage.selectEnvironmentMobile(); // Select Mobile environment
        editAgentPage.selectPortalBoth(); // Select Both portal
        Thread.sleep(3000);
        editAgentPage.clickSaveButtonKnowledgeBase(); // Save in Knowledge Base tab

        // Check if "No Changes are found" or "Data updated successfully" message is displayed
        if (editAgentPage.isNoChangesFoundInKnowledgeBase()) {
            editAgentPage.clickOkButtonKnowledgeBaseNoChanges();
            System.out.println("No changes detected in Knowledge Base tab; 'OK' button clicked.");
        } else {
            Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Knowledge Base update confirmation message not displayed.");
            editAgentPage.clickOkButton(); // Close confirmation dialog
            System.out.println("Data updated successfully in Knowledge Base tab; 'OK' button clicked.");
        }


        // Navigate to Positive AC tab
        editAgentPage.clickNextToPositiveACTab();
        Thread.sleep(3000);

        // Positive AC tab actions: Edit, enter test data, and save
        editAgentPage.clickEditButtonPositiveACTab(); // Click Edit in Positive AC tab
        Thread.sleep(3000);
        editAgentPage.enterPositiveACTestData("test"); // Enter test data in Positive AC textarea
        Thread.sleep(3000);
        editAgentPage.clickSaveButtonPositiveACTab(); // Save in Positive AC tab
        Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Positive AC update confirmation message not displayed.");
        editAgentPage.clickOkButton(); // Close confirmation dialog

        // Navigate to Negative AC tab
        editAgentPage.clickNextToNegativeACTab();
        Thread.sleep(3000);
    
 

    // Negative AC tab actions: Edit, enter text, and save
    editAgentPage.clickEditButtonNegativeACTab(); // Click Edit in Negative AC tab
    Thread.sleep(3000);
    editAgentPage.enterNegativeACTestData("This is a test negative AC."); // Enter text in Negative AC textarea
    Thread.sleep(3000);
    editAgentPage.clickSaveButtonNegativeACTab(); // Save in Negative AC tab
    Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Negative AC update confirmation message not displayed.");
    editAgentPage.clickOkButton(); // Close confirmation dialog
    Thread.sleep(3000);

    // Navigate to Example Response tab
    editAgentPage.clickNextToExampleResponseTab();
    Thread.sleep(3000);

    // Example Response tab actions: Edit, enter text, and save
    editAgentPage.clickEditButtonExampleResponse(); // Click Edit in Example Response tab
    Thread.sleep(3000);
    editAgentPage.enterExampleResponseText("This is a test example response."); // Enter text in Example Response textarea
    Thread.sleep(3000);
    editAgentPage.clickSaveButtonExampleResponse(); // Save in Example Response tab
    Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Example Response update confirmation message not displayed.");
    editAgentPage.clickOkButton(); // Close confirmation dialog

    // Navigate to Supporting Artifact tab
    editAgentPage.clickNextToSupportingArtifactTab();
    Thread.sleep(3000);

    // Supporting Artifact tab actions: Edit, enter text, and save
    editAgentPage.clickEditButtonSupportingArtifact(); // Click Edit in Supporting Artifact tab
    Thread.sleep(3000);
    editAgentPage.enterSupportingArtifactText("This is a test supporting artifact."); // Enter text in Supporting Artifact textarea
    Thread.sleep(3000);
    editAgentPage.clickSaveButtonSupportingArtifact(); // Save in Supporting Artifact tab
    Assert.assertTrue(editAgentPage.isDataUpdatedSuccessfully(), "Supporting Artifact update confirmation message not displayed.");
    editAgentPage.clickOkButton(); // Close confirmation dialog
    }

    // Common login method to reuse across tests
    private void performLogin() throws InterruptedException {
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
    }
}
