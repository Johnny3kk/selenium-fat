package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartPageStep {

  private static final Logger logger = LoggerFactory.getLogger(StartPageStep.class);

  private final PageManager pageManager = PageManager.getInstance();

  @И("^Закрыть сообщение cookies$")
  public void closeCookieDialog() {
    logger.info("Открытие стартовой страницы и закрытие всплывающего уведомления об использовании cookie");
    pageManager.getStartPage().checkOpenPage().closeCookieDialog();
    logger.info("Закрыто уведомления об использовании cookie");
  }

  @И("^Выбираем \"(.+)\" в главном меню$")
  public void selectBaseMenu(String nameBaseMenu) {
      pageManager.getStartPage().selectMortgageMenu(nameBaseMenu);
      logger.info("Нажата опция главного меню");
  }

  @И("^Выбираем \"(.+)\" в подменю главного меню$")
  public void selectSubMenu(String nameSubMenu) {
      pageManager.getStartPage().selectCreditForRealty(nameSubMenu);
      logger.info("Нажата опция подменю");
  }

}
