package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.guiTest.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class WebTest implements IAbstractTest {
    {
        System.setProperty("webdriver.chrome.driver", "D:\\Solvd\\carina-demo\\src\\main\\resources\\chromedriver.exe");
    }
    public WebDriver webDriver = new ChromeDriver();

    @BeforeTest
    public WebDriver getDriver() {
        return webDriver;
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        ButtonContainer buttonContainer = loginPage.getButtonContainer();
        Assert.assertTrue(buttonContainer.isUIObjectPresent(2), "Button container wasn't found!");
        buttonContainer.sendTextToUsername("");
        buttonContainer.sendTextToPassword("");
        buttonContainer.loginLinkClick();
        ExtendedWebElement errorButton = buttonContainer.getErrorButton();
        Assert.assertTrue(errorButton.isElementPresent(2), "Something wrong with error button");
        ExtendedWebElement errorMessage = buttonContainer.getErrorMessage();
        Assert.assertTrue(errorMessage.isElementPresent(2), "Something wrong with msg");
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Username is required");
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testWrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        ButtonContainer buttonContainer = loginPage.getButtonContainer();
        Assert.assertTrue(buttonContainer.isUIObjectPresent(2), "Button container wasn't found!");
        buttonContainer.sendTextToUsername("problem_user");
        Assert.assertTrue(loginPage.readAcceptedUsernames().contains(buttonContainer.readInputUsername()), "Wrong username was chosen");
        buttonContainer.sendTextToPassword("");
        buttonContainer.loginLinkClick();
        ExtendedWebElement errorButton = buttonContainer.getErrorButton();
        Assert.assertTrue(errorButton.isElementPresent(2), "Something wrong with error button");
        ExtendedWebElement errorMessage = buttonContainer.getErrorMessage();
        Assert.assertTrue(errorMessage.isElementPresent(2), "Something wrong with msg");
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Password is required");
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginAndPutThingsInBag() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        ButtonContainer buttonContainer = loginPage.getButtonContainer();
        Assert.assertTrue(buttonContainer.isUIObjectPresent(2), "Button container wasn't found!");
        buttonContainer.sendTextToUsername("standard_user");
        Assert.assertTrue(loginPage.readAcceptedUsernames().contains(buttonContainer.readInputUsername()), "Wrong username was chosen");
        buttonContainer.sendTextToPassword("secret_sauce");
        Assert.assertEquals(loginPage.readAcceptedPassword(), buttonContainer.readInputPassword(), "Wrong password was chosen");

        MainPage mainPage = buttonContainer.openMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");

        InventoryContainer inventoryContainer = mainPage.getInventoryContainer();
        Assert.assertTrue(inventoryContainer.isUIObjectPresent(2), "Inventory container wasn't found!");

        String fname = "Sauce Labs Onesie";
//        String[] tmp = Arrays.stream(name.split(" "))
//                .map(x -> x.toLowerCase(Locale.ROOT))
//                .collect(Collectors.toList())
//                .toArray(String[]::new);
//        String nameWithDelimeter = String.join("-", tmp);
        Assert.assertTrue(inventoryContainer.readItemNames().contains(fname), "Wrong name");
        //String sname = "Sauce Labs Bolt T-Shirt";
        //Assert.assertTrue(inventoryContainer.readItemNames().contains(sname), "Wrong name");
        String tname = "Biboba";
        Assert.assertFalse(inventoryContainer.readItemNames().contains(tname), "Check your names");

        List<ExtendedWebElement> cartButtons = inventoryContainer.getItemAddCartButtons();
        Assert.assertEquals(cartButtons.size(), 6, "Wrong size");

        List <ExtendedWebElement> cartList = inventoryContainer.getElementsInCart();
        Assert.assertEquals(cartList.size(), 0, "Something wrong with cart");

        int idxForCartItemMainPage = inventoryContainer.addCartButtonClick(fname);
        Assert.assertEquals(inventoryContainer.readElementsInCart(), "1", "Something wrong with cart");

        List<String> mainPageItemsNames = inventoryContainer.readItemNames();
        List<String> mainPageItemsPrices = inventoryContainer.readItemsPrices();

        // int secondIdx = inventoryContainer.addCartButtonClick(sname);
        // Assert.assertEquals(inventoryContainer.readElementsInCart(), "2", "Something wrong with cart");
        CartPage cartPage = inventoryContainer.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened!");
        CartContainer cartContainer = cartPage.getCartContainer();
        Assert.assertTrue(cartContainer.isUIObjectPresent(2), "Cart container was not found!");

        List<String> cartPageItemsPrices = cartContainer.readCartItemsPrices();
        List<String> cartPageItemsNames = cartContainer.readCartItemsNames();

        Assert.assertEquals(cartPageItemsNames.size(), 1, "Something went wrong with names");
        Assert.assertEquals(cartPageItemsPrices.size(), 1, "Something went wrong with prices");

        Assert.assertTrue(cartPageItemsNames.contains(fname), "Something wrong with name");
        Assert.assertTrue(mainPageItemsNames.contains(fname), "Something wrong with name");

        int mainIdx = mainPageItemsNames.indexOf(fname);
        int cartIdx = cartPageItemsNames.indexOf(fname);

        Assert.assertEquals(mainPageItemsPrices.get(mainIdx), cartPageItemsPrices.get(cartIdx),
                "Prices are not equals");

    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginOpenReturnUnlogin() {


    }


}
