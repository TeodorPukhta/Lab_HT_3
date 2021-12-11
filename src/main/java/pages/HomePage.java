package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getSearchField(){
        return webDriver.findElement(By.name("search"));
    }
    public WebElement getSearchButton(){
        return webDriver.findElement(By.xpath("//button[contains(@class,'search-form__submit')]"));
    }
    public void searchByKeyWord(final String keyword){
        getSearchField().sendKeys(keyword);
        getSearchButton().click();
    }
}
