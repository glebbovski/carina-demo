package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SortContainer extends AbstractUIObject {

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private ExtendedWebElement sortButton;

    @FindBy(xpath = "//span[@class='active_option']")
    private ExtendedWebElement activeOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"az\"]")
    private ExtendedWebElement azOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"za\"]")
    private ExtendedWebElement zaOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"lohi\"]")
    private ExtendedWebElement lohiOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"hilo\"]")
    private ExtendedWebElement hiloOption;

    public SortContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnSortButtonAndGetAtoZ() {
        sortButton.click();
        azOption.click();
    }

    public void clickOnSortButtonAndGetZtoA() {
        sortButton.click();
        zaOption.click();
    }

    public void clickOnSortButtonAndGetLowToHigh() {
        sortButton.click();
        lohiOption.click();
    }

    public void clickSortButtonAndGetHighToLow() {
        sortButton.click();
        hiloOption.click();
    }

    public String readActiveOption() {
        return activeOption.getText();
    }


}
