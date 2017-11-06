package com.projectname.qa.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

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
	
	@SuppressWarnings("rawtypes")
	public static void MultiTouch(MobileDriver driver, By by) throws InterruptedException{
		WebElement catImage=driver.findElement(by);
		//System.out.println(catImage.getLocation().x);//0
		//System.out.println(catImage.getLocation().y);//150
		int centerX = catImage.getLocation().x+ (catImage.getSize().width/2) ;
		int centerY = catImage.getLocation().y+ (catImage.getSize().height/2) ;
		//System.out.println(centerX+" --- "+centerY );//0
	
		
		MultiTouchAction multiTouch = new MultiTouchAction((MobileDriver)driver);
		
		Thread.sleep(1000);
		TouchAction act1 = new TouchAction((MobileDriver)driver);
		act1.press(centerX, centerY-10).moveTo(centerX, centerY-500).release();
		TouchAction act2 = new TouchAction((MobileDriver)driver);
		act2.press(centerX, centerY+10).moveTo(centerX, centerY+500).release();
		
		multiTouch.add(act1).add(act2).perform();
	}
	
	@SuppressWarnings("rawtypes")
	public static void SwipeAction(MobileDriver driver, By by, String SearchString){
		List<WebElement> friends = driver.findElements(by);
		
		WebElement lastElement = friends.get(friends.size()-1);
		int x = lastElement.getLocation().x;
		int y = lastElement.getLocation().y;
		
		while(!isFriendDisplayed(friends, SearchString)){
			driver.swipe(x, y, x, y-200, 2000);
		}
		// find friend and tap on it
		
		WebElement e = null;
		
		if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("ios"))
		{
			e = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name="+SearchString+"']"));
		}
		else if (GlobalThreadVariables.getplatformName().equalsIgnoreCase("android"))
		{
			e = driver.findElement(By.xpath("//android.widget.TextView[@text='"+SearchString+"']"));
		}
			
		TouchAction act = new TouchAction((MobileDriver)driver);
		act.tap(e).perform();
	}
	
	public static void DragandDrop(WebDriver driver, By dragSectionsListID, int FromIndex, int ToIndex) {
		List<WebElement> dragSections = driver.findElements(dragSectionsListID);
		//List<WebElement> allNames = driver.findElements(By.id("com.mobeta.android.demodslv:id/text"));
		WebElement e1=dragSections.get(FromIndex);
		WebElement e2=dragSections.get(ToIndex);
		
		//for(WebElement name:allNames){
		//	System.out.println(name.getText());
		//}
		
		TouchAction act = new TouchAction((MobileDriver)driver);
		act.longPress(e1).moveTo(e2).release().perform();
	}

	public static void DragandDropToLastElement(WebDriver driver, By dragSectionsListID, By dragSectionsTextID, int FromIndex){
		 List<WebElement> dragSections = driver.findElements(dragSectionsListID); //"com.mobeta.android.demodslv:id/drag_handle"
		 List<WebElement> allNames = driver.findElements(dragSectionsTextID); //By.id("com.mobeta.android.demodslv:id/text")
		 
		 WebElement e1=dragSections.get(FromIndex);
		 WebElement lastElement=dragSections.get(dragSections.size()-1);
		 
		 lastElement=dragSections.get(dragSections.size()-1);
		 int x = lastElement.getLocation().x;
		 int y = lastElement.getLocation().y;
		 
		 TouchAction act = new TouchAction((MobileDriver)driver);
		 
		 while(true){
			 String topElement = allNames.get(0).getText();
			 act.longPress(lastElement).moveTo(x,y+100).release().perform();
			 String newTopElement = allNames.get(0).getText();
			 if(newTopElement.equals(topElement))
				 break;			 
		 }
	}
	
	public static void TapAnElement(WebDriver driver, By by){
		WebElement e = driver.findElement(by); //android.widget.TextView[@text='Durga Etl']
		TouchAction act = new TouchAction((MobileDriver)driver);
		
		act.tap(e).perform();
	}
	
	public static void LongTapAnElement(WebDriver driver, By by){
		WebElement e = driver.findElement(by); //android.widget.TextView[@text='Durga Etl']
		TouchAction act = new TouchAction((MobileDriver)driver);
		
		act.longPress(e).perform();
	}
	
	public static void LongTapAnElementWithWait(WebDriver driver, By by, int delaytime){
		WebElement e = driver.findElement(by); //android.widget.TextView[@text='Durga Etl']
		TouchAction act = new TouchAction((MobileDriver)driver);
		
		act.press(e).waitAction(delaytime).perform();
	}
	
	public static void Zoom(WebDriver driver, int xcordinates, int ycordinates){
		MobileDriver mDriver= (MobileDriver)driver;
		mDriver.zoom(xcordinates, ycordinates);
	}
	
	public static void Pinch(WebDriver driver, int xcordinates, int ycordinates){
		MobileDriver mDriver= (MobileDriver)driver;
		mDriver.pinch(xcordinates, ycordinates);
	}
	
	public static void hidekeyboard(WebDriver driver){
		MobileDriver mDriver= (MobileDriver)driver;
		mDriver.hideKeyboard();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Keyboard is hidden.");
	}
	
	public static void SearchTextinList(WebDriver driver, By byID, By textID, String SearchText){
		 @SuppressWarnings("rawtypes")
		MobileDriver mDriver= (MobileDriver)driver;
	     MobileElement box = (MobileElement) mDriver.findElement(byID); //com.ebay.mobile:id/recycler
	     List<MobileElement> names= box.findElements(textID); //com.ebay.mobile:id/title_text
	     
	     //System.out.println("Total names - "+names.size());
	     int y_max=driver.manage().window().getSize().height;
	     
	     while(true){
	    	 for(int i=0;i<names.size();i++){
		    	 System.out.println(names.get(i).getText());
		    	 if(SearchText.equals(names.get(i).getText())){
		    		 names.get(i).click();
		    			 break;
		    	 }
		     }
	    	 mDriver.swipe(500, y_max-75, 500, 75, 5000);
	     }
	}
	
	//-----------------------------------------------------------------------------------
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
	
	private static boolean isFriendDisplayed(List<WebElement> friends, String SearchString){
		for(WebElement friend : friends ){
				if(friend.getText().equals(SearchString))
					return true;
		}
		
		return false;
	}
}
