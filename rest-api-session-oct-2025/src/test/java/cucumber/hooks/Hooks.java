package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

public class Hooks {
	
	@BeforeAll
	public static void beforeAllScearios() {
		System.out.println("Running before all the scenario");
	}
	
	@Before
	public void beforeEachScenarios() {
		System.out.println("Running before each scenario");
	}
	
	@BeforeStep
	public void beforeEachScenarioStep() {
		System.out.println("Running before each step of the scenarios");
	}
	
	@AfterStep
	public void afterEachScenaioSteps() {
		System.out.println("Running after each step of the scenarios");
	}
	
	@After
	public void afterEachScenarios() {
		System.out.println("Running after each scenario");
	}
	
	@AfterAll
	public static void afterAllScearios() {
		System.out.println("Running after all the scenario");
	}

}