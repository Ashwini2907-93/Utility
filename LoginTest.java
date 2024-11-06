package tests;

import Base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseClass {

    LoginPage loginPage;

    @Test
    public void testLogin() throws InterruptedException {
        // Initialize the login page object
        loginPage = new LoginPage(driver);

        // Perform login
        loginPage.clickLoginWithMicrosoft();
        loginPage.enterEmail(username);  // Access username from BaseClass
        loginPage.clickNext();
        loginPage.enterPassword(password);  // Access password from BaseClass
        loginPage.clickSignIn();
        loginPage.clickYes();

        // Add any further steps after login here if needed
    }
}
