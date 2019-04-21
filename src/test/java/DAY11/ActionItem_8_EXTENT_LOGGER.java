package DAY11;

import Resuable_Classes.Reusable_Methods;
import Resuable_Classes.Reusable_Methods_loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ActionItem_8_EXTENT_LOGGER extends Reusable_Methods {


    WebDriver driver = null;
    Workbook readableFile;
    Sheet readableSheet;
    int rows;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    Label label1;
    JavascriptExecutor jse;
    ExtentReports reports;
    ExtentTest Extest;

    @BeforeMethod
    public void Webxls () throws IOException, BiffException {//setting all drivers and excel workbooks

        driver = navigate(driver, "http://www.express.com");

        jse = (JavascriptExecutor)driver;

        readableFile = Workbook.getWorkbook(new File("src/main/resources/expressAI.xls"));

        readableSheet = readableFile.getSheet(0);

        rows = readableSheet.getRows();

        writableFile = Workbook.createWorkbook(new File("src/main/resources/ExxpressResults.xls"), readableFile);

        writableSheet = writableFile.getSheet(0);

        reports = new ExtentReports(("src/main/java/Report_Folder/Express_Checkout.html"));

    }//End of Before Method

    @Test
    public void testing () throws InterruptedException, WriteException {

        for (int i = 1; i < rows; i++) {

            //setting up my variables from my columns
            String size = readableSheet.getCell(0, i).getContents();
            String quantity = readableSheet.getCell(1,i).getContents();
            String firstName = readableSheet.getCell(2,i).getContents();
            String lastName = readableSheet.getCell(3,i).getContents();
            String email = readableSheet.getCell(4,i).getContents();
            String phoneNum = readableSheet.getCell(5,i).getContents();
            String address = readableSheet.getCell(6,i).getContents();
            String zipCode = readableSheet.getCell(7,i).getContents();
            String city = readableSheet.getCell(8,i).getContents();
            String state = readableSheet.getCell(9,i).getContents();
            String cardNum = readableSheet.getCell(10,i).getContents();

            //Navigate to express
            driver.navigate().to("http://www.express.com");

            //Verify page title
            //getTitle(driver, "Men’s and Women’s Clothing");

            Assert.assertEquals("Men's and Women's Clothing - Shop jeans, dresses, and suits",driver.getTitle());

            //Hover to the Womens Clothing Option
            mouseHover(driver,"//*[@href='/womens-clothing']",0,"Women's Clothing");

            //Hover to Dresses
            mouseHover(driver,"//*[@href='/womens-clothing/dresses/cat550007']",0,"Dresses");

            //Click on Shop by Style
            click(driver, "//*[@href='/womens-clothing/dresses/style/cat4480003']",0,"Shop by Style");

            //Pause
            Thread.sleep(3000);

            jse.executeScript("scroll(0,400)");

            //Click on first dress
            click(driver, "//*[contains(@alt, 'strapless')]",0,"First Dress");

            //Click on each size
            click(driver, "//*[@value = '" + size + "']",0,"Sizes");

            //Click on add to bag
            click(driver, "//*[contains(text(), 'Add to Bag')]",0,"Add to Bag");

            Thread.sleep(3000);

            //Click on checkout on pop up
            click(driver,"//*[@href = '/check-out/basket.jsp']",0,"Check out");

            //Select Quantity dropdown
            dropDownByText(driver,"//*[contains(@id, 'quantity')]",0, quantity);

            //Click checkout button
            click(driver, "//*[contains(@id,'checkout')]",0, "Checkout Button");

            Thread.sleep(3000);

            //Click guest option
            click(driver, "//*[contains(text(), 'Continue as Guest')]",0,"Continue as Guest");

            sendKeys(driver,"//*[contains(@id, 'firstname')]",0,"First Name", firstName);

            sendKeys(driver,"//*[contains(@name, 'lastname')]",0,"Last Name", lastName);

            sendKeys(driver,"//*[@type = 'email']",0,"Email", email);

            sendKeys(driver,"//*[@name = 'confirmEmail']",0,"Confirm Email", email);

            sendKeys(driver,"//*[@name = 'phone']",0,"Phone Number", phoneNum);

            click(driver, "//*[contains(text(), 'Continue')]",0,"Continue");

            Thread.sleep(3000);

            click(driver, "//*[contains(text(), 'Continue')]",0,"Continue");

            sendKeys(driver,"//*[contains(@name, 'line1')]",0,"Address", address);

            sendKeys(driver,"//*[contains(@name, 'postalCode')]",0,"ZipCode", zipCode);

            sendKeys(driver,"//*[contains(@name, 'city')]",0,"City", city);

            //Select state
            dropDownByText(driver, "//*[@name='shipping.state']",0, state);

            Thread.sleep(3000);

            //Click continue
            click(driver,"//*[contains(text(), 'Continue')]",0,"Continue Button");

            Thread.sleep(3000);


            //Send keys to credit card Field
            sendKeys(driver, "//*[@id='creditCardNumberInput']",0,"Credit Card Field", cardNum);

            //Click Place Order Button
            click(driver,"//*[contains(text(), 'Place Order')]",0,"Place Order");

            Thread.sleep(3000);

            //Capture Error Message
            String error = getText(driver,"//*[contains(text(), 'Enter a valid credit card number.')]",0,"Error Message");

            //Send the data back to the writableSheet
            label1 = new Label(11,i,error);//column 11 because 11th column is where your error msg is returned
            writableSheet.addCell(label1);

            driver.manage().deleteAllCookies();


        }//End of for loop
    }//End of Test Method


    @AfterMethod
    public void End () throws IOException, WriteException {

        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();

    }//End of After Method


}//End of public class
