package tests;

import Base.BaseClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddNewRecordPage;
import pages.EditModelPage;
import pages.LoginPage;

public class AddNewRecordTest extends BaseClass {

    private LoginPage loginPage;
    private EditModelPage editModelPage;
    private AddNewRecordPage addNewRecordPage;

    @BeforeClass
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        editModelPage = new EditModelPage(driver);
        addNewRecordPage = new AddNewRecordPage(driver);
    }

    private void performLogin() throws InterruptedException {
        loginPage.clickLoginWithMicrosoft();
        Thread.sleep(3000); 
        loginPage.enterEmail(username);
        Thread.sleep(3000);
        loginPage.clickNext();
        Thread.sleep(3000);
        loginPage.enterPassword(password);
        Thread.sleep(3000);
        loginPage.clickSignIn();
        Thread.sleep(3000);
        loginPage.clickYes();
        Thread.sleep(3000);
    }

    @Test
    public void testAddNewProjectRecord() throws InterruptedException {
        performLogin();  // Login

        editModelPage.goToModelsModule();  
        editModelPage.clickModelInTable();  
        editModelPage.clickConfirmationYes();  

        addNewRecordPage.clickAddNewRecord();  
        
        // Fill and save new record details for a project
        addNewRecordPage.selectType("project");
        addNewRecordPage.selectRelatedName("testingAPIs");
        addNewRecordPage.setUserDefinedInputTokens("100");
        addNewRecordPage.setUserDefinedOutputTokens("50");
        addNewRecordPage.setApiKey("sk-proj-85t4g9CFca2KYkl65tBT3BlbkFJT58qbAx6okl7RSWhiG9K");
        Thread.sleep(3000);
        addNewRecordPage.saveNewRecord();
    }
}
