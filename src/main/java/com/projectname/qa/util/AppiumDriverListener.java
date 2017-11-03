package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.projectname.qa.base.MobileTestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AppiumDriverListener implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
        	//System.out.println("beforeInvocation called");
        	String platformName;
        	//String browserName = TestBase.GlobalBrowser;
        	if (!GlobalThreadVariables.getplatformName().equals(null))
        	{
        		platformName = GlobalThreadVariables.getplatformName();
        	}
        	else if (!method.getTestMethod().getXmlTest().getLocalParameters().get("platformName").equals(null))
        	{
        		platformName = method.getTestMethod().getXmlTest().getLocalParameters().get("platformName");
        	}
        	else
        	{
        		platformName = System.getProperty("platformName");
        	}
        	
        	AppiumDriver<?> driver = LocalDriverFactory.createInstance(platformName);
            LocalDriverManager.setAppiumDriver(driver);
            ExtentTestManager.getTest().log(LogStatus.PASS, 
            		"<font color=green><B>CONFIGURATION</B></font> <br />" +
            		"Platform Name: <B>" + platformName.toUpperCase() + "</B><br />" +
            		"Platform Version: <B>" + GlobalThreadVariables.getplatformVersion().toUpperCase() + "</B><br />" +
            		"Device Name: <B>" + GlobalThreadVariables.getdeviceName().toUpperCase() + "</B><br />"); 
            ExtentTestManager.getTest().log(LogStatus.PASS, 
            		"Browser / app is opened.");
        }
    }
 
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
        	ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=GREEN><B>Closing Browser / App</B></font>");
            WebDriver driver = LocalDriverManager.getDriver();
            if (null!=driver) {
                driver.quit();
            }
            ExtentTestManager.getTest().log(LogStatus.PASS, "The browser / app is closed.");
        }
    }
    
}
