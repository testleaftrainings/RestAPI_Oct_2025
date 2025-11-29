package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/refactor-incident.feature:48"},
		          glue = {"step.defintions", "cucumber.hooks"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:cucumber-report/result.html",
		        		    "rerun:falied-scenarios.txt"		        		    
		          },
		          tags = ""
		        )
public class TestNGRunnerWithHooks extends AbstractTestNGCucumberTests {

}