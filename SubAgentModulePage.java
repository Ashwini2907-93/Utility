package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubAgentModulePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public SubAgentModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Timeout set to 40 seconds
    }

    // Locators for the dropdown and Create Sub-Agent button
    By agentsModuleButton = By.xpath("//button[contains(text(), 'Agents')]");  // Agents Module Button
    By agentDropdown = By.xpath("//select[contains(@class, 'Agents_custom_select__CiLmN')]/option[text()='DummyTestAgent']");  // Agent Dropdown
    By createSubAgentButton = By.xpath("//button[contains(@class, 'SubAgents_custom_button__MI24n') and text()='Create Sub-Agent']");  // Create Sub-Agent Button

    // Method to navigate to Agents module
    public void goToAgentsModule() {
        try {
            WebElement agentsButton = wait.until(ExpectedConditions.elementToBeClickable(agentsModuleButton));
            agentsButton.click();
            System.out.println("Navigated to Agents module.");
        } catch (Exception e) {
            System.out.println("Failed to navigate to Agents module: " + e.getMessage());
        }
    }

    // Method to select an existing agent from the dropdown
    public void selectAgentFromDropdown(String agentName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(agentDropdown));

            // Scroll the dropdown into view and click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
            dropdown.click();
            Thread.sleep(1000);  // Small wait to ensure the dropdown expands

            // Select the agent by visible text
            WebElement option = driver.findElement(By.xpath("//option[text()='" + agentName + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
            System.out.println("Agent selected: " + agentName);

        } catch (Exception e) {
            System.out.println("Failed to select agent from dropdown: " + e.getMessage());
        }
    }

    // Method to click "Create Sub-Agent"
    public void clickCreateSubAgent() {
        try {
            WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createSubAgentButton));
            createButton.click();
            System.out.println("Create Sub-Agent button clicked.");
        } catch (Exception e) {
            System.out.println("Failed to click 'Create Sub-Agent' button: " + e.getMessage());
        }
    }

    // Locators for agent details fields
    By agentNameField = By.name("agentName");
    By agentPurposeField = By.name("agentPurpose");
    By agentPersonalityField = By.name("agentPersonality");
    By agentDescriptionField = By.name("agentDescription");
    By agentSpecializedActivitiesField = By.name("agentSpecializedActivities");
    By agentManagedIntentsField = By.name("agentManagedIntents");
    By agentPreInstructionField = By.name("preInstruction");
    By agentMainInstructionField = By.name("instructionsToAgent");
    By agentPostInstructionField = By.name("postInstruction");
    By agentInstructionsToVaaField = By.name("instructionToAgent");
    By agentTypeWrite = By.xpath("//input[@type='radio' and @value='Write']");
    By nextButton = By.xpath("//div[@class='AgentProfile_buttonContainerStyle__Qforc']//button[contains(text(), 'Next')]");

    // Method to enter agent details
    public void enterAgentDetails(String name, String description) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameField)).sendKeys(name);  // Enter Agent Name
            driver.findElement(agentPurposeField).sendKeys(description);  // Enter Agent Purpose
            driver.findElement(agentPersonalityField).sendKeys(description);  // Enter Agent Personality
            driver.findElement(agentDescriptionField).sendKeys(description);  // Enter Agent Description
            driver.findElement(agentSpecializedActivitiesField).sendKeys(description);  // Enter Specialized Activities
            driver.findElement(agentManagedIntentsField).sendKeys(description);  // Enter Managed Intents
            System.out.println("Entered agent details.");
        } catch (Exception e) {
            System.out.println("Failed to enter agent details: " + e.getMessage());
        }
    }

    // Select 'Write' as agent type
    public void selectWriteType() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(agentTypeWrite)).click();
            System.out.println("Selected 'Write' as agent type.");
        } catch (Exception e) {
            System.out.println("Failed to select 'Write' as agent type: " + e.getMessage());
        }
    }

    // Method to enter instructions (Pre, Main, Post, and VAA)
    public void enterInstructions(String instructionText) {
        try {
            driver.findElement(agentPreInstructionField).sendKeys(instructionText);  // Enter Pre-Instruction
            driver.findElement(agentMainInstructionField).sendKeys(instructionText);  // Enter Main Instruction
            driver.findElement(agentPostInstructionField).sendKeys(instructionText);  // Enter Post-Instruction

            WebElement instructionsToVaaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(agentInstructionsToVaaField));
            instructionsToVaaElement.sendKeys(instructionText); // Enter Instructions to VAA
            System.out.println("Entered instructions.");
        } catch (Exception e) {
            System.out.println("Failed to enter instructions: " + e.getMessage());
        }
    }

    // Method to click "Next" button
    public void clickNext() throws InterruptedException {
        try {
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
            Thread.sleep(1000);  // Small wait to ensure scrolling is completed
            nextBtn.click();
            System.out.println("Next button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click Next button: " + e.getMessage());
        }
    }

    // Locators for Configuration tab
    By modelDropdown = By.name("modelName");
    By nextAfterModelButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][1]");

    // Method to select a model on the Configuration tab
    public void selectModel(String modelName) {
        try {
            WebElement modelDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(modelDropdown));
            modelDropdownElement.click();
            Select modelSelect = new Select(modelDropdownElement);
            modelSelect.selectByVisibleText(modelName);
            System.out.println("Model selected: " + modelName);
        } catch (Exception e) {
            System.out.println("Failed to select model: " + e.getMessage());
        }
    }

    // Method to click Next on the Configuration tab
    public void clickNextAfterModelSelection() {
        try {
            WebElement nextButtonConfigTab = wait.until(ExpectedConditions.elementToBeClickable(nextAfterModelButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButtonConfigTab);
            Thread.sleep(1000);
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

    // Method to click Next on the context window tab
    public void clickFinalNextButton() {
        try {
            WebElement finalNextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonAfterContext));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalNextButton);
            Thread.sleep(1000);
            finalNextButton.click();
        } catch (Exception e) {
            System.out.println("Failed to click final Next button: " + e.getMessage());
        }
    }

    // Methods for Positive AC, Negative AC, and Example Response
    By positiveACTextArea = By.name("positiveAC");
    By nextAfterPositiveACButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][3]");
    
    public void enterPositiveACDetails(String positiveACText) {
        WebElement positiveACField = wait.until(ExpectedConditions.elementToBeClickable(positiveACTextArea));
        positiveACField.sendKeys(positiveACText);
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterPositiveACButton));
        nextButton.click();
    }

    By negativeACTextArea = By.name("negativeAC");
    By nextAfterNegativeACButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][4]");
    
    public void enterNegativeACDetails(String negativeACText) {
        WebElement negativeACField = wait.until(ExpectedConditions.elementToBeClickable(negativeACTextArea));
        negativeACField.sendKeys(negativeACText);
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterNegativeACButton));
        nextButton.click();
    }

    By exampleResponseTextArea = By.name("example");
    By nextAfterExampleResponseButton = By.xpath("//select[@name='modelName']/following::button[contains(text(), 'Next')][5]");
    
    public void enterExampleResponseDetails(String exampleResponseText) {
        WebElement exampleResponseField = wait.until(ExpectedConditions.elementToBeClickable(exampleResponseTextArea));
        exampleResponseField.sendKeys(exampleResponseText);
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextAfterExampleResponseButton));
        nextButton.click();
    }

    // Method to enter Supporting Artifact details
    By supportingArtifactTextArea = By.xpath("//*[@id='controlled-tab-tabpane-supportingArtifacts']/div/div/div[1]/div[3]/div/textarea");
    By submitButton = By.xpath("//button[@type='submit' and @class='AgentProfileKnowledgeBase_save__xWi99 ']");

    public void enterSupportingArtifactDetails(String artifactText) {
        WebElement supportingArtifactField = wait.until(ExpectedConditions.elementToBeClickable(supportingArtifactTextArea));
        supportingArtifactField.sendKeys(artifactText);
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitBtn.click();
    }

    // Method to click OK on the confirmation dialog
    public void clickConfirmationOK() {
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
        okButton.click();
    }
}
