package ru.ibs.framework.pages.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.SearchingResultPage;
import ru.ibs.framework.pages.ShoppingCartPage;

public class Header {

    private DriverManager driverManager = DriverManager.getInstance();
    private PageManager pageManager = PageManager.getInstance();
    private WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    private Actions act = new Actions(driverManager.getDriver());

    public Header() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='header-menu-wrapper']//input[@name='q']")
    private WebElement search;

    @FindBy(xpath = "//div[@class='header-menu-wrapper']//span[contains(@class, 'icon_search')]")
    private WebElement searchBtn;

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    private WebElement cartItemCount;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cartSum;



    public SearchingResultPage searchProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return pageManager.getSearchPage();
    }

    public Integer getCartSum() {
        return Integer.parseInt(cartSum.getText().replaceAll("[^0-9]", ""));
    }

    public WebElement getCartItemCount() {
        return cartItemCount;
    }

    public ShoppingCartPage showCart() {
        cartItemCount.click();
        return pageManager.getShoppingCartPage();
    }
}
