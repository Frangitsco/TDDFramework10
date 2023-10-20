package com.qa;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	InputStream inputStream;
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
  @Parameters({"platformName","platformVersion","deviceName"})
  @BeforeTest
  public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
	  try {
		  props = new Properties();
		  String propFileName = "config.properties";
	  
	  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	  props.load(inputStream);
	  
	  
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", platformName);
    desiredCapabilities.setCapability("platformVersion", platformVersion);
    desiredCapabilities.setCapability("deviceName", deviceName);
    desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
    desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
    desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
 //   URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
    String appUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
    desiredCapabilities.setCapability("app", appUrl);
    
    URL url = new URL(props.getProperty("appiumURL"));
    
    driver = new AndroidDriver(url, desiredCapabilities);

    
  } catch (Exception e ) {
	  e.printStackTrace();
	  throw e;
	  }
  }
  public void waitForVisibility(WebElement e) {
	  WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(e));
  }
	  
public void clear(WebElement e) {
		  waitForVisibility(e);
		  e.clear();
  }
  
  public void click(WebElement e) {
	  waitForVisibility(e);
	  e.click();
	  
  }
  
  public void sendKeys(WebElement e, String txt) {
	  waitForVisibility(e);
	  e.sendKeys(txt);
	  
  }
  
  public String getAttribute(WebElement e, String attribute) {
	  waitForVisibility(e);
	 return e.getAttribute(attribute);
	  }
  
  
  @AfterTest
  public void afterTest() {
  }

}