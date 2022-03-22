package ru.ibs.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

  @FindBy(xpath = "//button[contains(@class, 'cookie')]")
  private WebElement cookieClose;

  public StartPage closeCookieDialog() {
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
