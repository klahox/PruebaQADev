package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Wikipedia {
	
	String divH2HistoriaID = "Historia";
	
	WebDriver driver;
	

	public Wikipedia( WebDriver driver){
		
		this.driver= driver;
		
	}
	
	public WebElement findElementWthThisID(String id){
		
		//En la pagina de wikipedia busco por id
		WebElement element = driver.findElement(By.id(id));
		
		return element;
		
	}

}
