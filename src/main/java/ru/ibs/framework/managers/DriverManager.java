package ru.ibs.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.ibs.framework.utils.PropsConst;


public class DriverManager {

    private WebDriver driver;
    private TestPropManager propManager = TestPropManager.getInstance();

    private static DriverManager INSTANCE = null;


    private DriverManager() {}

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("firefox")) {
        System.setProperty("webdriver.gecko.driver", propManager.getProperty(PropsConst.PATH_GECKO_DRIVER_WINDOWS));
        driver = new FirefoxDriver();
        } else if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_WINDOWS));
            driver = new ChromeDriver();
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
