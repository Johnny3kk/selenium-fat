package ru.ibs.framework.managers;

import ru.ibs.framework.utils.PropsConst;

import java.util.concurrent.TimeUnit;

public class InitManager {

    private static final TestPropManager propManager = TestPropManager.getInstance();

    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager
                .getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(
                        Integer.parseInt(propManager.getProperty(PropsConst.IMPLICITLY_WAIT)),
                        TimeUnit.SECONDS);
        driverManager
                .getDriver()
                .manage()
                .timeouts()
                .pageLoadTimeout(
                        Integer.parseInt(propManager.getProperty(PropsConst.PAGE_LOAD_TIMEOUT)),
                        TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}