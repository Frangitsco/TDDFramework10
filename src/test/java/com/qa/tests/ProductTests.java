package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ProductTests extends BaseTest{
	LoginPage loginPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	SettingsPage settingsPage;
	InputStream datais;
	JSONObject loginUsers;


	@BeforeClass
	public void beforeClass() throws Exception {
		try {
			String dataFileName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(datais != null) {
				datais.close();
			}
		}
		
		closeApp();
		launchApp();
		
	}

@AfterClass
public void afterClass() {
}

@BeforeMethod
public void beforeMethod(Method m) {
	loginPage = new LoginPage();
	System.out.println("\n" + "****** starting test:" + m.getName() + "******" + "\n");
	
	  productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"), 
			  loginUsers.getJSONObject("validUser").getString("password"));
	
}

@AfterMethod
public void afterMethod() {
	closeApp();
	launchApp();
}

  @Test(priority = 1)
  public void validateProductOnProductsPage() {
	  SoftAssert sa = new SoftAssert();
	  
	  String SLBTitle = productsPage.getSLBTitle();
	  sa.assertEquals(SLBTitle, "Sauce Labs Backpack");
	  
	  String SLBTPrice = productsPage.getSLBPrice();
	  sa.assertEquals(SLBTPrice, "$29.99");
	  
	  sa.assertAll();
  }
  
  @Test(priority = 2)
  public void validateProductOnProductDetailsPage() {
	  SoftAssert sa = new SoftAssert();
	  
	  productDetailsPage = productsPage.pressSLBTitle();
	  
	  String SLBTitle = productDetailsPage.getSLBTitle();
	  sa.assertEquals(SLBTitle, "Sauce Labs Backpack");
	  
	  String SLBTxt = productDetailsPage.getSLBTxt();
	  sa.assertEquals(SLBTxt, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
	  
	  productsPage = productDetailsPage.pressBackToProductsBtn();
	  
	  sa.assertAll();

}
}