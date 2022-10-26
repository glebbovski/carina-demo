package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends AbstractPage {

    private String itemPageUrl;
    private MainPage mainPage;

    @FindBy(id = "inventory_item_container")
    private InventoryItemContainer container;

    public ItemPage(WebDriver driver, int x) {
        super(driver);
        itemPageUrl = String.format("https://www.saucedemo.com/inventory-item.html?id=%d", x);
        setPageAbsoluteURL(itemPageUrl);
    }


    public InventoryItemContainer getContainer() {
        return container;
    }
}
