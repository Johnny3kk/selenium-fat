package ru.ibs.framework.managers;

import ru.ibs.framework.pages.*;

public class PageManager {

  private static PageManager INSTANCE = null;

  private StartPage startPage;
  private SearchingResultPage searchPage;
  private ProductPage productPage;
  private ShoppingCartPage shoppingCartPage;

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

  public SearchingResultPage getSearchPage() {
    if (searchPage == null) {
      searchPage = new SearchingResultPage();
    }
    return searchPage;
  }

  public ProductPage getProductPage() {
    if (productPage == null) {
      productPage = new ProductPage();
    }
    return productPage;
  }

  public ShoppingCartPage getShoppingCartPage() {
    if (shoppingCartPage == null) {
      shoppingCartPage = new ShoppingCartPage();
    }
    return shoppingCartPage;
  }
}
