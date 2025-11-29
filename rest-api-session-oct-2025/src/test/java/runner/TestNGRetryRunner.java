package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"@falied-scenarios.txt"},
		          glue = {"step.defintions"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:cucumber-report/result.html",
		        		    "rerun:falied-scenarios.txt"		        		    
		          },
		          tags = ""
		        )
public class TestNGRetryRunner extends AbstractTestNGCucumberTests {

}