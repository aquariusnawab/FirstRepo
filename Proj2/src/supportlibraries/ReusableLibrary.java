package supportlibraries;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.formula.ExternSheetReferenceToken;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.framework.CraftDataTable;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Settings;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.SeleniumReport;

import uimap.CMSArticlePage;
import uimap.MediaModelPage;
import uimap.News_FrontPage;
import uimap.coverPage;

/**
 * Abstract base class for reusable libraries created by the user
 * 
 * @author Cognizant
 */
public abstract class ReusableLibrary {
	/**
	 * The {@link CraftDataTable} object (passed from the test script)
	 */
	protected CraftDataTable dataTable;
	/**
	 * The {@link SeleniumReport} object (passed from the test script)
	 */
	protected SeleniumReport report;
	/**
	 * The {@link WebDriver} object
	 */
	protected WebDriver driver;
	/**
	 * The {@link ScriptHelper} object (required for calling one reusable
	 * library from another)
	 */
	protected ScriptHelper scriptHelper;

	/**
	 * The {@link Properties} object with settings loaded from the framework
	 * properties file
	 */
	protected Properties properties;
	/**
	 * The {@link FrameworkParameters} object
	 */
	protected FrameworkParameters frameworkParameters;

	/**
	 * Constructor to initialize the {@link ScriptHelper} object and in turn the
	 * objects wrapped by it
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object
	 */
	public ReusableLibrary(ScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;

		this.dataTable = scriptHelper.getDataTable();
		this.report = scriptHelper.getReport();
		this.driver = scriptHelper.getDriver();

		properties = Settings.getInstance();
		frameworkParameters = FrameworkParameters.getInstance();
	}

	public List<WebElement> findElements(By property) {
		List<WebElement> elements = null;
		elements = driver.findElements(property);
		return elements;
	}

	public WebElement findElement(By property) {
		WebElement element = null;
		element = driver.findElement(property);
		return element;
	}

	public void wait(int mSec) {
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyElementVisible(By property, String cssProperty) {
		if (findElement(property).getCssValue(cssProperty).equalsIgnoreCase("none")) {
			return false;
		} else {
			return true;
		}
	}

	public void enterText(By txtElement, String Value, String fieldName) {

		try {
			driver.findElement(txtElement).clear();
			driver.findElement(txtElement).sendKeys(Value);
			report.updateTestLog("Enter Text", Value + " is entered in " + fieldName, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Enter Text", Value + " is NOT entered in " + fieldName + e.getMessage(), Status.FAIL);

		}
	}

	public void clickElement(By clkElement, String fieldName) {

		try {
			waitForObjectVisible(clkElement);
			driver.findElement(clkElement).click();
			loadPage();
			report.updateTestLog("Click Element", fieldName + " is clicked", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click Element", fieldName + " is NOT clicked " + e.getMessage(), Status.FAIL);

		}
	}

	public void selectElement(By clkElement, String fieldName) {
		// WebDriver driver1 = driver.getWebDriver();
		try {
			waitForObjectVisible(clkElement);
			// waitForObjectClickable(clkElement);
			driver.findElement(clkElement).click();
			loadPage();
			report.updateTestLog("Select Element", fieldName + " is selected", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Select Element", fieldName + " is NOT selected " + e.getMessage(), Status.FAIL);

		}
	}

	public void selectDropdownVal(By selElement, String Value, String fieldName) {
		// WebDriver driver1 = driver.getWebDriver();
		try {
			waitForObjectVisible(selElement);
			WebElement w = driver.findElement(selElement);
			Select sel = new Select(w);
			sel.selectByVisibleText(Value);

			report.updateTestLog("Select Dropdown Value", fieldName + " is selected as " + Value, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Select Dropdown Value", fieldName + " is NOT selected as " + Value + e.getMessage(),
					Status.FAIL);

		}
	}

	public void verifyDropdownSel(By selElement, String Value, String fieldName) {

		try {
			WebElement w = driver.findElement(selElement);
			Select sel = new Select(w);
			String selVal = sel.getFirstSelectedOption().getText();

			if (selVal.equals(Value)) {
				report.updateTestLog("Verify Dropdown selected Value", fieldName + " is selected as " + Value,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Dropdown selected Value", fieldName + " is NOT selected as " + Value,
						Status.FAIL);
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Dropdown selected Value", "Exception occured : " + e.getMessage());

		}
	}

	public String getDropdownSelVal(By selElement) {

		try {
			WebElement w = driver.findElement(selElement);
			Select sel = new Select(w);
			String selVal = sel.getFirstSelectedOption().getText();
			return selVal;

		} catch (Exception e) {
			throw new FrameworkException("Get Dropdown selected Value", "Exception occured : " + e.getMessage());

		}
	}

	public void loadPage() {
		// WebDriver driver1 = driver.getWebDriver();
		try {
			// WebDriver driver1 = driver.getWebDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int j = 0; j < 60; j++) {
				wait(1000);
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		} catch (Exception e) {
			wait(5000);
			// WebDriver driver1 = driver.getWebDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int j = 0; j < 60; j++) {
				wait(1000);
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		}
	}

	public boolean waitForObjectVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement we = driver.findElement(locator);
			wait.until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean waitForObjectInVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement we = driver.findElement(locator);
			wait.until(ExpectedConditions.invisibilityOf(we));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean waitForObjectClickable(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement we = driver.findElement(locator);
			wait.until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isAlertExist() {
		try {
			WebDriverWait w = new WebDriverWait(driver, 5);
			w.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void navigateToSubMenu(String link1, String link2) throws InterruptedException {

		try {
			wait(2000);

			Actions action = new Actions(driver);
			// li[@class='expandable']/a[text()='Article']

			waitForObjectVisible(By.xpath("//ul[@id='admin-menu-menu']/li/a[contains(text(),'" + link1 + "')]"));
			WebElement we1 = driver
					.findElement(By.xpath("//ul[@id='admin-menu-menu']/li/a[contains(text(),'" + link1 + "')]"));

			action.moveToElement(we1).build().perform();

			/*
			 * waitForObjectVisible(By.linkText(link3)); WebElement
			 * subMnuArticlePage = driver.findElement(By.linkText(link3));
			 */
			waitForObjectVisible(By.xpath("//a[contains(text(), '" + link1
					+ "')]/./following-sibling::ul[@class='dropdown']//a[text()='" + link2 + "']"));
			WebElement subMnuArticlePage = driver.findElement(By.xpath("//a[contains(text(), '" + link1
					+ "')]/./following-sibling::ul[@class='dropdown']//a[text()='" + link2 + "']"));

			subMnuArticlePage.click();
			loadPage();

			report.updateTestLog("Navigate Sub-Menu", "Navigate to " + link1 + " ---> " + link2, Status.PASS);

		} catch (Exception e) {
			throw new FrameworkException("Navigate Sub-Menu", "Exception occured : " + e.getMessage());
		}

	}

	public void navigateToSubMenu(String link1, String link2, String link3) throws InterruptedException {

		try {
			wait(2000);

			Actions action = new Actions(driver);
			// li[@class='expandable']/a[text()='Article']

			waitForObjectVisible(By.xpath("//ul[@id='admin-menu-menu']/li/a[contains(text(),'" + link1 + "')]"));
			WebElement we1 = driver
					.findElement(By.xpath("//ul[@id='admin-menu-menu']/li/a[contains(text(),'" + link1 + "')]"));

			action.moveToElement(we1).build().perform();

			/*
			 * waitForObjectVisible(By.linkText(link2)); WebElement we2 =
			 * driver.findElement(By.linkText(link2));
			 */
			waitForObjectVisible(By.xpath("//li[@class='expandable']/a[text()='" + link2 + "']"));
			WebElement we2 = driver.findElement(By.xpath("//li[@class='expandable']/a[text()='" + link2 + "']"));

			action.moveToElement(we2).build().perform();

			/*
			 * waitForObjectVisible(By.linkText(link3)); WebElement
			 * subMnuArticlePage = driver.findElement(By.linkText(link3));
			 */
			waitForObjectVisible(By.xpath("//a[contains(text(), '" + link2
					+ "')]/./following-sibling::ul[@class='dropdown']//a[text()='" + link3 + "']"));
			WebElement subMnuArticlePage = driver.findElement(By.xpath("//a[contains(text(), '" + link2
					+ "')]/./following-sibling::ul[@class='dropdown']//a[text()='" + link3 + "']"));

			subMnuArticlePage.click();
			loadPage();

			report.updateTestLog("Navigate Sub-Menu", "Navigate to " + link1 + " ---> " + link2 + " ---> " + link3,
					Status.PASS);

		} catch (Exception e) {
			throw new FrameworkException("Navigate Sub-Menu", "Exception occured : " + e.getMessage());
		}

	}

	public void goHome() {

		clickElement(CMSArticlePage.menuHome, "Home");
	}

	public void addVideo() {

		try {

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			// clickElement(CMSArticlePage.btnVids, "Videos");

			selectDropdownVal(CMSArticlePage.listVideoSearchDate, "All", "Date");

			clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			waitForObjectVisible(CMSArticlePage.selDirVid);

			selectElement(CMSArticlePage.selDirVid, "A Video");

			clickElement(CMSArticlePage.btnSubmitVid, "Submit");

			/*
			 * WebElement wf1v =
			 * driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']")
			 * ); driver.switchTo().frame(wf1v); wait(1000);
			 * 
			 * clickElement(CMSArticlePage.btnSubmitImgConf, "Submit");
			 */

			/*
			 * WebElement element1 =
			 * driver.findElement(CMSArticlePage.addMedia);
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);",
			 * element1); report.updateTestLog("Attach Video",
			 * "A Video is attached", Status.SCREENSHOT);
			 */

			// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
			// 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Video", "Video could not be added - Error : " + e.getMessage(), Status.FAIL);
		}

	}

	public void addImage() {

		try {

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			selectElement(CMSArticlePage.selImage, "An Image");

			clickElement(CMSArticlePage.btnSubmitImg, "Submit");

			/*
			 * WebElement wf1 =
			 * driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']")
			 * ); driver.switchTo().frame(wf1);
			 * 
			 * wait(1000); clickElement(CMSArticlePage.btnSubmitImgConf,
			 * "Submit");
			 */

			wait(1000);
			driver.switchTo().parentFrame();

			/*
			 * WebElement element = driver.findElement(CMSArticlePage.addMedia);
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);",
			 * element);
			 * 
			 * report.updateTestLog("Attach Image", "An Image is attached",
			 * Status.SCREENSHOT);
			 */

			// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
			// 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Image", "Image could not be added - Error : " + e.getMessage(), Status.FAIL);
		}

	}

	public void selectWidgetTabAndMenu(String widgetTab, String tabMenu) {
		selectElement(By.linkText(widgetTab), widgetTab);

		selectElement(By.linkText(tabMenu), tabMenu);
	}

	public void multiCuratedListType(int no) {
		try {
			String title = dataTable.getData("General_Data", "ArticleHeadline");
			String curRefType = dataTable.getData("General_Data", "Curated Ref Type");
			String[] a = title.split(";");
			String[] b = curRefType.split(";");

			for (int i = 1; i <= no; i++) {
				enterText(By.xpath("(//div[contains(@id,'article-or-list-add-more-wrapper')]//input[1])[" + i + "]"),
						a[i - 1], "Article, List Or Slideshow");

				selectDropdownVal(By.xpath("(//select[contains(@id,'curated-reference-type')])[" + i + "]"), b[i - 1],
						"Curated Reference Type");
			}

			report.updateTestLog("Curated List Reference", "Filled Curated List References " + no, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Curated List Reference", "NOT Filled Curated List References " + no + e.getMessage(),
					Status.FAIL);

		}
	}

	public void singleCuratedListType(int no) {
		try {
			String title = dataTable.getData("General_Data", "ArticleHeadline");
			String curRefType = dataTable.getData("General_Data", "Curated Ref Type");

			enterText(By.xpath("(//div[contains(@id,'article-or-list-add-more-wrapper')]//input[1])[" + no + "]"),
					title, "Article, List Or Slideshow");

			selectDropdownVal(By.xpath("(//select[contains(@id,'curated-reference-type')])[" + no + "]"), curRefType,
					"Curated Reference Type");

			report.updateTestLog("Curated List Reference", "Filled the Curated List Reference " + no, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Curated List Reference",
					"NOT Filled the Curated List Reference " + no + e.getMessage(), Status.FAIL);

		}
	}

	public void isChecked(By locator, String chkName) {
		try {
			waitForObjectVisible(locator);
			if (driver.findElement(locator).isSelected()) {
				report.updateTestLog("Verify CheckBox", chkName + " is checked", Status.PASS);
			} else {
				report.updateTestLog("Verify CheckBox", chkName + " is NOTchecked", Status.FAIL);
			}
		} catch (Exception e) {
			throw new FrameworkException("Verify CheckBox", "Exception occured : " + e.getMessage());
		}
	}

	public String selectAuto() {
		try {
			String txtList;
			waitForObjectVisible(By.xpath("//div[@id='autocomplete']//li[1]"));

			txtList = driver.findElement(By.xpath("//div[@id='autocomplete']//li[1]")).getText();
			selectElement(By.xpath("//div[@id='autocomplete']//li[1]"), txtList);
			return txtList;
		} catch (Exception e) {
			throw new FrameworkException("Auto Select", "Exception occured : " + e.getMessage());
		}
	}

	public String positionFinder(By locator1, By locator2) {
		Point p1 = driver.findElement(locator1).getLocation();

		Point p2 = driver.findElement(locator2).getLocation();

		if (p1.getX() > p2.getX() && p1.getY() == p2.getY()) {
			return "x Greater";
		} else if (p1.getX() < p2.getX() && p1.getY() == p2.getY()) {
			return "x Smaller";
		}

		else if (p1.getY() > p2.getY() && p1.getX() == p2.getX()) {
			return "y Greater";
		} else {
			return "y Smaller";
		}
	}

	public void chkUnchkCheckBox(String value, By locator, String fieldName) {

		WebDriverWait w = new WebDriverWait(driver, 60);
		w.until(ExpectedConditions.presenceOfElementLocated(locator));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));

		if (value.equals("Check")) {
			if (driver.findElement(locator).isSelected())
				report.updateTestLog("Select Check Box", fieldName + " Check box is already Checked", Status.DONE);
			else {
				// clickElement(locator,fieldName);
				driver.findElement(locator).click();
				report.updateTestLog("Select Check Box", fieldName + " Check box is Checked", Status.DONE);
				if (!driver.findElement(locator).isSelected())
					clickElement(locator, fieldName);
			}
		} else if (value.equals("Uncheck")) {
			if (driver.findElement(locator).isSelected()) {
				wait(2000);
				// clickElement(locator,fieldName);
				driver.findElement(locator).click();
				report.updateTestLog("Select Check Box", fieldName + " Check box is Unchecked", Status.DONE);
				wait(2000);
				if (driver.findElement(locator).isSelected())
					clickElement(locator, fieldName);
			} else
				report.updateTestLog("Select Check Box", fieldName + " Check box is already Unchecked", Status.DONE);
		}
	}

	public boolean isClickable(By locator) {
		try {
			WebDriverWait wdw = new WebDriverWait(driver, 10);
			wdw.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String generateDynamicNames() throws InterruptedException {
		long randomInt = System.currentTimeMillis();
		Thread.sleep(2000);

		return "TITLE" + randomInt;

	}

	public void enterText(By txtElement, Keys Value, String fieldName) {

		try {
			waitForObjectVisible(txtElement);
			// driver.findElement(txtElement).clear();
			driver.findElement(txtElement).sendKeys(Value);
			report.updateTestLog("Enter Text", Value + " is entered in " + fieldName, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Enter Text", Value + " is NOT entered in " + fieldName + e.getMessage(), Status.FAIL);

		}
	}

	public void verifyDefaultValue(By locator, String chkName, String fieldName) {
		String string = driver.findElement(locator).getText().trim();
		if (string.contains(chkName)) {
			System.out.println(driver.findElement(locator).getText().trim());
			report.updateTestLog(fieldName + " Default value", fieldName + "  value is set to " + chkName, Status.PASS);

		}

		else {
			report.updateTestLog(fieldName + " Default value",
					fieldName + "  value is NOT set to " + chkName + " And it is set to " + string, Status.FAIL);

		}

	}

	public Boolean verifyDefaultValueBoolean(By locator, String chkName, String fieldName) {
		String string = driver.findElement(locator).getText().trim();
		if (string.contains(chkName)) {
			System.out.println(driver.findElement(locator).getText().trim());
			report.updateTestLog(fieldName + " Default value", fieldName + "  value is set to " + chkName, Status.PASS);
			return true;
		}

		else {
			report.updateTestLog(fieldName + " Default value",
					fieldName + "  value is NOT set to " + chkName + " And it is set to " + string, Status.FAIL);
			return false;
		}

	}

	public String verifyDefaultValueOfWebElement(By locator) {

		String string = driver.findElement(locator).getText().trim();
		return string;

	}

	public void dbClick(By by, String fieldName, By ElementToPresnt) {
		clickElement(by, fieldName);
		if (waitForObjectClickable1(ElementToPresnt)) {
			System.out.println("single click enuf");
		} else {
			// dbClick( by , fieldName , ElementToPresnt);
			System.out.println("Double click required");
			clickElement(by, fieldName);
		}
	}

	public boolean waitForObjectClickable1(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) driver, 2);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement we = driver.findElement(locator);
			wait.until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public String getAllSelectoptions(By by) {
		Select select = new Select(driver.findElement(by));
		List<WebElement> list = select.getOptions();
		// System.out.println(list);
		String string = "";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			string = webElement.getText() + "," + string;
		}
		return string;
	}

	public void navigateBack() {
		driver.navigate().back();

	}

	public String getElementAttValue(By by, String value) {
		try {
			String string = driver.findElement(by).getAttribute(value);
			return string;
		} catch (Exception e) {
			throw new FrameworkException("Verify Dropdown input Value", "Exception occured : " + e.getMessage());
		}
	}

	public String getInputValue(By by) {
		try {
			String string = driver.findElement(by).getText();
			return string;
		} catch (Exception e) {
			throw new FrameworkException("Verify Dropdown input Value", "Exception occured : " + e.getMessage());
		}
	}

	public Boolean presenceOfElement(By element) {
		if ((driver.findElements(element).size() > 0)) {
			return true;
		} else {
			return false;
		}
	}

	public String getinputsFromInput(By element) {
		List<WebElement> list = driver.findElements(element);
		String string = "";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			string = string + webElement.getText().trim();
		}
		return string;
	}

	public String selectPresentInput(By element) {
		try {
			Select select = new Select(driver.findElement(element));
			String string = select.getFirstSelectedOption().getText();
			report.updateTestLog("Validating present input", "Present input in select dropdown" + string, Status.PASS);
			return string;
		} catch (Exception e) {
			throw new FrameworkException("Verify Dropdown input Value", "Exception occured : " + e.getMessage());

		}

	}

	public void messageLogForAction(By by, String entityId, String action) throws InterruptedException {

		popUpBlock();
		navigateToSubMenu("Content", "NBC News Message Log");

		List<WebElement> elements = driver.findElements(by);
		System.out.println(elements.size());
		Boolean flag = false;

		for (int i = 0; i < 2; i++) {
			String string2 = elements.get(i).getText().trim();
			System.out.println(string2);
			if (string2.contains(entityId)) {
				flag = true;
				report.updateTestLog("Message log validation",
						"<b>Published card with entity Id " + entityId + " has Action " + action + "</b>", Status.PASS);
				break;
			}
		}

		if (!flag) {
			report.updateTestLog("Message log validation",
					"<b>Published card with entity Id " + entityId + " is not found" + action + " </b>", Status.FAIL);
		}
	}

	public void popUpBlock() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.onbeforeunload = function() {};");
	}

	public void selectInput(By element, String input) {
		try {
			Select select = new Select(driver.findElement(element));
			select.selectByVisibleText(input);
			report.updateTestLog("Selecting an value ", "Present input is" + input, Status.PASS);
		} catch (Exception e) {
			throw new FrameworkException("Verify Dropdown input Value", "Exception occured : " + e.getMessage());

		}

	}

}