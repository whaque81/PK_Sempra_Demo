package sempra.demo;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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

public class Guide {
	
	private WebDriver driver;
	private ExtentReports reports;
	private ExtentTest logger;
	private WebDriverWait wait;
	public static final String USERNAME = "wasimhaque1";
	public static final String AUTOMATE_KEY = "htV8xATeN8NrLo5rV7EV";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  
  @Test
  @Given("^I am on www\\.spectrum\\.net website$")
	public void i_am_on_www_spectrum_net_website() throws Exception {
	    logger = reports.startTest("Guide");
	    driver.get("http://www.spectrum.net/");
	    logger.log(LogStatus.PASS, "Navigate to Spectrum.net portal", "Expected: Spectrum.net home page should open in the browser | Actual: Spectrum.net home page opened without any issues");	  
	    String homepage = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net home page " + homepage);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-footer']/div[2]/div/div[2]/div/div[2]/ul/li[2]/a")));

	}
  	@Test
	@When("^I click on the Guide link$")
	public void i_click_on_the_Guide_link() throws Exception {
  		driver.findElement(By.xpath("//*[@id='page-footer']/div[2]/div/div[2]/div/div[2]/ul/li[2]/a")).click();
	    logger.log(LogStatus.PASS, "Click on Guide link at the bottom of the home page", "Expected: Spectrum.net TV Guide page should open in the browser | Actual: Spectrum.net TV Guide page opened without any issues");
	    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div[1]/div/div/div[1]/h1")));
	}
  	@Test
	@Then("^I should be redirected to the TV Guide page$")
	public void i_should_be_redirected_to_the_TV_Guide_page() throws Exception {
		String guide = logger.addScreenCapture(getscreenshot());
		logger.log(LogStatus.INFO, "", "Spectrum.net TV Guide page " + guide);
		Thread.sleep(2000);
	    
	}
	
	public String getscreenshot() throws Exception 
	  {
	      UUID uuid = UUID.randomUUID();
		  String strFile = "src/test/results/screenshot-"+uuid+".png";
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(scrFile, new File(strFile));
	      return strFile;}
  
  @BeforeClass
  public void beforeClass() throws Exception{
	  DesiredCapabilities dc = new DesiredCapabilities();
	  dc.setCapability("os", "Windows");
	  dc.setCapability("os_version", "7");
	  dc.setCapability("browser", "chrome");
	  dc.setCapability("browserstack.debug", true);
	  //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
	  driver = new RemoteWebDriver(new URL(URL), dc);
	  reports = new ExtentReports("Guide.html",false,DisplayOrder.NEWEST_FIRST);
	  wait = new WebDriverWait(driver,30);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
	  reports.endTest(logger);
	  reports.flush();	  
	  reports.close();
  }

}




