package testscripts.CoverPage;

import org.testng.annotations.Test;

import com.cognizant.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class TC2 extends TestCase
{
	@Test
	public void runTC2()
	{
		testParameters.setCurrentTestDescription("Test for verifying the Header in Cover page");
		testParameters.setBrowser(Browser.Chrome);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}