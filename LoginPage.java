package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    // Constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for login page elements
    By loginButton = By.className("Login_mslogin__DYoiW");
    By emailField = By.id("i0116");
    By nextButton = By.xpath("//input[@id='idSIButton9' and @value='Next']");
    By passwordField = By.id("i0118");
    By signInButton = By.xpath("//input[@id='idSIButton9' and @value='Sign in']");
    By yesButton = By.xpath("//input[@id='idSIButton9' and @value='Yes']");

    // Clear field using JavaScript
    public void clearFieldUsingJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", driver.findElement(locator));
    }

    // Clear field by sending Backspace keys (simulate user clearing input)
    public void clearFieldWithBackspace(By locator) {
        WebElement element = driver.findElement(locator);
        element.click(); // Ensure the element is clicked and focused
        element.sendKeys(Keys.CONTROL + "a");  // Select all text
        element.sendKeys(Keys.BACK_SPACE);  // Delete selected text
    }

    // Wait for the element to be clickable
    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Actions for the login page
    public void clickLoginWithMicrosoft() {
        driver.findElement(loginButton).click();
    }

    public void enterEmail(String email) {
        waitForElementToBeClickable(emailField);
        clearFieldUsingJS(emailField);  // Clear using JS (you can switch this with backspace if needed)
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void enterPassword(String password) {
        waitForElementToBeClickable(passwordField);
        clearFieldWithBackspace(passwordField);
        clearFieldUsingJS(passwordField);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickYes() {
        driver.findElement(yesButton).click();
    }
}
