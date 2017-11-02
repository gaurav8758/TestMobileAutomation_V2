package com.projectname.qa.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.projectname.qa.base.MobileTestBase;
import com.projectname.qa.util.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class IOS_LoginTest extends MobileTestBase{
	WebDriver driver;
	//LoginPage loginPage;
	//HomePage homePage;

	public IOS_LoginTest(){
		super();
	}
	
	public void setup(){
		driver = getDriver();
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=GREEN><B>Setup Completed</B></font>");
	}
	
	@Test(groups={"IOS"})
	public void IOS_FirstTest(Method method) throws InterruptedException {
		System.out.println("Started Test:" + method.getName());
		setup();
		driver.findElement(MobileTestBase.OR.link_Alerts).click();
		Thread.sleep(5000);
	}
	
	@Test(groups={"IOS"})
	public void IOS_SecondTest(Method method) throws InterruptedException {
		System.out.println("Started Test:" + method.getName());
		setup();
		driver.findElement(MobileTestBase.OR.link_Alerts).click();
		Thread.sleep(5000);
	}
	
	@Test(groups={"IOS"})
	public void IOS_ThirdTest(Method method) throws InterruptedException {
		System.out.println("Started Test:" + method.getName());
		setup();
		driver.findElement(MobileTestBase.OR.link_Alerts).click();
		Thread.sleep(5000);
	}
	
	@Test(groups={"IOS"})
	public void IOS_FourthTest(Method method) throws InterruptedException {
		System.out.println("Started Test:" + method.getName());
		setup();
		driver.findElement(MobileTestBase.OR.link_Alerts).click();
		Thread.sleep(5000);
	}
}
