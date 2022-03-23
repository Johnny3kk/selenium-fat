package ru.ibs.framework.pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.managers.DriverManager;

public class StartPage extends BasePage {

  @FindBy(xpath = "//button[contains(@class, 'cookie')]")
  private WebElement cookieClose;

  public StartPage checkOpenPage() {
    waitUntilElementToBeVisible(getHeader().getSberlogo());
    waitUntilElementToBeClickable(getHeader().getSberlogo());
    return this;
  }

  public StartPage closeCookieDialog() {
    waitUntilElementToBeVisible(cookieClose);
    waitUntilElementToBeClickable(cookieClose).click();
    return this;
  }

  public StartPage selectMortgageMenu(String nameBaseMenu) {
    getHeader().selectBaseMenu(nameBaseMenu);
    return this;
  }

  public MortgagePage selectCreditForRealty(String nameSubMenu) {
    getHeader().selectSubMenu(nameSubMenu);
    return pageManager.getMortgagePage().checkOpenPage();
  }
}
