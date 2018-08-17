package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for verifying the coverpage Top stories and Top videos section
 * @author Cognizant
 */
public class TC9 extends TestCase
{
	@Test //5test case in akhila
	public void runTC9()
	{
		testParameters.setCurrentTestDescription("Verification of social sharing links in Cover Page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}