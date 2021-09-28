package com.revature.projectOnePOMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Employee {
	
	@FindBy(id = "myButton")
	private WebElement createRequestBtn; 
	
	public Employee(WebDriver driver) {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.elementToBeClickable(createRequestBtn));
	}
	
	public void createRequest() {
		createRequestBtn.click();
	}

}
