package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for verifying the coverpage Top stories and Top videos section
 * @author Cognizant
 */
public class TC10 extends TestCase
{
	@Test
	public void runTC10()
	{
		testParameters.setCurrentTestDescription("Test for verifying the Breaking news section in Cover Page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}