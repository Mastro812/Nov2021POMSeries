package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constants;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value ='Login']");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2.Page constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.public page actions /methods
	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitleContains(constants.LOGIN_PAGE_TITLE, constants.DEFAULT_TIMEOUT);
	}
	
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(constants.LOGIN_PAGE_URL_FRACTION, constants.DEFAULT_TIMEOUT);
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPasswordLink);
		
	}
	public AccountsPage doLogin(String username,String pwd) {
		eleUtil.doSendKeys(emailId, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
	}
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

}
