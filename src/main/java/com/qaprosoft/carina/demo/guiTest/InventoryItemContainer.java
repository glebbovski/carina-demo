package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryItemContainer extends AbstractUIObject {

    @FindBy(xpath = "//div[@class=\"inventory_details_name large_size\"]")
    private ExtendedWebElement itemName;

    @FindBy(xpath = "//div[@class=\"inventory_details_price\"]")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private ExtendedWebElement backToProductsButton;

    public InventoryItemContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public MainPage clickToBack(MainPage mainPage) {
        backToProductsButton.click(3000);
        setPageAbsoluteURL(mainPage.getMainPageUrl());
        return mainPage;
    }

    public String readItemName() {
        return itemName.getText();
    }

    public String readItemPrice() {
        return itemPrice.getText();
    }

    public String readBackButton() {
        return backToProductsButton.getText();
    }

}
