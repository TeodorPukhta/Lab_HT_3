package SmokeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;
import util.PropertiesReader;
import util.XMLReader;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertTrue;

public class BacketTest {
    private WebDriver webDriver;
    private PropertiesReader propertiesReader;
    private XMLReader xmlReader;
    @BeforeMethod
    public void testSetUp() {
        webDriver = new ChromeDriver();
        propertiesReader = new PropertiesReader();
        xmlReader = new XMLReader();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get(propertiesReader.getURL());
    }

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }

    @Test(priority = 1)
    public void checkThatProductIsInBucketAndBlowPrice() throws InterruptedException {
        HomePage homePage = new HomePage(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSearchField()));
        homePage.searchByKeyWord(xmlReader.getProduct());
        SearchResultPage searchResultPage = new SearchResultPage(webDriver);
        searchResultPage.waitForPageToLoad();
        searchResultPage.scrollToBrandFilterInput();
        searchResultPage.inputBrandNameInSearchFilterField(xmlReader.getBrand());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(searchResultPage.getXpathOfListOfBrandCheckboxes()),1));
        searchResultPage.clickOnBrandFilterCheckBox();
        try {
            searchResultPage.clickOnAddProductToBasketButton();
        } catch (Exception e){
            wait.until(ExpectedConditions.elementToBeClickable(searchResultPage.getAddProductToBasketButton()));
            searchResultPage.clickOnAddProductToBasketButton();
        }
        searchResultPage.clickOnBasketIcon();
        wait.until(ExpectedConditions.visibilityOf(searchResultPage.getSumPrice()));
        assertTrue(xmlReader.getPrice() < Integer.parseInt(searchResultPage.getSumPrice().getText()));
        assertTrue(searchResultPage.getListOfProductsInBasket().size() == 1);
        Thread.sleep(10000);
    }
    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }
}
