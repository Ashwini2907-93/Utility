package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaidModelModulePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public PaidModelModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators for model module elements
    By modelsButton = By.xpath("//button[contains(@class,'sidebar_navLinkStyleDark__8v1wT') and contains(.,'Models')]");
    By createNewModelButton = By.xpath("//button[contains(@class,'Agents_custom_button__c4t94') and contains(.,'Create New Model')]");
    By confirmationYesButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='Yes']");
    By selectModelDropdown = By.xpath("//button[contains(@class,'NewAgentDetail_inputStyle__todV3') and contains(.,'Select Model')]");
    By openAIGpt4Option = By.xpath("//a[@role='button' and text()='gpt-4o']");  // Adjust according to the actual option text
    By googlePalm2Option = By.xpath("//a[@role='button' and text()='Google-PaLM-2']");  // For alternative selection

    // API Key input field locator
    By apiKeyInputField = By.xpath("//input[@name='apiKey' and @placeholder='API Key']");
    
    By testButton = By.xpath("//button[contains(@class,'NewAgentDetail_buttonEnabledDark__I1nUy') and contains(.,'Test')]");
    By saveModelButton = By.xpath("//button[contains(@class,'NewAgentDetail_buttonEnabledDark__I1nUy') and contains(.,'Save Model')]");
    By confirmationOkButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");

    // Checkbox locator
    By modelCheckbox = By.xpath("//input[@type='checkbox' and contains(@style,'cursor: pointer;')]");

    // Method to navigate to Models module
    public void goToModelsModule() {
        WebElement modelsBtn = wait.until(ExpectedConditions.elementToBeClickable(modelsButton));
        modelsBtn.click();  // Click the 'Models' button on the sidebar
    }

    // Method to click 'Create New Model'
    public void clickCreateNewModel() {
        WebElement createModelBtn = wait.until(ExpectedConditions.elementToBeClickable(createNewModelButton));
        createModelBtn.click();  // Click the 'Create New Model' button
    }

    // Method to click the 'Yes' button in the confirmation message
    public void clickConfirmationYes() {
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmationYesButton));
        yesBtn.click();  // Click 'Yes' to confirm
    }

    // Method to select a model from the dropdown (Google-PaLM-2 first, fallback to gpt-4o)
    public void selectModel() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(selectModelDropdown));
        dropdown.click();  // Click the dropdown to open it

        try {
            WebElement modelOption = wait.until(ExpectedConditions.elementToBeClickable(googlePalm2Option));
            modelOption.click();  // Try to select 'Google-PaLM-2'
        } catch (Exception e) {
            System.out.println("Model 'Google-PaLM-2' is not available. Selecting gpt-4o instead.");
            WebElement fallbackModelOption = wait.until(ExpectedConditions.elementToBeClickable(openAIGpt4Option));
            fallbackModelOption.click();  // Select 'gpt-4o' if Google-PaLM-2 is not available
        }
    }

    // Method to handle model already exists error
    public void handleModelAlreadyExists() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Model already exists')]")));
            if (errorMessage.isDisplayed()) {
                System.out.println("Model already exists. Selecting a different model...");
                selectModel();  // Try to select an alternative model if the error appears
            }
        } catch (Exception e) {
            System.out.println("No 'Model already exists' error detected.");
        }
    }

    // Method to enter the API key
    public void enterApiKey(String apiKey) {
        WebElement apiKeyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(apiKeyInputField));
        apiKeyInput.clear();
        apiKeyInput.sendKeys(apiKey);  // Enter the API key
    }

    // Method to click 'Test' button
    public void clickTestButton() {
        WebElement testBtn = wait.until(ExpectedConditions.elementToBeClickable(testButton));
        testBtn.click();  // Click the 'Test' button
    }

    // Method to click 'Save Model' button using JavaScript
    public void clickSaveModel() {
        try {
            // Wait for the 'Save Model' button to be clickable
            WebElement saveModelBtn = wait.until(ExpectedConditions.elementToBeClickable(saveModelButton));

            // Scroll to the button using JavaScript to bring it into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveModelBtn);

            // Click the button using JavaScript as a fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveModelBtn);

        } catch (Exception e) {
            System.out.println("Exception while clicking 'Save Model' button: " + e.getMessage());
        }
    }

    // Method to click the 'OK' button after saving the model
    public void clickConfirmationOk() {
        WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmationOkButton));
        okBtn.click();  // Click 'OK' to confirm model creation
    }
}
