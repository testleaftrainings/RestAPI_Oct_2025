package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/refactor-incident.feature:24:64"},
		          glue = {"step.definitions.som"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:cucumber-report/result.html",
		        		    "rerun:falied-scenarios.txt"		        		    
		          },
		          tags = ""
		        )
public class TestNGParallelRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {		
		return super.scenarios();
	}

}