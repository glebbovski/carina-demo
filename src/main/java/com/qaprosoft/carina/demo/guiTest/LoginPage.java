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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "login_button_container")
    private ButtonContainer buttonContainer;

    @FindBy(xpath = "//div[@id=\"login_credentials\"]")
    private List<ExtendedWebElement> acceptedUsernames;

    @FindBy(xpath = "//div[@class=\"login_password\"]")
    private ExtendedWebElement acceptedPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public ButtonContainer getButtonContainer() {
        return buttonContainer;
    }

    public String readAcceptedPassword() {
        String[] tmp = acceptedPassword.getText().split("\n");
        return tmp[1];
    }

    public List<String> readAcceptedUsernames() {
        List<String> tmp = Arrays.asList(acceptedUsernames.get(0).getText().split("\n")).stream()
                .filter(x -> !x.contains(":")).collect(Collectors.toList());
        return tmp;
    }

}
