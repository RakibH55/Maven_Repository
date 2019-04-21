package Resuable_Classes;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Reusable_Methods_loggers {

    static int timeOut = 50;

    //method for navigating to a site
    public static WebDriver navigate(WebDriver driver, String url) throws IOException {
        //define the path of the chrome driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        //quit all open chrome browsers
        //Runtime.getRuntime().exec("taskkill /im chromedriver1.exe /f /t");

        //seeting up the chromeoptions
        ChromeOptions options = new ChromeOptions();
        //add the precondition arguments
        options.addArguments("start-maximized", "incognito", "disable-infobars");

        //define the chrome web driver
        driver = new ChromeDriver(options);

        //navigate to usps.com
        driver.navigate().to(url);

        return driver;
    }

    //method to click on an element
    public static void click(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        System.out.println("Clicking on element " + elementName);
        logger.log(LogStatus.INFO, "Clicking on elemnet " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
            logger.log(LogStatus.PASS, "Able to click on element" + elementName);
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
            logger.log(LogStatus.ERROR, "Unable to click on element " + elementName);
            getScreenshot(driver, logger, "clicking on element");
        }
    }//end of click method

    //method to hover to an element
    public static void mouseHover(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        Actions mouse = new Actions(driver);
        System.out.println("Hovering to an element " + elementName);
        logger.log(LogStatus.INFO, "Hovering over " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).perform();//you move mouse to element using .moveToElement
        } catch (Exception e) {
            System.out.println("Unable to hover on element " + elementName + " " + e);
            getScreenshot(driver, logger, "hovering over element");
        }
    }//end of mouse hover

    //Create sendKeys element method
    public static void sendKeys(WebDriver driver, ExtentTest logger, String locator, int index, String elementName, String userInput) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("SendKeys to element " + elementName);
        logger.log(LogStatus.INFO, "Entering value " + elementName);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.clear();
            element.sendKeys(userInput);
            logger.log(LogStatus.PASS, "able to send keys " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to send Keys to element " + elementName + " " + e);
            logger.log(LogStatus.ERROR, "Unable to send keys to" + elementName);
            getScreenshot(driver, logger, "sendkeys");
        }

    }//End of sendKeys method


    //Method for get title
    public static void getTitle(WebDriver driver, ExtentTest logger, String expectedTitle) throws IOException {

        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Yes the title Matches");
            logger.log(LogStatus.PASS, "title matches" + expectedTitle);
        } else {
            System.out.println("Title doesn't match - " + actualTitle);
            logger.log(LogStatus.ERROR, "Title unable to match");
            getScreenshot(driver, logger, "title");
        }

    }//End of get title Method


    //Create return method for Get Text
    public static String getText(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {

        String result = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        logger.log(LogStatus.INFO, "Capturing text from element " + elementName);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            result = element.getText();
            logger.log(LogStatus.PASS, "Able to get text from " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to locate element " + elementName + " " + e);
            logger.log(LogStatus.ERROR, "failed to get text from " + elementName);
            getScreenshot(driver, logger, "text");
        }

        return result;
    }//End of get text method


    //Create Select Method
    public static void dropDownByText(WebDriver driver, String locator, int index, String selection) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        try {
            WebElement elementName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            Select selectName = new Select(elementName);
            selectName.selectByVisibleText(selection);
        } catch (Exception e) {
            System.out.println("Unable to select item " + selection + " from dropdown");
        }
    }// End of Select Method

    //create xls commands

    public static int XLS(String rPath, int readableindex, String wPath, int writableindex) throws IOException, BiffException {
        Workbook readablefile = Workbook.getWorkbook(new File(rPath));
        Sheet readableSheet = readablefile.getSheet(readableindex);
        int rows = readableSheet.getRows();

        WritableWorkbook writableFile = Workbook.createWorkbook(new File(wPath));
        WritableSheet writableSheet = writableFile.getSheet(writableindex);

        return rows;


    }

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);


    }

    public static String shortUUID() {

        UUID uuid = UUID.randomUUID();

        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();

        return Long.toString(l, Character.MAX_RADIX);

    }
}