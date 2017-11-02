package com.projectname.qa.objectrepository;

import org.openqa.selenium.By;

public class IOSObjectRespository extends ObjectRepository{
	public IOSObjectRespository(){
		
	//Welcome Page Objects
	btnAgreeAndContinue = By.id("com.whatsapp:id/eula_accept");
	element_TextWelcome = By.xpath("//android.widget.TextView[@text='Welcome to asdfa']");
	
	//VerifyYourPhoneNumber Page Objects
	
	//Default page for IOS
	link_Alerts = By.xpath("//XCUIElementTypeButton[@name='Alerts']");
	}
}
