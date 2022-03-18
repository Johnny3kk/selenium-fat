package ru.ibs.appline.tests.dns;

import org.junit.jupiter.api.Test;
import ru.ibs.appline.tests.base.BaseTests;
import ru.ibs.framework.pages.StartPage;

public class BuySwitchScenarioTest extends BaseTests {

  @Test
  public void testScenario() {
pageManager.getStartPage().checkOpenPage();
pageManager.getStartPage().searchByProductName("switch");
  }
}
