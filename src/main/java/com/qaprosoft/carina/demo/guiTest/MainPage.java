package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MainPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String mainPageUrl = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "inventory_container")
    private InventoryContainer inventoryContainer;

    @FindBy(className = "bm-menu")
    private MenuContainer menuContainer;

    @FindBy(xpath = "//span[@class='select_container']")
    private SortContainer sortContainer;

    public MainPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(mainPageUrl);
    }

    public InventoryContainer getInventoryContainer() {
        return inventoryContainer;
    }
    public MenuContainer getMenuContainer() {
        return menuContainer;
    }
    public SortContainer getSortContainer() {
        return sortContainer;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }


}
