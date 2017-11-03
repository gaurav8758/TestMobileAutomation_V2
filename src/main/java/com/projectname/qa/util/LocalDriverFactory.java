package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.projectname.qa.base.MobileTestBase;

public class LocalDriverFactory {
    static synchronized AppiumDriver<?> createInstance(String platformName)  {
        AppiumDriver<?> Localdriver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium-version", GlobalThreadVariables.getappiumVersion());
        capabilities.setCapability("deviceName", GlobalThreadVariables.getdeviceName());
    	capabilities.setCapability("platformVersion", GlobalThreadVariables.getplatformVersion());
    	capabilities.setCapability("platformName", GlobalThreadVariables.getplatformName());

   		if ((null!=GlobalThreadVariables.getlocalappURL()) && !(GlobalThreadVariables.getlocalappURL().equalsIgnoreCase("")))
   		{
   			File app = new File(GlobalThreadVariables.getlocalappURL());
   			capabilities.setCapability("app", app.getAbsolutePath());
   		}
   		else
   		{
        	capabilities.setCapability("appPackage", GlobalThreadVariables.getappPackage());
        	capabilities.setCapability("appActivity", GlobalThreadVariables.getappActivity());
   		}
   		
   		//Checking the Appium server URL
   		String AppiumServerURL = "";
   		if ((null!=GlobalThreadVariables.getappiumURL()) && !(GlobalThreadVariables.getappiumURL().equalsIgnoreCase("")))
   		{
   			AppiumServerURL = GlobalThreadVariables.getappiumURL();
   		}
   		
   		if (platformName.equalsIgnoreCase("android"))
   		{
   			AndroidDriver<AndroidElement>driver=null;
			try 
			{
				driver = new AndroidDriver<AndroidElement>(new URL(AppiumServerURL), capabilities);
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Localdriver = driver;	
   		}
   		else
   		{
   			IOSDriver<IOSElement>driver=null;
			try 
			{
				capabilities.setCapability("xcodeOrgId", GlobalThreadVariables.getxcodeOrgId());
				capabilities.setCapability("xcodeSigningId", GlobalThreadVariables.getxcodeSigningId());
				capabilities.setCapability("udid", GlobalThreadVariables.getUDID());
				capabilities.setCapability("automationName", GlobalThreadVariables.getAutomationName());
				capabilities.setCapability("bundleID", GlobalThreadVariables.getBundleID());
				driver = new IOSDriver<IOSElement>(new URL(AppiumServerURL), capabilities);
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Localdriver = driver;
   		}
        return Localdriver;
    }
}