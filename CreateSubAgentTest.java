package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.SubAgentModulePage;
import pages.LoginPage;

public class CreateSubAgentTest extends BaseClass {

    LoginPage loginPage;
    SubAgentModulePage subAgentModulePage;

    @Test
    public void testLoginAndCreateSubAgent() throws InterruptedException {
        // Initialize the login and sub-agent module page objects
        loginPage = new LoginPage(driver);
        subAgentModulePage = new SubAgentModulePage(driver);

        // Perform login
        loginPage.clickLoginWithMicrosoft();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        loginPage.enterEmail(username);  // Using username from BaseClass
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        loginPage.clickNext();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        loginPage.enterPassword(password);  // Using password from BaseClass
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        loginPage.clickSignIn();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        loginPage.clickYes();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Navigate to Agents module
        subAgentModulePage.goToAgentsModule();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Select the existing agent from the dropdown (e.g., "DummyAgent")
        subAgentModulePage.selectAgentFromDropdown("DummyAgent");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click "Create Sub-Agent"
        subAgentModulePage.clickCreateSubAgent();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter Sub-Agent details
        subAgentModulePage.enterAgentDetails("SubAgentTest", "SubAgentTest Description");

        // Select the 'Write' type for the sub-agent
        subAgentModulePage.selectWriteType();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter Pre, Main, Post, and VAA Instructions
        subAgentModulePage.enterInstructions("SubAgent instruction text.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next to go to the next step (Configuration tab)
        subAgentModulePage.clickNext();  // This will now use multiple strategies to click the "Next" button
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== Configuration Tab ==========

        // Select the model from the dropdown on the Configuration tab (e.g., "gpt-4o")
        subAgentModulePage.selectModel("gpt-4o");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next button after model selection on Configuration tab
        subAgentModulePage.clickNextAfterModelSelection();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== KB Tab (Context Window) ==========

        // Choose the environment (Mobile) and portal (Company)
        subAgentModulePage.chooseEnvironmentAndPortal();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter context window text
        subAgentModulePage.enterContextWindow("Context window text for sub-agent.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next button after entering context window details
        subAgentModulePage.clickFinalNextButton();
        Thread.sleep(5000);  // Adding 3 seconds delay to slow down the execution

        // ========== Positive AC Tab ==========
        subAgentModulePage.enterPositiveACDetails("Positive AC example text for sub-agent.");
        Thread.sleep(5000);  // Adding 3 seconds delay to slow down the execution

        // ========== Negative AC Tab ==========
        subAgentModulePage.enterNegativeACDetails("Negative AC example text for sub-agent.");
        Thread.sleep(5000);  // Adding 3 seconds delay to slow down the execution

        // ========== Example Response Tab ==========
        subAgentModulePage.enterExampleResponseDetails("Example response text for sub-agent.");
        Thread.sleep(5000);  // Adding 3 seconds delay to slow down the execution

        // ========== Supporting Artifact Tab ==========
        subAgentModulePage.enterSupportingArtifactDetails("Supporting artifact text for sub-agent.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Submit to finish the sub-agent creation
        subAgentModulePage.enterSupportingArtifactDetails("Supporting artifact example text for sub-agent.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click OK on the confirmation message
        subAgentModulePage.clickConfirmationOK();
        Thread.sleep(5000);  // Adding 3 seconds delay to slow down the execution
    }
}
