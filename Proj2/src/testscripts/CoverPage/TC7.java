package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for verifying the coverpage Top stories and Top videos section
 * @author Cognizant
 */
public class TC7 extends TestCase
{
	@Test
	public void runTC7()
	{
		testParameters.setCurrentTestDescription("Test for verifying the banner and Box (300x250 | 300x600) Ads in Article Page of cover page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}