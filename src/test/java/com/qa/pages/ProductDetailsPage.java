package com.qa.pages;

import com.qa.MenuPage;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage {
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]") private WebElement SLBTitle;
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]") private WebElement SLBTxt;
	@AndroidFindBy (accessibility = "test-BACK TO PRODUCTS") private WebElement backToProductsBtn;
	
	public String getSLBTitle() {
	    return getAttribute(SLBTitle, "text");
		}
	public String getSLBTxt() {
	    return getAttribute(SLBTxt, "text");
		}

	public ProductsPage pressBackToProductsBtn() {
		click(backToProductsBtn);
		return new ProductsPage();
	}
	}