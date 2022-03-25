package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;

public class MortgagePageStep {

  private static final Logger logger = LoggerFactory.getLogger(MortgagePageStep.class);

  private final PageManager pageManager = PageManager.getInstance();

  @И("^Заполнить поля:$")
  public void fillFields(DataTable mapFieldsAndValue) {
    logger.info("Начало заполнения полей");
    mapFieldsAndValue
        .asMap(String.class, String.class)
        .forEach(
            (key, value) -> pageManager.getMortgagePage().fillField((String) key, (String) value));
    logger.info("Поля заполнены");
  }

  @И("^Убрать опцию 'Страхование жизни'$")
  public void disableInsuranceCheck() {
    pageManager.getMortgagePage().disableInsuranceCheck();
    logger.info("Опция выключена");
  }

  @И("^Проверить значения полей:$")
  public void checkFields(DataTable mapFieldsAndValue) {
    logger.info("Начало проверки полей");
    mapFieldsAndValue
        .asMap(String.class, String.class)
        .forEach(
            (key, value) ->
                pageManager.getMortgagePage().assertCorrectData((String) key, (String) value));
    logger.info("Проверка полей завершена");
  }
}
