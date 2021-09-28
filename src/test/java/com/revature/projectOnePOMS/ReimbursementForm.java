package com.revature.projectOnePOMS;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReimbursementForm {

	
	@FindBy(id = "reimbursementType")
	private WebElement reimbursementTypeField;
	@FindBy(id = "reimbursementAmount") 
	private WebElement reimbursementAmountField;
	@FindBy(id = "submit")
	private WebElement submitBtn;
	
	public ReimbursementForm(WebDriver driver) {
		
			PageFactory.initElements(driver, this);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			
			wait.until(ExpectedConditions.elementToBeClickable(reimbursementTypeField));
		
	}
	
	public void submitRequest(String reimbursementType, String reimbursementAmount) {
		reimbursementTypeField.sendKeys(reimbursementType);
		reimbursementAmountField.sendKeys(reimbursementAmount);
		submitBtn.click();
	}
	
	public void submitRequestWithEnterKey(String reimbursementType, String reimbursementAmount) {
		reimbursementTypeField.sendKeys(reimbursementType);
		reimbursementAmountField.sendKeys(reimbursementAmount, Keys.ENTER);
		
	}
}
