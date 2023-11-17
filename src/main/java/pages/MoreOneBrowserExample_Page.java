package pages;

import base.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MoreOneBrowserExample_Page {
    WebDriver driver;
    public MoreOneBrowserExample_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void browseropen() throws InterruptedException {
        driver.get("https://www.nesine.com");
        Thread.sleep(4000);
    }
}
