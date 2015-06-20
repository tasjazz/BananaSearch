package com.softserve.inc.googleSearch.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.softserve.inc.googleSearch.pages.GoogleSearchPage;
import com.softserve.inc.googleSearch.tools.BrowserRepository;
import com.softserve.inc.googleSearch.tools.WebDriverUtils;

public class RunnerTest {
	static Logger logger =Logger.getLogger(RunnerTest.class);
	private final String url = "http://www.google.com";
	private final String searchQuery = "banana";
	private static final int COUNT = 20;

	@BeforeTest
	public void startUpBrovser(){
		
		logger.info("App Start");
		GoogleSearchPage googleSearchPage = GoogleSearchPage.load(BrowserRepository.getFirefoxByTemporaryProfile(), url);
		googleSearchPage.searchQuery(searchQuery);
		googleSearchPage.addFixedNumberResultsToMap(COUNT);
	}
	
	@Test
	public void testBananaCount(){
		
	}
	
	@AfterTest
	public void tearDown(){
		WebDriverUtils.get().stop();
	}
}
