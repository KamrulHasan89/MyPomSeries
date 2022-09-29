package com.qa.mypom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.mypom.constants.AppConstants;
import com.qa.mypom.eleutils.ElementUtil;

public class SearchResultsPage {
	
	public WebDriver driver;
	private ElementUtil eleUtil;
	
	By productCount = By.cssSelector("div.product-thumb");
	
	
	public SearchResultsPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();
		
	}
	public ProductInfoPage selectProduct(String searchProductName) {
		By product = By.linkText(searchProductName);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
	}

}
