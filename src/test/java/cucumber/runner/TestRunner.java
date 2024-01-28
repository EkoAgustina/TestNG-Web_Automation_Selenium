package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber/features",
    glue = {"cucumber.steps","hooks"},
    plugin = {"json:target/cucumber-reports/cucumber.json", "pretty","html:report/cucumber/report.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = false,
    publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
}
