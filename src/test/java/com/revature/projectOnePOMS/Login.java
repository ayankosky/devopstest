package com.revature.projectOnePOMS;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	
	@FindBy(id = "username")
	private WebElement usernameField;
	@FindBy(id = "password")
	private WebElement passwordField;
	@FindBy(id = "submit")
	private WebElement submitBtn;
	
	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(250));
		
	}
	
	public void userLogin (String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitBtn.click();
	}
	
	public void loginByHittingEnterButton(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password, Keys.ENTER);
	}

}
