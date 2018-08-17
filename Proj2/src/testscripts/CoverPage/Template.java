package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;
import com.cognizant.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Template test
 * @author Cognizant
 */
public class Template extends TestCase
{
	@Test(enabled = false)
	public void runTemplate()
	{
		// Modify the test parameters as required
		testParameters.setCurrentTestDescription("Template test");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.InternetExplorer);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}