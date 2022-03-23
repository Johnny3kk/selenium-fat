package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.openqa.selenium.By;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;

public class MortgagePageStep {

  private final DriverManager driverManager = DriverManager.getInstance();
  private final PageManager pageManager = PageManager.getInstance();

  @И("^Заполнить поля:$")
  public void fillFields(DataTable mapFieldsAndValue) {
    driverManager
        .getDriver()
        .switchTo()
        .frame(driverManager.getDriver().findElement(By.xpath("//iframe[@id='iFrameResizer0']")));
    mapFieldsAndValue
        .asMap(String.class, String.class)
        .forEach(
            (key, value) -> pageManager.getMortgagePage().fillField((String) key, (String) value));
  }

  @И("^Убрать опцию 'Страхование жизни'$")
  public void disableInsuranceCheck() {
    pageManager.getMortgagePage().disableInsuranceCheck();
  }

  @И("^Проверить значения полей:$")
  public void checkFields(DataTable mapFieldsAndValue) {
    mapFieldsAndValue
        .asMap(String.class, String.class)
        .forEach(
            (key, value) ->
                pageManager.getMortgagePage().assertCorrectData((String) key, (String) value));
  }
}
