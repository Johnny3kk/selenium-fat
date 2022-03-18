package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends BasePage {

  @FindBy(xpath = "//a[@id= 'header-logo']")
  private WebElement logo;

  @FindBy(xpath = "//input[@class = 'ui-input-search__input ui-input-search__input_presearch']")
  private WebElement search;

  public void checkOpenPage() {
    wait.until(ExpectedConditions.visibilityOf(logo));
    Assertions.assertEquals(
        "DNS – интернет магазин цифровой и бытовой техники по доступным ценам.",
        driverManager.getDriver().getTitle(),
        "Нужная страница не загрузилась.");
  }

  /**
   * Использование поисковой строки каталога магазина
   *
   * @param productName - текст поискового запроса
   */
  public void searchByProductName(String productName) {
    search.sendKeys(productName);
    wait.until(ExpectedConditions.attributeContains(search, "value", productName));
    act.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
  }
}
