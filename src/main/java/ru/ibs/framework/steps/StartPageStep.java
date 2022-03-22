package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class StartPageStep {

  private final PageManager pageManager = PageManager.getInstance();

  @И("^Закрыть сообщение cookies$")
  public void closeCookieDialog() {
    pageManager.getStartPage().closeCookieDialog();
  }

  @И("^Выбираем \"(.+)\" в главном меню$")
  public void selectBaseMenu(String nameBaseMenu) {
      pageManager.getStartPage().selectMortgageMenu(nameBaseMenu);
  }

  @И("^Выбираем \"(.+)\" в подменю главного меню$")
  public void selectSubMenu(String nameSubMenu) {
      pageManager.getStartPage().selectCreditForRealty(nameSubMenu);
  }

}
