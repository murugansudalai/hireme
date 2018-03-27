package testsuite.sales;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import engine.excelDataReader;
import engine.readExcelSheet;



public class sales {

	//initialise web driver to null
	public static WebDriver driver = null;
	

	public static String baseurl = "http://www.rightmove.co.uk/"; 
	public static int timeoutInSeconds = 30;
	public static String area_search = "searchLocation";
	public static String forsale = "buy";
	public static String searchtext = "Hounslow, Middlesex";
	public static String area = "radius";
	public static String area_value = "Within ¼ mile";
	public static String price_min = "minPrice";
	public static String price_min_value = "50,000";
	public static String price_max = "maxPrice";
	public static String price_max_value = "250,000";
	
	public static String minBedrooms = "minBedrooms";
	public static String value_minBedrooms = "1";
	
	public static String maxBedrooms = "maxBedrooms";
	public static String value_maxBedrooms = "2";
	
	public static String displayPropertyType = "displayPropertyType";
	public static String value_displayPropertyType = "hounses";
	
	public static String maxDaysSinceAdded = "maxDaysSinceAdded";
	public static String value_maxDaysSinceAdded = "Last 3 days";
	
	public static String includeSSTC = "includeSSTC";
	public static boolean value_includeSSTC = true;
	
	public static String submit = "submit";
	
	//before suite execution
	@BeforeSuite
	//Setup and invoke the right browser
	public static void setup() throws Exception{
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/supportdrivers/chromedriver");				
		//invoke browser based on user input

	}
	
	@AfterSuite
	//Setup and invoke the right browser
	public static void closedriver() throws Exception{
		driver.close();
	}
			
	//open the url
	@Test
	public static void navigate(){
		Map<String, String> DATA_MAP = null;

		
		WebDriver driver = new ChromeDriver();

		readExcelSheet edr = new readExcelSheet();
		
		String excelPath = (System.getProperty("user.dir")+"/dataprovider/InputSheet_1.xls");
		String shtName = "customer_sales";
		String ScriptName = "testName";
		
		
		try {
			DATA_MAP = edr.CreateMapFromExcel(excelPath, shtName, ScriptName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.get(baseurl);
		driver.manage().window().maximize();

		
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(area_search)));
			searchtext = DATA_MAP.get("area").toString().toUpperCase();
			System.out.println("test"+area);
			driver.findElement(By.id(area_search)).sendKeys(searchtext);			
			driver.findElement(By.id(forsale)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(area)));		
			
			WebElement select = driver.findElement(By.id(area));
			List<WebElement> options = select.findElements(By.tagName("option"));
				
			for (WebElement option : options) {
			    if(area_value.equals(option.getText())) {
			        option.click();   
			    }
			}
			
			
			WebElement select_min = driver.findElement(By.id(price_min));
			List<WebElement> options_min = select_min.findElements(By.tagName("option"));
				
			for (WebElement option : options_min) {
			    if(price_min_value.equals(option.getText())) {
			        option.click();   
			    }
			}	
			
			WebElement select_max = driver.findElement(By.id(price_max));
			List<WebElement> options_max = select_max.findElements(By.tagName("option"));
				
			for (WebElement option : options_max) {
			    if(price_max_value.equals(option.getText())) {
			        option.click();   
			    }
			}
			
			
			WebElement select_minBedrooms = driver.findElement(By.id(minBedrooms));
			List<WebElement> options_minBedrooms = select_minBedrooms.findElements(By.tagName("option"));
				
			for (WebElement option : options_minBedrooms) {
			    if(value_minBedrooms.equals(option.getText())) {
			        option.click();   
			    }
			}
			
			WebElement select_maxBedrooms = driver.findElement(By.id(maxBedrooms));
			List<WebElement> options_maxBedrooms = select_maxBedrooms.findElements(By.tagName("option"));
				
			for (WebElement option : options_maxBedrooms) {
			    if(value_maxBedrooms.equals(option.getText())) {
			        option.click();   
			    }
			}
			
			WebElement select_displayPropertyType = driver.findElement(By.id(displayPropertyType));
			List<WebElement> options_displayPropertyType = select_displayPropertyType.findElements(By.tagName("option"));
				
			for (WebElement option : options_displayPropertyType) {
			    if(value_displayPropertyType.equals(option.getText())) {
			        option.click();   
			    }
			}	
			
			WebElement select_maxDaysSinceAdded = driver.findElement(By.id(maxDaysSinceAdded));
			List<WebElement> options_maxDaysSinceAdded = select_maxDaysSinceAdded.findElements(By.tagName("option"));
				
			for (WebElement option : options_maxDaysSinceAdded) {
			    if(value_maxDaysSinceAdded.equals(option.getText())) {
			        option.click();   
			    }
			}

			//driver.findElement(By.id(includeSSTC)).click();
			driver.findElement(By.id(submit)).click();			
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("element not found" + e.getMessage());
		}
		
	}
		
}
