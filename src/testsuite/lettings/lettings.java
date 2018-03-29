package testsuite.lettings;

//excel class
import engine.readExcelSheet;
import jxl.Sheet;

//java imports
import java.util.List;
import java.util.Map;
//selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//TestNG import
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class lettings {

	//initialise web driver to null
	public static WebDriver driver = null;
	public static int timeoutInSeconds = 30;
	
	public static String baseurl = "http://www.rightmove.co.uk/"; 
	
	public static String area_search = "searchLocation";	
	public static String searchtext = "Hounslow, Middlesex";
	
	public static String forsale = "buy";
		
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
	
	//open the url
	@Test
	public static void navigate(){
		Map<String, String> dataMap = null;
		Object[][] rowMap = null;
		WebDriver driver = new ChromeDriver();
		readExcelSheet rexsht = new readExcelSheet();
		String excelPath = (System.getProperty("user.dir")+"/dataprovider/inputsheet_v1.xls");

		System.out.println("excel path is"+excelPath);

		String shtName = "Sales";
		Object row = null;

		//rowMap = rexsht.getExcelData(excelPath, shtName, ScriptName);

		try {

			Sheet sheet;			
			sheet = rexsht.Excel(excelPath, shtName);
			row = sheet.getRows();

			System.out.println("the number of list"+row+"number of the rows of execution"+	row);
			
			System.out.println("Search text before this test is"+searchtext);

			
			for (int i = 1; i < (int) row; i++) { 
			
				driver.manage().window().maximize();
				driver.get(baseurl);
				
			dataMap = rexsht.CreateMapFromExcel(excelPath, shtName,Integer.toString((int) i));
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(area_search)));
			String searchtext = dataMap.get("area").toString().toUpperCase();
			area_value = dataMap.get("radius").toString().toUpperCase();
			price_min_value= dataMap.get("price_range_min").toString();
			price_max_value= dataMap.get("price_range_max").toString();
			value_minBedrooms= dataMap.get("no_of_bedroom_min").toString();
			value_maxBedrooms= dataMap.get("no_of_bedroom_max").toString();
			value_displayPropertyType= dataMap.get("displayPropertyType").toString().toUpperCase();
			value_maxDaysSinceAdded= dataMap.get("added_to_site_on").toString().toUpperCase();
			
			
			System.out.println("Valures are "
			+searchtext	
			+area_value		
			+price_min_value
			+price_max_value
			+value_minBedrooms
			+value_maxBedrooms
			+value_displayPropertyType
			+value_maxDaysSinceAdded+"	thats it");
			driver.findElement(By.id(area_search)).sendKeys(searchtext);			
			driver.findElement(By.id(forsale)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(area)));		
			
			WebElement select = driver.findElement(By.id(area));
			List<WebElement> options = select.findElements(By.tagName("option"));
				
			for (WebElement option : options) {
			    if(area_value.equals(option.getText().toUpperCase())) {
			        option.click();   
			    }
			}
		
	    	System.out.println("Valures are "
					+price_min+	
					price_max
					);	
			

			WebElement select_min = driver.findElement(By.id(price_min));
			select_min.sendKeys(price_min_value);
			
			List<WebElement> options_min = select_min.findElements(By.tagName("option"));
			
			for (WebElement option : options_min) {
			    if(price_min_value.equals(option.getText())) {
			    	System.out.println("Valures are "
							+price_min_value
							+option.getText());
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
			    if(value_displayPropertyType.equals(option.getText().toUpperCase())) {
			        option.click();   
			    }
			}	
			
			WebElement select_maxDaysSinceAdded = driver.findElement(By.id(maxDaysSinceAdded));
			List<WebElement> options_maxDaysSinceAdded = select_maxDaysSinceAdded.findElements(By.tagName("option"));
				
			for (WebElement option : options_maxDaysSinceAdded) {
			    if(value_maxDaysSinceAdded.equals(option.getText().toUpperCase())) {
			        option.click();   
			    }
			}

			//driver.findElement(By.id(includeSSTC)).click();
			driver.findElement(By.id(submit)).click();			
			
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("element not found" + e.getMessage());
		}
		
		String propertycard="propertyCard-0";
		
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("propertySearch-results-container")));
		
		String featuredProperty1 = null;
		
			WebElement featuredProperty = driver.findElement(By.className("propertyCard-moreInfoFeaturedTitle"));
			featuredProperty1 = featuredProperty.getText();
			//driver.findElement(By.cssSelector(".field[data-test='"+propertycard+"']"));
			if (featuredProperty1==null) {
				System.out.println("Feture property is not found" + featuredProperty1 );
				//driver.findElement(By.cssSelector('[data-test="propertyCard-2"]'));
				driver.findElement(By.cssSelector("[data-test='propertyCard-0']")).click();
				
			}else {
				propertycard = "propertyCard-"+1;		
				System.out.println("Feture property is found" + featuredProperty1 );
				driver.findElement(By.cssSelector("[data-test='propertyCard-1']")).click();
				
			}
		}		
		
	@AfterSuite
	//Setup and invoke the right browser
	public static void closedriver() throws Exception{
		
		driver.close();
		
	}
		
}
