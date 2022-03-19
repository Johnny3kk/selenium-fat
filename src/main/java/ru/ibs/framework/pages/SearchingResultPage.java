package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchingResultPage extends BasePage {

  @FindBy(xpath = "//h1[@class = 'title']")
  private WebElement title;

  @FindBy(
      xpath =
          "//button[contains(text(), 'Купить')]//../../a/span[contains(text(), 'консоль') and contains(text(), 'серый')]")
  private WebElement nintendo;

  public SearchingResultPage checkOpenPage() {
    wait.until(ExpectedConditions.visibilityOf(title));
    Assertions.assertTrue(title.isDisplayed(), "Нужная страница не загрузилась.");
    return this;
  }

  public ProductPage openNintendoPage() {
    nintendo.click();
    return pageManager.getProductPage();
  }
}
