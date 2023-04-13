package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Amazon_new {
	WebDriver driver;
	
	@BeforeMethod
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[@class='nav-line-2 ']")).click();
		WebElement email=driver.findElement(By.xpath("//input[@name='email']"));
		email.sendKeys("9003603706"); // amazon email or phone_number
		email.submit();
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("Deepak@1amazon"); // amazon password
		password.submit();
	}


	@Test(priority=1)
	public void LoginCheck() {
		// TODO Auto-generated method stub
		String username=driver.findElement(By.id("nav-link-accountList-nav-line-1")).getText();
		Assert.assertEquals(username, "Hello, Deepu"); 
	}
		
	@Test(priority=2)
	public void FindFirstResult() {
		WebElement searchbox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("iqoo z7");
		searchbox.submit();
		WebElement firstresult=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][1]"));
		String text=firstresult.getText().toLowerCase();
		System.out.println(text);
		boolean test2=text.contains("iqoo z7");
		Assert.assertEquals(true, test2);
		}
	
	@Test(priority=3)
	public void Addtocart() throws InterruptedException {
		WebElement searchbox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("iqoo z7");
		searchbox.submit();
		WebElement firstresult=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][1]"));
		Actions Mousebar= new Actions(driver);
		Mousebar.moveToElement(firstresult).click().perform();
		Set<String> Windowhandle=driver.getWindowHandles();
		List<String> list = new ArrayList<String>(Windowhandle);
		driver.switchTo().window(list.get(1));
		WebElement addtocart=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		Mousebar.moveToElement(addtocart).click().perform();
		driver.findElement(By.xpath("//a[@id='attach-close_sideSheet-link'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("nav-cart-count-container")).click();
		
		
		WebElement iqoo=driver.findElement(By.xpath("//div[@data-name='Active Items']/div[@data-asin='B07WFPMQB1']"));
		String z=iqoo.getText().toLowerCase();
		boolean test3=z.contains("iqoo z7"); 
		Assert.assertEquals(true, test3);
	}
	
	@Test(priority=4)
	public void OrderConfirmationTest() throws InterruptedException
	{		
		WebElement searchbox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("iqoo z7");
		searchbox.submit();
		WebElement firstresult=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][1]"));
		Actions Mousebar= new Actions(driver);
		Mousebar.moveToElement(firstresult).click().perform();
		Set<String> Windowhandle=driver.getWindowHandles();
		List<String> list = new ArrayList<String>(Windowhandle);
		driver.switchTo().window(list.get(1));
		WebElement Buynow=driver.findElement(By.xpath("//input[@id='buy-now-button']"));
		Mousebar.moveToElement(Buynow).click().perform();
	
		
		Thread.sleep(3000);
		List<WebElement> options=driver.findElements(By.xpath("//div[@data-a-input-name='ppw-instrumentRowSelection']"));
		for (WebElement option:options) {
			if(option.getText().equals("Cash on Delivery/Pay on Delivery")) {
				option.click();
			}
		}
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//span[@id='orderSummaryPrimaryActionBtn']")).click();
		WebElement order=driver.findElement(By.xpath("//input[@name='placeYourOrder1' and @class='a-button-input' and @aria-labelledby='bottomSubmitOrderButtonId-announce']"));
		order.click();
		Thread.sleep(3000);
		String Orderconfirmation2=driver.findElement(By.xpath("//h4[@class='a-alert-heading']")).getText();
		Assert.assertEquals(Orderconfirmation2, "Order placed, thank you!");	
	}
	@AfterMethod
	public void quit() {
		driver.quit();
	}
	
	
}


