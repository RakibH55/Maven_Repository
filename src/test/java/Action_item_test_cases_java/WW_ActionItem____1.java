package Action_item_test_cases_java;

import Resuable_Classes.Abstract_Class;
import Resuable_Classes.Reusable_Methods;
import Resuable_Classes.Reusable_Methods_loggers;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WW_ActionItem____1 extends Abstract_Class {


    @Test
    public void testCase2() throws WriteException, IOException {


        driver.navigate().to("https://www.weightwatchers.com/us/");

        getTitle(driver, logger, "Weight Loss Program, Recipes & Help | Weight Watchers");

        click(driver, logger, "//*[@class='find-a-meeting']", 0, "find a meeting");

        getTitle(driver, logger, "Find a Studio & Meeting Near You |");

        sendKeys(driver, logger, "//*[@name= 'meetingSearch']", 0, "meeting search", "11218");

        click(driver, logger, "//*[@spice = 'SEARCH_BUTTON']", 0, "clicking search");


        String studioInfo = getText(driver, logger, "//*[@class='location__container']", 0, "studio locations");

        click(driver, logger, "//*[@class='location__container']", 0, "clicking on result");
        jse.executeScript("scroll(0,1000)");
        jse.executeScript("scroll(0,-1000)");
        //jse.executeScript("Arguements[0].scrollIntoView(true;"locator");//sroll to specific element
        String opHours = getText(driver,logger, "//*[contains(@class, 'currentday')]", 0, "getting operation hours");

        if (opHours == null) {
            opHours = "Operation Hours do not exist";


            //end of for loop
        }
    }
}

