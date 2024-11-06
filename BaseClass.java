package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {

    public WebDriver driver;
    public String baseUrl = "https://dev-utility.sstglobal.net/";
    public String username = "ashwini-gis@sstus.net";  // Your username
    public String password = "A@sh123$";  // Your password

    // Setup method to initialize WebDriver
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "D:\\edgedriver_win64 (3)\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        
        // Set options to prevent sign-in prompts and disable sync
        options.addArguments("--disable-features=msEdgeAccountSigninDialog,msEdgeClearBrowsingDataOnExit");
        options.addArguments("--no-first-run");  // Skips first-run experience
        options.addArguments("--disable-sync");   // Disables account sync
        options.addArguments("--disable-extensions"); // Disable extensions
        options.addArguments("--inprivate");  // Launch in incognito mode
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("acceptInsecureCerts", true);

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get(baseUrl);
    }

    // Tear down method to close the browser after tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
