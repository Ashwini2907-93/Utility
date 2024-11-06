package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddNewRecordPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    By modelRowInTable = By.xpath("//div[contains(@class,'rdt_TableCell') and .//span[text()='Google-PaLM-2']]");
    By confirmationYesButton = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='Yes']");
    By addNewRecordButton = By.xpath("//button[contains(text(),'Add New Record')]");
    By typeDropdown = By.xpath("//select[@name='type']");
    By relatedNameDropdown = By.xpath("//select[@name='related_name']");
    By userDefinedInputTokensField = By.xpath("//input[@name='userDefinedInputTokens']");
    By userDefinedOutputTokensField = By.xpath("//input[@name='userDefinedOutputTokens']");
    By apiKeyField = By.xpath("//input[@name='apiKey' and @type='password' and @id='apikey']");
    By saveNewRecordButton = By.xpath("//button[contains(text(), 'Save') and @type='button' and contains(@class, 'btn-primary')]");
    By loader = By.xpath("//div[contains(@class, 'loader')]");
    By modalDialog = By.xpath("//div[@role='dialog' and contains(@class, 'modal')]");

    // Constructor
    public AddNewRecordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickModelRowInTable() {
        try {
            WebElement modelRow = wait.until(ExpectedConditions.elementToBeClickable(modelRowInTable));
            scrollToElement(modelRow);
            modelRow.click();
        } catch (Exception e) {
            System.out.println("Retrying click on model row due to: " + e.getMessage());
            fallbackClick(modelRowInTable);
        }
    }

    public void clickConfirmationYes() {
        try {
            WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(confirmationYesButton));
            scrollToElement(yesButton);
            yesButton.click();
        } catch (Exception e) {
            System.out.println("Retrying click on 'Yes' due to: " + e.getMessage());
            fallbackClick(confirmationYesButton);
        }
    }

    public void clickAddNewRecord() {
        waitForLoaderToDisappear();
        closeModalIfOpen();

        try {
            WebElement addNewRecordBtn = wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton));
            scrollToElement(addNewRecordBtn);
            addNewRecordBtn.click();
        } catch (Exception e) {
            System.out.println("Retrying click on 'Add New Record' due to: " + e.getMessage());
            fallbackClick(addNewRecordButton);
        }
    }

    public void selectType(String typeValue) {
        try {
            WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(typeDropdown));
            new Select(typeElement).selectByValue(typeValue);
        } catch (StaleElementReferenceException e) {
            System.out.println("Retrying selection of type due to stale element.");
            WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(typeDropdown));
            new Select(typeElement).selectByValue(typeValue);
        }
    }

    public void selectRelatedName(String relatedNameValue) {
        try {
            WebElement relatedNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedNameDropdown));
            wait.until(driver -> relatedNameElement.findElements(By.tagName("option")).size() > 1);
            new Select(relatedNameElement).selectByVisibleText(relatedNameValue);
        } catch (Exception e) {
            System.out.println("Retrying selection of related name due to: " + e.getMessage());
            WebElement relatedNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedNameDropdown));
            new Select(relatedNameElement).selectByVisibleText(relatedNameValue);
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
        waitForLoaderToDisappear();
        closeModalIfOpen();

        int attempts = 0;
        while (attempts < 3) {  // Retry clicking up to 3 times if needed
            try {
                WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveNewRecordButton));
                scrollToElement(saveBtn);
                saveBtn.click();
                System.out.println("Save button clicked successfully using standard click.");
                break; // Exit loop if successful
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying due to stale element in save button.");
                attempts++;
                wait.withTimeout(Duration.ofMillis(500)); // Brief wait before retry
            } catch (Exception e) {
                System.out.println("Attempting JavaScript click on 'Save' button due to: " + e.getMessage());
                fallbackClick(saveNewRecordButton); // JavaScript fallback
                attempts++;
                wait.withTimeout(Duration.ofMillis(500)); // Brief wait before next retry if needed
            }
        }
    }

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

    private void closeModalIfOpen() {
        int attempts = 0;
        while (attempts < 3) {
            List<WebElement> modals = driver.findElements(modalDialog);
            if (!modals.isEmpty()) {
                try {
                    WebElement closeButton = modals.get(0).findElement(By.xpath(".//button[@aria-label='Close']"));
                    closeButton.click();
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(modalDialog));
                    System.out.println("Modal closed successfully.");
                    break; // Exit loop if successful
                } catch (Exception e) {
                    System.out.println("Retrying modal close due to: " + e.getMessage());
                    attempts++;
                }
            } else {
                break; // Exit loop if no modal is found
            }
        }
    }
}
