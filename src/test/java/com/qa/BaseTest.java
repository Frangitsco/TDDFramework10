package com.qa;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	private static AppiumDriverLocalService server;
	InputStream inputStream;
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	@BeforeSuite
	public void beforeSuite() {
        server = getAppiumService();
		server.start();
		System.out.println("Appium server started");
	}
	
	
	@AfterSuite
	public void afterSuit() {
		server.stop();
		System.out.println("Appium server stopped");
	}
	
	//public AppiumDriverLocalService getAppiumServerDefault() {
	//	return AppiumDriverLocalService.buildDefaultService();
	//}
	
	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", "/usr/local/Cellar/openjdk/20.0.2/libexec/openjdk.jdk/Contents/Home/bin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:" + System.getenv("PATH"));
		environment.put("ANDROID_HOME", "/Users/cex/Library/Android/sdk");
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("/usr/local/bin/node"))
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingPort(4723)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
				.withEnvironment(environment));
//			    .withLogFile(new File("ServerLogs/server.log")));
	
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
  
  public void closeApp() {
	    ((InteractsWithApps) driver).terminateApp(props.getProperty("androidAppPackage"));
	}

  
  public void launchApp() {
	    ((InteractsWithApps) driver).activateApp(props.getProperty("androidAppPackage"));
	}

  
  
  @AfterTest
  public void afterTest() {
  }

}