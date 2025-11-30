package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/CreateIncident.feature"},
		          glue = {"step.definitions.som"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:cucumber-report/result.html",
		        		    "rerun:falied-scenarios.txt"		        		    
		          },
		          tags = ""
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}