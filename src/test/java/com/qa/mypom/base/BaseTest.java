package com.qa.mypom.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.mypom.factory.DriverFactory;
import com.qa.mypom.pages.AccountPage;
import com.qa.mypom.pages.LoginPage;
import com.qa.mypom.pages.ProductInfoPage;
import com.qa.mypom.pages.RegisterPage;
import com.qa.mypom.pages.SearchResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginPage;
	public Properties prop;
	public AccountPage accPage;
	public SearchResultsPage searchResPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage regPage;
	
	public SoftAssert softAssert;
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
