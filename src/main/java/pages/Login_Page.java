package pages;

import base.BrowserManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class Login_Page {

    WebDriver driver;
    String username;
    String password;

    Response apiResponse;

    public Login_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() throws InterruptedException {
        driver.get("https://www.nesine.com");
        Thread.sleep(4000);
    }

    //    @FindBy(id = "c-p-bn")
//    private WebElement acceptButton;
    @FindBy(name = "header-username-input")
    private WebElement usernameTextField;
    @FindBy(name = "header-password-input")
    private WebElement passwordTextField;
    @FindBy(css = "[data-test-id='header-login-btn']")
    private WebElement loginButton;

    @FindBy(css = "[class='nsn-btn active']")
    private WebElement futbolTabText;

    @FindBy(css = "#nesine-com > div.ui.page.modals.dimmer.transition.visible.active > div > div > div.actions > button")
    private WebElement popupTamamBtn;

    @FindBy(css = "#nsn-header-mobile-nav > div > div > div.ab295950b76125a21f90 > div.a6e1932ef3e6b1b31b3d > div.b39186c8acbd0ee2d94e > nav > ul > li:nth-child(2) > a")
    private WebElement hesabimText;

    @FindBy(css = "#nsn-header-mobile-nav > div > div > div.ab295950b76125a21f90 > div.a6e1932ef3e6b1b31b3d > div.b39186c8acbd0ee2d94e > nav > ul > li:nth-child(2) > ul > li:nth-child(7) > a")
    private WebElement cikisText;


    public void accepButtonClick() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("c-p-bn")));
        Thread.sleep(5000);
        acceptButton.click();
        Thread.sleep(3000);
    }

    public void enterCredentials() {
        try {
            // /getCredentials API'sini çağırarak kullanıcı adı ve şifreyi al
            Response response = RestAssured.get("http://localhost:3000/getCredentials");

            // API'den gelen yanıtı kontrol et
            if (response.getStatusCode() == 200) {
                // Kullanıcı adı ve şifreyi çıkart
                username = response.jsonPath().getString("data.username");
                password = response.jsonPath().getString("data.password");

                System.out.println("username = " + username);
                System.out.println("password = " + password);

                usernameTextField.sendKeys(username);
                passwordTextField.sendKeys(password);
            } else {
                throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // API isteği veya diğer hataları yakala
            e.printStackTrace();
        }
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(className = "popular-bets")
    private WebElement populerBahisTabText;


    public void openPopulerBahisFutbol(String url) {
        driver.get(url);
    }

    public void clickPopulerBahisTabClick() {
        populerBahisTabText.click();
    }

    public void populerBahisLinkTextAssert() {
        String currentPageUrl = driver.getCurrentUrl();
        assertEquals("https://www.nesine.com/iddaa/populer-bahisler", currentPageUrl, "Değerler birbirine eşleşmiyor.");
    }

    public void futbolTabLinkClick(String text) {
        if (!futbolTabText.getText().equals("text")) {
            assertEquals(text, futbolTabText.getText(), "Değerler birbirine eşleşmiyor.");
        } else {
            futbolTabText.click();
        }
    }

    public void checkApiResponseValue(String url) {
        apiResponse = RestAssured.get(url);
    }

    @FindBy(css = "#nsn-popularBets > div > div.wrapper > div.betList > div > div > div.matchCode > button > span")
    private List<WebElement> tableRowsOne;

    @FindBy(css = "#nsn-popularBets > div > div.wrapper > div.betList > div > div > div.playedCount")
    private List<WebElement> tableRowsLast;

    public void chechTableAndResponseValue() {
        // API'den gelen verileri al
        List<String> apiMarketNo = apiResponse.jsonPath().getList("d.PopularBetList.MarketNo");

        List<String> apiPlayedCounts = apiResponse.jsonPath().getList("d.PopularBetList.PlayedCount");

        System.out.println("apiMarketNo  apı = " + apiMarketNo);
        System.out.println("apiPlayedCounts  api= " + apiPlayedCounts);

        for (int i = 0; i < tableRowsOne.size(); i++) {
            String tableCode = tableRowsOne.get(i).getText();
            boolean containsMarketNo = apiMarketNo.contains(tableCode);
            assertEquals(containsMarketNo, true);
        }
        System.out.println("Oynanma sayıs= " + tableRowsLast.size());

        for (int j = 0; j < tableRowsLast.size(); j++) {
            String tablePlayedCount = (tableRowsLast.get(j)).getText().replace(".", "");
            boolean containsPlayedCount = apiPlayedCounts.contains(tablePlayedCount);
//            assertEquals(containsPlayedCount, true);
        }
    }

    public void clickOkBtn() throws InterruptedException {
        popupTamamBtn.click();
        Thread.sleep(2000);
    }


    public void clickLogout() throws InterruptedException {
        hesabimText.click();
        Thread.sleep(4000);
        cikisText.click();
        Thread.sleep(3000);

    }
}
