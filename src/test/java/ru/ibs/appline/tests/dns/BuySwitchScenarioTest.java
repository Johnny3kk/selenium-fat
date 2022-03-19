package ru.ibs.appline.tests.dns;

import org.junit.jupiter.api.Test;
import ru.ibs.appline.tests.base.BaseTests;
import ru.ibs.framework.utils.Product;

public class BuySwitchScenarioTest extends BaseTests {

  @Test
  public void testScenario() {

    pageManager
        .getStartPage()
        .checkOpenPage()
        .confirmCity()
        .getHeader()
        .searchProduct("Switch")
        .checkOpenPage()
        .openNintendoPage()
        .checkOpenPage();

    Product nintendo = pageManager.getProductPage().createProduct();

    pageManager
        .getProductPage()
        .buyProduct()
        .checkOpenPage()
        .getHeader()
        .searchProduct("mario odyssey");

    pageManager.getProductPage().checkOpenPage().buyProduct();

    Product marioGame = pageManager.getProductPage().createProduct();

    pageManager.getProductPage().waitUntilCartGetItem().cartSumEquals(nintendo, marioGame);

    pageManager
        .getProductPage()
        .getHeader()
        .showCart()
        .checkOpenPage()
        .compareItemsInCart(nintendo, marioGame)
        .removeNintendoFromCart()
        .nintendoIsNotInCart(marioGame)
        .plusItems(marioGame)
        .restoreDeleted(nintendo);

  }
}
