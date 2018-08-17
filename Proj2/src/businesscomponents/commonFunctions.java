package businesscomponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

import com.cognizant.framework.Status;
import uimap.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognizant.framework.selenium.WebDriverUtil;

public class commonFunctions extends ReusableLibrary {

	private static final Object[] String = null;
	private static final int MAX_TIMEOUT = 180;

	public commonFunctions(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	public void actionClick(By element1) {
		wait.until(ExpectedConditions.elementToBeClickable(element1)).click();

	}

	// code done by akhila
	public void actionClick(WebElement element1) {
		wait.until(ExpectedConditions.elementToBeClickable(element1)).click();

	}

	// Code done by anand
	public void actionClickWE(WebElement element1) {
		wait.until(ExpectedConditions.elementToBeClickable(element1)).click();

	}

	public void actionDisplayWE(By element1) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element1));

	}

	public boolean waitForObjectVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, MAX_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean waitForObjectVisible(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, MAX_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(locator));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void validatePageTitle(String titleString, String sectionName, String pageName) {
		report.updateTestLog("Validate " + driver.getTitle() + " Section",
				"Successfully navigated to the page - " + pageName, Status.PASS);

		if (driver.getTitle().contains(titleString)) {
			report.updateTestLog("Validate " + sectionName + " Section",
					"Successfully navigated to the page - " + pageName, Status.PASS);
		} else {
			report.updateTestLog("Validate " + sectionName + " Section", "Link broken - " + pageName, Status.FAIL);
		}
	}

	public void validatePageUrl(String titleString, String sectionName, String pageName) {
		// report.updateTestLog("Validate "+driver.getCurrentUrl()+" Section",
		// "Successfully navigated to the page - "+pageName, Status.PASS);
		System.out.println("Current Url is displayed as " + driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains(titleString)) {

			report.updateTestLog("Validate " + sectionName + " Section",
					"Successfully navigated to the page - " + pageName, Status.PASS);
		} else {
			report.updateTestLog("Validate " + sectionName + " Section", "Link broken - " + pageName, Status.FAIL);
		}
	}

	public void validatePageUrlCustomFront(String titleString, String sectionName, String pageName) {
		// report.updateTestLog("Validate "+driver.getCurrentUrl()+" Section",
		// "Successfully navigated to the page - "+pageName, Status.PASS);

		if (driver.getCurrentUrl().contains(titleString)) {

			report.updateTestLog("Validate " + sectionName + " Section",
					"Successfully navigated to the page - " + pageName, Status.PASS);
		} else {
			report.updateTestLog("Validate " + sectionName + " Section", "Link broken - " + pageName, Status.WARNING);
		}
	}

	public void verifyStatic(By item, String sectionName, String itemName) {

		if (driver.findElement(item).isDisplayed()) {
			report.updateTestLog("Verify " + sectionName + " Section", itemName + " Present", Status.PASS);
		} else {
			report.updateTestLog("Verify " + sectionName + " Section", itemName + " not Present", Status.FAIL);
		}
	}

	public void verifyDynamic(By item, String sectionName) {

		if (driver.findElement(item).isDisplayed()) {
			String itemName = driver.findElement(item).getText();

			report.updateTestLog("Verify " + sectionName + " Section", itemName + " Present", Status.PASS);
		} else {
			report.updateTestLog("Verify " + sectionName + " Section", "Item not Present", Status.FAIL);
		}
	}

	// changes done by akhila
	// Function to Verify a link by clicking on it and validating whether it is
	// navigating to correct Page
	public void verifySection(By item, String sectionName) {

		if (driver.findElement(item).isDisplayed()) {
			String titleString = driver.findElement(item).getText();
			driver.findElement(item).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("LinkText :" + titleString);
			report.updateTestLog("Title of the New Page " + driver.getCurrentUrl() + " Section",
					"Successfully navigated to the page - " + driver.getCurrentUrl(), Status.PASS);

			System.out.println("Title of the New Page :" + driver.getCurrentUrl());
			/*
			 * if(titleString.contains(" ")) { String newTitle =
			 * titleString.replace(" ", "-");
			 * 
			 * if (driver.getCurrentUrl().contains(newTitle.toLowerCase())) {
			 * System.out.println("Title of the URL is :"+driver.getTitle());
			 * report.updateTestLog("Verify "+sectionName+" Section",
			 * "Successfully navigated to the page - "+titleString,
			 * Status.PASS); } else {
			 * report.updateTestLog("Verify "+sectionName+" Section",
			 * "Link broken - "+titleString, Status.FAIL); } }
			 */
			wait(3000);
			if (titleString.contains(" ") || (titleString.contains("'")) || ((titleString.contains("#")))) {
				String newTitle = titleString.replace("'", "");
				String newTitle1 = newTitle.replace(" ", "-");

				if (driver.getCurrentUrl().contains(newTitle1.toLowerCase())) {
					System.out.println("Title of the URL is :" + driver.getTitle());
					report.updateTestLog("Verify " + sectionName + " Section",
							"Successfully navigated to the page - " + titleString, Status.PASS);
				} else {
					report.updateTestLog("Verify " + sectionName + " Section", "Link broken - " + titleString,
							Status.FAIL);
				}
			} else if (!(titleString.contains(" ")) || ((titleString.contains("#")))) {
				String newTitle = titleString.replace("#", "");
				if (driver.getCurrentUrl().contains(newTitle.toLowerCase())) {
					System.out.println("Title of the URL is :" + driver.getTitle());
					report.updateTestLog("Verify " + sectionName + " Section",
							"Successfully navigated to the page - " + titleString, Status.PASS);
				} else {
					report.updateTestLog("Verify " + sectionName + " Section", "Link broken - " + titleString,
							Status.FAIL);
				}
			} else {
				if (driver.getCurrentUrl().contains(titleString.toLowerCase())) {
					System.out.println("Title of the URL is :" + driver.getTitle());
					report.updateTestLog("Verify " + sectionName + " Section",
							"Successfully navigated to the page - " + titleString, Status.PASS);
				} else {
					report.updateTestLog("Verify " + sectionName + " Section", "Link broken - " + titleString,
							Status.FAIL);
				}
			}
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
	}

	public void verifySocialShare(By item, String iconName) {

		String sCurrentUrl = driver.getCurrentUrl();
		System.out.println("Current Url :" + driver.getCurrentUrl());
		driver.navigate().back();
		if ((sCurrentUrl.contains(iconName))) {
			report.updateTestLog("verifySocialSharingicons", "Validation- Successfull" + iconName, Status.PASS);
		} else {
			report.updateTestLog("verifySocialSharingicons", "Validation- Unsuccessfull" + iconName, Status.FAIL);
		}
	}

	/*
	 * public List<WebElement> getSectionLinks(String sectionName) {
	 * List<WebElement> sectionLinks; //
	 * SectionFronts.sectionLinks(sectionName); //sectionLinks =
	 * driver.findElements(SectionFronts.sectionLink); //return sectionLinks;
	 * 
	 * }
	 */

	/*
	 * public List<WebElement> getSectionImg(String sectionName) {
	 * List<WebElement> sectionImg; SectionFronts.sectionImages(sectionName);
	 * sectionImg = driver.findElements(SectionFronts.sectionImg); return
	 * sectionImg; }
	 */

	public void verifyATWPage() {
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			if (!newWindow.equals(parentWindow)) {
				driver.switchTo().window(newWindow);
				wait(2000);
				if (driver.getTitle() != "NULL") {
					report.updateTestLog("Around the Web", "Successfully navigated to page - " + driver.getTitle(),
							Status.PASS);
				} else {
					report.updateTestLog("Around the Web", "Broken Link", Status.FAIL);
				}
				wait(1000);
				driver.close();
			}

		}

		driver.switchTo().window(parentWindow);
	}

	// Function to Verify the New Browser Page opened is not Null
	public void verifyNewBrowserPage() {
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
		}
		if (driver.getTitle() != "NULL") {
			report.updateTestLog("verifyNewBrowserPage", "Successfully navigated to page - " + driver.getTitle(),
					Status.PASS);
		} else {
			report.updateTestLog("verifyNewBrowserPage", "Broken Link", Status.FAIL);
		}
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	// Function to Verify the Social sharing links which opens in a New Browser
	public void verifySocialSharingPages() {
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
			System.out.println("Title of the Page:" + driver.switchTo().window(newWindow).getTitle());
		}
		if ((driver.getTitle().contains("Facebook")) || (driver.getTitle().contains("Twitter"))
				|| (driver.getTitle().contains("Google")) || ((driver.getTitle().contains("Newsletter")))) {
			report.updateTestLog("verifyNewBrowserPage", "Successfully navigated to page - " + driver.getTitle(),
					Status.PASS);
		} else {
			report.updateTestLog("verifyNewBrowserPage", "Broken Link", Status.FAIL);
		}
		driver.close();
		driver.switchTo().window(parentWindow);

	}

	public void verifyArticleFooter(By item) throws InterruptedException {
		Thread.sleep(2000);
		if (driver.findElement(item).isDisplayed()) {
			actionClick(item);
		}
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
		}
		if ((driver.getTitle().contains("Taboola"))) {
			report.updateTestLog("verifyNewBrowserPage", "Successfully navigated to page - " + driver.getTitle(),
					Status.PASS);
		} else {
			report.updateTestLog("verifyNewBrowserPage", "Broken Link", Status.FAIL);
		}
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	/*
	 * changes done by anand
	 ***/

	public int genRandNum(int max, int min) {
		Random rn = new Random();
		int randNum = rn.nextInt((max - min) + 1) + min;
		return randNum;
	}

	public void validateLinksNew(By _link, String validateString, String validationMsg, String linkName) {
		actionClick(_link);
		/*
		 * Actions action = new Actions(driver);
		 * action.moveToElement(driver.findElement(_link)).perform();
		 * action.click(driver.findElement(_link)).build().perform();
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();", driver.findElement(_link));
		 * js.executeScript("document.getElementById(_link).click()");
		 */
		wait(3000);
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
		}
		wait(3000);
		validatePageTitle(validateString, "validation of " + validationMsg + " links", linkName);
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	/*
	 * public List<WebElement> getSectionTextLinks(String sectionName) {
	 * List<WebElement> sectionTextWeb;
	 * SectionFronts.sectionTextLinks(sectionName); sectionTextWeb =
	 * driver.findElements(SectionFronts.sectionText); return sectionTextWeb; }
	 */

	public void validatePageURLSectionFronts(String titleString, String sectionName, String pageName) {

		wait(5000);
		report.updateTestLog("Validate " + driver.getCurrentUrl() + " Section",
				"Successfully navigated to the page - " + pageName, Status.PASS);
		if (titleString.contains(" ")) {
			String newTitleString = titleString.replace(" ", "-");
			if (driver.getCurrentUrl().contains(newTitleString)) {
				report.updateTestLog("Validate " + sectionName + " Section",
						"Successfully navigated to the page - " + pageName, Status.PASS);
			} else {
				report.updateTestLog("Validate " + sectionName + " Section", "Link broken - " + pageName, Status.FAIL);
			}
		} else {
			if (driver.getCurrentUrl().contains(titleString)) {
				report.updateTestLog("Validate " + sectionName + " Section",
						"Successfully navigated to the page - " + pageName, Status.PASS);
			} else {
				report.updateTestLog("Validate " + sectionName + " Section", "Link broken - " + pageName, Status.FAIL);
			}
		}
	}

	public void waitforload() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return document.readyState").toString().equals("complete");
		wait(5000);
	}

	public void verifyNewPageBrowser(By item, String tileName) {
		String parentWindow = driver.getWindowHandle();
		for (String newWindow : driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
		}
		if (driver.getCurrentUrl().contains(tileName)) {
			report.updateTestLog("verifyNewPageBrowser", "Successfully navigated to page - " + driver.getTitle(),
					Status.PASS);
		} else {
			report.updateTestLog("verifyNewPageBrowser", "Broken Link", Status.FAIL);
		}
		driver.close();
		driver.switchTo().window(parentWindow);

	}

	// Verify Email Address

	public boolean validateEmail(String emailStr) {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(emailStr);
		System.out.println(emailStr + " : " + matcher.matches());
		if (matcher.matches()) {
			return true;
		}
		return false;

	}

	public boolean AlertPresent() throws InterruptedException {

		try {
			// Handle the alert pop-up using seithTO alert statement
			Alert alert = driver.switchTo().alert();

			// Print alert is present
			System.out.println("Alert is present");

			// get the message which is present on pop-up
			String message = alert.getText();

			// print the pop-up message
			System.out.println(message);

			// Click on OK button on pop-up
			alert.accept();
			Thread.sleep(2000);
			return true;
		} catch (NoAlertPresentException e) {
			// if alert is not present print message
			System.out.println("alert is not present");
			return false;
		}

	}

	public void refreshPage() throws AWTException {
		wait(2000);
		driver.get(driver.getCurrentUrl());
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		wait(2000);
	}

}
