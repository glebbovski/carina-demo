package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String mainPageUrl = "https://www.saucedemo.com/inventory.html";

    @FindBy(xpath = "//*[*[div[text()='%s']]]/following-sibling::div//button")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//*[*[div[text()='%s']]]/following-sibling::div//div")
    private ExtendedWebElement price;

    @FindBy(xpath = "//div[@class=\"pricebar\"]//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> itemPrices;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_name\"]")
    private List<ExtendedWebElement> itemNames;

    @FindBy(xpath = "//div[text()='%s']")
    private ExtendedWebElement itemLink;


    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private ExtendedWebElement shoppingCartLink;

    @FindBy(xpath = "//div[@class='bm-burger-button']")
    private ExtendedWebElement burgerMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private ExtendedWebElement sortButton;

    @FindBy(xpath = "//span[@class='active_option']")
    private ExtendedWebElement activeOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value='%s']")
    private ExtendedWebElement option;

    public MainPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(mainPageUrl);
    }

    public void addCartToButtonClick(String text) {
        addToCartButton.format(text).click();
    }

    public ItemPage openItemPage(String text) {
        itemLink.format(text).click();
        return new ItemPage(driver);
    }


    public CartPage openCartPage() {
        shoppingCartLink.click();
        return new CartPage(driver);
    }

    public void clickBurgerButton() {
        burgerMenuButton.click();
    }

    public LoginPage clickToLogout() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutLink.click(100);
        return new LoginPage(driver);
    }

    public String readPrice(String text) {
        return price.format(text).getText();
    }

    public String readActiveOption() {
        return activeOption.getText();
    }

    public List<String> readItemsPrices() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < itemPrices.size(); i++) {
            res.add(itemPrices.get(i).getText());
        }
        return res;
    }

    public List<String> readItemNames() {
        List<String> tmp = new ArrayList<>();
        for(int i = 0; i < itemNames.size(); i++) {
            tmp.add(itemNames.get(i).getText());
        }
        return tmp;
    }

    public void clickOnSortButton(String text) {
        sortButton.click();
        option.format(text).click();
    }

}
