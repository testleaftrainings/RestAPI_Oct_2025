package com.testleaf.matchie.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"src/test/java/com/testleaf/matchie/features/Incident.feature"},
		         glue = {"com.testleaf.matchie.step.definitions"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		         },
		         tags = ""
		        )
public class TestngRunner extends AbstractTestNGCucumberTests {

}