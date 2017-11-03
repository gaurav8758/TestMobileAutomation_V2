package com.projectname.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.projectname.qa.objectrepository.AndroidObjectRespository;
import com.projectname.qa.objectrepository.IOSObjectRespository;
import com.projectname.qa.objectrepository.ObjectRepository;
import com.projectname.qa.util.AppiumEventListener;
import com.projectname.qa.util.ExtentManager;
import com.projectname.qa.util.ExtentTestManager;
import com.projectname.qa.util.GlobalThreadVariables;
import com.projectname.qa.util.LocalDriverManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MobileTestBase {
	public static WebDriver driver;
	
	public static long ScriptWaitTimeCounter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static AppiumEventListener eventListener;
	
	public static ObjectRepository OR;
	
	public static boolean GlobalExtentReportsOverWrite=false;
	public static String GlobalExtentReportsLocation="C:" + File.separator + "Automation_Framework" + File.separator + "Reports" + File.separator + "ExtentReport.html";
	
	public MobileTestBase(){
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(new File(System.getProperty("user.dir") + File.separator +
					"src" + File.separator + "main"+ File.separator +"java" + File.separator + "com" + File.separator + 
					"projectname" + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties"));
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Parameters({"appiumVersion","platformName", "platformVersion", "automationName", 
		"deviceName", "app", "bundleID", "udid", "xcodeOrgId", "xcodeSigningId",
		"appPackage", "appActivity", "AppiumURL",
		"extentReportsOverwrite","extentReportsLocation"})
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(String appiumVersion, String platformName, String platformVersion,
			@Optional String automationName, String deviceName, String app,@Optional String bundleID,@Optional String udid,@Optional String xcodeOrgId,@Optional String xcodeSigningId,
			@Optional String appPackage, @Optional String appActivity, String AppiumURL,
			boolean extentReportsOverwrite, String extentReportsLocation,
			Method method
			) throws InterruptedException, MalformedURLException{
		
		
		new GlobalThreadVariables(appiumVersion, platformName, platformVersion, automationName,
				deviceName, app, bundleID, udid, xcodeOrgId, xcodeSigningId, appPackage, 
				appActivity, AppiumURL);
		
    	//both use reporting
    	GlobalExtentReportsOverWrite = Boolean.valueOf(extentReportsOverwrite);
    	GlobalExtentReportsLocation = extentReportsLocation;
    	
        ExtentTestManager.startTest(method.getName());
        
        if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("android"))
        {
        	OR = new AndroidObjectRespository();
        	ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=green><B>Object Repository:</B></font> Android Object Repository attached");
        }
        else if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("ios"))
        {
        	OR = new IOSObjectRespository();
        	ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=green><B>Object Repository:</B></font> IOS Object Repository attached");
        }
	}
	
	
    public WebDriver getDriver() {
        //System.out.println("Thread id = " + Thread.currentThread().getId());
        //System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        driver = LocalDriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new AppiumEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		return driver;
    }

	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result, Method method){
	       if (result.getStatus() == ITestResult.FAILURE) {
	           //ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
	           System.out.println(method.getName() + ": Fail");
	           //ExtentTestManager.getTest().log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(TestUtil.getLocationTimeStampOnFailures()));
	       } else if (result.getStatus() == ITestResult.SKIP) {
	           //ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	           System.out.println(method.getName() + ": Skip");
	       } else {
	           ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	           System.out.println(method.getName() + ": Pass");
	       }
	       
	       ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
	       ExtentManager.getReporter().flush();
	}
}
