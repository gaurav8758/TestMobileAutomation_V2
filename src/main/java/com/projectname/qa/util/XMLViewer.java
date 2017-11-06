package com.projectname.qa.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class XMLViewer {
	
	static File iOSFilePath;
	static File AndroidFilePath;
	
	
	public XMLViewer() throws FileNotFoundException{
		iOSFilePath = new File(System.getProperty("user.dir") + File.separator +
			"src" + File.separator + "main"+ File.separator +"java" + File.separator + "com" + File.separator + 
			"projectname" + File.separator + "qa" + File.separator + "config" + File.separator + "iOSXMLLoad.xml");
		AndroidFilePath = new File(System.getProperty("user.dir") + File.separator +
				"src" + File.separator + "main"+ File.separator +"java" + File.separator + "com" + File.separator + 
				"projectname" + File.separator + "qa" + File.separator + "config" + File.separator + "androidXMLLoad.xml");

	}
	
	public static void savePageSource(WebDriver driver) throws IOException{
        String src = driver.getPageSource();
        
        File file = null;
        if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("ios")){
        	file = iOSFilePath;
        }
        else if(GlobalThreadVariables.getplatformName().equalsIgnoreCase("android"))
        {
        	file = AndroidFilePath;
        }
        
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(src);
		bw.close();
	}

}
