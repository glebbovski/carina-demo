package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class InventoryContainer extends AbstractUIObject {

    @FindBy(xpath = "//div[@class=\"inventory_item\"]") // "//div[@class=\"inventory_item\"][%d]"
    private List<ExtendedWebElement> items;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_name\"]")
    private List<ExtendedWebElement> itemNames;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//button")
    private List<ExtendedWebElement> itemAddCartButtons;

//    @FindBy(xpath = "//div[@class='inventory_item_name']")
//    private List<ExtendedWebElement> itemNames;

    public InventoryContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<String> readItems() {
        List<String> tmp = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            tmp.add(items.get(i).getText());
        }
        return tmp;
    }

    public List<String> readItemNames() {
        List<String> tmp = new ArrayList<>();
        for(int i = 0; i < itemNames.size(); i++) {
            tmp.add(itemNames.get(i).getText());
        }
        return tmp;
    }


    public List<String> readAddCartButtons() {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < itemAddCartButtons.size(); i++) {
            tmp.add(itemAddCartButtons.get(i).getText());
        }
        return tmp;
    }

    public void addCartButtonClick(String name) {
        int idx = readItemNames().indexOf(name);
        if (idx < 0) {
            System.out.println(Arrays.toString(itemNames.toArray()));
            throw new RuntimeException();
        } else {
            itemAddCartButtons.get(idx).click();
        }
    }

    public List<ExtendedWebElement> getItems() {
        return items;
    }

    public List<ExtendedWebElement> getItemNames() {
        return itemNames;
    }

    public List<ExtendedWebElement> getItemAddCartButtons() {
        return itemAddCartButtons;
    }
}
