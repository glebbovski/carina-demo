package com.qaprosoft.carina.demo.guiTest;

/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.pages.CompareModelsPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.NewsPage;

public class ButtonContainer extends AbstractUIObject {

    private String username;
    private String password;

    @FindBy(xpath = "//div[@class='login-box']")
    private ExtendedWebElement loginBox;

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

    public ButtonContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void loginLinkClick() {
        loginLink.click();
    }

    public MainPage openMainPage(){
        loginLink.click();
        return new MainPage(driver);
    }

    public void sendTextToUsername(String text) {
        this.username = text;
        inputUsername.click();
        inputUsername.type(text);

    }

    public void sendTextToPassword(String text) {
        this.password = text;
        inputPassword.click();
        inputPassword.type(text);
    }

    public String readInputUsername() {
        return username;
    }

    public String readInputPassword() {
        return  password;
    }

    public ExtendedWebElement getErrorButton() {
        return errorButton;
    }

    public ExtendedWebElement getErrorMessage() {
        return errorMessage;
    }



}

