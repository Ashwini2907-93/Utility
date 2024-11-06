package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EditAgentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EditAgentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // Locators
    By agentsModuleButton = By.xpath("//button[contains(text(), 'Agents')]");
    By agentDropdown = By.xpath("//select[contains(@class, 'Agents_custom_select__CiLmN')]");
    By profileTab = By.xpath("//button[@id='controlled-tab-tab-agentProfile']");
    By editButtonProfile = By.xpath("//button[contains(@class, 'NewAgentDetail_saveButtonStyle') and text()='Edit']");
    By editButtonConfiguration = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[3]/div/div/div[6]/div/button[2]");
    By agentNameField = By.name("agentName");
    By agentDescriptionField = By.name("agentDescription");
    By saveButton = By.xpath("//button[contains(@class, 'NewAgentDetail_save___SN0r') and text()='Save']");
    By nextButton = By.xpath("//button[contains(@class, 'NewAgentDetail_saveButtonStyle__KniHz') and text()='Next']");
    By modelDropdown = By.name("modelName");
    By confirmationMessage = By.xpath("//div[@id='swal2-html-container' and text()='Data updated successfully']");
    By noChangesMessage = By.xpath("//div[@id='swal2-html-container' and contains(text(), 'No Changes are found')]");
    By okButton = By.xpath("//button[contains(@class, 'swal2-confirm')]");
    By okButtonNoChangesFound = By.xpath("//button[@class='swal2-confirm swal2-styled']");

    // Locator for Knowledge Base tab
    By knowledgeBaseTabDirect = By.xpath("//button[@type='button' and @role='tab' and contains(text(),'Knowledge Base')]");

    // Locator for Save button in the Knowledge Base tab with refined search
    By knowledgeBaseSaveButton = By.xpath("//div[@id='controlled-tab-tabpane-agentKnowledge']//button[@type='submit' and contains(@class, 'NewAgentDetail_saveButtonStyle__KniHz') and contains(@class, 'NewAgentDetail_save___SN0r') and normalize-space()='Save']");
    
    // Locator for Edit button in the Knowledge Base tab
    By knowledgeBaseEditButton = By.xpath("//div[@id='controlled-tab-tabpane-agentKnowledge']//button[contains(text(), 'Edit') and contains(@class, 'NewAgentDetail_saveButtonStyle')]");

    // Knowledge Base Environment and Portal Radio Buttons
    By environmentRadioButtonMobile = By.id("mobile"); // Environment: Mobile
    By portalRadioButtonBoth = By.id("bothPortal");    // Portal: Both
 // Locator for "No Changes are found" message in the Knowledge Base tab
    By noChangesMessageKnowledgeBase = By.xpath("/html/body/div[2]/div/div[2]");
    By okButtonKnowledgeBaseNoChanges = By.xpath("/html/body/div[2]/div/div[6]/button[1]");
 
    
    // Locator for Knowledge Base tab's Next button to navigate to Positive AC tab
    By nextButtonToPositiveACTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[1]/div/div[3]/button[3]");
    
    // Locator for Edit button in Positive AC tab
    By editButtonPositiveACTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/button[2]");

    // Locator for Positive AC text area
    By positiveACTextarea = By.xpath("//textarea[@name='positiveAC' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");

    // Locator for Save button in Positive AC tab
    By saveButtonPositiveAC = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/button[2]");

    // Locator for Next button in Positive AC tab to go to Negative AC tab
    By nextButtonToNegativeACTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/button[3]");
    
 // Locator for Edit button in Negative AC tab
    By editButtonNegativeACTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[3]/div/div[2]/button[2]");

    // Locator for Negative AC textarea
    By negativeACTestarea = By.xpath("//textarea[@name='negativeAC' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");

    // Locator for Save button in Negative AC tab
    By saveButtonNegativeACTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[3]/div/div[2]/button[2]");

    // Locator for Next button from Negative AC to Example Response tab
    By nextButtonToExampleResponseTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[3]/div/div[2]/button[3]");

    // Locator for Edit button in Example Response tab
    By editButtonExampleResponse = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[4]/div/div[2]/button[2]");

    // Locator for Example Response textarea
    By exampleResponseTextarea = By.xpath("//textarea[@name='example' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");

    // Locator for Save button in Example Response tab
    By saveButtonExampleResponse = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[4]/div/div[2]/button[2]");

    // Locator for Next button from Example Response to Supporting Artifact tab
    By nextButtonToSupportingArtifactTab = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[4]/div/div[2]/button[3]");

    // Locator for Edit button in Supporting Artifact tab
    By editButtonSupportingArtifact = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div[2]/button[2]");

    // Locator for Supporting Artifact textarea
    By supportingArtifactTextarea = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div[1]/div[3]/div/textarea");

    // Locator for Save button in Supporting Artifact tab
    By saveButtonSupportingArtifact = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div[2]/button[2]");

    // Method to click Edit button in Negative AC tab
    public void clickEditButtonNegativeACTab() {
        WebElement editBtn = waitUntilClickable(editButtonNegativeACTab);
        editBtn.click();
        System.out.println("Edit button in Negative AC tab clicked.");
    }

    // Method to enter text in Negative AC textarea
    public void enterNegativeACTestData(String text) {
        WebElement textarea = waitUntilVisible(negativeACTestarea);
        textarea.clear();
        textarea.sendKeys(text);
        System.out.println("Entered text in Negative AC textarea: " + text);
    }

    // Method to click Save button in Negative AC tab
    public void clickSaveButtonNegativeACTab() {
        WebElement saveBtn = waitUntilClickable(saveButtonNegativeACTab);
        saveBtn.click();
        System.out.println("Save button clicked in Negative AC tab.");
    }

    // Method to navigate to Example Response tab
    public void clickNextToExampleResponseTab() {
        WebElement nextBtn = waitUntilClickable(nextButtonToExampleResponseTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
        System.out.println("Navigated to Example Response tab.");
    }

    // Method to click Edit button in Example Response tab
    public void clickEditButtonExampleResponse() {
        WebElement editBtn = waitUntilClickable(editButtonExampleResponse);
        editBtn.click();
        System.out.println("Edit button in Example Response tab clicked.");
    }

    // Method to enter text in Example Response textarea
    public void enterExampleResponseText(String text) {
        WebElement textarea = waitUntilVisible(exampleResponseTextarea);
        textarea.clear();
        textarea.sendKeys(text);
        System.out.println("Entered text in Example Response textarea: " + text);
    }

    // Method to click Save button in Example Response tab
    public void clickSaveButtonExampleResponse() {
        WebElement saveBtn = waitUntilClickable(saveButtonExampleResponse);
        saveBtn.click();
        System.out.println("Save button clicked in Example Response tab.");
    }

    // Method to navigate to Supporting Artifact tab
    public void clickNextToSupportingArtifactTab() {
        WebElement nextBtn = waitUntilClickable(nextButtonToSupportingArtifactTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
        System.out.println("Navigated to Supporting Artifact tab.");
    }

    // Method to click Edit button in Supporting Artifact tab
    public void clickEditButtonSupportingArtifact() {
        WebElement editBtn = waitUntilClickable(editButtonSupportingArtifact);
        editBtn.click();
        System.out.println("Edit button in Supporting Artifact tab clicked.");
    }

    // Method to enter text in Supporting Artifact textarea
    public void enterSupportingArtifactText(String text) {
        WebElement textarea = waitUntilVisible(supportingArtifactTextarea);
        textarea.clear();
        textarea.sendKeys(text);
        System.out.println("Entered text in Supporting Artifact textarea: " + text);
    }

    // Method to click Save button in Supporting Artifact tab
    public void clickSaveButtonSupportingArtifact() {
        WebElement saveBtn = waitUntilClickable(saveButtonSupportingArtifact);
        saveBtn.click();
        System.out.println("Save button clicked in Supporting Artifact tab.");
    }


    // Method to verify if the "No Changes are found" message is displayed in the Knowledge Base tab
    public boolean isNoChangesFoundInKnowledgeBase() {
        return isElementDisplayed(noChangesMessageKnowledgeBase);
    }

    // Method to navigate to Agents module
    public void goToAgentsModule() {
        waitUntilClickable(agentsModuleButton).click();
        System.out.println("Navigated to Agents module.");
    }

    public void selectAgentFromDropdown(String agentValue) {
        WebElement dropdown = waitUntilClickable(agentDropdown);
        dropdown.click();
        waitUntilVisible(By.xpath("//option[@value='" + agentValue + "']")).click();
        System.out.println("Agent selected with value: " + agentValue);
    }

    public void openProfileTab() {
        waitUntilClickable(profileTab).click();
        System.out.println("Navigated to Profile tab.");
    }

    public void clickEditButtonProfile() {
        WebElement editBtn = waitUntilClickable(editButtonProfile);
        clickElement(editBtn);
        System.out.println("Edit button in Profile tab clicked.");
    }

    public void clickEditButtonConfiguration() {
        WebElement editBtn = waitUntilClickable(editButtonConfiguration);
        clickElement(editBtn);
        System.out.println("Edit button in Configuration tab clicked.");
    }

    public void clickNextButton() {
        WebElement nextBtn = waitUntilClickable(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
        System.out.println("Next button clicked to navigate to the Configuration tab.");
    }

    public void clickKnowledgeBaseTabDirectly() {
        WebElement knowledgeBaseTab = waitUntilClickable(knowledgeBaseTabDirect);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", knowledgeBaseTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knowledgeBaseTab);
        System.out.println("Knowledge Base tab clicked directly.");
    }

    public void clickEditButtonKnowledgeBase() {
        WebElement editBtn = waitUntilClickable(knowledgeBaseEditButton);
        clickElement(editBtn);
        System.out.println("Edit button in Knowledge Base tab clicked.");
    }

    public void editAgentName(String newName) {
        WebElement nameField = waitUntilVisible(agentNameField);
        nameField.clear();
        nameField.sendKeys(newName);
        System.out.println("Agent name updated to: " + newName);
    }

    public void clickOkButtonKnowledgeBaseNoChanges() {
        try {
            WebElement okBtn = waitUntilClickable(okButtonKnowledgeBaseNoChanges);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
            System.out.println("OK button clicked for 'No Changes Found' message in Knowledge Base tab.");
        } catch (Exception e) {
            System.err.println("Error clicking OK button: " + e.getMessage());
        }
    }

    public void updateAgentDescription(String description) {
        WebElement descriptionField = waitUntilClickable(agentDescriptionField);
        descriptionField.clear();
        descriptionField.sendKeys(description);
        System.out.println("Agent description updated to: " + description);
    }

    public void clickSaveButton() {
        WebElement saveBtn = waitUntilClickable(saveButton);
        clickElement(saveBtn);
        System.out.println("Save button clicked.");
    }

    public void selectModelFromDropdown(String modelValue) throws InterruptedException {
        for (int attempt = 0; attempt < 3; attempt++) { 
            try {
                WebElement dropdown = waitUntilClickable(modelDropdown);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
                waitUntilVisible(By.xpath("//option[@value='" + modelValue + "']")).click();
                System.out.println("Model selected: " + modelValue);
                break;
            } catch (org.openqa.selenium.TimeoutException e) {
                System.err.println("Attempt " + (attempt + 1) + ": Failed to select model dropdown due to timeout.");
                if (attempt == 2) throw e;
                Thread.sleep(1000);
            }
        }
    }

    public boolean isDataUpdatedSuccessfully() {
        return isElementDisplayed(confirmationMessage);
    }

    public boolean isNoChangesFound() {
        return isElementDisplayed(noChangesMessage);
    }

    public void clickOkButton() {
        waitUntilClickable(okButton).click();
        System.out.println("OK button clicked.");
    }

    public void clickOkButtonNoChanges() {
        WebElement okBtn = waitUntilClickable(okButtonNoChangesFound);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
        waitUntilInvisibilityOfElement(okButtonNoChangesFound);
        System.out.println("OK button in No Changes Found dialog clicked and dialog disappeared.");
    }
    
    public void clickSaveButtonKnowledgeBase() {
        List<WebElement> saveButtons = driver.findElements(knowledgeBaseSaveButton);
        for (WebElement saveBtn : saveButtons) {
            if (saveBtn.isDisplayed()) {
                clickElement(saveBtn);
                System.out.println("Save button clicked in Knowledge Base tab.");
                return;
            }
        }
        System.out.println("No visible Save button found in Knowledge Base tab.");
    }

    public void selectEnvironmentMobile() {
        WebElement envRadio = waitUntilClickable(environmentRadioButtonMobile);
        clickElement(envRadio);
        System.out.println("Environment 'Mobile' selected.");
    }

    public void selectPortalBoth() {
        WebElement portalRadio = waitUntilClickable(portalRadioButtonBoth);
        clickElement(portalRadio);
        System.out.println("Portal 'Both' selected.");
    }

    public void clickEditButtonPositiveACTab() {
        WebElement editBtn = waitUntilClickable(editButtonPositiveACTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBtn);
        editBtn.click();
        System.out.println("Edit button in Positive AC tab clicked.");
    }

    public void clickNextToPositiveACTab() {
        WebElement nextBtn = waitUntilClickable(nextButtonToPositiveACTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
        System.out.println("Navigated to Positive AC tab.");
    }

    public void enterPositiveACTestData(String text) {
        WebElement textarea = waitUntilVisible(positiveACTextarea);
        textarea.clear();
        textarea.sendKeys(text);
        System.out.println("Entered text in Positive AC textarea: " + text);
    }

    public void clickSaveButtonPositiveACTab() {
        WebElement saveBtn = waitUntilClickable(saveButtonPositiveAC);
        saveBtn.click();
        System.out.println("Save button clicked in Positive AC tab.");
    }

    public void clickNextToNegativeACTab() {
        WebElement nextBtn = waitUntilClickable(nextButtonToNegativeACTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
        System.out.println("Navigated to Negative AC tab.");
    }

    // Helper methods
    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitUntilInvisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

}