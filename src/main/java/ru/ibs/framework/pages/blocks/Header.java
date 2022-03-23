package ru.ibs.framework.pages.blocks;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.BasePage;

import java.util.List;

public class Header {

    private DriverManager driverManager = DriverManager.getInstance();
    private PageManager pageManager = PageManager.getInstance();
    private WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    private Actions act = new Actions(driverManager.getDriver());

    public Header() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    @FindBy(xpath = "//img[@class='header__logo-image']")
    private WebElement sberlogo;

    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item_first')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@data-cga_click_top_menu]")
    private List<WebElement> listSubMenu;

    public void selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
                return;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
    }

    public void selectSubMenu(String nameSubMenu) {
        wait.until(ExpectedConditions.visibilityOf(driverManager.getDriver().findElement(By.xpath("//a[contains(text(), 'Оформить ипотеку')]"))));
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
                return;
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
    }

    public WebElement getSberlogo() {
        return sberlogo;
    }

}
