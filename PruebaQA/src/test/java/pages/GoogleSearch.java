package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearch {


	String buttonAcceptCookiesID = "L2AGLb";
	String textAreaGoogleID = "APjFqb";
	
	WebDriver driver;
	

	public GoogleSearch( WebDriver driver){
		
		this.driver= driver;
		
	}
	
	public void searchOnGoogleByText(String text){
		
	//Por si sala la pagina de cookies
	try {
		
		WebElement acceptButtonID = driver.findElement(By.id(buttonAcceptCookiesID));
		if (acceptButtonID.isDisplayed()) {
		
			acceptButtonID.click();
		}
		
	}catch(Exception e) {
		// No ha saltado la pagina de Cookies asi que no hacemos nada, continuamos.
	}
	
	
	//Buscamos en google el texto
	WebElement textAreaGoogle = driver.findElement(By.id(textAreaGoogleID));
	textAreaGoogle.sendKeys(text);
	textAreaGoogle.sendKeys(Keys.ENTER);
		
	}
	
	

}
