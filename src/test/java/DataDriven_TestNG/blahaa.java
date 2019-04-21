package DataDriven_TestNG;

import Resuable_Classes.Reusable_Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;


public class blahaa extends Reusable_Methods {

    WebDriver driver;
    JavascriptExecutor jse;

    @BeforeMethod

    public void OpenBroswer () throws IOException {

        driver = navigate(driver, "https://www.yahoo.com");

        jse=(JavascriptExecutor)driver;

    }


    @Test

    public void testing(){

        driver.navigate().to("https://www.yahoo.com");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Yahoo",driver.getTitle());

        List<WebElement>counting = driver.findElements(By.xpath("//span[contains(@class,'Mstart(2')]"));

        System.out.println("The amount is " + counting.size());

        sendKeys(driver,"//*[@name = 'p']",0,"Entering in search bar","Nutrition");

        click(driver,"//*[@id = 'uh-search-button']",0,"Clicking search");

        jse.executeScript("scroll(0,1000)");

        String result = getText(driver,"//*[contains(text(),'35,200,000 results')]",0,"getting result");

        System.out.println(result);

        jse.executeScript("scroll(0,-1000)");




    }

    //@AfterMethod


}
