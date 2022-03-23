package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.managers.DriverManager;

public class MortgagePage extends BasePage {

  @FindBy(xpath = "//div[contains(@class, 'lg-top_40')]//h1[contains(@class, 'header')]")
  private WebElement title;

  @FindBy(xpath = "//h2[contains(text(), 'Рассчитайте')]")
  private WebElement calcTitle;

  @FindBy(xpath = "//iframe[@id='iFrameResizer0']")
  private WebElement iframe;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'realty-cost')]//input")
  private WebElement realtyCost;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'initial-fee-input')]//input")
  private WebElement initialFee;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'credit')]//input")
  private WebElement creditTerm;

  @FindBy(
      xpath =
          "//span[@class='_1PGdMG1naHFzrroHZcN0Sd' and contains(text(), 'Страхование')]/../..//label/div")
  private WebElement insuranceCheck;

  @FindBy(
      xpath =
          "//div[contains(@data-test-id, 'main')]//li[contains(@data-e2e-id, 'credit-sum')]//span/span")
  private WebElement creditSum;

  @FindBy(xpath = "//div[contains(@data-test-id, 'main')]//li[contains(@data-e2e-id, 'credit-rate')]//span/span")
  private WebElement creditRate;

  @FindBy(xpath = "//div[contains(@data-test-id, 'main')]//li[contains(@data-e2e-id, 'monthly-payment')]//span/span")
  private WebElement monthlyPayment;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'income-required')]//div[@class='_3FDuwMMlfn70Wl-s4FooMs']//span/span")
  private WebElement requiredIncome;

  public MortgagePage checkOpenPage() {
    waitUntilElementToBeVisible(title);
    Assertions.assertTrue(
        title.getText().contains("готовые квартиры"),
        "Заголовок отсутствует/не соответствует требуемому");
    return this;
  }

  public MortgagePage fillField(String nameField, String value) {
    WebElement element = null;
    switch (nameField) {
      case "Стоимость недвижимости":
        driverManager.getDriver().switchTo().frame(iframe);
        fillInputField(realtyCost, value);
        element = realtyCost;
        break;
      case "Первоначальный взнос":
        fillInputField(initialFee, value);
        element = initialFee;
        break;
      case "Срок кредита":
        fillInputField(creditTerm, value);
        element = creditTerm;
        break;
      default:
        Assertions.fail(
            "Поле с наименованием '"
                + nameField
                + "' отсутствует на странице "
                + "'Ипотека на вторичное жильё'");
    }
    return this;
  }

  public MortgagePage disableInsuranceCheck() {
    waitUntilElementToBeClickable(insuranceCheck).click();
    Assertions.assertEquals(
        "false", insuranceCheck.findElement(By.xpath(".//input")).getAttribute("aria-checked"));

    return this;
  }

  public MortgagePage assertCorrectData(String nameData, String value) {


    switch (nameData) {
      case "Сумма кредита":
        driverManager.getDriver().switchTo().defaultContent();
        scrollToElementJs(calcTitle);
        waitUntilElementToBeVisible(calcTitle);
        driverManager.getDriver().switchTo().frame(iframe);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Assertions.assertEquals(
            value, creditSum.getText().trim().replaceAll("[^0-9]", ""), "Ошибка расчёта кредита.");
        break;
      case "Ежемесячный платёж":
        Assertions.assertEquals(
            value,
            monthlyPayment.getText().trim().replaceAll("[^0-9]", ""),
            "Ошибка расчёта ежемесечного платежа.");
        break;
      case "Необходимый доход":
        Assertions.assertEquals(
            value,
            requiredIncome.getText().trim().replaceAll("[^0-9]", ""),
            "Ошибка расчёта необходимого дохода.");
        break;
      case "Процентная ставка":
        Assertions.assertEquals(
            value,
            creditRate.getText().trim().replaceAll("[^0-9]", ""),
            "Ошибка расчёта процентной ставки кредита.");
        break;
      default:
        Assertions.fail(
            "Данные по '"
                + nameData
                + "' отсутствует на странице "
                + "'Ипотека на вторичное жильё'");
    }
    return this;
  }
}
