package stepDefinitions;

import base.BrowserManager;
import io.cucumber.java.en.Given;
import pages.Login_Page;
import pages.MoreOneBrowserExample_Page;
import runners.MoreOneBrowserRunner;

public class MoreBrowserExample_Steps extends BrowserManager {

    MoreOneBrowserExample_Page moreOneBrowserExamplePage;
    @Given("I am on the Nesine.com login page using")
    public void iAmOnTheNesineComLoginPageUsing() throws InterruptedException {
        String selectedBrowser = MoreOneBrowserRunner.getBrowser();
        super.initializeDriver(selectedBrowser);
        moreOneBrowserExamplePage = new MoreOneBrowserExample_Page(driver);
        moreOneBrowserExamplePage.browseropen();;
        Thread.sleep(4000);
    }
}
