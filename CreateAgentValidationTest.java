package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.AgentValidationModulePage;
import pages.LoginPage;

public class CreateAgentValidationTest extends BaseClass {

    LoginPage loginPage;
    AgentValidationModulePage agentValidationModulePage;

    @Test
    public void testFieldValidations() throws InterruptedException {
        // Initialize the login and agent module page objects
        loginPage = new LoginPage(driver);
        agentValidationModulePage = new AgentValidationModulePage(driver);

        // Perform login steps
        loginPage.clickLoginWithMicrosoft();
        Thread.sleep(3000);
        loginPage.enterEmail(username);
        Thread.sleep(3000);
        loginPage.clickNext();
        Thread.sleep(3000);
        loginPage.enterPassword(password);
        Thread.sleep(3000);
        loginPage.clickSignIn();
        Thread.sleep(3000);
        loginPage.clickYes();
        Thread.sleep(3000);

        // Navigate to Agents module and create a new agent
        agentValidationModulePage.goToAgentsModule();
        Thread.sleep(3000);
        agentValidationModulePage.clickCreateNewAgent();
        Thread.sleep(3000);

        // 1. Trigger validation by leaving all fields blank
        agentValidationModulePage.triggerValidation();
        Thread.sleep(3000);  // Wait to ensure the errors appear

        // Capture and print each field's error message
        String agentNameError = agentValidationModulePage.getAgentNameErrorMessage();
        String agentPurposeError = agentValidationModulePage.getAgentPurposeErrorMessage();
        String agentDescriptionError = agentValidationModulePage.getAgentDescriptionErrorMessage();
        String mainInstructionError = agentValidationModulePage.getMainInstructionErrorMessage();

        // Print the error messages in the console
        System.out.println("Agent Name Error: " + agentNameError);
        System.out.println("Agent Purpose Error: " + agentPurposeError);
        System.out.println("Agent Description Error: " + agentDescriptionError);
        System.out.println("Main Instruction Error: " + mainInstructionError);

        // 2. Test the "Agent name already exists" validation
        // Enter an existing agent name to trigger the "already exists" validation
        agentValidationModulePage.enterAgentName("TestAgent");  // Use a name that already exists
        agentValidationModulePage.triggerValidation();
        Thread.sleep(3000);  // Wait for the error message to appear

        // Capture and print the "Agent name already exists" error message
        String agentNameExistsError = agentValidationModulePage.getAgentNameExistsErrorMessage();
        System.out.println("Agent Name Already Exists Error: " + agentNameExistsError);

        // Optional: Assertions for validation (you can add assertions to check the expected error messages)
    }
}
