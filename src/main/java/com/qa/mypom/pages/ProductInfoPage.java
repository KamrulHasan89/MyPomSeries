package com.qa.mypom.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mypom.constants.AppConstants;
import com.qa.mypom.eleutils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;
	
	
	By productHeader = By.cssSelector("div#content h1");
	By productImages = By.cssSelector("ul.thumbnails img");
	By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	
	public ProductInfoPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
//		By mainProduct = By.xpath("//h1[text()='"+mainProductNmae+"']");
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("product header : "+ productHeaderVal );
		return productHeaderVal;
	}
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsToBeVisible(productImages, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
		System.out.println("imges count : " + imagesCount);
		return imagesCount;
	}
	public Map<String,String> getProductInfo() {
		//productMap = new HashMap<String,String>();
		productMap = new LinkedHashMap<String,String>();
		//productMap = new TreeMap<String,String>();

		productMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println("=====product info=====");
		productMap.forEach((k,v) -> System.out.println(k+":"+v));

        return productMap;

	}
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();	
			productMap.put(key, value);
	}
		
}
		private void getProductPriceData() {
			List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
			String productPrice = metaPriceList.get(0).getText().trim();
			String productExTaxPrice = metaPriceList.get(1).getText().trim();
			
			productMap.put("productprice", productPrice);
			productMap.put("extaxprice", productExTaxPrice);
		
	}

}
