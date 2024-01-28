package helper;

import static helper.base_screen.*;

import org.openqa.selenium.NoSuchElementException;

public class base_fill {

    /**
     * Used to simulate typing or keyboard input to an element
     * @param locator
     * @param testData
     */
    public static void actionFill (String locator, String testData) {
        try {
            baseFind(locator).sendKeys(testData);
        } catch (NoSuchElementException err) {
            throw err;
        }
    }
    
}
