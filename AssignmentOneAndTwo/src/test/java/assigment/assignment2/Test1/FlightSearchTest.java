package assigment.assignment2.Test1;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FlightSearchTest{

	private WebDriver driver;
	WebDriverWait webDriverWait; 
	Properties prop_locator;
	Properties prop_testData;
	
	@BeforeTest
	public void setUp(){
		prop_locator = FlightSearch.setPropertiesFile("src\\test\\resources\\locator.properties");
		prop_testData = FlightSearch.setPropertiesFile("src\\test\\resources\\testData.properties");
	}
	

	
	@Parameters("browser")	 
	@BeforeClass
	public void beforeTest(String browser) {
	  
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shwetambari.pansare.INFOSTRETCH\\git\\Assignments1_Infostretch\\Assignment2\\Test1\\chromedriver.exe");
			driver = new ChromeDriver();
			webDriverWait = new WebDriverWait(driver, 30);
	    }else if (browser.equalsIgnoreCase("ie")) { 
	    	System.setProperty("webdriver.ie.driver", "C:\\Users\\shwetambari.pansare.INFOSTRETCH\\git\\Assignments1_Infostretch\\Assignment2\\Test1\\IEDriverServer.exe");
	    	driver = new InternetExplorerDriver();
	    	webDriverWait = new WebDriverWait(driver, 30);
	  } 
	driver.manage().window().maximize();
	}
	
	
	@Test(priority = 0)
	public void navigate(){
		try{
			driver.get(prop_testData.getProperty("URL"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 1)
	public void searchForFlights(){
		try{
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(prop_locator.getProperty("flightTab")))); 
		    driver.findElement(By.id(prop_locator.getProperty("flightTab"))).click();
		    
		    //search for a flight
		    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(prop_locator.getProperty("flyingFrom"))));
		    driver.findElement(By.id(prop_locator.getProperty("flyingFrom"))).clear();
		    driver.findElement(By.id(prop_locator.getProperty("flyingFrom"))).sendKeys(prop_testData.getProperty("DepartingFrom"));
		    driver.findElement(By.id(prop_locator.getProperty("flyingTo"))).clear();
		    driver.findElement(By.id(prop_locator.getProperty("flyingTo"))).sendKeys(prop_testData.getProperty("DepartingTo"));
		    driver.findElement(By.id(prop_locator.getProperty("departingDate"))).clear();
		    driver.findElement(By.id(prop_locator.getProperty("departingDate"))).sendKeys(prop_testData.getProperty("DepartingDate"));
		    driver.findElement(By.id(prop_locator.getProperty("returningDate"))).clear();
		    driver.findElement(By.id(prop_locator.getProperty("returningDate"))).sendKeys(prop_testData.getProperty("ReturningDate"));
		    
		    //save a screenshot of search criteria  
		    FlightSearch.getScreenshot(driver, "firstScreenshot");
		    
		    driver.findElement(By.id(prop_locator.getProperty("searchButton"))).click();
		    
		    //save a screenshot of search result
		    FlightSearch.getScreenshot(driver, "SecondScreenshot");
		    System.out.println("Done");
		   
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void validateResult(){
		try{
			Thread.sleep(6000);
		   List<WebElement> sourceDestinationNames = driver.findElements(By.xpath(prop_locator.getProperty("sourceDestination")));
		   for(int i=0;i<sourceDestinationNames.size();i++) {
			   Assert.assertEquals(prop_testData.getProperty("expctedSourceDestination"), sourceDestinationNames.get(i).getText());
		   }
		   
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	@AfterTest
	public void closeBrowser(){
		try{
			driver.quit();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
