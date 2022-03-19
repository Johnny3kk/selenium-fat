package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.utils.Product;

public class ShoppingCartPage extends BasePage {

  @FindBy(xpath = "//span[contains(@class, 'select-all')]")
  private WebElement selectAll;

  @FindBy(xpath = "//h1[@class='cart-title']")
  private WebElement title;

  @FindBy(xpath = "//a[contains(@class, 'name-link') and contains(text(), 'консоль')]")
  private WebElement nintendo;

  @FindBy(xpath = "//a[contains(@class, 'name-link') and contains(text(), 'Mario')]")
  private WebElement marioGame;

  @FindBy(xpath = "//div[contains(@class, 'total-amount__info-block')]//span[@class]")
  private WebElement shoppingCartSum;

  @FindBy(xpath = "//div[@class='cart-tabs']//span[contains(@class, 'restore')]")
  private WebElement restore;

  private int count = Integer.parseInt(getHeader().getCartItemCount().getText());

  private int nintendoCost =
      Integer.parseInt(
          nintendo
              .findElement(By.xpath("./../../../../..//span[@class='price__current']"))
              .getText()
              .replaceAll("[^0-9]", ""));
  private int marioCost =
      Integer.parseInt(
          marioGame
              .findElement(By.xpath("./../../../../..//span[@class='price__current']"))
              .getText()
              .replaceAll("[^0-9]", ""));

  public ShoppingCartPage checkOpenPage() {
    wait.until(ExpectedConditions.visibilityOf(title));
    Assertions.assertTrue(title.getText().equals("Корзина"), "Нужная страница не загрузилась.");
    return this;
  }

  public ShoppingCartPage compareItemsInCart(Product product1, Product product2) {

    if (product1.getTitle().contains("Mario")) {
      Assertions.assertEquals(product1.getPrice(), marioCost);
      Assertions.assertEquals(product2.getPrice(), nintendoCost);
    } else {
      Assertions.assertEquals(product2.getPrice(), marioCost);
      Assertions.assertEquals(product1.getPrice(), nintendoCost);
    }
    Assertions.assertEquals(
        product1.getPrice() + product2.getPrice(),
        Integer.parseInt(shoppingCartSum.getText().replaceAll("[^0-9]", "")));
    return this;
  }

  public ShoppingCartPage removeNintendoFromCart() {
    nintendo.findElement(By.xpath("./../..//button[contains(@class, 'remove')]")).click();
    count--;
    return this;
  }

  public ShoppingCartPage nintendoIsNotInCart(Product product) {
    wait.until(
        ExpectedConditions.textToBePresentInElement(
            getHeader().getCartItemCount(), String.valueOf(count)));
    Assertions.assertEquals(
        product.getPrice(), Integer.parseInt(shoppingCartSum.getText().replaceAll("[^0-9]", "")));
    return this;
  }

  public ShoppingCartPage plusItems(Product product) {
    WebElement plus =
        marioGame.findElement(By.xpath("./../../../../..//button[contains(@class, 'plus')]"));
    plus.click();
    count++;
    wait.until(
        ExpectedConditions.textToBePresentInElement(
            getHeader().getCartItemCount(), String.valueOf(count)));
    Assertions.assertEquals(
        product.getPrice() * 2,
        Integer.parseInt(shoppingCartSum.getText().replaceAll("[^0-9]", "")));
    return this;
  }

  public ShoppingCartPage restoreDeleted(Product product) {
    restore.click();
    count++;
    wait.until(ExpectedConditions.visibilityOf(nintendo));
    Assertions.assertTrue(nintendo.isDisplayed());
    selectAll.click();
    selectAll.click();
    wait.until(
        ExpectedConditions.textToBePresentInElement(
            getHeader().getCartItemCount(), String.valueOf(count)));
    Assertions.assertEquals(
        marioCost * 2,
        Integer.parseInt(shoppingCartSum.getText().replaceAll("[^0-9]", "")) - product.getPrice());
    return this;
  }
}
