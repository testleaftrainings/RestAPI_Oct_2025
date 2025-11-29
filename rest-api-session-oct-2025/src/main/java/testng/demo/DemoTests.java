package testng.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import testng.listeners.RetryFailedTest;

public class DemoTests {
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void test1() {
		Assert.assertTrue(true);
	}
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void test2() {
		Assert.assertTrue(false);
	}
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void test3() {
		Assert.assertTrue(true);
	}
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void test4() {
		Assert.assertTrue(false);
	}

}