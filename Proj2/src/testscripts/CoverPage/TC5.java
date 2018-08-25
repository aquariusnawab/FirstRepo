package testscripts.CoverPage;
import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Verification of Footer links
 * @author Cognizant
 */
public class TC5 extends TestCase
{
	@Test
	public void runTC5()
	{
		testParameters.setCurrentTestDescription("Verification of More drop down is displayed in header in Cover page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

}
