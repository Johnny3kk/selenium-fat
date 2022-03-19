package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

  @FindBy(xpath = "//a[contains(text(), 'Да')]")
  private WebElement confirmCityBtn;

  public StartPage checkOpenPage() {
    Assertions.assertEquals(
        "DNS – интернет магазин цифровой и бытовой техники по доступным ценам.",
        driverManager.getDriver().getTitle(),
        "Нужная страница не загрузилась.");
    return pageManager.getStartPage();
  }

  public StartPage confirmCity() {
    confirmCityBtn.click();
    return pageManager.getStartPage();
  }


}
