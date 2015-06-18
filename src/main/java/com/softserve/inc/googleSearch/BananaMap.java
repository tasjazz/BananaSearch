package com.softserve.inc.googleSearch;

import org.apache.log4j.Logger;
/**
 * search banana and get title and description
 *
 */
public class BananaMap {
	static Logger logger =
			Logger.getLogger(BananaMap.class);
	
		public static void main(String[] args) {

		
		logger.info("App Start");
		Helper helper = new Helper();
		helper.Connect();
		helper.typeSearchQuery("banana");
		
		helper.getTitleFromResult();
		
		helper.CloseBrowser();
		
//		System.out.println("Total size = " + helper.getMapResult().size());
		helper.printResult();

		logger.info("App DONE");

	}
}
