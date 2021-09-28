package frontEndTests;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.projectOnePOMS.Employee;
import com.revature.projectOnePOMS.Login;
import com.revature.projectOnePOMS.ReimbursementForm;


@TestMethodOrder(OrderAnnotation.class)
public class EmployeeRequestSuccess {
	
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://localhost:3000/login.html");
		
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
	}
	
	@Test
	@Order(1)
	public void EmployeeUserLogin() {
		Login login = new Login(driver);
		
		login.userLogin("jsmith", "12345");
		
		Assert.assertEquals("redirect success", "http://localhost:3000/form/employee.html", driver.getCurrentUrl());
	}
	
	@Test
	@Order(2)
	public void ReimbursementClickCreate() {
		Employee employee = new Employee(driver);
		
		employee.createRequest();
		Assert.assertEquals("redirect success", "http://localhost:3000/form/reimbursementForm.html", driver.getCurrentUrl());
	}
	
	@Test
	@Order(3)
	public void SubmitReimbursement() {
		ReimbursementForm rf = new ReimbursementForm(driver);
		
		rf.submitRequest("Coffee Run", "33.75");
		Assert.assertEquals("redirect success", "http://localhost:3000/form/employee.html", driver.getCurrentUrl());
		
	}
	
	@AfterAll
	public static void tearDown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
		
	}
	
	

}
