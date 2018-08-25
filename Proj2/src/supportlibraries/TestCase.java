package supportlibraries;

import java.util.Date;

import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Util;
import com.cognizant.framework.selenium.*;

import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


/**
 * Abstract base class for all the test cases to be automated
 * @author Cognizant
 */
public abstract class TestCase
{
	/**
	 * The {@link SeleniumTestParameters} object to be used to specify the test parameters
	 */
	protected SeleniumTestParameters testParameters;
	/**
	 * The {@link DriverScript} object to be used to execute the required test case
	 */
	protected DriverScript driverScript;
	
	private ResultSummaryManager resultSummaryManager = new ResultSummaryManager();
	private Date startTime, endTime;
	
	
	/**
	 * Function to do the required set-up activities before executing the overall test suite in TestNG
	 * @param testContext The TestNG {@link ITestContext} of the current test suite 
	 */
	@BeforeSuite
	public void suiteSetup(ITestContext testContext)
	{
		resultSummaryManager.setRelativePath();
		resultSummaryManager.initializeTestBatch(testContext.getSuite().getName());
		
		int nThreads;
		if (testContext.getSuite().getParallel().equalsIgnoreCase("false")) {
			nThreads = 1;
		} else {
			nThreads = testContext.getCurrentXmlTest().getThreadCount();
		}
		resultSummaryManager.initializeSummaryReport(nThreads);
		resultSummaryManager.setupErrorLog();
	}
	
	/**
	 * Function to do the required set-up activities before executing each test case in TestNG
	 */
	@BeforeMethod
	public void testMethodSetup()
	{
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		if(frameworkParameters.getStopExecution()) {
			suiteTearDown();
			
			throw new SkipException("Aborting all subsequent tests!");
		} else {
			startTime = Util.getCurrentTime();
			
			String currentScenario =
					capitalizeFirstLetter(this.getClass().getPackage().getName().substring(12));
			String currentTestcase = this.getClass().getSimpleName();
			testParameters = new SeleniumTestParameters(currentScenario, currentTestcase);
		}
	}
	
	private String capitalizeFirstLetter(String myString)
	{
		StringBuilder stringBuilder = new StringBuilder(myString);
		stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
		return stringBuilder.toString();
	}
	
	/**
	 * Function to do the required wrap-up activities after executing each test case in TestNG
	 */
	@AfterMethod
	public void testMethodTearDown()
	{
		String testStatus = driverScript.getTestStatus();
		endTime = Util.getCurrentTime();
		String executionTime = Util.getTimeDifference(startTime, endTime);
		resultSummaryManager.updateResultSummary(testParameters.getCurrentScenario(),
									testParameters.getCurrentTestcase(),
									testParameters.getCurrentTestDescription(),
									executionTime, testStatus);
	}
	
	/**
	 * Function to do the required wrap-up activities after executing the overall test suite in TestNG
	 */
	@AfterSuite
	public void suiteTearDown()
	{
		resultSummaryManager.wrapUp(true);
		//resultSummaryManager.launchResultSummary();
	}
}