package helper;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import static mappings.mapper.*;

public class base_screen {
    static Properties prop=new Properties();
    public static WebDriver driver;

    /**
    * Used to provide a delay in program execution
    * @param duration duration
    */
    public static void base_sleep(int duration) throws InterruptedException {
        TimeUnit.SECONDS.sleep(duration);
    }

    /**
     * Used to wait for certain conditions on elements or events during testing
     * @param period
     */
    public static WebDriverWait wait (int period){
        WebDriverWait wait = new WebDriverWait(driver, period);
        return wait;
    }

    /**
     * Used to start a test session with the appropriate browser.
     * @param browser
     */
    public static WebDriver browserDriver(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "headless":
                ChromeOptions chromeHeadless = new ChromeOptions();
                chromeHeadless.addArguments("--remote-allow-origins=*","ignore-certificate-errors","--headless");
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver(chromeHeadless);
                break;
            default:
                throw new Error("Browser not recognized");
        }
        return driver;
    }

    /**
     * Used to search for elements on a web page using Selenium WebDriver
     * @param locator
     * @return returns web elements
     */
    public static WebElement baseFind (String locator) {
        WebElement el = null;

        try {
            WebElement webElement = driver.findElement(locatorParser(prop.getProperty("locator", locator)));
            el = (WebElement) wait(10).until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException err) {
            throw err;
        }
        return el;
    }

    /**
     * Used to ensure that the page has fully loaded before fetching or interacting with elements
     * @param driver
     * @param durationInSeconds
     */
    public static void pageLoad(WebDriver driver, int durationInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, durationInSeconds);

        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        });
    }
    
    /**
     * Used to take screenshots
     * @param screenshotName
     */
    public static byte[] captureScreen (String screenshotName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        try {
            FileUtils.copyFile(screenshotFile, new File("./screenshot/"+screenshotName+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenshot;
    }

}



