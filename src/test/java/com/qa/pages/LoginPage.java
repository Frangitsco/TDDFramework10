package com.qa.pages;

import com.qa.BaseTest;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	@AndroidFindBy (accessibility = "test-Username") private WebElement usernameTxtFld;
	@AndroidFindBy (accessibility = "test-Password") private WebElement passwordTxtFld;
	@AndroidFindBy (accessibility = "test-LOGIN") private WebElement loginBtn;
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errTxt;
	
public LoginPage enterUserName(String username) {
	clear(usernameTxtFld);	
	sendKeys(usernameTxtFld, username);
	return this;
}

public LoginPage enterPassword(String password) {
	clear(passwordTxtFld);
	sendKeys(passwordTxtFld, password);
	return this;
}

public ProductsPage pressLoginBtn() {
	click(loginBtn);
	return new ProductsPage();
}

public ProductsPage login(String username, String password) {
	enterUserName(username);
	enterPassword(password);
	return pressLoginBtn();
}

public String getErrTxt() {
	return getAttribute(errTxt, "text");
}

}