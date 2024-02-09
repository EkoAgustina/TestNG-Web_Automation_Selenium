package cucumber.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.FileNotFoundException;

import static helper.base_screen.*;
import static helper.base_fill.*;
import static helper.base_expect.*;
import static helper.base_click.*;
import static mappings.mapper.*;
import static hooks.driverHooks.*;



public class jobDetailSteps {
    WebDriver myBrowser;
    String path_screenshot;
   

    @Given("^User open \"(.*)\"$")
    public void userOpenWith(String url) throws Exception {
        String browser = System.getenv("browser");
        if (continuousIntegration != null && continuousIntegration) {
            String remoteUrl = "http://"+browser.split(":")[0]+":"+browser.split(":")[1]+"/wd/hub";
            System.out.println(remoteUrl);
            myBrowser = browserDriver(remoteUrl);
            myBrowser.manage().window().maximize();
            myBrowser.get(url);
        }
        else {
            myBrowser = browserDriver(browser);
            myBrowser.manage().window().maximize();
            myBrowser.get(url);
        }

        // pageLoad(3);
    }

    @And("^User click \"(.*)\"$")
    public void userClick(String element) throws FileNotFoundException {
        try{
            actionClick(key_element(element));
        } catch (Exception err) {
            throw new RuntimeException("Failed to click, with original error: " + err.getMessage());
        }
    }

    @And("^Wait (.*) seconds$")
    public void userWaitSeconds(int time) throws InterruptedException {

        try {
            base_sleep(time);
        } catch (Exception err){
            throw new RuntimeException("Failed to wait, with original error: "+err.getMessage());
        }
    }

    @And("^Fill in \"(.*)\" with \"(.*)\"$")
    public void userFillsInWith(String element, String test_data) {
        try{
            actionFill(key_element(element),key_data(test_data));
        }catch (Exception err){
            throw new RuntimeException("Failed to fill, with original error: "+err.getMessage());
        }
    }

    @Then("^Element \"(.*)\" (is displayed|not displayed)")
    public void verifyElementWillBeDisplayed(String element, String condition){
        switch (condition) {
            case "is displayed":
                Assert.assertTrue(
                    isElementDisplayed(key_element(element)),
                    " Element '" + key_element(element) + "' "+ "is not displayed!"
                );
                break;
            case "not displayed":
                Assert.assertFalse(isElementDisplayed(
                    key_element(element)), 
                    " Element '" + key_element(element) + "' "+ "is displayed, not as expected!"
                    );
                break;
            default:
                throw new RuntimeException ("Unknown condition!");
        }
        
    }

    @Then("^Element \"(.*)\" is (equal|not equal) with (data|regex) \"(.*)\"$")
    public void VerifyValueIsWithData(String element, String condition,String match, String test_data ) {
        try {
            equalData(key_element(element),key_data(test_data),match,condition);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Then("^User take screenshot with file name \"(.*)\"$")
    public void userTakesScreenshotWithFileName(String screenshotName) {
        try {
            scenario.attach(captureScreen(screenshotName), "image/png", screenshotName);
        }
        catch (Exception e){
            throw e;
        }

    }
    
}
