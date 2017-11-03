package com.projectname.qa.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.projectname.qa.base.MobileTestBase;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    static ExtentReports extent;
    //final static String filePath = System.getProperty("user.dir") + "\\test-output\\ExtentReport.html";
    final static Path filePath = Paths.get(MobileTestBase.GlobalExtentReportsLocation + File.separator +"ExtentReport" + timestamp() + ".html");
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
        	if (MobileTestBase.GlobalExtentReportsOverWrite)
        	{
        		//System.out.println("Results File Location: " + filePath.toString());
        		extent = new ExtentReports(filePath.toString(), true, DisplayOrder.OLDEST_FIRST);
        		//System.out.println("inside true");
        	}
        	else
        	{
        		extent = new ExtentReports(filePath.toString(), false, DisplayOrder.OLDEST_FIRST);
        		//System.out.println("inside false");
        	}
        	extent
            //.addSystemInfo("Host Name", java.net.InetAddress.getLocalHost())
            .addSystemInfo("Environment", "Automation Testing - Environment")
            .addSystemInfo("User Name", System.getProperty("user.name"));
            
    		extent.loadConfig(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + 
    				"java" + File.separator + "com" + File.separator + "projectname" + File.separator + "qa" + File.separator + 
    				"config" + File.separator + "extent-config.xml"));
        }
        
        return extent;
    }
    
    private static String timestamp() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
    }
}