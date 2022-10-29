package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class LoginPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String loginPageUrl = "https://www.saucedemo.com/";

    @FindBy(xpath = "//div[@class='form_group']//input[@placeholder='Username']")
    private ExtendedWebElement inputUsername;

    @FindBy(xpath = "//div[@class='form_group']//input[@placeholder='Password']")
    private ExtendedWebElement inputPassword;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private ExtendedWebElement loginLink;

    @FindBy(xpath = "//div[@class='error-message-container error']//button")
    private ExtendedWebElement errorButton;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public boolean isErrorMessagePresent() {
        return errorMessage.isElementPresent();
    }

    public String readErrorMessage() {
        return errorMessage.getText();
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void sendTextToUsername(String text) {
        inputUsername.type(text);

    }

    public void sendTextToPassword(String text) {
        inputPassword.type(text);
    }

    public void loginLinkClick() {
        loginLink.click();
    }

    public MainPage openMainPage(){
        loginLink.click(3);
        return new MainPage(driver);
    }

}
