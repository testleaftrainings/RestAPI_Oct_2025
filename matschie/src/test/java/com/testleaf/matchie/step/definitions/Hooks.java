package com.testleaf.matchie.step.definitions;

import com.testleaf.matschie.general.utils.AllureHandler;

import io.cucumber.java.AfterAll;

public class Hooks {
	
	@AfterAll
	public static void afterAll() {
		AllureHandler.moveHistoryFolderToAllureResults();
	}

}