package com.comparator.qa.test;

import org.testng.*;

public class TestNGListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	public void onTestStart(ITestResult result) {

	}


	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getTestContext().getAttribute("baseObj") + " equals " + result.getTestContext().getAttribute("newObj"));
	}


	public void onTestFailure(ITestResult result) {
		System.out.println(result.getTestContext().getAttribute("baseObj") + " not equals " + result.getTestContext().getAttribute("newObj"));
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}


	public void onStart(ITestContext context) {

	}


	public void onFinish(ITestContext context) {

	}



	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

	}


	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

	}


	public void onStart(ISuite suite) {

	}


	public void onFinish(ISuite suite) {

	}
}
