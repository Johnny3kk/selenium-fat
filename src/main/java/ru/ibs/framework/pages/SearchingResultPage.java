package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchingResultPage extends BasePage{

    WebDriver driver;
    WebDriverWait wait;
    Actions act = new Actions(driver);

    @FindBy(xpath = "//a[@id= 'header-logo']")
    private WebElement logo;

    @FindBy(xpath = "//h1[@class = 'title']")
    private WebElement title;

    public void checkOpenPage() {
        wait.until(ExpectedConditions.visibilityOf(title));
        Assertions.assertTrue(title.isDisplayed(), "Нужная страница не загрузилась.");
    }


}
