package testscripts.CoverPage;
import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Verification of Nightly News and other links in Header section
 * @author Cognizant
 */
public class TC4 extends TestCase
{
	@Test
	public void runTC4()
	{
		testParameters.setCurrentTestDescription("Verification of NIGHTLY NEWS,TODAY MEET THEPRESS,DATELINE links in header section of Cover page ");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

}
