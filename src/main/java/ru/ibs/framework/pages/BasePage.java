package ru.ibs.framework.pages;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected Actions act = new Actions(driverManager.getDriver());

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

}