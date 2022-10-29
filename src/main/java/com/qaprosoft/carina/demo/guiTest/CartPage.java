package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {

    private final String cartPageUrl = "https://www.saucedemo.com/cart.html";

    @FindBy(xpath = "//div[@class=\"inventory_item_name\"]")
    private List<ExtendedWebElement> cartItemsNames;

    @FindBy(xpath = "//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> cartItemsPrices;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(cartPageUrl);
    }

    public List<String> readCartItemsNames() {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < cartItemsNames.size(); i++) {
            res.add(cartItemsNames.get(i).getText());
        }
        return res;
    }

    public List<String> readCartItemsPrices() {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < cartItemsPrices.size(); i++) {
            res.add(cartItemsPrices.get(i).getText());
        }
        return res;
    }
}
