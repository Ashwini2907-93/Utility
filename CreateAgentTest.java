package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.AgentModulePage;
import pages.LoginPage;

public class CreateAgentTest extends BaseClass {

    LoginPage loginPage;
    AgentModulePage agentModulePage;

    @Test
    public void testLoginAndCreateAgent() throws InterruptedException {
        // Initialize the login and agent module page objects
        loginPage = new LoginPage(driver);
        agentModulePage = new AgentModulePage(driver);

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

        // Navigate to Agents module and create a new agent
        agentModulePage.goToAgentsModule();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        agentModulePage.clickCreateNewAgent();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter Agent details
        agentModulePage.enterAgentDetails("TestAgent", "#### Sample Agent Description");

        // Select the 'Write' type for the agent
        agentModulePage.selectWriteType();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter Pre, Main, Post, and VAA Instructions
        agentModulePage.enterInstructions("Sample instruction for agent.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next to go to the next step (Configuration tab)
        agentModulePage.clickNext();  // This will now use multiple strategies to click the "Next" button
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== NEW: Steps for the Configuration Tab ==========

        // Select the model from the dropdown on the Configuration tab
        agentModulePage.selectModel("gpt-3.5-turbo");  // For example, selecting "gpt-4o"
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next button after model selection on Configuration tab
        agentModulePage.clickNextAfterModelSelection();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ==========================================================
        // Additional steps can be added after this if there are more tabs to handle
     // ========== KB Tab (Context Window) ==========

        // Choose the environment (Mobile) and portal (Company)
        agentModulePage.chooseEnvironmentAndPortal();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Enter context window text
        agentModulePage.enterContextWindow("#### a. Z-Score or Standard Score\n- **Description**: Calculates how many standard deviations an element is from the mean.\n- **Usage**: Suitable for data that follows a normal distribution.\n- **Formula**: \\( z = \\frac{(X - \\mu)}{\\sigma} \\)\n- **Threshold**: Typically, a threshold (e.g., |z| > 3) is set to identify anomalies.");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Next button after entering context window details
        agentModulePage.clickFinalNextButton();
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution
        
     // ========== Positive AC Tab ==========
        agentModulePage.enterPositiveACDetails("Positive AC example text...");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== Negative AC Tab ==========
        agentModulePage.enterNegativeACDetails("Negative AC example text...");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== Example Response Tab ==========
        agentModulePage.enterExampleResponseDetails("Example response text...");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // ========== Supporting Artifact Tab ==========
        agentModulePage.enterSupportingArtifactDetails("Supporting artifact example text...");
        Thread.sleep(3000);  // Adding 3 seconds delay to slow down the execution

        // Click Submit to finish the agent creation
        agentModulePage.enterSupportingArtifactDetails("Supporting artifact example text...");
     // Call to click OK on the confirmation message
        agentModulePage.clickConfirmationOK();
    }

    }

    

