package com.projectname.qa.objectrepository;

import org.openqa.selenium.By;

public class AndroidObjectRespository extends ObjectRepository{
	public AndroidObjectRespository(){
	
	//Welcome Page Objects
	btnAgreeAndContinue = By.id("com.whatsapp:id/eula_accept");
	element_TextWelcome = By.xpath("//android.widget.TextView[@text='Welcome to WhatsApp']");
	
	//VerifyYourPhoneNumber Page Objects
	}
}
