package com.projectname.qa.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.projectname.qa.base.MobileTestBase;
import com.projectname.qa.util.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class Screen_Welcome {
	WebDriver driver=null;
	
	public Screen_Welcome(WebDriver tdriver) {
		driver = tdriver;
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=Blue><B>Welcome Screen</B></font>");
		// TODO Auto-generated constructor stub
	}
	
	public WebElement btnAgreeAndContinue(){
		//return driver.findElement(btnAgreeAndContinue);
		return driver.findElement(MobileTestBase.OR.btnAgreeAndContinue);
	}
	
	public WebElement TextWelcome(){
		return driver.findElement(MobileTestBase.OR.element_TextWelcome);
	}
}
