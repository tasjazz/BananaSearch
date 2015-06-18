package com.softserve.inc.googleSearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.inc.googleSearch.tools.ContextVisible;
import com.softserve.inc.googleSearch.tools.IBrowser;
import com.softserve.inc.googleSearch.tools.WebDriverUtils;

public class GoogleSearchPage {

	private WebElement searchField;
	private WebElement nextResultPageLink;
	private WebElement pageContent;
	
	
	public GoogleSearchPage() {
		this.searchField = ContextVisible.get().getVisibleWebElement(By.id("lst-ib"));
		this.nextResultPageLink = ContextVisible.get().getVisibleWebElement(By.id("pnnext"));
		this.pageContent = ContextVisible.get().getVisibleWebElement(By.id("ires"));
	}
	
	public static GoogleSearchPage load(IBrowser browser, String url){
		WebDriverUtils.get(browser).load(url);
		return new GoogleSearchPage();
	}
}
