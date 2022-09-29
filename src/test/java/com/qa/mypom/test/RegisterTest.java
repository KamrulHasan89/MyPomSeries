package com.qa.mypom.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.mypom.base.BaseTest;

public class RegisterTest extends BaseTest {
	
	@BeforeTest
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}
//	@DataProvider
//	public Object[][] getRegiData() {
//		return new Object[][] {
//		{"Saad", "Hasan", "saad@gmail.com", "123456789", "saad@123", "yes"},
//		{"Kamrul", "Hasan", "kamrul@gmail.com", "321456789", "kamrul@123", "no"},
//		{"Halima", "Begum", "halima@gmail.com", "978456789", "halima@123", "yes"},
//		};
//	}
	
	
	@Test(dataProvider = "getRegiData")
	public void userRegiTest(String fiName,String laName,
			String mail,String phn,String pwd,String subscri) {
		boolean successFlag = regPage.userRegistration
				( fiName, laName, mail, phn, pwd, subscri);
		regPage.goToRegiPage();
		Assert.assertEquals(successFlag, true);
	}
	

}
