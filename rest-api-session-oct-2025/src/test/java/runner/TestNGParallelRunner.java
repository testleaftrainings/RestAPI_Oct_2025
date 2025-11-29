package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/Incident.feature"},
		          glue = {"step.defintions"},
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