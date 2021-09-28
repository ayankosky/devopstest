package com.revature.projectOnePOMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Manager {
	
	@FindBy(id = "names")
	private WebElement nameSelect;
	@FindBy(className = "status")
	private WebElement statusUpdateSelect;
	
	public Manager(WebDriver driver) {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(statusUpdateSelect));
	}
	
	

}
