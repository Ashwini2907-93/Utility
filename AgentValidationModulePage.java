package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgentValidationModulePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public AgentValidationModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Timeout set to 20 seconds for handling interactions
    }

    // Locators for agent profile elements
    By agentsModuleButton = By.xpath("//button[contains(text(), 'Agents')]");
    By createNewAgentButton = By.xpath("//button[contains(text(), 'Create New Agent')]");
    By agentNameField = By.name("agentName");
    By agentPurposeField = By.name("agentPurpose");
    By agentDescriptionField = By.name("agentDescription");
    By mainInstructionField = By.name("instructionsToAgent");

    // Error message locators
    By agentNameError = By.xpath("//div[text()='Agent Name is required.']");
    By agentPurposeError = By.xpath("//div[text()='Agent Purpose is required.']");
    By agentDescriptionError = By.xpath("//div[text()='Agent Description is required.']");
    By mainInstructionError = By.xpath("//div[text()='Instructions To Main is required.']");
    By agentNameExistsError = By.xpath("//div[text()='Agent name already exists.']");

    // Locator for Next button
    By nextButtonLocator = By.xpath("//div[@class='AgentProfile_buttonContainerStyle__Qforc']//button[contains(text(), 'Next')]");

    // Method to navigate to Agents module
    public void goToAgentsModule() {
        wait.until(ExpectedConditions.elementToBeClickable(agentsModuleButton)).click();
    }

    // Method to create a new agent
    public void clickCreateNewAgent() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewAgentButton)).click();
    }

    // Method to enter the agent name in the Agent Name input field
    public void enterAgentName(String agentName) {
        try {
            WebElement agentNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("agentName")));
            agentNameField.clear();  // Clear any existing text
            agentNameField.sendKeys(agentName);  // Enter the new agent name
            System.out.println("Agent name entered: " + agentName);
        } catch (Exception e) {
            System.out.println("Failed to enter agent name: " + e.getMessage());
        }
    }

    // Method to trigger validation by clicking Next without filling the fields
    public void triggerValidation() {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonLocator));

            // Scroll the Next button into view in case it is not in the visible viewport
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
            Thread.sleep(1000);  // Wait to ensure scroll completes

            // Attempt to click the Next button using the standard click
            nextButton.click();
            System.out.println("Next button clicked successfully.");

        } catch (Exception e) {
            System.out.println("Failed to click the Next button: " + e.getMessage());
        }
    }

    // Methods to get error messages and return the actual text
    public String getAgentNameErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameError));
            return error.getText();
        } catch (Exception e) {
            return "Agent Name Error message not found";
        }
    }

    public String getAgentPurposeErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(agentPurposeError));
            return error.getText();
        } catch (Exception e) {
            return "Agent Purpose Error message not found";
        }
    }

    public String getAgentDescriptionErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(agentDescriptionError));
            return error.getText();
        } catch (Exception e) {
            return "Agent Description Error message not found";
        }
    }

    public String getMainInstructionErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(mainInstructionError));
            return error.getText();
        } catch (Exception e) {
            return "Main Instruction Error message not found";
        }
    }

    // Method to get the error message for an already existing agent name
    public String getAgentNameExistsErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameExistsError));
            return error.getText();
        } catch (Exception e) {
            return "Agent Name Already Exists Error message not found";
        }
    }
}
