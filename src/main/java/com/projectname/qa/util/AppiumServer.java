package com.projectname.qa.util;

import java.io.File;

import org.testng.Assert;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumServer {
	
	static String WinappiumInstallationDir = "C:/Program Files (x86)";// e.g. in Windows
	static String MacappiumInstallationDir = "/Applications";// e.g. for Mac
	static AppiumDriverLocalService service = null;
	static String service_url = "";
	
	public static void AppiumServer() {
		File classPathRoot = new File(System.getProperty("user.dir"));
		String osName = System.getProperty("os.name");
		
		if (osName.contains("Windows")) 
		{
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(WinappiumInstallationDir + File.separator + "Appium" + File.separator + "node.exe"))
				.withAppiumJS(new File(WinappiumInstallationDir + File.separator + "Appium" + File.separator
										+ "node_modules" + File.separator + "appium" + File.separator + "bin" + File.separator + "appium.js"))
				.withLogFile(new File(new File(classPathRoot, File.separator + "log"), "androidLog.txt")));
			service_url = service.getUrl().toString();
		} 
		else if (osName.contains("Mac")) 
		{
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
			.usingDriverExecutable(new File(MacappiumInstallationDir + "/Appium.app/Contents/Resources/node/bin/node"))
			.withAppiumJS(new File(
			MacappiumInstallationDir + "/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js"))
			.withLogFile(new File(new File(classPathRoot, File.separator + "log"), "androidLog.txt")));
			service_url = service.getUrl().toString();
		} 
		else 
		{
		// you can add for other OS, just to track added a fail message
			Assert.fail("Starting appium is not supporting the current OS.");
		}
	}

    public static boolean isAppiumrunning() {
    	if (!service.getUrl().toString().equalsIgnoreCase("") || !service.getUrl().toString().equalsIgnoreCase(null)){
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
	public void startAppiumServer() {
		service.start();
	}

	public void stopAppiumServer() {
		service.stop();
	}
}