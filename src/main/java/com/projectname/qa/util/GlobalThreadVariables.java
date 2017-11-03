package com.projectname.qa.util;


import com.projectname.qa.objectrepository.ObjectRepository;

public class GlobalThreadVariables {
	private static ThreadLocal<String> localappURL= new ThreadLocal<String>();;
    public static synchronized String getlocalappURL() {
        return localappURL.get();
    }
 
    static void setlocalappURL(String localappURL1) {
    	localappURL.set(localappURL1);
    }
	
	private static ThreadLocal<String> deviceName= new ThreadLocal<String>();;
    public static synchronized String getdeviceName() {
        return deviceName.get();
    }
 
    static void setlocaldeviceName(String deviceName1) {
    	deviceName.set(deviceName1);
    }
	
	private static ThreadLocal<String> platformVersion= new ThreadLocal<String>();
    public static synchronized String getplatformVersion() {
        return platformVersion.get();
    }
 
    static void setlocalplatformVersion(String platformVersion1) {
    	platformVersion.set(platformVersion1);
    }
	
	private static ThreadLocal<String> platformName= new ThreadLocal<String>();
    public static synchronized String getplatformName() {
        return platformName.get();
    }
 
    static void setlocalplatformName(String platformName1) {
    	platformName.set(platformName1);
    }
    
	private static ThreadLocal<String> appiumURL= new ThreadLocal<String>();
    public static synchronized String getappiumURL() {
        return appiumURL.get();
    }
 
    static void setlocalappiumURL(String appiumURL1) {
    	appiumURL.set(appiumURL1);
    }
    
	private static ThreadLocal<String> appPackage= new ThreadLocal<String>();
    public static synchronized String getappPackage() {
        return appPackage.get();
    }
 
    static void setlocalappPackage(String appPackage1) {
    	appPackage.set(appPackage1);
    }
	
	private static ThreadLocal<String> appActivity= new ThreadLocal<String>();
    public static synchronized String getappActivity() {
        return appActivity.get();
    }
 
    static void setlocalappActivity(String appActivity1) {
    	appActivity.set(appActivity1);
    }
	
	private static ThreadLocal<String> appiumVersion= new ThreadLocal<String>();
    public static synchronized String getappiumVersion() {
        return appiumVersion.get();
    }
 
    static void setlocalappiumVersion(String appiumVersion1) {
    	appiumVersion.set(appiumVersion1);
    }
    
	private static ThreadLocal<String> AutomationName= new ThreadLocal<String>();
    public static synchronized String getAutomationName() {
        return AutomationName.get();
    }
 
    static void setlocalAutomationName(String AutomationName1) {
    	AutomationName.set(AutomationName1);
    }
	
	private static ThreadLocal<String> BundleID= new ThreadLocal<String>();
    public static synchronized String getBundleID() {
        return BundleID.get();
    }
 
    static void setlocalBundleID(String BundleID1) {
    	BundleID.set(BundleID1);
    }
    
	private static ThreadLocal<String> UDID= new ThreadLocal<String>();
    public static synchronized String getUDID() {
        return UDID.get();
    }
 
    static void setlocalUDID(String UDID1) {
    	UDID.set(UDID1);
    }
	
	private static ThreadLocal<String> xcodeOrgId= new ThreadLocal<String>();
    public static synchronized String getxcodeOrgId() {
        return xcodeOrgId.get();
    }
 
    static void setlocalxcodeOrgId(String xcodeOrgId1) {
    	xcodeOrgId.set(xcodeOrgId1);
    }
    
	private static ThreadLocal<String> xcodeSigningId= new ThreadLocal<String>();
    public static synchronized String getxcodeSigningId() {
        return xcodeSigningId.get();
    }
 
    static void setlocalxcodeSigningId(String xcodeSigningId1) {
    	xcodeSigningId.set(xcodeSigningId1);
    }
    
	private static ThreadLocal<ObjectRepository> OR;//Need to change this to OR
    public static synchronized ObjectRepository getOR() {
        return OR.get();
    }
 
    static void setlocalOR(ObjectRepository OR1) {
    	OR.set(OR1);
    }
    
    public GlobalThreadVariables(String appiumVersion, String platformName, 
    		String platformVersion, String automationName,
			String deviceName, String app, String bundleID, String udid, 
			String xcodeOrgId, String xcodeSigningId, String appPackage, 
			String appActivity, String AppiumURL) throws InterruptedException {
		setlocalappiumVersion(appiumVersion);
		setlocalplatformName(platformName);
		setlocalplatformVersion(platformVersion);
		setlocalAutomationName(automationName);
		setlocaldeviceName(deviceName);
		setlocalappURL(app);
		setlocalBundleID(bundleID);
		setlocalUDID(udid);
		setlocalxcodeOrgId(xcodeOrgId);
		setlocalxcodeSigningId(xcodeSigningId);
		setlocalappPackage(appPackage);
		setlocalappActivity(appActivity);
		setlocalappiumURL(AppiumURL);
		Thread.sleep(500);
	}
}
