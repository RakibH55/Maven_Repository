package DataDriven_TestNG;

import Resuable_Classes.Reusable_Methods;
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

public class WW_ActionItem extends Reusable_Methods {

    WebDriver driver = null;
    Workbook readableFile;
    Sheet readableSheet ;
    int rows;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    Label label1, label2;
    JavascriptExecutor jse;
    @BeforeMethod
    public void OpenBrowser() throws IOException, BiffException {

        driver = navigate(driver, "https://www.weightwatchers.com/us/");
        readableFile = Workbook.getWorkbook(new File("src/main/resources/WWEXCEL.xls"));
        readableSheet = readableFile.getSheet(0);
        rows =  readableSheet.getRows();
        writableFile = Workbook.createWorkbook(new File("src/main/resources/WWdataResults.xls"),readableFile);
        writableSheet = writableFile.getSheet(0);

    }//end of pre-conditions

    @Test
    public void testScenario() throws WriteException {

        for(int i = 1; i< rows; i++){

            System.out.println("---------------------------------------------");

            String zipCode =  readableSheet.getCell(0,i).getContents();

            driver.navigate().to("https://www.weightwatchers.com/us/");

            getTitle(driver, "Weight Loss Program, Recipes & Help | Weight Watchers");

            click(driver,"//*[@class='find-a-meeting']",0,"find a meeting");

            getTitle(driver, "Find a Studio & Meeting Near You |");

            sendKeys(driver,"//*[@name= 'meetingSearch']",0,"meeting search",zipCode);

            click(driver, "//*[@spice = 'SEARCH_BUTTON']",0,"clicking search");


           String studioInfo = getText(driver,"//*[@class='location__container']",0,"studio locations");

            click(driver,"//*[@class='location__container']",0,"clicking on result");
            jse.executeScript("scroll(0,1000)");
            jse.executeScript("scroll(0,-1000)");
            //jse.executeScript("Arguements[0].scrollIntoView(true;"locator");//sroll to specific element 
            String opHours = getText(driver,"//*[contains(@class, 'currentday')]",0,"getting operation hours");

            if(opHours == null){
                opHours = "Operation Hours do not exist";
            }//end of if statement

            label1 = new Label(1,i,studioInfo);
            writableSheet.addCell(label1);


            label2 = new Label(2,i,opHours);
            writableSheet.addCell(label2);

            System.out.println("---------------------------------------------");

        }//end of for loop

    }//end of testing

    @AfterMethod
    public void closeBrowser() throws IOException, WriteException {

        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();

    }//After method

}
