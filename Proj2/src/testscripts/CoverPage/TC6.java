package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for verifying the coverpage Top stories and Top videos section
 * @author Cognizant
 */
public class TC6 extends TestCase
{
	@Test
	public void runTC6()
	{
		testParameters.setCurrentTestDescription("Test for verifying the Search lens in header is being displayed in cover page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}