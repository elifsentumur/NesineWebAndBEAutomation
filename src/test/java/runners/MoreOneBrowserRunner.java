package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "src/test/java/stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class MoreOneBrowserRunner {

    public static final String BROWSER_PROPERTY = "browser";
// Burada firefox, chrome, edge vs gıbı browserları tanımlayıp koşum yapılır
    public static String getBrowser() {
        return System.getProperty(BROWSER_PROPERTY, "firefox");
    }
}