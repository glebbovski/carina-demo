package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends AbstractPage {

    private String itemPageUrl;

    @FindBy(xpath = "//div[@class=\"inventory_details_name large_size\"]")
    private ExtendedWebElement itemName;

    @FindBy(xpath = "//div[@class=\"inventory_details_price\"]")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private ExtendedWebElement backToProductsButton;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickToBackToMainPage() {
        backToProductsButton.click();
        return new MainPage(driver);
    }

    public String readItemName() {
        return itemName.getText();
    }

    public String readItemPrice() {
        return itemPrice.getText();
    }



}
