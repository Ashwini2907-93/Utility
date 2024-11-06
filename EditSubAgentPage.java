package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditSubAgentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EditSubAgentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // Locators for Sub Agent actions
    By agentsModuleButton = By.xpath("//button[contains(text(), 'Agents')]");
    By agentDropdown = By.xpath("//select[contains(@class, 'Agents_custom_select__CiLmN')]");
    By subAgentTab = By.xpath("//button[contains(text(), 'Sub Agents')]");
    By editButtonSubAgentProfile = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[3]/div/div/div/div[1]/div/div[8]/button[1]");
    By subAgentNameField = By.xpath("//input[@name='agentName' and contains(@class, 'AgentProfile_inputStyle__d_z8m') and @placeholder='Enter Details' and @maxlength='25' and @value='dummysubagenttool']");
    By subAgentDescriptionField = By.xpath("//input[@name='agentDescription' and contains(@class, 'AgentProfile_inputStyle__d_z8m') and @placeholder='Enter Details' and @maxlength='3000' and @value='test']");
    By saveButton = By.xpath("(//button[contains(@class, 'NewAgentDetail_saveButtonStyle__KniHz') and contains(@class, 'NewAgentDetail_save___SN0r') and text()='Save'])[7]");
    By confirmationMessage = By.xpath("//div[@id='swal2-html-container' and text()='Data updated successfully']");
    By okButton = By.xpath("//button[contains(@class, 'swal2-confirm')]");
 // Locator for "No changes are found" message
    By noChangesMessageLocator = By.xpath("//div[@class='swal2-html-container' and contains(text(), 'No changes are found')]");

    // Locator for the OK button in the "No changes are found" message dialog
    By okButtonNoChanges = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");

    // Locators for Configuration tab actions
    By nextButton = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[3]/div/div/div/div[1]/div/div[8]/button[2]");
    By editButtonConfiguration = By.xpath("(//button[@type='submit' and contains(@class, 'NewAgentDetail_saveButtonStyle__KniHz') and normalize-space(text())='Edit'])[9]");
    By modelDropdown = By.name("modelName");

    // Locators for Knowledge Base tab actions
    By knowledgeBaseTabDirect = By.xpath("//button[@type='button' and @role='tab' and contains(text(),'Knowledge Base')]");
    By environmentRadioButtonMobile = By.id("mobile");
    By portalRadioButtonBoth = By.id("bothPortal");
    By knowledgeBaseSaveButton = By.xpath("//button[contains(@class, 'NewAgentDetail_save___SN0r') and text()='Save']");

    // Locators for Positive AC, Negative AC, Example Response, Supporting Artifact tabs
    By positiveACTextarea = By.xpath("//textarea[@name='positiveAC' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");
    By saveButtonPositiveACTab = By.xpath("XPATH_OF_SAVE_BUTTON_IN_POSITIVE_AC_TAB"); // Replace with actual XPath

    By negativeACTestarea = By.xpath("//textarea[@name='negativeAC' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");
    By saveButtonNegativeACTab = By.xpath("XPATH_OF_SAVE_BUTTON_IN_NEGATIVE_AC_TAB"); // Replace with actual XPath

    By exampleResponseTextarea = By.xpath("//textarea[@name='example' and contains(@class, 'AgentProfile_inputStyle__d_z8m')]");
    By saveButtonExampleResponse = By.xpath("XPATH_OF_SAVE_BUTTON_IN_EXAMPLE_RESPONSE_TAB"); // Replace with actual XPath

    By supportingArtifactTextarea = By.xpath("/html/body/div/div/div/div/div[2]/main/div/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div[1]/div[3]/div/textarea");
    By saveButtonSupportingArtifact = By.xpath("XPATH_OF_SAVE_BUTTON_IN_SUPPORTING_ARTIFACT_TAB"); // Replace with actual XPath

    // Methods for navigation and actions
    public void goToAgentsModule() {
        waitUntilClickable(agentsModuleButton).click();
    }

    public void selectAgentFromDropdown(String agentValue) {
        WebElement dropdown = waitUntilClickable(agentDropdown);
        dropdown.click();
        waitUntilVisible(By.xpath("//option[@value='" + agentValue + "']")).click();
    }

    public void clickSubAgentTab() {
        waitUntilClickable(subAgentTab).click();
    }

    public void selectSubAgentFromTable(String subAgentName) {
        By subAgentRow = By.xpath("//tr[contains(@class, 'SubAgents_table_row__S62B_') and .//td[text()='" + subAgentName + "']]");
        WebElement subAgentRowElement = waitUntilClickable(subAgentRow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subAgentRowElement);
        subAgentRowElement.click();
        System.out.println("Sub Agent selected: " + subAgentName);
    }

    public void clickEditButtonSubAgentProfile() {
        WebElement editButton = waitUntilClickable(editButtonSubAgentProfile);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        System.out.println("Edit button in Sub Agent Profile clicked via JavaScript.");
    }

    public void editSubAgentName(String newName) {
        WebElement nameField = waitUntilVisible(subAgentNameField);
        nameField.clear();
        nameField.sendKeys(newName);
    }

    public void editSubAgentDescription(String description) {
        WebElement descriptionField = waitUntilVisible(subAgentDescriptionField);
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void clickSaveButton() {
        waitUntilClickable(saveButton).click();
    }

    public boolean isDataUpdatedSuccessfully() {
        return isElementDisplayed(confirmationMessage);
    }

    public void clickOkButton() {
        waitUntilClickable(okButton).click();
    }
    public void verifyUpdateMessage() {
        try {
            // Check if "No changes are found" message is displayed
            if (isElementDisplayed(noChangesMessageLocator)) {
                System.out.println("No changes are found message displayed.");
                waitUntilClickable(okButtonNoChanges).click();  // Click OK for "No changes are found"
                System.out.println("OK button clicked for 'No changes are found' message.");
            } else if (isDataUpdatedSuccessfully()) {
                System.out.println("Data updated successfully message displayed.");
                clickOkButton();  // Click OK for successful update
            }
        } catch (Exception e) {
            System.err.println("Error verifying update message: " + e.getMessage());
        }
    }


    // Methods for navigating through tabs and editing additional information
    public void clickNextButton() {
        waitUntilClickable(nextButton).click();
    }

    public void clickEditButtonConfiguration() {
        waitUntilClickable(editButtonConfiguration).click();
    }

    public void selectModelFromDropdown(String modelValue) {
        waitUntilClickable(modelDropdown).click();
        waitUntilVisible(By.xpath("//option[@value='" + modelValue + "']")).click();
    }

    public void clickKnowledgeBaseTabDirectly() {
        waitUntilClickable(knowledgeBaseTabDirect).click();
    }

    public void selectEnvironmentMobile() {
        waitUntilClickable(environmentRadioButtonMobile).click();
    }

    public void selectPortalBoth() {
        waitUntilClickable(portalRadioButtonBoth).click();
    }

    // Method to enter text in Positive AC textarea
    public void enterPositiveACTestData(String text) {
        WebElement textarea = waitUntilVisible(positiveACTextarea);
        textarea.clear();
        textarea.sendKeys(text);
    }

    public void clickSaveButtonPositiveACTab() {
        waitUntilClickable(saveButtonPositiveACTab).click();
    }

    public void enterNegativeACTestData(String text) {
        WebElement textarea = waitUntilVisible(negativeACTestarea);
        textarea.clear();
        textarea.sendKeys(text);
    }

    public void clickSaveButtonNegativeACTab() {
        waitUntilClickable(saveButtonNegativeACTab).click();
    }

    public void enterExampleResponseText(String text) {
        WebElement textarea = waitUntilVisible(exampleResponseTextarea);
        textarea.clear();
        textarea.sendKeys(text);
    }

    public void clickSaveButtonExampleResponse() {
        waitUntilClickable(saveButtonExampleResponse).click();
    }

    public void enterSupportingArtifactText(String text) {
        WebElement textarea = waitUntilVisible(supportingArtifactTextarea);
        textarea.clear();
        textarea.sendKeys(text);
    }

    public void clickSaveButtonSupportingArtifact() {
        waitUntilClickable(saveButtonSupportingArtifact).click();
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
}
