package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchResultPage extends BasePage{
    private String LIST_OF_BRAND_CHECKBOXES = "//div[@data-filter-name='producer']//a[@class='checkbox-filter__link']";

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getSearchBrandField(){
        return webDriver.findElement(By.xpath("//div[@data-filter-name='producer']//input[@name]"));
    }

    public List<WebElement> getListOfBrandCheckBoxes(){
        return webDriver.findElements(By.xpath(LIST_OF_BRAND_CHECKBOXES));
    }
    public void clickOnBrandFilterCheckBox(){
        getListOfBrandCheckBoxes().get(0).click();
    }
    public void inputBrandNameInSearchFilterField(final String brand){
        getSearchBrandField().sendKeys(brand);
    }
    public WebElement getAddProductToBasketButton(){
        return webDriver.findElements(By.xpath("//button[contains(@class,'buy-button')]")).get(0);
    }
    public void clickOnAddProductToBasketButton(){
        getAddProductToBasketButton().click();
    }
    public WebElement getBasketIcon(){
        return webDriver.findElement(By.xpath("//li[contains(@class,'item--cart')]"));
    }
    public void clickOnBasketIcon(){
        getBasketIcon().click();
    }
    public String getXpathOfListOfBrandCheckboxes() {
        return LIST_OF_BRAND_CHECKBOXES;
    }
    public WebElement getSumPrice(){
        return webDriver.findElement(By.xpath("//div[@class='cart-receipt__sum-price']//span[not(@*)]"));
    }
    public List<WebElement> getListOfProductsInBasket(){
        return webDriver.findElements(By.xpath("//ul[@class='cart-list ng-star-inserted']//li[@class='cart-list__item ng-star-inserted']"));
    }
    public void scrollToBrandFilterInput(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(getSearchBrandField()).build().perform();
    }
}
