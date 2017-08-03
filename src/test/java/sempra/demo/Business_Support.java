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

public class Business_Support {
	
	private WebDriver driver;
	private ExtentReports reports;
	private ExtentTest logger;
	private WebDriverWait wait;
	public static final String USERNAME = "wasimhaque1";
	public static final String AUTOMATE_KEY = "htV8xATeN8NrLo5rV7EV";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
  
	@Test
	@Given("^I am on www\\.spectrumbusiness\\.net portal$")
	public void i_am_on_www_spectrumbusiness_net_portal() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logger = reports.startTest("Business_Support");
	    driver.get("https://www.spectrumbusiness.net/");
	    logger.log(LogStatus.PASS, "Navigate to Spectrum Business portal", "Expected: Spectrum Business home page should open in the browser | Actual: Spectrum Business home page opened without any issues");	  
	    String homepage = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum Business home page " + homepage);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sb-body']/main/div[1]/div/div/div/div[2]/div[1]/div/div")));
	}
	
	@Test
	@When("^I click on the Support link$")
	public void i_click_on_the_Support_link() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id='sb-body']/main/div[1]/div/div/div/div[2]/div[1]/div/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[id='zipCode']")).clear();
		driver.findElement(By.cssSelector("input[id='zipCode']")).sendKeys("31901");
	    driver.findElement(By.xpath("//*[@id='dialogContent_0']/div/div[2]/div[1]/div/div[3]/form/button/span")).click();
		Thread.sleep(4000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sb-body']/main/div[1]/div[1]/div[1]/div[2]/div[2]/ul/li[4]/a")));
	} 

	@Test
	@When("^I click on the Internet link$")
	public void i_click_on_the_Internet_link() throws Exception {
	    // Write code here that turns the phrase above into concrete actions

		//driver.findElement(By.xpath("//*[@id='sb-body']/main/div[1]/div[1]/div[1]/div[2]/div[2]/ul/li[4]/a")).click();
		//driver.findElement(By.xpath("//*[@id='sb-body']/div[2]/div/div[3]/form/input")).sendKeys("internet");
		driver.findElement(By.xpath("//div[text()='Search']")).click();
	}

	@Test
	@Then("^I should be able to see the list of internet related troubleshooting tips$")
	public void i_should_be_able_to_see_the_list_of_internet_related_troubleshooting_tips() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Spectrum Speed Test']")));
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
	  
	  DesiredCapabilities dc = new DesiredCapabilities().firefox();
	  /*dc.setCapability("os", "Windows");
	  dc.setCapability("os_version", "7");
	  dc.setCapability("browser", "chrome");
	  dc.setCapability("browserstack.debug", true);*/
	  //dc.setBrowserName("chrome");
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
	  //driver = new RemoteWebDriver(new URL(URL), dc);
	  //driver.manage().window().maximize();
	  reports = new ExtentReports("TestResults.html",false,DisplayOrder.NEWEST_FIRST);
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
