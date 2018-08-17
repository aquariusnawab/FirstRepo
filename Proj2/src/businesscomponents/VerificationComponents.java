package businesscomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.WebDriverUtil;

/**
 * Verification Components class
 * 
 * @author Cognizant
 */
public class VerificationComponents extends ReusableLibrary {
	private WebDriverUtil driverUtil;

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public VerificationComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);

		driverUtil = new WebDriverUtil(driver);
	}

	public void verifyLoginValidUser() {
		if (driverUtil.objectExists(By.linkText("SIGN-OFF"))) {
			report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
		} else {
			frameworkParameters.setStopExecution(true);
			throw new FrameworkException("Verify Login", "Login failed for valid user");
		}
	}

	public void verifyLoginInvalidUser() {
		if (!driverUtil.objectExists(By.linkText("SIGN-OFF"))) {
			report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
		} else {
			report.updateTestLog("Verify Login", "Login succeeded for invalid user", Status.FAIL);
		}
	}

	public void verifyRegistration() {
		String userName = dataTable.getData("General_Data", "Username");

		if (driverUtil.isTextPresent("^[\\s\\S]*Dear " + dataTable.getExpectedResult("FirstName") + " "
				+ dataTable.getExpectedResult("LastName") + "[\\s\\S]*$")) {
			report.updateTestLog("Verify Registration", "User " + userName + " registered successfully", Status.PASS);
		} else {
			throw new FrameworkException("Verify Registration", "User " + userName + " registration failed");
		}
	}

	public void verifyBooking() {
		if (driverUtil.isTextPresent("^[\\s\\S]*Your itinerary has been booked![\\s\\S]*$")) {
			report.updateTestLog("Verify Booking", "Tickets booked successfully", Status.PASS);

			// WebElement flightConfirmation =
			// driver.findElement(By.xpath("//font/font/b/font"));
			WebElement flightConfirmation = driver.findElement(By.cssSelector("font > font > b > font"));

			String flightConfirmationNumber = flightConfirmation.getText();
			flightConfirmationNumber = flightConfirmationNumber.split("#")[1].trim();
			dataTable.putData("Flights_Data", "FlightConfirmationNumber", flightConfirmationNumber);
			report.updateTestLog("Flight Confirmation", "The flight confirmation number is " + flightConfirmationNumber,
					Status.DONE);
		} else {
			report.updateTestLog("Verify Booking", "Tickets booking failed", Status.FAIL);
		}
	}
}