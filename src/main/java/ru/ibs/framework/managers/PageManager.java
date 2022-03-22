package ru.ibs.framework.managers;

import ru.ibs.framework.pages.MortgagePage;
import ru.ibs.framework.pages.StartPage;

public class PageManager {

  private static PageManager INSTANCE = null;
  private StartPage startPage;
  private MortgagePage mortgagePage;

  private PageManager() {}

  public static PageManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PageManager();
    }
    return INSTANCE;
  }

  public StartPage getStartPage() {
    if (startPage == null) {
      startPage = new StartPage();
    }
    return startPage;
  }

  public MortgagePage getMortgagePage() {
    if (mortgagePage == null) {
      mortgagePage = new MortgagePage();
    }
    return mortgagePage;
  }
}
