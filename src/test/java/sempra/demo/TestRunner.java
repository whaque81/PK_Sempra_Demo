package sempra.demo;

/*public class TestRunner {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		TestNG testNG = new TestNG();
		testNG.setTestClasses(new Class[]{Login.class,Guide.class,Store_Locator.class});
		testNG.setParallel("methods");
		testNG.run();

	}

}*/

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions (
monochrome = true,
features = "src/test/java",
plugin = {"pretty", "html:target/cucumber-html-report"}
//glue = " sempra.demo"
//tags = "@testdiary"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
