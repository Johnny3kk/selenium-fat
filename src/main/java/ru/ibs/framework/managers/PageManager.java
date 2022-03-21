package ru.ibs.framework.managers;

public class PageManager {

    private static PageManager INSTANCE = null;


    private PageManager() {}

    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }
}
