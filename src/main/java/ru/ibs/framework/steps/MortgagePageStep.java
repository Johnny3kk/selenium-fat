package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class MortgagePageStep {

    private final PageManager pageManager = PageManager.getInstance();

    @И("^Заполнить поля:$")
    public void fillFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagePage().fillField((String) key, (String) value));
    }

    @И("^Проверить значения полей:$")
    public void checkFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagePage().assertCorrectData((String) key, (String) value));
    }
}
