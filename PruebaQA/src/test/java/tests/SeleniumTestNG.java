package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GoogelResult;
import pages.GoogleSearch;
import pages.Wikipedia;


public class SeleniumTestNG {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\klajd\\Documents\\workspace\\PruebaQA\\driver\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}


	@Test
	public void initDriver() {
		
		//Busco en la pagina GoogleSearch por el texto Automatizacion
		GoogleSearch gSearch= new GoogleSearch(driver);
		gSearch.searchOnGoogleByText("automatizacion");
		
		//Busco en la pagina GoogleResult un hiperviculo que contenga wikipedia
		GoogelResult gResult = new GoogelResult(driver);
		gResult.findAndClickOverHyperLinkThatContains("wikipedia");
		
		//Busco en la pagina Wikipedia el elemento con id Historia
		Wikipedia wiki = new Wikipedia(driver); 
		WebElement historia = wiki.findElementWthThisID("Historia");
		
		//Estando en la pagina de wikipedia, hacemos scroll hasta el elemento que buscamos para que salga por captura
		Actions actions = new Actions(driver);
		actions.moveToElement(historia);
		actions.perform();	
		
		// Comprobamos que el elemento Historia sea visible la pagian de Wikipedia
		Assert.assertTrue(historia.isDisplayed(),"The seccion Historia should be visible in the wikipedia");	
		
		
	}
	

	@AfterMethod
	public void tearDown() throws IOException {
		

		//Obtengo la fecha actual con formato hasta segundos para ponerlo en el fichero de salida
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormat.format(currentDate);
		
		//Hacemos una captura de pantalla y mostramos la ruta del fichero		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("captura"+currentDateTime+".png");
		FileUtils.copyFile(srcFile,destFile);
		System.out.println("Ruta de la caputa => "+destFile.getAbsolutePath());
		
		//Close and quit
		driver.close();
		driver.quit();
    }
	



}