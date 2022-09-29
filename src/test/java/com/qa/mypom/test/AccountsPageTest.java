package com.qa.mypom.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.mypom.base.BaseTest;
import com.qa.mypom.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test
	public void isLogoutLinkExist() {
		Assert.assertEquals(accPage.isLogoutLinkExist(),true);	
	}
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
	}
	@Test
	public void accHeadersTest() {
		List<String> actHeadersList = accPage.getAccountHeaders();
		System.out.println("===act headers=====" + actHeadersList);
		Assert.assertEquals(actHeadersList, AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
	

}
