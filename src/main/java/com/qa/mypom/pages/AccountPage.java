package com.qa.mypom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.mypom.constants.AppConstants;
import com.qa.mypom.eleutils.ElementUtil;

public class AccountPage {
	
	public WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accPageHeaders = By.cssSelector("div#content h2");
	
	public AccountPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("Acc page title : " + title);
		return title;
	}
	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("acc page url : "+ url);
		return url;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
    }
	public List<String> getAccountHeaders() {
		return eleUtil.getAllElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}
	public SearchResultsPage doSearch(String productName) {
		System.out.println("searching for : " + productName );
        eleUtil.doSendKeysWithWait(searchField,10, productName);
        eleUtil.doClick(searchIcon);
        return new SearchResultsPage(driver);
	}

}
