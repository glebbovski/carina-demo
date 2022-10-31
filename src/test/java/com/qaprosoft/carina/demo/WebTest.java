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
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WebTest implements IAbstractTest {
    {
        System.setProperty("webdriver.chrome.driver", "D:\\properties\\chromedriver.exe");
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
        loginPage.sendTextToUsername("");
        loginPage.sendTextToPassword("");
        loginPage.loginButtonClick();
        Assert.assertTrue(loginPage.isErrorMessagePresent(), "Something wrong with error message");
        Assert.assertEquals(loginPage.readErrorMessage(), "Epic sadface: Username is required",
                "Wrong message");
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testWrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("prikol");
        loginPage.loginButtonClick();
        Assert.assertTrue(loginPage.isErrorMessagePresent(), "Something wrong with error message");
        Assert.assertEquals(loginPage.readErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Wrong message");
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginAndPutThingsInBag() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("secret_sauce");
        String name = "Sauce Labs Onesie";
        MainPage mainPage = loginPage.openMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");
        mainPage.addCartToButtonClick(name);
        String price = mainPage.readPrice(name);
        CartPage cartPage = mainPage.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        Assert.assertEquals(price, cartPage.readCartItemsPrices().get(0), "Something wrong with prices");
        Assert.assertEquals(name, cartPage.readCartItemsNames().get(0), "Something wrong with names");
    }

    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginOpenReturnUnlogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("secret_sauce");
        String name = "Sauce Labs Fleece Jacket";
        MainPage mainPage = loginPage.openMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");
        String price = mainPage.readPrice(name);
        ItemPage itemPage = mainPage.openItemPage(name);
        //Assert.assertTrue(itemPage.isPageOpened(), "Item page is not opened");
        Assert.assertEquals(price, itemPage.readItemPrice(), "Something wrong with price");
        Assert.assertEquals(name, itemPage.readItemName(), "Something wrong with name");
        mainPage = itemPage.clickToBackToMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");
        mainPage.clickBurgerButton();
        loginPage = mainPage.clickToLogout();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
    }


    @Test()
    @MethodOwner(owner = "gleb chekmezov")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginAndSort() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("secret_sauce");
        MainPage mainPage = loginPage.openMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");
        Assert.assertEquals(mainPage.readActiveOption(), "name (a to z)".toUpperCase(Locale.ROOT),
                "Error with start sorting");

        List<String> sorted = mainPage.readItemNames().stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sorted, mainPage.readItemNames(), "Wrong word order");

        mainPage.clickOnSortButton("za");
        sorted = mainPage.readItemNames().stream().sorted().collect(Collectors.toList());
        Collections.reverse(sorted);
        Assert.assertEquals(sorted, mainPage.readItemNames(), "Wrong word order");

        mainPage.clickOnSortButton("lohi");
        List<Double> tmpDouble = mainPage.readItemsPrices().stream()
                .map(x -> Double.parseDouble(x.substring(1)))
                .collect(Collectors.toList());
        List<Double> sortedDouble = tmpDouble.stream()
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedDouble, tmpDouble, "Wrong price order");

        mainPage.clickOnSortButton("hilo");
        tmpDouble = mainPage.readItemsPrices().stream()
                .map(x -> Double.parseDouble(x.substring(1)))
                .collect(Collectors.toList());
        sortedDouble = tmpDouble.stream()
                .sorted().collect(Collectors.toList());
        Collections.reverse(sortedDouble);
        Assert.assertEquals(sortedDouble, tmpDouble, "Wrong price order");
    }



}
