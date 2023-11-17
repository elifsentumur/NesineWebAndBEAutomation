import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GalenTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginPageLayout() throws IOException {
        driver.get("https://www.nesine.com");

        // Galen test senaryosunu burada oluşturun
        LayoutReport layoutReport = Galen.checkLayout(driver, "galen-specs/loginPage.gspec", Arrays.asList("desktop"));

        // Galen raporunu oluşturun
        List<GalenTestInfo> tests = new LinkedList<>();
        GalenTestInfo test = GalenTestInfo.fromString("Test Login Page Layout");
        test.getReport().layout(layoutReport, "Login Page Layout");
        tests.add(test);

        // Raporu HTML olarak çıktı alın
        new HtmlReportBuilder().build(tests, "target/galen-reports");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
