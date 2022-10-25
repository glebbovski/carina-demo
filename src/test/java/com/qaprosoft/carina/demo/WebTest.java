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
        buttonContainer.sendTextToUsername("problem_user");
        Assert.assertTrue(loginPage.readAcceptedUsernames().contains(buttonContainer.readInputUsername()), "Wrong username was chosen");
        buttonContainer.sendTextToPassword("secret_sauce");
        Assert.assertEquals(loginPage.readAcceptedPassword(), buttonContainer.readInputPassword(), "Wrong password was chosen");
        MainPage mainPage = buttonContainer.openMainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");
        InventoryContainer inventoryContainer = mainPage.getInventoryContainer();
        Assert.assertTrue(inventoryContainer.isUIObjectPresent(2), "Inventory container wasn't found!");
        String name = "Sauce Labs Onesie";
        String[] tmp = Arrays.stream(name.split(" "))
                .map(x -> x.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList())
                .toArray(String[]::new);
        String nameWithDelimeter = String.join("-", tmp);
        Assert.assertTrue(inventoryContainer.readItemNames().contains(name), "Wrong name");
        List<ExtendedWebElement> cartButtons = inventoryContainer.getItemAddCartButtons();
        Assert.assertEquals(cartButtons.size(), 6, "Wrong size");
        inventoryContainer.addCartButtonClick(name);




    }

}
