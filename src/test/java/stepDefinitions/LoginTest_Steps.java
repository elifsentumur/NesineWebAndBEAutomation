package stepDefinitions;


import base.BrowserManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.Login_Page;
import pages.MoreOneBrowserExample_Page;
import runners.MoreOneBrowserRunner;

public class LoginTest_Steps extends BrowserManager {

    private Login_Page loginPage;


    @Given("the user is on the Nesine.com login page")
    public void theUserIsOnTheNesineComLoginPage() throws InterruptedException {
//        super.initializeDriver();
//        loginPage = new Login_Page(driver);
//        loginPage.open();
//        Thread.sleep(4000);
        String selectedBrowser = MoreOneBrowserRunner.getBrowser();
        super.initializeDriver(selectedBrowser);
        loginPage = new Login_Page(driver);
        loginPage.open();;
        Thread.sleep(4000);
    }

    @When("the user is click accept button")
    public void theUserIsClickAcceptButton() throws InterruptedException {
        loginPage.accepButtonClick();
    }

    @When("the users logs in with valid credentials from \\/getCredentials API")
    public void theUsersLogsInWithValidCredentialsFromGetCredentialsAPI() {
        loginPage.enterCredentials();
    }


    @And("the user click on the login button")
    public void theUserClickOnTheLoginButton() throws InterruptedException {
        loginPage.clickLoginButton();
        Thread.sleep(5000);
    }


    @And("I check the page link")
    public void iCheckThePageLink() {
        loginPage.populerBahisLinkTextAssert();
    }

    @When("I click the Popüler Bahisler button")
    public void iClickThePopulerBahislerButton() {
        loginPage.clickPopulerBahisTabClick();
    }

    @And("I select the {string} tab")
    public void iSelectTheTab(String text) throws InterruptedException {
        Thread.sleep(1000);
        loginPage.futbolTabLinkClick(text);
    }

    @And("I send a GET request to {string}")
    public void iSendAGETRequestTo(String text) {
        loginPage.checkApiResponseValue(text);
    }

    @Then("I compare played counts between the response and the webpage")
    public void iComparePlayedCountsBetweenTheResponseAndTheWebpage() {
        loginPage.chechTableAndResponseValue();
    }

    @Then("the user should be  logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {

    }

    @And("Click on the popup ok button")
    public void clickOnThePopupOkButton() throws InterruptedException {
        loginPage.clickOkBtn();
    }

    @And("Go to Logout")
    public void goToLogout() throws InterruptedException {
        loginPage.clickLogout();
    }
}
