package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModelModulePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public ModelModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators for model module elements
    By modelsButton = By.xpath("//button[contains(@class,'sidebar_navLinkStyleDark__8v1wT') and contains(.,'Models')]");
    By createNewModelButton = By.xpath("//button[contains(@class,'Agents_custom_button__c4t94') and contains(.,'Create New Model')]");
    By confirmationYesButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='Yes']");
    By openSourceRadioButton = By.xpath("//input[@id='openSource' and @value='Open Source']");
    By selectModelDropdown = By.xpath("//button[contains(@class,'NewAgentDetail_inputStyle__todV3') and contains(.,'Select Model')]");
    By modelAlreadyExistsError = By.xpath("//div[contains(text(),'Model already exists') and contains(@style,'color: red')]");
    By saveModelButton = By.xpath("//button[@class='NewAgentDetail_buttonEnabledDark__I1nUy me-2 btn btn-primary' and text()='Save Model']");
    By confirmationOkButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");

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

    // Method to select "Open Source" as the model type
    public void selectOpenSourceModel() {
        WebElement openSourceBtn = wait.until(ExpectedConditions.elementToBeClickable(openSourceRadioButton));
        openSourceBtn.click();  // Select the "Open Source" radio button
    }

    // Method to select a model from the dropdown (handling existing model error)
    public void selectModelFromDropdown(String modelName) {
        try {
            WebElement modelDropdown = wait.until(ExpectedConditions.elementToBeClickable(selectModelDropdown));
            modelDropdown.click();  // Open the model dropdown
            
            // Select the desired model from the list
            WebElement modelOption = driver.findElement(By.xpath("//a[text()='" + modelName + "']"));
            modelOption.click();  // Click the model
            
            System.out.println("Selected model: " + modelName);
            
            // Check if the model already exists
            if (isModelAlreadyExists()) {
                System.out.println("Model already exists. Selecting a different model.");
                throw new Exception("ModelAlreadyExists");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while selecting the model: " + e.getMessage());
            // Handle the model already exists case or retry with a different model
        }
    }

    // Check if "Model already exists" error is displayed
    public boolean isModelAlreadyExists() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(modelAlreadyExistsError));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;  // Return false if the error is not present
        }
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
