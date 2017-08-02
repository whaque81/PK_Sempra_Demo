package sempra.demo;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class Store_Locator {
	
	private WebDriver driver;
	private ExtentReports reports;
	private ExtentTest logger;
	private WebDriverWait wait;
	
	@Test
	@Given("^I am on www\\.spectrum\\.net portal$")
	public void i_am_on_www_spectrum_net_portal() throws Exception {
		
	    logger = reports.startTest("Store_Locator");
	    driver.get("http://www.spectrum.net/");
	    logger.log(LogStatus.PASS, "Navigate to Spectrum.net portal", "Expected: Spectrum.net home page should open in the browser | Actual: Spectrum.net home page opened without any issues");	  
	    String homepage = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net home page " + homepage);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Locations")));
	}
	
	@Test
	@When("^I click on the Locations link$")
	public void i_click_on_the_Locations_link() throws Exception {
		driver.findElement(By.linkText("Locations")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ng-app']")).sendKeys(Keys.ESCAPE);
		Thread.sleep(3000);
	}

	@Test
	@Then("^I should be redirected to Spectrum Service page$")
	public void i_should_be_redirected_to_Spectrum_Service_page() throws Exception {
	    logger.log(LogStatus.PASS, "Click on Locations link at the bottom of the home page", "Expected: Spectrum.net service locations page should open in the browser | Actual: Spectrum.net service locations page opened without any issues");	  
		String locations = logger.addScreenCapture(getscreenshot());
		logger.log(LogStatus.INFO, "", "Spectrum.net service locations page " + locations);
	}

	@Test
	@When("^I enter zip code and distance within and click on the submit button$")
	public void i_enter_zip_code_and_distance_within() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://www.spectrum.com/browse/content/store-locations-partner']")));
		driver.findElement(By.xpath("//*[@id='store-locator-form']/input[1]")).sendKeys("80237"); 
		driver.findElement(By.xpath("//*[@id='store-locator-form']/input[2]")).sendKeys("100");
		driver.findElement(By.xpath("//*[@id='store-locator-form']/div[1]/button")).click();
	}

	
	@Test
	@Then("^I should be able to see the list of service centers near me$")
	public void i_should_be_able_to_see_the_list_of_service_centers_near_me() throws Exception {
		Thread.sleep(3000);
		String stores = logger.addScreenCapture(getscreenshot());
		logger.log(LogStatus.INFO, "", "Spectrum stores list " + stores);
	}
	
	public String getscreenshot() throws Exception 
	  {
	      UUID uuid = UUID.randomUUID();
		  String strFile = "src/test/results/screenshot-"+uuid+".png";
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(scrFile, new File(strFile));
	      return strFile;
	  }	
	
	@BeforeClass
	  public void beforeClass() throws Exception{
		  DesiredCapabilities dc = new DesiredCapabilities();
		  dc.setBrowserName("chrome");
		  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		  driver.manage().window().maximize();
		  reports = new ExtentReports("Store_Locator.html",false,DisplayOrder.NEWEST_FIRST);
		  wait = new WebDriverWait(driver,60);
	  }

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
		  reports.endTest(logger);
		  reports.flush();	  
		  reports.close();
	  }


}
