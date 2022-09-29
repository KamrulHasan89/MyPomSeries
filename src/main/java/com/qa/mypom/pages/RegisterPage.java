package com.qa.mypom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mypom.constants.AppConstants;
import com.qa.mypom.eleutils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegistration(String fiName,String laName,
			String mail,String phn,String pwd,String subscri) {
		eleUtil.doSendKeysWithWait(firstName, AppConstants.MEDIUM_DEFAULT_TIME_OUT, fiName);
		eleUtil.doSendKeys(lastName, laName);
		eleUtil.doSendKeys(email, mail);
		eleUtil.doSendKeys(telephone,phn);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirmpassword, pwd);
		
		if(subscri.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String actSuccessMsg = eleUtil.waitForElementVisible(sucessMessg,AppConstants.MEDIUM_DEFAULT_TIME_OUT).getText();
		System.out.println("user regi success msg======" + actSuccessMsg);
		if(actSuccessMsg.contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
			//eleUtil.doClick(logoutLink);
			//eleUtil.doClick(registerLink);
           return true;
		}
		return false;
	}
	public void goToRegiPage() {
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
	}

}
