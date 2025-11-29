package testng.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {
	
	private int maxTry = 3;
	private int count = 0;

	@Override
	public boolean retry(ITestResult result) {
		if(count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}