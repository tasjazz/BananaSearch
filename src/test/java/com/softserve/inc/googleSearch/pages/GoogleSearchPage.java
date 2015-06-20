package com.softserve.inc.googleSearch.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.softserve.inc.googleSearch.tools.ContextVisible;
import com.softserve.inc.googleSearch.tools.IBrowser;
import com.softserve.inc.googleSearch.tools.WebDriverUtils;

public class GoogleSearchPage {

	static Logger logger = Logger.getLogger(GoogleSearchPage.class);
	private Map<String, String> googleSearch = new HashMap();

	private WebElement searchField;
	private WebElement nextLink;

	public GoogleSearchPage() {
		logger.info("GoogleSearchPage constructor");

		this.searchField = ContextVisible.get().getVisibleWebElement(
				By.id("lst-ib"));
		// this.nextLink =
		// ContextVisible.get().getVisibleWebElement(By.xpath("//a[@id='pnnext']/span[2]"));
	}

	public static GoogleSearchPage load(IBrowser browser, String url) {
		WebDriverUtils.get(browser).load(url);
		logger.info("GoogleSearchPage load method");
		return new GoogleSearchPage();
	}

	public void searchQuery(String query) {
		searchField.sendKeys(query);
		searchField.sendKeys(Keys.RETURN);

	}

	/**
	 * 
	 * @param count
	 *            describe how mush resuls shoud be added to MAP
	 */
	private void getTitleAndDescriptionFromResult(int count) {

		String title = null;
		String description = null;
		List<WebElement> results = new ArrayList<WebElement>();

		WebElement allRes = ContextVisible.get().getVisibleWebElement(
				By.id("ires")); // data from all page
		List<WebElement> elements = allRes.findElements(By.className("srg"));
		for (WebElement el : elements) {
			results.addAll(el.findElements(By.className("g")));
		}

		for (WebElement res : results) {
			title = res.findElement(By.className("r")).getText();
			description = res.findElement(By.className("st")).getText();
			googleSearch.put(title, description);

			System.out.println(googleSearch.size());
			if (googleSearch.size() == count)
				break;
		}
	}

	private void clickNextPage() {
		this.nextLink = ContextVisible.get().getVisibleWebElement(
				By.xpath("//a[@id='pnnext']/span[2]"));
		nextLink.click();
	}

	public void addFixedNumberResultsToMap(int count) {

		getTitleAndDescriptionFromResult(count);

		if (googleSearch.size() < count) {

			clickNextPage();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getTitleAndDescriptionFromResult(count);
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
