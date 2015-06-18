package com.softserve.inc.googleSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Tarik
 * @date 17.06.2015
 * @version 1.0
 *  
 * @about getting first 10 search query
 * in the Map  
 */
public class Helper {
	
	
	static final String URL = "http://www.gooogle.com";
	private WebDriver driver;
	private WebElement searchField;
	private Map<String, String> googleSearch = new HashMap();
	

	public Map getMapResult() {
		return googleSearch;
	}

	public void Connect() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get(URL);
	}

	public void CloseBrowser() {
		driver.quit();
	}

	public void typeSearchQuery(String search) {
		this.searchField = driver.findElement(By.id("lst-ib"));
		searchField.sendKeys(search);
		searchField.sendKeys(Keys.RETURN);
	}

	public void getTitleFromResult() {

		String title = null;
		String description = null;
		List<WebElement> results = new ArrayList<WebElement>();
		
		WebElement allRes = driver.findElement(By.id("ires")); // data from all page
		List<WebElement> elements = allRes.findElements(By.className("srg"));
		for (WebElement el : elements) {
			results.addAll(el.findElements(By.className("g")));
		}

		for (WebElement res : results) {
			title = res.findElement(By.className("r")).getText();
			description = res.findElement(By.className("st")).getText();

//			System.out.println("------------\n" + title + "\n" + description);
			googleSearch.put(title, description);
		}

		// finding text from NEWS section
		System.out.println("====NEWS Section====");

		List<WebElement> elementsNews = allRes.findElements(By
				.className("_I2"));
		for (WebElement elNews : elementsNews) {
			title = elNews.findElement(By.className("_Dk")).getText();
			description = elNews.findElement(
					By.xpath("//ol[@id='rso']/div[2]/li/span")).getText();
//			System.out.println("-----------\n" + title + "\n");
//			System.out.println(description);

			googleSearch.put(title, description);
		}
	}

	public void printResult() {
		System.out.println("Result:");
		System.out.println(googleSearch.size());
		for (String key : googleSearch.keySet()) {
			System.out.println("-----------\n" + key + " \n"
					+ googleSearch.get(key));
		}
	}
}
