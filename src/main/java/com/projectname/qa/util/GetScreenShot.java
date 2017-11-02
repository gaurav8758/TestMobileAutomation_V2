package com.projectname.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.projectname.qa.base.MobileTestBase;

public class GetScreenShot {
    public static String capture(WebDriver driver) 
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = MobileTestBase.GlobalExtentReportsLocation + File.separator + "Screenshots" + File.separator + "Screenshot_" + timestamp() + ".png";
        File destination = new File(dest);
        try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
                     
        return dest;
    }
    
    public static String timestamp() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
    }
}
