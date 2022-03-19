package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.utils.Product;

public class ProductPage extends BasePage {

  @FindBy(xpath = "//h1[@class='product-card-top__title']")
  private WebElement title;

  @FindBy(xpath = "//div[@class='product-buy__price']")
  private WebElement price;

  @FindBy(xpath = "//div[@class='product-card-top__buy']//button[contains(@class, 'buy-btn')]")
  private WebElement buyProduct;

  private int count;

  public ProductPage checkOpenPage() {
    wait.until(ExpectedConditions.visibilityOf(title));
    Assertions.assertTrue(title.isDisplayed(), "Нужная страница не загрузилась.");
    wait.until(ExpectedConditions.visibilityOf(price));
    Assertions.assertTrue(price.isDisplayed(), "Нужная страница не загрузилась.");
    return this;
  }

  public Product createProduct() {
    wait.until(ExpectedConditions.visibilityOf(price));
    return new Product(title.getText(), Integer.parseInt(price.getText().replaceAll("[^0-9]", "")));
  }

  public ProductPage buyProduct() {
    wait.until(ExpectedConditions.elementToBeClickable(buyProduct)).click();
    count++;
    return this;
  }

  public ProductPage waitUntilCartGetItem() {
      wait.until(ExpectedConditions.textToBePresentInElement(getHeader().getCartItemCount(), String.valueOf(count)));
      return this;
  }

  public ProductPage cartSumEquals(Product product1, Product product2) {
      Assertions.assertEquals(
              product1.getPrice() + product2.getPrice(),
              getHeader().getCartSum());
      return this;
  }
}
