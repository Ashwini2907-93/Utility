package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgentModulePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public AgentModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Timeout set to 20 seconds for handling interactions
    }

    // Locators for agent module elements
    By agentsModuleButton = By.xpath("//button[contains(text(), 'Agents')]");
    By createNewAgentButton = By.xpath("//button[contains(text(), 'Create New Agent')]");
    By agentNameField = By.name("agentName");
    By agentPurposeField = By.name("agentPurpose");
    By agentPersonalityField = By.name("agentPersonality");
    By agentDescriptionField = By.name("agentDescription");
    By agentSpecializedActivitiesField = By.name("agentSpecializedActivities");
    By agentManagedIntentsField = By.name("agentManagedIntents");
    By agentPreInstructionField = By.name("preInstruction");
    By agentMainInstructionField = By.name("instructionsToAgent");
    By agentPostInstructionField = By.name("postInstruction");
    By agentInstructionsToVaaField = By.name("instructionToVaa");
    By agentTypeWrite = By.xpath("//input[@type='radio' and @value='Write']");
    By nextButton = By.xpath("//div[@class='AgentProfile_buttonContainerStyle__Qforc']//button[contains(text(), 'Next')]");

    // Method to navigate to Agents module
    public void goToAgentsModule() {
        wait.until(ExpectedConditions.elementToBeClickable(agentsModuleButton)).click();
    }

    // Method to create a new agent
    public void clickCreateNewAgent() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewAgentButton)).click();
    }

    // Fill out agent details
    public void enterAgentDetails(String name, String description) {
        driver.findElement(agentNameField).sendKeys(name);  // Enter Agent Name
        driver.findElement(agentPurposeField).sendKeys(description);  // Enter Agent Purpose
        driver.findElement(agentPersonalityField).sendKeys(description);  // Enter Agent Personality
        driver.findElement(agentDescriptionField).sendKeys(description);  // Enter Agent Description
        driver.findElement(agentSpecializedActivitiesField).sendKeys(description);  // Enter Specialized Activities
        driver.findElement(agentManagedIntentsField).sendKeys(description);  // Enter Managed Intents
    }

    // Select 'Write' as agent type
    public void selectWriteType() {
        wait.until(ExpectedConditions.elementToBeClickable(agentTypeWrite)).click();
    }

    // Commented out agent tools selection
    /*
    public void selectAgentToolsWriteToFile() {
        try {
            WebElement agentToolsPlaceholder = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-2-placeholder")));
            agentToolsPlaceholder.click();

            WebElement agentToolsInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-2-input")));
            agentToolsInput.sendKeys("WriteToFile");

            WebElement writeToFileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='WriteToFile']")));
            writeToFileOption.click();
        } catch (Exception e) {
            System.out.println("Exception occurred while selecting agent tools: " + e.getMessage());
        }
    }
    */

    // Fill Pre, Main, Post instructions and Instructions to VAA
    public void enterInstructions(String description) {
        driver.findElement(agentPreInstructionField).sendKeys(description);  // Enter Pre-Instruction
        driver.findElement(agentMainInstructionField).sendKeys(description);  // Enter Main Instruction
        driver.findElement(agentPostInstructionField).sendKeys(description);  // Enter Post-Instruction

        // Wait for instructionsToVaa to be visible
        WebElement instructionsToVaaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(agentInstructionsToVaaField));
        instructionsToVaaElement.sendKeys(description); // Enter Instructions to VAA
    }

    // Click "Next" button with multiple fallback strategies
    public void clickNext() throws InterruptedException {
        try {
            // Wait for the button to be clickable
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

            // Scroll the button into view using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
            Thread.sleep(2000);  // Short wait to ensure scrolling is completed

            // Try to click the button
            nextBtn.click();
            System.out.println("Next button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Standard click failed, trying JavaScript click: " + e.getMessage());

            // Fallback to JavaScript click
            try {
                WebElement nextBtn = driver.findElement(nextButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
                System.out.println("Next button clicked using JavaScript.");
            } catch (Exception jsException) {
                System.out.println("JavaScript click also failed: " + jsException.getMessage());

                // Final fallback - Try to remove overlays that might be blocking the click
                try {
                    ((JavascriptExecutor) driver).executeScript("document.querySelector('main').style.display = 'none';");
                    WebElement nextBtn = driver.findElement(nextButton);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
                    System.out.println("Next button clicked after removing potential overlays.");
                } catch (Exception finalException) {
                    System.out.println("Final attempt to click Next button failed: " + finalException.getMessage());
                }
            }
        }
    }

//Adding these lines after the existing code for 'Next' click strategy in AgentModulePage.java

//Locators for configuration tab elements
By modelDropdown = By.name("modelName");  // Model dropdown
By nextAfterModelButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][1]");  // Next button on Configuration tab

//Method to select model from the dropdown on the Configuration tab
public void selectModel(String modelName) {
 try {
     WebElement modelDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(modelDropdown));
     modelDropdownElement.click();
     
     // Select model using JavaScript execution
     Select modelSelect = new Select(modelDropdownElement);
     modelSelect.selectByVisibleText(modelName);  // For example, "gpt-4o"
     System.out.println("Model selected: " + modelName);
 } catch (Exception e) {
     System.out.println("Failed to select model: " + e.getMessage());
 }
}

//Method to click Next button on the Configuration tab after selecting a model
public void clickNextAfterModelSelection() {
 try {
     WebElement nextButtonConfigTab = wait.until(ExpectedConditions.elementToBeClickable(nextAfterModelButton));
     
     // Scroll to the button and click using JavaScript in case of any overlays
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButtonConfigTab);
     Thread.sleep(2000);  // Short wait to ensure scrolling completes
     nextButtonConfigTab.click();
     System.out.println("Next button clicked after model selection.");
 } catch (Exception e) {
     System.out.println("Failed to click Next on Configuration tab: " + e.getMessage());
 }
}

//Locators for KB tab elements
By mobileEnvironmentRadio = By.id("mobile");
By companyPortalRadio = By.id("companyPortal");
By contextWindowTextArea = By.name("contextWindow");
By nextButtonAfterContext = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][2]");

// Method to choose environment (Mobile) and portal (Company)
public void chooseEnvironmentAndPortal() {
    try {
        WebElement mobileRadio = wait.until(ExpectedConditions.elementToBeClickable(mobileEnvironmentRadio));
        mobileRadio.click();
        System.out.println("Mobile environment selected.");

        WebElement companyPortalRadioBtn = wait.until(ExpectedConditions.elementToBeClickable(companyPortalRadio));
        companyPortalRadioBtn.click();
        System.out.println("Company portal selected.");
    } catch (Exception e) {
        System.out.println("Failed to select environment or portal: " + e.getMessage());
    }
}

// Method to enter context window text
public void enterContextWindow(String contextText) {
    try {
        WebElement contextWindow = wait.until(ExpectedConditions.elementToBeClickable(contextWindowTextArea));
        contextWindow.sendKeys(contextText);
        System.out.println("Context window text entered.");
    } catch (Exception e) {
        System.out.println("Failed to enter context window text: " + e.getMessage());
    }
}

// Method to click the final "Next" button on the context window tab
public void clickFinalNextButton() {
    try {
        WebElement finalNextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonAfterContext));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalNextButton);
        Thread.sleep(2000);
        finalNextButton.click();
        System.out.println("Final 'Next' button clicked.");
    } catch (Exception e) {
        System.out.println("Failed to click final 'Next' button: " + e.getMessage());
    }
}

// Locators for positive AC, negative AC, example response, and supporting artifact sections
By positiveACTextArea = By.name("positiveAC");
By negativeACTextArea = By.name("negativeAC");
By exampleResponseTextArea = By.name("example");
By supportingArtifactTextArea = By.xpath("//*[@id='controlled-tab-tabpane-supportingArtifacts']/div/div/div[1]/div[3]/div/textarea");  // Assuming same name for artifact text area

// XPaths for Next buttons
By nextAfterPositiveACButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][3]");
By nextAfterNegativeACButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][4]");
By nextAfterExampleResponseButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][5]");
By submitButton = By.xpath("//button[@type='submit' and @class='AgentProfileKnowledgeBase_save__xWi99 ']");

// Method to enter details in Positive AC tab and click Next
public void enterPositiveACDetails(String positiveACText) {
    try {
        WebElement positiveACField = wait.until(ExpectedConditions.elementToBeClickable(positiveACTextArea));
        positiveACField.sendKeys(positiveACText);
        System.out.println("Positive AC details entered.");

        // Click Next button after entering details
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterPositiveACButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        nextButton.click();
        System.out.println("Next button clicked after Positive AC.");
    } catch (Exception e) {
        System.out.println("Failed to enter Positive AC details or click Next: " + e.getMessage());
    }
}

// Method to enter details in Negative AC tab and click Next
public void enterNegativeACDetails(String negativeACText) {
    try {
        WebElement negativeACField = wait.until(ExpectedConditions.elementToBeClickable(negativeACTextArea));
        negativeACField.sendKeys(negativeACText);
        System.out.println("Negative AC details entered.");

        // Click Next button after entering details
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterNegativeACButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        nextButton.click();
        System.out.println("Next button clicked after Negative AC.");
    } catch (Exception e) {
        System.out.println("Failed to enter Negative AC details or click Next: " + e.getMessage());
    }
}

// Method to enter details in Example Response tab and click Next
public void enterExampleResponseDetails(String exampleResponseText) {
    try {
        WebElement exampleResponseField = wait.until(ExpectedConditions.elementToBeClickable(exampleResponseTextArea));
        exampleResponseField.sendKeys(exampleResponseText);
        System.out.println("Example Response details entered.");

        // Click Next button after entering details
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterExampleResponseButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        nextButton.click();
        System.out.println("Next button clicked after Example Response.");
    } catch (Exception e) {
        System.out.println("Failed to enter Example Response details or click Next: " + e.getMessage());
    }
}

//Method to enter details in Supporting Artifact tab and click Submit
public void enterSupportingArtifactDetails(String artifactText) {
 try {
     // Wait for the supporting artifact field using the updated XPath
     WebElement supportingArtifactField = wait.until(ExpectedConditions.elementToBeClickable(supportingArtifactTextArea));
     
     // Scroll the textarea into view using JavaScript
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", supportingArtifactField);
     Thread.sleep(1000);  // Small pause to ensure scroll completes

     // Enter text in the Supporting Artifact field
     supportingArtifactField.sendKeys(artifactText);
     System.out.println("Supporting Artifact details entered.");

     // Wait for the Submit button to be clickable
     WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));

     // Scroll to the Submit button
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
     Thread.sleep(1000);  // Small pause to ensure scroll completes

     // Click the Submit button
     submitBtn.click();
     System.out.println("Submit button clicked after Supporting Artifact.");
 } catch (Exception e) {
     System.out.println("Failed to enter Supporting Artifact details or click Submit: " + e.getMessage());
 }
}

//Method to click OK on the confirmation message
public void clickConfirmationOK() {
 try {
     // Locate the OK button of the confirmation message
     WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.swal2-confirm")));
     
     // Click the OK button
     okButton.click();
     System.out.println("OK button clicked on confirmation message.");
 } catch (Exception e) {
     System.out.println("Failed to click OK button on confirmation message: " + e.getMessage());
 }
}
}

