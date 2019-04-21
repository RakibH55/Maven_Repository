package Action_item_test_cases_java;

import Resuable_Classes.Abstract_Class;
import Resuable_Classes.Reusable_Methods_loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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

public class
ActionItem_8___1 extends Abstract_Class {


    @Test
    public void testCase1 () throws InterruptedException, WriteException, IOException {

            //Navigate to express
            driver.navigate().to("http://www.express.com");

            //Verify page title
            //getTitle(driver, "Men’s and Women’s Clothing");

            //Hover to the Womens Clothing Option
            mouseHover(driver,logger,"//*[@href='/womens-clothing']",0,"Women's Clothing");

            //Hover to Dresses
            mouseHover(driver,logger,"//*[@href='/womens-clothing/dresses/cat550007']",0,"Dresses");

            //Click on Shop by Style
            click(driver,logger, "//*[@href='/womens-clothing/dresses/style/cat4480003']",0,"Shop by Style");

            //Pause
            Thread.sleep(3000);

            jse.executeScript("scroll(0,400)");

            //Click on first dress
            click(driver,logger, "//*[contains(@alt, 'strapless')]",0,"First Dress");

            //Click on each size
            click(driver,logger, "//*[@value = '0']",0,"Sizes");

            //Click on add to bag
            click(driver,logger, "//*[contains(text(), 'Add to Bag')]",0,"Add to Bag");

            Thread.sleep(3000);

            //Click on checkout on pop up
            click(driver,logger,"//*[@href = '/check-out/basket.jsp']",0,"Check out");

            //Select Quantity dropdown
            dropDownByText(driver,"//*[contains(@id, 'quantity')]",0,"2");

            //Click checkout button
            click(driver,logger, "//*[contains(@id,'checkout')]",0, "Checkout Button");

            Thread.sleep(3000);

            //Click guest option
            click(driver,logger, "//*[contains(text(), 'Continue as Guest')]",0,"Continue as Guest");

            sendKeys(driver,logger,"//*[contains(@id, 'firstname')]",0,"First Name", "Rakib");

            sendKeys(driver,logger,"//*[contains(@name, 'lastname')]",0,"Last Name", "Hossain");

            sendKeys(driver,logger,"//*[@type = 'email']",0,"Email", "nhkedbrhjv@gmail.com");

            sendKeys(driver,logger,"//*[@name = 'confirmEmail']",0,"Confirm Email","nhkedbrhjv@gmail.com");

            sendKeys(driver,logger,"//*[@name = 'phone']",0,"Phone Number", "9292236672");

            click(driver,logger, "//*[contains(text(), 'Continue')]",0,"Continue");

            Thread.sleep(3000);

            click(driver,logger ,"//*[contains(text(), 'Continue')]",0,"Continue");

            sendKeys(driver,logger,"//*[contains(@name, 'line1')]",0,"Address", "223 ave H");

            sendKeys(driver,logger,"//*[contains(@name, 'postalCode')]",0,"ZipCode", "11218");

            sendKeys(driver,logger,"//*[contains(@name, 'city')]",0,"City", "Brooklyn");

            //Select state
            dropDownByText(driver, "//*[@name='shipping.state']",0, "New York");

            Thread.sleep(3000);

            //Click continue
            click(driver,logger,"//*[contains(text(), 'Continue')]",0,"Continue Button");

            Thread.sleep(3000);


            //Send keys to credit card Field
            sendKeys(driver,logger, "//*[@id='creditCardNumberInput']",0,"Credit Card Field", "1234567890");

            //Click Place Order Button
            click(driver,logger,"//*[contains(text(), 'Place Order')]",0,"Place Order");

            Thread.sleep(3000);

            //Capture Error Message
            String error = getText(driver,logger,"//*[contains(text(), 'Enter a valid credit card number.')]",0,"Error Message");
            logger.log(LogStatus.INFO, error);

            driver.manage().deleteAllCookies();

    }//End of Test Method


}//End of public class
