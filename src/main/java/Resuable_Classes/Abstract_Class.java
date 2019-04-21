package Resuable_Classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class extends Reusable_Methods_loggers {
    //all the public static variable need to be set to null to be reused on multiple test classes
    public static WebDriver driver = null;
    public static ExtentReports reports = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;

    //Before suite is needed to define your chrome driver
    @BeforeSuite

    public void openBrowser() throws IOException {

        //define chrome driver here


        reports = new ExtentReports("src/main/java/Report_Folder/AutomationReport " + shortUUID() + ".html", true);


    }
    @Parameters("browser")
    @BeforeMethod

    public void getTestName(Method methodName, String browser) throws IOException {

        jse = (JavascriptExecutor) driver;
        //loggers below will get the name of the each of your @test


        if (browser.equalsIgnoreCase("Chrome")) {
            driver = navigate(driver, "https://www.google.com");
        } else if (browser.equalsIgnoreCase("FireFox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://www.google.com");

        }

        logger = reports.startTest(methodName.getName()+"--"+browser);//starting test
        logger.log(LogStatus.INFO, "Automation Test Scenario started...");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void endLogger() {
        reports.endTest(logger);
        logger.log(LogStatus.INFO, "Automation Test Scenario has Ended...");
    }

    @AfterSuite

    public void closeSuite() {
        reports.flush();

        //reports.close();
        //driver.quit();
    }


}
