package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

import static helper.base_screen.*;



public class driverHooks {
    public static Scenario scenario;
    
    @Before
    public void testPreparation (Scenario scenario) throws InterruptedException {
        String browser = System.getenv("browser");
        System.out.println("Test is run using " + browser);
        this.scenario = scenario;
        base_sleep(2);
        
    }

    @BeforeStep
    public void beforeStep () throws InterruptedException {
        base_sleep(1);
    }

    @AfterStep
    public void afterStep () throws InterruptedException {
        base_sleep(1);
    }

    @After
    public void afterTest () throws InterruptedException {
        base_sleep(2);
        if (scenario.isFailed()) {
            scenario.attach(captureScreen(scenario.getName() + "Failed"), "image/png", "FailedStepScreenshot");
        }
            
        driver.close();
    }
}
