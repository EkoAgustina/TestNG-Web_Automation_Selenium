package helper;

import static helper.base_screen.*;
import org.testng.Assert;
import org.testng.internal.RunInfo;


public class base_expect {

    /**
     * Used to check whether an element is displayed or not
     * @param locator
     * @return Returns a boolean value
     */
    public static boolean isElementDisplayed(String locator) {
        try {
            return baseFind(locator).isDisplayed();
            
        } catch (Exception err) {
            return false;
        }
    }

    /**
     * Used to verify values on web pages in the context of automated testing, providing flexibility in defining conditions and data comparison types
     * @param locator
     * @param test_data
     * @param match
     * @param condition
     * @return
     */
    public static String equalData (String locator, String test_data, String match, String condition){
        String getText = baseFind(locator).getText();
        switch (match) {
            case "data":
                if (condition.equals("equal")) {
                    Assert.assertEquals(test_data, getText, "Expected value does not match actual value.");
                }
                else if(condition.equals("not equal")) {
                    Assert.assertNotEquals(test_data, getText, "Expected value should not be same as actual value.");
                }
                break;
            default:
                throw new RuntimeException("Unknown condition!");
        }
        return null;
    }
    
}
