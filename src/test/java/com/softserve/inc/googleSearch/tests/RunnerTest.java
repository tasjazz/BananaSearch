package com.softserve.inc.googleSearch.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.softserve.inc.googleSearch.BananaMap;
import com.softserve.inc.googleSearch.pages.GoogleSearchPage;
import com.softserve.inc.googleSearch.tools.BrowserRepository;

public class RunnerTest {
	static Logger logger =Logger.getLogger(RunnerTest.class);
	private final String url = "http://www.google.com";

	@BeforeTest
	public void startUpBrovser(){
		
		logger.info("App Start");
		GoogleSearchPage.load(BrowserRepository.getFirefoxByTemporaryProfile()
				, url);
		
	}
	
	@Test
	public void testBananaCount(){
		assert(true);
	}
}
