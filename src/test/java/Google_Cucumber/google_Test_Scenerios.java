package Google_Cucumber;

import Resuable_Classes.Reusable_Methods;
import Resuable_Classes.Reusable_Methods_loggers;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class google_Test_Scenerios extends Reusable_Methods {
    WebDriver driver;
    @Given("^I navigate to google.com$")
        public void openBrowser() throws IOException {
            driver = navigate(driver,"https://www.google.com/");
    }

    @When("^I verify my Google Home title$")
    public void verifyHomeTitle(){
        getTitle(driver,"Google");
    }
    @Then("^I enter a keyword in my search field$")
    public void enter_search_field(){
        sendKeys(driver,"//*[@name='q']",0,"Searching","cars");
    }
    @And("^I click on Search Icon$")
    public void clickingButton(){
        click(driver,"//*[@name='btnk']",0,"Clicking Search Button");
    }

    //@When("^I verify the Google Search$")
  //  public void VerifySearch()


    }

