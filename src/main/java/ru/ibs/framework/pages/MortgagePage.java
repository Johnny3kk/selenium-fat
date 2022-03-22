package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MortgagePage extends BasePage {

  @FindBy(xpath = "//h1[contains(@class, 'header')]")
  private WebElement title;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'realty-cost')]//input")
  private WebElement realtyCost;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'initial-fee-input')]//input")
  private WebElement initialFee;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'credit')]//input")
  private WebElement creditTerm;

  @FindBy(
      xpath =
          "//span[@class='_1PGdMG1naHFzrroHZcN0Sd' and contains(text(), 'Страхование')]/../..//input")
  private WebElement insuranceCheck;

  @FindBy(xpath = "//li[contains(@data-e2e-id, 'credit-sum')]//span/span")
  private WebElement creditSum;

  @FindBy(xpath = "//li[contains(@data-e2e-id, 'credit-rate')]//span/span")
  private WebElement creditRate;

  @FindBy(xpath = "//li[contains(@data-e2e-id, 'monthly-payment')]//span/span")
  private WebElement monthlyPayment;

  @FindBy(xpath = "//div[contains(@data-e2e-id, 'income-required')]//span/span")
  private WebElement requiredIncome;

  public MortgagePage checkOpenPage() {
    Assertions.assertTrue(
        title.getText().contains("готовые квартиры"),
        "Заголовок отсутствует/не соответствует требуемому");
    return this;
  }

  public MortgagePage fillField(String nameField, String value) {
    WebElement element = null;
    switch (nameField) {
      case "Стоимость недвижимости":
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
    wait.until(ExpectedConditions.attributeToBe(element, "value", value));
    Assertions.assertEquals(
        "Поле '" + nameField + "' было заполнено некорректно",
        value,
        element.getAttribute("value"));
    return this;
  }

  public MortgagePage disableInsuranceCheck() {
    waitUntilElementToBeClickable(initialFee).click();
    Assertions.assertEquals(initialFee.getCssValue("aria-checked"), "false");
    waitUntilElementToBeClickable(initialFee);
    return this;
  }

  public MortgagePage assertCorrectData(String nameData, String value) {
    switch (nameData) {
      case "Сумма кредита":
        Assertions.assertEquals(
            creditSum.getText().trim().replaceAll("[^0-9]", ""), value, "Ошибка расчёта кредита.");
        break;
      case "Ежемесячный платёж":
        Assertions.assertEquals(
            monthlyPayment.getText().trim().replaceAll("[^0-9]", ""),
            value,
            "Ошибка расчёта ежемесечного платежа.");
        break;
      case "Необходимый доход":
        Assertions.assertEquals(
            requiredIncome.getText().trim().replaceAll("[^0-9]", ""),
            value,
            "Ошибка расчёта необходимого дохода.");
        break;
      case "Процентная ставка":
        Assertions.assertEquals(
            creditRate.getText().trim().replaceAll("[^0-9]", ""),
            value,
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
