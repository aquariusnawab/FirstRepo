package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for verifying the coverpage Top stories and Top videos section
 * @author Cognizant
 */
public class TC13 extends TestCase
{
	@Test//9th test case in akhila's
	
	public void runTC13()
	{
		testParameters.setCurrentTestDescription("Test for Verification of hyperlinks of section Covers in Cover Page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}