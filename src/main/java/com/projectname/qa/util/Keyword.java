package com.projectname.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class Keyword {
	public static WebElement Button(WebDriver driver, String buttonText){
		WebElement e=null;
		
			if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("ios"))
			{
				By by = By.xpath("//XCUIElementTypeButton[@name='" + buttonText + "']");
				e = getElement(driver, by);
			}
			else if(GlobalThreadVariables.getplatformName().equalsIgnoreCase("android"))
			{
				By by = By.xpath("//android.widget.Button[@text='" + buttonText + "']");
				e = getElement(driver, by);
			}
		return e;
	}
	
	private static WebElement getElement(WebDriver driver, By by){
		WebElement e = null;
		if (driver.findElements(by).size()==0)
		{
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Element '" + by + "' is not found.");
        	e = null;
		}
		else if (driver.findElements(by).size()==1)
		{
			e = driver.findElement(by);
		}
		else if (driver.findElements(by).size()>1)
		{
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "More than 1 element '" + by + "' found for this object in the screen.");
        	e = driver.findElement(by);
		}
		return e;
	}
}
