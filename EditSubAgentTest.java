package tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EditSubAgentPage;
import pages.LoginPage;

public class EditSubAgentTest extends BaseClass {

    private LoginPage loginPage;
    private EditSubAgentPage editSubAgentPage;

    @BeforeClass
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        editSubAgentPage = new EditSubAgentPage(driver);
    }

    @Test
    public void testEditSubAgentProfile() throws InterruptedException {
        performLogin(); // Perform login

        editSubAgentPage.goToAgentsModule();
        editSubAgentPage.selectAgentFromDropdown("613"); // Select main agent
        editSubAgentPage.clickSubAgentTab(); // Navigate to Sub Agent tab
        editSubAgentPage.selectSubAgentFromTable("UpdatedSubAgentDummy"); // Select sub-agent by name
        Thread.sleep(5000);


        editSubAgentPage.clickEditButtonSubAgentProfile();
        Thread.sleep(5000);

        editSubAgentPage.editSubAgentName("UpdatedSubAgent"); // Updated name for testing
        Thread.sleep(3000);

        editSubAgentPage.editSubAgentDescription("Updated description for Sub Agent.");
        Thread.sleep(3000);

        editSubAgentPage.clickSaveButton();
        Assert.assertTrue(editSubAgentPage.isDataUpdatedSuccessfully(), "Sub Agent profile update confirmation not displayed.");
        editSubAgentPage.clickOkButton();

        // Positive AC tab
        editSubAgentPage.enterPositiveACTestData("Positive test data for Sub Agent.");
        editSubAgentPage.clickSaveButtonPositiveACTab();
        Assert.assertTrue(editSubAgentPage.isDataUpdatedSuccessfully(), "Positive AC update confirmation message not displayed.");
        editSubAgentPage.clickOkButton();

        // Negative AC tab
        editSubAgentPage.enterNegativeACTestData("Negative test data for Sub Agent.");
        editSubAgentPage.clickSaveButtonNegativeACTab();
        Assert.assertTrue(editSubAgentPage.isDataUpdatedSuccessfully(), "Negative AC update confirmation message not displayed.");
        editSubAgentPage.clickOkButton();

        // Example Response tab
        editSubAgentPage.enterExampleResponseText("Example response for Sub Agent.");
        editSubAgentPage.clickSaveButtonExampleResponse();
        Assert.assertTrue(editSubAgentPage.isDataUpdatedSuccessfully(), "Example Response update confirmation message not displayed.");
        editSubAgentPage.clickOkButton();

        // Supporting Artifact tab
        editSubAgentPage.enterSupportingArtifactText("Supporting artifact for Sub Agent.");
        editSubAgentPage.clickSaveButtonSupportingArtifact();
        Assert.assertTrue(editSubAgentPage.isDataUpdatedSuccessfully(), "Supporting Artifact update confirmation message not displayed.");
        editSubAgentPage.clickOkButton();
    }

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
