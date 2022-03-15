package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constants;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#logo a");
	private By sections = By.cssSelector("div#content h2");
	private By logoutLinkTest = By.linkText("Logout");
    private By search = By.name("search");
    private By searchIcon = By.cssSelector("div#search button");
    
    public AccountsPage(WebDriver driver) {
    	this.driver = driver;
    	eleUtil = new ElementUtil(driver);
    }
    
    public String getAccountsPageTitle() {
    	return eleUtil.doGetPageTitleIs(constants.ACCOUNTS_PAGE_TITLE, constants.DEFAULT_TIMEOUT);
    }
    public String getAccountsPageUrl(){
    	return eleUtil.waitForUrlContains(constants.ACCOUNTS_PAGE_URL_FRACTION, constants.DEFAULT_TIMEOUT);
    }
    public String getAccPageHeader() {
    	return eleUtil.doGetText(header);
    }
    public boolean isLogoutLinkExist() {
    	return eleUtil.doIsDisplayed(logoutLinkTest);
    }
    public boolean logout() {
    	if(isLogoutLinkExist()) {
    		eleUtil.doClick(logoutLinkTest);
    		return true;
    	}
    		return false;
    }
    public List<String> getAccPageSections() {
    	List<WebElement>sectionsList = eleUtil.waitForElementsVisible(sections, 10);
    	List<String> secValList = new ArrayList<String>();
    	for(WebElement e:sectionsList) {
    		String val = e.getText();
    		secValList.add(val);
    	}
    	return secValList;
    }
    public boolean searchExist() {
    	return eleUtil.doIsDisplayed(search);
    }
    public ResultsPage doSearch(String productName) {
    	if(searchExist()) {
    		eleUtil.doSendKeys(search, productName);
    		eleUtil.doClick(searchIcon);
    		
    	}
    	return new ResultsPage(driver);
    }
    
    
    
    
}
