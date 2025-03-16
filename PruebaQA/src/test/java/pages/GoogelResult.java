package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogelResult {
	
	WebDriver driver;
	

	public GoogelResult( WebDriver driver){
		
		this.driver= driver;
		
	}
	
	public void findAndClickOverHyperLinkThatContains(String text){
		
		WebElement aHyperlinkElement = driver.findElement(By.cssSelector("a[href*="+text+"]"));
		
		aHyperlinkElement.click();
	}

}
