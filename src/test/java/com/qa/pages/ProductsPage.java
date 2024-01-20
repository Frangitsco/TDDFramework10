package com.qa.pages;

import com.qa.MenuPage;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView") private WebElement productTitleTxt;        
	@AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]") private WebElement SLBTitle;
	@AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]") private WebElement SLBPrice;
	
	public String getTitle() {
    return getAttribute(productTitleTxt, "text");
	}
	public String getSLBTitle() {
	    return getAttribute(SLBTitle, "text");
		}
	public String getSLBPrice() {
	    return getAttribute(SLBPrice, "text");
		}
	public ProductDetailsPage pressSLBTitle() {
		click(SLBTitle);
		return new ProductDetailsPage();
	}
	}