package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions", // Senaryoların ve step tanımlamalarının olduğu paket
        plugin = {"pretty", "json:target/cucumber.json", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
)
public class RunCucumberTest {
}
