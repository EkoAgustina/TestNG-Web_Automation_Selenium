package helper;

import static helper.base_screen.*;

public class base_click {
    
    public static void actionClick (String locator) {
        try {
            baseFind(locator).click();
        } catch (Exception err) {
            throw err;
        }
    }
}
