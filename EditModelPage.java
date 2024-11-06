package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditModelPage {

    WebDriver driver;
    WebDriverWait wait;

    public EditModelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Locators for model module elements
    By modelsButton = By.xpath("//button[contains(@class,'sidebar_navLinkStyleDark__8v1wT') and contains(.,'Models')]");
    By modelRowInTable = By.xpath("//div[contains(@class,'rdt_TableCell') and .//span[text()='Google-PaLM-2']]");
    By confirmationYesButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='Yes']");
    By editButton = By.xpath("//button[contains(@class,'NewAgentDetail_custom_button_disable__2Lf0q') and contains(text(),'Edit')]");
    By saveChangesButton = By.xpath("//button[contains(@class, 'NewAgentDetail_custom_button_disable__2Lf0q') and contains(@class, 'btn-primary') and contains(text(), 'Save')]");
    By addNewRecordButton = By.xpath("//button[contains(text(),'Add New Record')]");
    By confirmationOkButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");
    By confirmationMessageSuccess = By.xpath("//div[@class='swal2-html-container' and contains(text(), 'Model updated successfully.')]");
    By confirmationMessageNoChanges = By.xpath("//div[@class='swal2-html-container' and contains(text(), 'No changes are found')]");
    By loader = By.xpath("//div[contains(@class, 'loader')]");  // Adjust if the loader locator differs

    // Input fields locators
    By frequencyPenaltyField = By.xpath("//input[@name='frequencyPenalty']");
    By presencePenaltyField = By.xpath("//input[@name='presencePenalty']");
    By sensitivityField = By.xpath("//input[@name='sensitivity']");
    By costLimitField = By.xpath("//input[@name='costLimit']");
    By baseCostInputTokensField = By.xpath("//input[@name='baseCostInputTokens']");
    By baseCostOutputTokensField = By.xpath("//input[@name='baseCostOutputTokens']");
    By maxTokenLenField = By.xpath("//input[@name='maxTokenLen']");
    By temperatureField = By.xpath("//input[@name='temprature']");
    By topPField = By.xpath("//input[@name='topP']");

    // Additional locators for "Add New Record" functionality
    By typeDropdown = By.xpath("//select[@name='type']");
    By relatedNameDropdown = By.xpath("//select[@name='related_name']");
    By userDefinedInputTokensField = By.xpath("//input[@name='userDefinedInputTokens']");
    By userDefinedOutputTokensField = By.xpath("//input[@name='userDefinedOutputTokens']");
    By apiKeyField = By.xpath("//input[@name='apiKey' and @type='password' and @id='apikey' and contains(@style, 'flex: 1 1 0%')]");
    By saveNewRecordButton = By.xpath("//button[contains(text(), 'Save') and @type='button' and contains(@class, 'btn-primary') and contains(@style, 'margin-right: 2px;')]");

    public void goToModelsModule() {
        WebElement modelsBtn = wait.until(ExpectedConditions.elementToBeClickable(modelsButton));
        modelsBtn.click();
    }

    public void clickModelInTable() {
        waitForLoaderToDisappear();
        WebElement modelRow = wait.until(ExpectedConditions.elementToBeClickable(modelRowInTable));
        modelRow.click();
    }

    public void clickConfirmationYes() {
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmationYesButton));
        yesBtn.click();
    }

    public void clickEditButton() {
        try {
            waitForLoaderToDisappear();
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
            scrollToElement(editBtn);
            editBtn.click();
        } catch (Exception e) {
            System.out.println("Failed to click the 'Edit' button: " + e.getMessage());
            fallbackClick(editButton);
        }
    }

    // Field value interactions
    public void clearAndSetFrequencyPenalty(String newValue) { interactWithField(frequencyPenaltyField, newValue); }
    public void clearAndSetPresencePenalty(String newValue) { interactWithField(presencePenaltyField, newValue); }
    public void clearAndSetSensitivity(String newValue) { interactWithField(sensitivityField, newValue); }
    public void clearAndSetCostLimit(String newValue) { interactWithField(costLimitField, newValue); }
    public void clearAndSetBaseCostInputTokens(String newValue) { interactWithField(baseCostInputTokensField, newValue); }
    public void clearAndSetBaseCostOutputTokens(String newValue) { interactWithField(baseCostOutputTokensField, newValue); }
    public void clearAndSetMaxTokenLength(String newValue) { interactWithField(maxTokenLenField, newValue); }
    public void clearAndSetTemperature(String newValue) { interactWithField(temperatureField, newValue); }
    public void clearAndSetTopP(String newValue) { interactWithField(topPField, newValue); }

    // Common method to interact with fields
    private void interactWithField(By fieldLocator, String newValue) {
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
            scrollToElement(field);
            field.clear();
            field.sendKeys(newValue);
        } catch (Exception e) {
            System.out.println("Failed to interact with field: " + e.getMessage());
        }
    }

    public void saveChanges() {
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
            scrollToElement(saveBtn);
            saveBtn.click();
            waitForLoaderToDisappear();
        } catch (Exception e) {
            System.out.println("Failed to click the 'Save' button: " + e.getMessage());
            fallbackClick(saveChangesButton);
        }
    }

    public boolean isConfirmationMessageDisplayed() {
        try {
            boolean isSuccessMessageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageSuccess)).isDisplayed();
            return isSuccessMessageDisplayed;
        } catch (Exception e) {
            // If the success message is not found, check for the no-changes message
            try {
                boolean isNoChangesMessageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageNoChanges)).isDisplayed();
                return isNoChangesMessageDisplayed;
            } catch (Exception ex) {
                System.out.println("No confirmation message displayed: " + ex.getMessage());
                return false;
            }
        }
    }

    public boolean isModelUpdatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageSuccess));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickConfirmationOkButton() {
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(confirmationOkButton));
        okButton.click();
    }

    // Add new record methods
    public void clickAddNewRecord() {
        try {
            WebElement addNewRecordBtn = wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton));
            scrollToElement(addNewRecordBtn);
            addNewRecordBtn.click();
        } catch (Exception e) {
            System.out.println("Failed to click the 'Add New Record' button: " + e.getMessage());
            fallbackClick(addNewRecordButton);
        }
    }

    public void selectType(String typeValue) {
        WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(typeDropdown));
        new Select(typeElement).selectByValue(typeValue);
    }

    public void selectRelatedName(String relatedNameValue) {
        try {
            WebElement relatedNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedNameDropdown));
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                relatedNameElement.findElements(By.tagName("option")).size() > 1
            );
            new Select(relatedNameElement).selectByVisibleText(relatedNameValue);
        } catch (Exception e) {
            System.out.println("Failed to select related name: " + e.getMessage());
        }
    }

    public void setUserDefinedInputTokens(String value) {
        interactWithField(userDefinedInputTokensField, value);
    }

    public void setUserDefinedOutputTokens(String value) {
        interactWithField(userDefinedOutputTokensField, value);
    }

    public void setApiKey(String apiKey) {
        interactWithField(apiKeyField, apiKey);
    }

    public void saveNewRecord() {
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveNewRecordButton));
            scrollToElement(saveBtn);
            saveBtn.click();
        } catch (Exception e) {
            System.out.println("Failed to click the 'Save' button for new record: " + e.getMessage());
            fallbackClick(saveNewRecordButton);
        }
    }

    // Helper methods
    private void waitForLoaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    private void fallbackClick(By elementLocator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Fallback click failed for element: " + e.getMessage());
        }
    }
}
