package com.softserve.inc.googleSearch;

import java.util.HashMap;
import java.util.Map;

/**
 * search banana and get title and description
 *
 */
public class BananaMap {
	
	
		public static void main(String[] args) {
		
		Helper helper = new Helper();
		helper.Connect();
		helper.typeSearchQuery("banana");
		
		helper.getTitleFromResult();
		
		
		helper.CloseBrowser();
		
		System.out.println("Total size = " + helper.getMapResult().size());
		
		helper.printResult();
		
		System.out.println("+++DONE+++");
		
	}
}
