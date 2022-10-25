package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryItem extends AbstractUIObject {


    @FindBy(xpath = "//div[@class='inventory_item']//button[@data-test=\"add-to-cart-'%s'\"]")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//div[@class=\"inventory_item\"][%d]")
    private ExtendedWebElement thisItem;

    public InventoryItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickAddToCart(String text) {
        addToCartButton.format(text);
        addToCartButton.click();
    }

}
