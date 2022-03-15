package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", constants.MACBOOK_IMAGES_COUNT},
			{"Macbook", "MacBook Air", constants.MACBOOK_IMAGES_COUNT},
			{"iMac", "iMac", constants.IMAC_IMAGES_COUNT},
		};
	}
	
	@Test(dataProvider = "productData")
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int totalImages = productInfoPage.getProductImageCount();
		System.out.println("total images for : " + mainProductName + ":" + totalImages);
		Assert.assertEquals(totalImages, imagesCount);
	}

}
