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

    @FindBy(xpath = "//div[@class=\"pricebar\"]//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> itemPrices;

    @FindBy(xpath = "//div[@id=\"shopping_cart_container\"]//span[@class=\"shopping_cart_badge\"]")
    private List<ExtendedWebElement> elementsInCart;

    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private ExtendedWebElement shoppingCartLink;

    public InventoryContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CartPage openCartPage() {
        shoppingCartLink.click();
        return new CartPage(driver);
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

    public List<String> readItemsPrices() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < itemPrices.size(); i++) {
            res.add(itemPrices.get(i).getText());
        }
        return res;
    }

    public String readElementsInCart() {
        return elementsInCart.get(0).getText();
    }

    public int addCartButtonClick(String name) {
        int idx = readItemNames().indexOf(name);
        if (idx < 0) {
            //System.out.println(Arrays.toString(itemNames.toArray()));
            throw new RuntimeException();
        } else {
            assertElementPresent(itemAddCartButtons.get(idx));
            //System.out.println("-*-*-*-*-*-*-**--*-*-*-*-*-* " + itemAddCartButtons.get(idx).getText());
            itemAddCartButtons.get(idx).click();
            return idx;
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

    public List<ExtendedWebElement> getElementsInCart() {
        return elementsInCart;
    }
}
