package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.blocks.Header;

public class BasePage {

  protected DriverManager driverManager = DriverManager.getInstance();
  protected PageManager pageManager = PageManager.getInstance();
  protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 13, 1000);
  protected Actions act = new Actions(driverManager.getDriver());
  private Header header = new Header();

  public BasePage() {
    PageFactory.initElements(driverManager.getDriver(), this);
  }

  public Header getHeader() {
    return header;
  }

  protected WebElement waitUntilElementToBeClickable(WebElement element) {
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  protected WebElement waitUntilElementToBeVisible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected WebElement waitUntilElementToBeVisible(WebElement element) {
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected void scrollToElementJs(WebElement element) {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  protected void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
    waitUntilElementToBeVisible(element);
    waitUntilElementToBeClickable(element);
    element.click();
    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    element.sendKeys(Keys.DELETE);
    element.clear();
    element.sendKeys(value);
//    wait.until(ExpectedConditions.textToBePresentInElement(element, value));
//    boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
//    Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
  }
}
