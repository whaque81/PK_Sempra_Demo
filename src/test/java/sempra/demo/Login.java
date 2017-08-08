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

public class Login {
	
	private WebDriver driver;
	private ExtentReports reports;
	private ExtentTest logger;
	private WebDriverWait wait;
	public static final String USERNAME = "wasimhaque2";
	public static final String AUTOMATE_KEY = "31sgzMQsi6rptoY3AZd1";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	@Test
	@Given("^I am on www\\.spectrum\\.net portal and I click on Sign In button$")
	public void i_am_on_www_spectrum_net_portal_and_I_click_on_Sign_In_button() throws Exception {
		
	    logger = reports.startTest("Login");
	    driver.get("http://www.spectrum.net/");
	    logger.log(LogStatus.PASS, "Navigate to Spectrum.net portal", "Expected: Spectrum.net home page should open in the browser | Actual: Spectrum.net home page opened without any issues");	  
	    String homepage = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net home page " + homepage);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sign In")));
	    driver.findElement(By.partialLinkText("Sign In")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='username']")));
	}
	@Test
	@When("^I enter valid credentials and click on the Sign In button$")
	public void i_enter_valid_autoqa__hoh_charter_net_and_Testing_in_the_textboxes() throws Exception {
		logger.log(LogStatus.PASS, "Click on Sign In link at the top of the page", "Expected: Spectrum.net login page should open in the browser | Actual: Spectrum.net login page opened without any issues");	  
	    String login = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net login page " + login);
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("autoqa003_hoh@charter.net");
	    driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Testing01");
	    logger.log(LogStatus.PASS, "Clicked on Sign In button", "Expected: Spectrum.net landing should open in the browser | Actual: Spectrum.net landing page opened without any issues");	  
	    String signin = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net login page " + signin);
	    driver.findElement(By.cssSelector("input[id='login-form-button']")).click(); 
	}

	@Test
	@Then("^I should be able to login into Spectrum portal$")
	public void i_should_be_able_to_login_into_Spectrum_portal() throws Exception {
		Thread.sleep(3000);
		String loggedin = logger.addScreenCapture(getscreenshot());
	    logger.log(LogStatus.INFO, "", "Spectrum.net landing page " + loggedin);
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
		  dc.setCapability("os", "OS X");
		  dc.setCapability("os_version", "Sierra");
		  dc.setCapability("browser", "Safari");
		  dc.setCapability("browserstack.debug", true);
		  //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		  driver = new RemoteWebDriver(new URL(URL), dc);
		  driver.manage().window().maximize();
		  reports = new ExtentReports("Login.html",false,DisplayOrder.NEWEST_FIRST);
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
