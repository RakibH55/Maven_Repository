package Resuable_Classes;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Reusable_Methods {

    static int timeOut = 50;

    //method for navigating to a site
    public static WebDriver navigate(WebDriver driver, String url) throws IOException {
        //define the path of the chrome driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        //quit all open chrome browsers
        //Runtime.getRuntime().exec("taskkill /im chromedriver1.exe /f /t");

        //seeting up the chromeoptions
        ChromeOptions options = new ChromeOptions();
        //add the precondition arguments
        options.addArguments("start-maximized","incognito","disable-infobars");

        //define the chrome web driver
        driver = new ChromeDriver(options);

        //navigate to usps.com
        driver.navigate().to(url);

        return driver;
    }

    //method to click on an element
    public static void click(WebDriver driver, String locator, int index,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        System.out.println("Clicking on element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
        }
    }//end of click method

    //method to hover to an element
    public static void mouseHover(WebDriver driver, String locator, int index,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        Actions mouse = new Actions(driver);
        System.out.println("Hovering to an element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).perform();//you move mouse to element using .moveToElement
        } catch (Exception e) {
            System.out.println("Unable to hover on element " + elementName + " " + e);
        }
    }//end of mouse hover

    //Create sendKeys element method
    public static void sendKeys(WebDriver driver, String locator, int index, String elementName, String userInput) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("SendKeys to element " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.clear();
            element.sendKeys(userInput);
        } catch (Exception e) {
            System.out.println("Unable to send Keys to element " + elementName + " " + e);
        }
    }//End of sendKeys method



    //Method for get title
    public static void getTitle(WebDriver driver, String expectedTitle) {

        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Yes the title Matches");
        } else {
            System.out.println("Title doesn't match - " + actualTitle);
        }

    }//End of get title Method




    //Create return method for Get Text
    public static String getText(WebDriver driver, String locator, int index, String elementName) {

        String result = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            result = element.getText();
        } catch (Exception e){
            System.out.println("Unable to locate element " + elementName + " " + e);
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

    public static int  XLS (String rPath, int readableindex, String wPath, int writableindex) throws IOException, BiffException {
        Workbook readablefile = Workbook.getWorkbook(new File(rPath));
        Sheet readableSheet = readablefile.getSheet(readableindex);
        int rows = readableSheet.getRows();

        WritableWorkbook writableFile = Workbook.createWorkbook(new File(wPath));
        WritableSheet writableSheet = writableFile.getSheet(writableindex);

        return rows;


    }








}
