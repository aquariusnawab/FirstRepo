package businesscomponents;

//import static org.testng.Assert.assertFalse;

import java.awt.Robot;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
//import org.hamcrest.core.StringContains;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/*import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.remote.server.handler.GetElementAttribute;
import org.openqa.selenium.remote.server.handler.GetElementLocationInView;
import org.openqa.selenium.support.ui.ExpectedConditions;*/
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;
/*import com.gargoylesoftware.htmlunit.javascript.host.dom.IdleDeadline;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.glass.events.KeyEvent;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;*/

//import sun.awt.shell.Win32ShellFolder2.SystemIcon;
import supportlibraries.DriverScript;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
import uimap.ArticlePage;
import uimap.CMSArticlePage;
import uimap.FrontPage;
//import uimap.SearchPageDashBoard;
import uimap.SearchPageDashboardComponents;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class SearchPageDashboard extends ReusableLibrary {

	public SearchPageDashboard(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */

	private static final int MAX_TIMEOUT = 180;

	public void invokeCMSApplication() {
		report.updateTestLog("Invoke CMS Application",
				"Invoke the CMS application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));
	}

	public void loginCMS() {

		try {
			String userName = dataTable.getData("General_Data", "Username");
			String password = dataTable.getData("General_Data", "Password");

			enterText(CMSArticlePage.txtEmail, userName, "Username");

			enterText(CMSArticlePage.txtPass, password, "Password");

			clickElement(CMSArticlePage.btnSubmit, "Submit");
		} catch (Exception e) {
			report.updateTestLog("Login Error", "loginCMS Error " + e.getMessage(), Status.FAIL);
		}
	}

	public void verifyCMSLoginSuccessful() {
		waitForObjectVisible(CMSArticlePage.mnuDashboard);
		WebElement mnuDashboard = driver.findElement(CMSArticlePage.mnuDashboard);

		if (mnuDashboard.isDisplayed()) {
			System.out.println("Menu Dashboard visible");
			report.updateTestLog("Verify CMS Login", "CMS login is successfull", Status.PASS);
		} else {
			throw new FrameworkException("Verify CMS Login", "CMS login is NOT successfull registration failed");
		}

	}

	public void verifyTitleField() {

		// checking for the input box to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.titleName)) {
			report.updateTestLog("Title field validation", "Title Edit Box field is present", Status.PASS);
		} else {
			report.updateTestLog("Title field validation", "Title Edit Box field is not present", Status.FAIL);

		}

		// getting input data from datatable
		String titleNameInput = dataTable.getData("SearchPageDashboard", "TitleField");
		enterText(SearchPageDashboardComponents.titleName, titleNameInput, "Title");

		// clicking search button
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		verifyTitleFieldInSearchRes(titleNameInput);
		verifyTitleFieldInArticle(titleNameInput);

	}

	public void verifyTitleFieldInSearchRes(String titleNameInput) {
		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.tableHeader);
		Boolean flag = true;
		// checking for the textColumns that contains input text
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String string = webElement.getText().toLowerCase();
			if (string.contains(titleNameInput)) {
				flag = true;
				continue;
			} else {
				report.updateTestLog(
						"Title Search results", "Found a Title " + string
								+ " in search results which DOES NOT include the searched word : " + titleNameInput,
						Status.FAIL);
				flag = false;
				break;
			}

		}
		if (flag) {
			report.updateTestLog("Title Search results",
					"All Titles displayed include the searched word : <b>" + titleNameInput + "</b>", Status.PASS);
		}
	}

	public void verifyTitleFieldInArticle(String titleNameInput) {
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.tableLink).getText();
		clickElement(SearchPageDashboardComponents.tableLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		String titleName = driver.findElement(SearchPageDashboardComponents.resultText).getAttribute("value")
				.toLowerCase();

		// Again checking the titlefield in article page
		if (titleName.contains(titleNameInput)) {
			report.updateTestLog("Title Name validation In Article",
					"Selected Title from search results and the Title in Article Page are same", Status.PASS);
		} else {
			report.updateTestLog("Title Name validation In Article",
					"Search results title and input title are NOT same, Given input :" + titleNameInput
							+ "actual input present" + titleName,
					Status.FAIL);
		}
	}

	public void verifyPublishedDate() {

		// checking for the input box to be clickable
		if ((waitForObjectClickable(SearchPageDashboardComponents.startDate))
				&& (waitForObjectClickable(SearchPageDashboardComponents.endDate))) {
			report.updateTestLog("PublishedDate field validation", "PublishedDate field  is present", Status.PASS);
		} else {
			report.updateTestLog("PublishedDate validation", "PublishedDate is not present", Status.FAIL);

		}

		// entering start date and endDate
		String startdate = dataTable.getData("searchPageDashboard", "startDate");
		String enddate = dataTable.getData("SearchPageDashboard", "endDate");
		enterText(SearchPageDashboardComponents.startDate, startdate, "Input Start Date");
		enterText(SearchPageDashboardComponents.endDate, enddate, "Input End Date");

		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		verifyPublishedDateInSearchRes(startdate, enddate);
		verifyPublishedDateInArticle();
	}

	public void verifyPublishedDateInSearchRes(String startdate, String enddate) {
		// iterating all the date value fields
		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.updatedDate);
		Boolean flag = false;
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			// System.out.println(webElement.getText());
			String sdate = webElement.getText().substring(0, 10);

			// converting to actual date format for comparision
			String actualDate = sdate.substring(6) + "-" + sdate.substring(0, 2) + "-" + sdate.substring(3, 5);

			if ((actualDate.compareTo(startdate) >= 0) && (actualDate.compareTo(enddate) <= 0)) {
				flag = true;
				continue;

			}

			else {
				report.updateTestLog(
						"Validate Published date ", "Search results DATES does not fall into specified range: "
								+ startdate + "entered endDate: " + enddate + "article Date :" + actualDate,
						Status.FAIL);
				flag = false;
				break;
			}

		}

		// Checking the same in the article page
		if (flag) {
			report.updateTestLog("Validate Published date ",
					"Search results DATES are within the specified input range, STARTDATE : " + startdate
							+ " and ENDDATE : " + enddate,
					Status.PASS);
		}

	}

	public void verifyPublishedDateInArticle() {
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.tableLink).getText();
		// System.out.println(txtColTitle);
		String publishedDatevalue = driver.findElement(SearchPageDashboardComponents.firstRowDate).getText();
		// System.out.println(publishedDatevalue);
		String trimmedDatevalue = publishedDatevalue.substring(0, 10);
		// System.out.println(trimmedDatevalue);
		clickElement(SearchPageDashboardComponents.tableLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		// waitForObjectClickable(SearchPageDashboardComponents.settingTab);
		String setting = driver.findElement(SearchPageDashboardComponents.settingTab).getText();
		// System.out.println(setting);
		clickElement(SearchPageDashboardComponents.settingTab, setting);
		String dateInArticle = driver.findElement(SearchPageDashboardComponents.updatedDateInArticle)
				.getAttribute("value").trim();
		// System.out.println(dateInArticle);
		if (dateInArticle.contains(trimmedDatevalue)) {
			report.updateTestLog("Validating Published Date in article Page", "Selected Date from search results : "
					+ dateInArticle + " is same as Date in article : " + dateInArticle, Status.PASS);
		} else {
			report.updateTestLog("Validating Published Date in article Page",
					"Date is NOT same as expected Date in article:" + dateInArticle + "Date in Search results "
							+ trimmedDatevalue,
					Status.FAIL);
		}
	}

	public void verifyPublishStatus() {

		// waiting for the piblish status dropdown to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.publishStatusDropdown)) {
			report.updateTestLog("Publish Status dropdown field", "Publish Status dropdown field is present",
					Status.PASS);
		} else {
			report.updateTestLog("Publish Status dropdown field", "Publish Status dropdown field is NOT present",
					Status.FAIL);

		}

		// Checking whether dropdown is having default value of ANY
		String publishStatusDropdownText = driver.findElement(SearchPageDashboardComponents.publishStatusDropdown)
				.getText();
		// System.out.println(publishStatusDropdownText);
		verifyDefaultValue(SearchPageDashboardComponents.publishStatusDropdown, "Any", "Publish Status");
		dbClick(SearchPageDashboardComponents.publishStatusDropdown, "Publish Status",
				SearchPageDashboardComponents.dropdownInput);
		String statusString = dataTable.getData("searchPageDashboard", "PublishStatus");
		enterText(SearchPageDashboardComponents.dropdownInput, statusString, "published type Dropdown list");
		enterText(SearchPageDashboardComponents.dropdownInput, Keys.TAB, "published type Dropdown list");

		// Clicking on search button
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		Boolean flag = false;

		// Checking whether the search results contains proper publish status
		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.publicStatusresults);
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();

			String publishStatusResults = webElement.getText().trim();
			// System.out.println(publishStatusResults);
			if (statusString.equalsIgnoreCase(publishStatusResults)) {
				flag = true;
				continue;
			} else if ((publishStatusResults.contains("published")) && (statusString.contains("Live"))) {
				flag = true;
				continue;
			} else {
				report.updateTestLog("Publish Status value in search results", "Search results have Publish Status "
						+ publishStatusDropdownText + " Given input" + statusString, Status.FAIL);
				flag = false;
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Publish Status value in search results",
					"All search results have Published status " + statusString + " as expected ", Status.PASS);
		}

		verifyPublishStatusInArticle(statusString);
		// clicking on any link and validating the same in the page

		goHome();
	}

	public void verifyPublishStatusInArticle(String statusString) {

		// clicking on the revisoions link in article page and comparing
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		String revionsLink = driver.findElement(SearchPageDashboardComponents.rivisionLink).getText();
		clickElement(SearchPageDashboardComponents.rivisionLink, revionsLink);
		Select select = new Select(driver.findElement(SearchPageDashboardComponents.dropdown));
		String actualPublishType = select.getFirstSelectedOption().getText();
		if ((statusString.contains(actualPublishType))
				|| ((statusString.equalsIgnoreCase("Unpublished")) && (actualPublishType.contains("Select")))) {
			report.updateTestLog("Validate Publish Status",
					"Selected title has Published Status : " + actualPublishType, Status.PASS);
		}

		else {
			report.updateTestLog("Validate Publish Status",
					"Published type is <b>NOT same as selected input type</b> Given input :" + statusString
							+ " actual input present" + actualPublishType,
					Status.FAIL);
		}

		// we use this link for other testcases involved in multiple inpuit
		// fields comparision
		// clickElement(SearchPageDashboardComponents.homePageLink , "Navigated
		// to homePage");

	}

	public void navigateToArticle() {

		// clicking on any link and validating the same in the page
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
	}

	public void verifyBylineDropdown() {

		// checking for byline field to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.byLineDropdown)) {
			report.updateTestLog("ByLine  dropdown field", "ByLine  dropdown field field is present", Status.PASS);
		} else {
			report.updateTestLog("ByLine  dropdown field", "ByLine  dropdown field field is NOT present", Status.FAIL);

		}
		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.byLineDropdown, "Any", "Byline");

		// Entering the input from datatable
		// String byLineDropdownText =
		// driver.findElement(SearchPageDashboardComponents.byLineDropdown).getText();

		dbClick(SearchPageDashboardComponents.byLineDropdown, "Byline",
				SearchPageDashboardComponents.byLineDropdownInput);
		String statusString = dataTable.getData("searchPageDashboard", "ByLineDropdown");
		System.out.println(statusString);
		enterText(SearchPageDashboardComponents.byLineDropdownInput, statusString, "ByLine dropdown field ");

		// retriving the suggestions xpath
		List<WebElement> List = driver.findElements(SearchPageDashboardComponents.byLineDropdownSuggestions);
		Boolean flag = false;
		// System.out.println(List.size());

		for (WebElement webElement : List) {
			String suggestions = webElement.getText();
			// System.out.println(suggestions);
			// comparing the input string with suggestions
			if (suggestions.equalsIgnoreCase(statusString)) {
				flag = true;
				continue;

			} else {
				flag = false;
				report.updateTestLog("Suggestions validation", "suggestions are NOT  displayed as expected Given input"
						+ statusString + " suggestions retrived" + suggestions, Status.FAIL);
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Suggestions validation", "suggestions are displayed as " + statusString, Status.PASS);
		}

		enterText(SearchPageDashboardComponents.byLineDropdownInput, Keys.TAB, "ByLine dropdown field ");
		// String byLineInput =
		// driver.findElement(SearchPageDashboardComponents.byLineDropdownInput).getText();

		// Clicking on search button
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		verifyByLineInArticle(statusString);

	}

	public void verifyByLineInArticle(String statusString) {
		String titleName = driver.findElement(SearchPageDashboardComponents.tableLink).getText();
		clickElement(SearchPageDashboardComponents.tableLink, titleName);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}

		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.byLineDropdownvalueInArticle);
		String byLineArticleValues = "";
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String string = webElement.getAttribute("value");
			byLineArticleValues += string;
		}
		System.out.println(byLineArticleValues);
		if ((byLineArticleValues.contains(statusString))) {
			report.updateTestLog("ByLine field verifaction in article", "Byline field is" + statusString + " same as ",
					Status.PASS);
		} else {
			report.updateTestLog("ByLine field verifaction in article",
					"Byline field is NOT same as expected Input byline :" + statusString
							+ "actual input  byline values are " + byLineArticleValues,
					Status.FAIL);

		}
	}

	public Boolean verifyNoresults() {

		// checking the NOresults found for the input search results
		String string = driver.findElement(SearchPageDashboardComponents.noResltsFound).getText();
		String status = "There are no results. Please try again with different search terms";
		if (string.contains(status)) {
			report.updateTestLog("Checking the NO results found text", "NO resluts were found", Status.PASS);
			return true;
		} else {
			report.updateTestLog("Checking the NO results found text",
					"Text mismatch actualText :" + string + "Expected text" + status, Status.FAIL);
			return false;
		}

	}

	public void verifySectionsAndTopics() {

		// checking for the object to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listSectionsAndTopics)) {
			report.updateTestLog("Sections and  Topics list ", "Sections and  Topics list field is present",
					Status.PASS);
		} else {
			report.updateTestLog("Sections and  Topics list", "Sections and  Topics list field is present",
					Status.FAIL);

		}

		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listSectionsAndTopics, "Any", "SectionsAndTopics");

		String SetionsAndTopicsDefaultValue = driver
				.findElement(SearchPageDashboardComponents.listSectionsAndTopicsName).getText();

		dbClick(SearchPageDashboardComponents.listSectionsAndTopics, "SectionsAndTopics",
				SearchPageDashboardComponents.listSectionsAndTopicsNameInput);
		String statusString = dataTable.getData("searchPageDashboard", "sectionsAndTopicsDropdown");
		// System.out.println(statusString);
		enterText(SearchPageDashboardComponents.listSectionsAndTopicsNameInput, statusString, "Sections and  Topics");

		// Retrieving the suggestions xpath
		List<WebElement> List = driver.findElements(SearchPageDashboardComponents.byLineDropdownSuggestions);
		Boolean flag = false;
		// System.out.println(List.size());

		for (WebElement webElement : List) {
			String suggestions = webElement.getText();
			// System.out.println(suggestions);
			// Select select = new Select(driver.findElement(arg0))

			// comparing the input string with suggestions
			if (suggestions.equalsIgnoreCase(statusString)) {
				flag = true;
				continue;

			} else {
				flag = false;
				report.updateTestLog("Suggestions validation",
						"Suggestions are NOT  displayed as expected Suggestion are :" + suggestions + " input is "
								+ statusString,
						Status.FAIL);
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Suggestions validation", "Suggestions are displayed as " + statusString, Status.PASS);
		}

		enterText(SearchPageDashboardComponents.listSectionsAndTopicsNameInput, Keys.TAB, "Sections and  Topics");
		// String byLineInput =
		// driver.findElement(SearchPageDashboardComponents.byLineDropdownInput).getText();
		String updatedInput = driver.findElement(SearchPageDashboardComponents.listSectionsAndTopics).getText();
		report.updateTestLog("Entering Input", "Entered input is " + updatedInput, Status.PASS);

		// cliking th esearch button and verifying the results

		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		// triming -- and - in input
		updatedInput = updatedInput.replaceFirst("^-+", "");
		verifySectionAndTopicsInArticle(updatedInput);
	}

	public void verifySectionAndTopicsInArticle(String string) {

		waitForObjectVisible(SearchPageDashboardComponents.titleLink1);
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink1).getText();
		clickElement(SearchPageDashboardComponents.titleLink1, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}

		clickElement(SearchPageDashboardComponents.settingTab, "Setting tab in Articlepage");
		String primaryTopic = selectPresentInput(SearchPageDashboardComponents.listPrimaryTopic).trim();
		System.out.println(primaryTopic);
		String primarySection = selectPresentInput(SearchPageDashboardComponents.listPrimarySection).trim();
		System.out.println(primarySection);
		String additionalTop = getinputsFromInput(SearchPageDashboardComponents.listAdditionalTopic).trim();
		System.out.println(additionalTop);
		String additionalSect = getinputsFromInput(SearchPageDashboardComponents.listAdditonalSections).trim();
		System.out.println(additionalSect);
		String additionalSubTop = getinputsFromInput(SearchPageDashboardComponents.listAdditionalSubTopics).trim();
		System.out.println(additionalSubTop);
		if ((string.equalsIgnoreCase(primaryTopic)) || (string.equalsIgnoreCase(primarySection))
				|| (additionalSubTop.contains(string)) || (additionalSect.contains(string))
				|| (additionalTop.contains(string))) {
			report.updateTestLog("Sections and topics field in Article page", "Feilds values are same as " + string,
					Status.PASS);
		} else {
			report.updateTestLog("Sections and topics field in Article page",
					"Feilds MISMATCH givenInput:" + string + "primary topic:" + primaryTopic + "primary section:"
							+ primarySection + "additionalsections:" + additionalSect + "additionalSubtopics:"
							+ additionalSubTop + "additionalTopics" + additionalTop,
					Status.FAIL);
		}

	}

	public void verifyContentType() throws InterruptedException {

		// checking for the object to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listContentType)) {
			report.updateTestLog("Contents and type list field ", "Contents and type list field  is present",
					Status.PASS);
		} else {
			report.updateTestLog("Contents and type list field ", "Contents and type list field NOT  is present",
					Status.FAIL);

		}
		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listContentType, "Any", "Content Type");

		String statusString = dataTable.getData("searchPageDashboard", "contentAndType");

		dbClick(SearchPageDashboardComponents.listContentType, "Content Type",
				SearchPageDashboardComponents.listContentTypeInput);
		enterText(SearchPageDashboardComponents.listContentTypeInput, statusString, "contents type List");

		// driver.findElement(SearchPageDashboardComponents.listContentTypeInput).sendKeys(statusString);
		enterText(SearchPageDashboardComponents.listContentTypeInput, Keys.TAB, "contents type List");

		// clicking on search button
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		verifyContentTypeInSearchResults(statusString);

	}

	public void verifyContentTypeInSearchResults(String statusString) {

		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.tableTypeInSearchRes);
		Boolean flag = false;

		// checking for the textColumns that contains input text
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println(webElement.getText());
			if (webElement.getText().contains(statusString)) {
				flag = true;
				continue;

			} else {
				report.updateTestLog("Contents and type Search results",
						"Contents and type Search results mismatch : Selected input=" + statusString
								+ ": Actual field present=" + webElement.getText(),
						Status.FAIL);
				flag = false;
				break;
			}

		}
		if (flag) {
			report.updateTestLog("Contents and type Search results",
					"Contents and type Search results displayed as " + statusString, Status.PASS);
		}

	}

	public void verifyShows() {
		// checking for the object to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listShows)) {
			report.updateTestLog("Shows list field visibility ", "Shows list field  field  is present", Status.PASS);
		} else {
			report.updateTestLog("Shows list field visibility", "Shows list field  field NOT  is present", Status.FAIL);

		}
		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listShows, "Any", "Shows");

		String statusString = dataTable.getData("searchPageDashboard", "Shows");
		// driver.findElement(SearchPageDashboardComponents.listShows).click();
		// clickElement(SearchPageDashboardComponents.listShows, "Shows list");

		dbClick(SearchPageDashboardComponents.listShows, "Shows", SearchPageDashboardComponents.listShowsInput);
		// System.out.println(statusString);
		enterText(SearchPageDashboardComponents.listShowsInput, statusString, "ShowsList");
		// driver.findElement(SearchPageDashboardComponents.listContentTypeInput).sendKeys(statusString);
		enterText(SearchPageDashboardComponents.listShowsInput, Keys.TAB, "ShowsList");

		// clicking on search button
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		verifyShowsInArticle(statusString);

	}

	public void verifyShowsInArticle(String statusString) {

		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		clickElement(SearchPageDashboardComponents.settingTab, "Setting tab in Articlepage");
		String string = driver.findElement(SearchPageDashboardComponents.listShowsInArticle).getText();
		verifyDefaultValue(SearchPageDashboardComponents.listShowsInArticle, statusString, "Shows");

	}

	public void verifyArticleType() {

		// checking for the object to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listArticleType)) {
			report.updateTestLog("Article type list field visibility ", "Article type list field  is present",
					Status.PASS);
		} else {
			report.updateTestLog("Article type list field visibility ", "Article type list field NOT  is present",
					Status.FAIL);
		}

		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listArticleType, "Any", "Article Type");

		String statusString = dataTable.getData("searchPageDashboard", "Article");

		dbClick(SearchPageDashboardComponents.listArticleType, "Article Type",
				SearchPageDashboardComponents.listArticleTypeInput);

		enterText(SearchPageDashboardComponents.listArticleTypeInput, statusString, "Article Type");
		enterText(SearchPageDashboardComponents.listArticleTypeInput, Keys.TAB, "Article Type");

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		verifyArticleTypeInReslts(statusString);
		goHome();

	}

	public void verifyArticleTypeInReslts(String statusString) {

		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		String actualArticleValue = selectPresentInput(SearchPageDashboardComponents.listArticleValueInArticle);
		System.out.println(actualArticleValue);
		if (statusString.equalsIgnoreCase(actualArticleValue)) {
			report.updateTestLog("Article value in article page",
					"Article list value is <b>" + actualArticleValue + "</b> as expected", Status.PASS);
		} else {
			report.updateTestLog("Article value in article page", "Article list value is NOT same as expected"
					+ "Expected =" + statusString + "and Actual =" + actualArticleValue, Status.FAIL);
		}
		// clickElement(SearchPageDashboardComponents.homePageLink , " HomePage
		// link");

	}

	public void verifySeries() {

		// checking for the object to be clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listSeries)) {
			report.updateTestLog("Article type list field visibility ", "Article type list field  is present",
					Status.PASS);
		} else {
			report.updateTestLog("Article type list field visibility ", "Article type list field NOT  is present",
					Status.FAIL);
		}

		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listSeriesValue, "Any", "Series");

		dbClick(SearchPageDashboardComponents.listSeriesValue, "Series", SearchPageDashboardComponents.listSeriesInput);

		String sugString = dataTable.getData("searchPageDashboard", "Series_Sug");

		enterText(SearchPageDashboardComponents.listSeriesInput, sugString, "Series downDown list");

		// retriving the suggestions xpath
		List<WebElement> List = driver.findElements(SearchPageDashboardComponents.byLineDropdownSuggestions);
		Boolean flag = false;
		// System.out.println(List.size());

		// comparing the input string with suggestions
		for (WebElement webElement : List) {
			String suggestions = webElement.getText();
			// System.out.println(suggestions);

			if (suggestions.equalsIgnoreCase(sugString)) {
				flag = true;
				continue;

			} else {
				flag = false;
				report.updateTestLog("Suggestions validation", "Suggestions are NOT  displayed as expected ",
						Status.FAIL);
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Suggestions validation", "Suggestions are displayed as " + sugString, Status.PASS);
		}

		String statusString = dataTable.getData("searchPageDashboard", "Series_Input");
		// System.out.println(statusString);
		enterText(SearchPageDashboardComponents.listSeriesInput, statusString, "Series_Input");
		enterText(SearchPageDashboardComponents.listSeriesInput, Keys.TAB, "Series_Input");
		// String byLineInput =
		// driver.findElement(SearchPageDashboardComponents.byLineDropdownInput).getText();
		report.updateTestLog("Entering Input", "Entered input is " + statusString, Status.PASS);

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

	}

	public void verifySeriesInArticle(String statusString) {
		verifySeriesInArticle(statusString);
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}

		// clicking setting tab and checking the field
		String fieldName = driver.findElement(SearchPageDashboardComponents.settingTab).getText();
		clickElement(SearchPageDashboardComponents.settingTab, fieldName);
		String actualInput = getInputValue(SearchPageDashboardComponents.listSeriesInArticle);
		System.out.println(actualInput);
		if (statusString.equalsIgnoreCase(actualInput)) {
			report.updateTestLog("Series drop down value ", "Series drop down is present as " + statusString,
					Status.PASS);
		} else {
			report.updateTestLog("Serires drop down value ",
					"Series drop down is NOT present as expected  ActualInput :" + statusString + "  Input in Artilce:"
							+ actualInput,
					Status.FAIL);
		}
	}

	public void verifyCMS() {

		// waiting for the field clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.inputCMS)) {
			report.updateTestLog("CMS SEARCH input field", "CMS search input field is displayed", Status.PASS);
		} else {
			report.updateTestLog("CMS SEARCH input field", "CMS search input field NOT is displayed", Status.FAIL);
		}
		String CMSValue = getElementAttValue(SearchPageDashboardComponents.inputCMS, "placeholder");
		// String CMSValue =
		// driver.findElement(SearchPageDashboardComponents.inputCMS).getAttribute("placeholder");
		System.out.println(CMSValue);
		String expectedCMSvalue = "Enter # at end of URL (no letters)";

		// Checking whether dropdown is having default value
		if (CMSValue.equalsIgnoreCase(expectedCMSvalue)) {
			report.updateTestLog("CMS Input default value", "Input value is same as " + expectedCMSvalue, Status.PASS);
		} else {
			report.updateTestLog("CMS Input default value", "Input value is NOT same as expected CMS value :" + CMSValue
					+ "   Expected value" + expectedCMSvalue, Status.FAIL);
		}
		String string = dataTable.getData("SearchPageDashboard", "nodeID");
		enterText(SearchPageDashboardComponents.inputCMS, string, "CMS search input");
		enterText(SearchPageDashboardComponents.inputCMS, Keys.ENTER, "CMS search input");
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		String pageUrl = driver.getCurrentUrl();

		// nodeID in pageURl
		if (pageUrl.contains(string)) {
			report.updateTestLog("Checking the NODE id of Article", "NodeId is present as " + string, Status.PASS);
		} else {
			report.updateTestLog("Checking the NODE id of Article",
					"NodeId is NOT  present as expected PageURl :" + pageUrl + "   NOdeID" + string, Status.FAIL);
		}

	}

	public void verifySource() {

		//// waiting for the field clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listSource)) {
			report.updateTestLog("Source dropdown field visibility", "Source dropdown list is displayed", Status.PASS);
		} else {
			report.updateTestLog("Source dropdown field visibility", "Source dropdown list NOT is displayed",
					Status.FAIL);
		}
		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listSource, "Any", "Source");

		dbClick(SearchPageDashboardComponents.listSource, "Source", SearchPageDashboardComponents.listSourceInput);

		String sugInput = dataTable.getData("SearchPageDashboard", "Source_sug");
		enterText(SearchPageDashboardComponents.listSourceInput, sugInput, "source dropdown list");
		List<WebElement> List = driver.findElements(SearchPageDashboardComponents.byLineDropdownSuggestions);
		Boolean flag = false;
		// System.out.println(List.size());

		// comparing the input string with suggestions
		for (WebElement webElement : List) {
			String suggestions = webElement.getText();
			// System.out.println(suggestions);

			if (suggestions.equalsIgnoreCase(sugInput)) {
				flag = true;
				continue;

			} else {
				report.updateTestLog("Suggestions validation", "Suggestions are NOT  displayed as expected input value "
						+ suggestions + "actual value present" + sugInput, Status.FAIL);

				flag = false;
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Suggestions validation", "Suggestions are displayed as " + sugInput, Status.PASS);
		}

		String sourceInput = dataTable.getData("SearchPageDashboard", "Source_input");
		enterText(SearchPageDashboardComponents.listSourceInput, sourceInput, "Source dropdown list  ");
		// enterText(SearchPageDashboardComponents.listSeriesInput,
		// statusString, "Series_Input");
		enterText(SearchPageDashboardComponents.listSourceInput, Keys.TAB, "Source dropdown list");

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		verifySourceInArticle(sourceInput);

	}

	public void verifySourceInArticle(String sourceInput) {
		// checking value in article
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.tableLink).getText();
		clickElement(SearchPageDashboardComponents.tableLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}
		String actualSouceInArticle = getElementAttValue(SearchPageDashboardComponents.sourceInArticle, "value");
		if (actualSouceInArticle.contains(sourceInput)) {
			report.updateTestLog("Source drop down value in article page ",
					"Source drop down value is present as  " + sourceInput, Status.PASS);
		} else {
			report.updateTestLog("Source drop down value in article page ",
					"Source drop down value is NOT present as expected  ActualInput :" + sourceInput
							+ "  Input in Artilce:" + actualSouceInArticle,
					Status.FAIL);
		}

	}

	public void verifyLabel() {

		// waiting for the field clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.listLabel)) {
			report.updateTestLog("Label dropdown field presence", "Label dropdown field is displayed", Status.PASS);
		} else {
			report.updateTestLog("Label dropdown field presence ", "Label dropdown field NOT is displayed",
					Status.FAIL);
		}

		// Checking whether dropdown is having default value of ANY
		verifyDefaultValue(SearchPageDashboardComponents.listLabel, "Any", "Label DropDown ");

		// entering suggestion input
		dbClick(SearchPageDashboardComponents.listLabel, "Label DropDown ",
				SearchPageDashboardComponents.listLabelInput);

		String labelsug = dataTable.getData("SearchPageDashboard", "Label_sug");
		System.out.println(labelsug);
		// System.out.println(list);
		Boolean flag = false;
		enterText(SearchPageDashboardComponents.listLabelInput, labelsug, "Label list");
		List<WebElement> list = driver.findElements(SearchPageDashboardComponents.byLineDropdownSuggestions);
		// System.out.println(List.size());

		// retreive suggestions and comparing
		for (WebElement webElement : list) {
			String suggestions = webElement.getText().trim();
			System.out.println(suggestions);

			if (suggestions.equalsIgnoreCase((labelsug))) {
				flag = true;
				continue;

			} else {
				report.updateTestLog("Suggestions of Label field",
						"Suggestions are NOT  displayed as expected Entered input " + labelsug + " actual value present"
								+ suggestions,
						Status.FAIL);

				flag = false;
				break;
			}
		}
		if (flag) {
			report.updateTestLog("Suggestions of Label field", "Suggestions are displayed as  " + labelsug,
					Status.PASS);
		}

		// entering suggestion input
		String labelInput = dataTable.getData("SearchPageDashboard", "Label_input");
		System.out.println(labelInput);
		enterText(SearchPageDashboardComponents.listLabelInput, labelInput, "Label dropdown list  ");
		enterText(SearchPageDashboardComponents.listLabelInput, Keys.TAB, "Label dropdown list  ");
		// String byLineInput =
		// driver.findElement(SearchPageDashboardComponents.byLineDropdownInput).getText();
		// report.updateTestLog("Entering Input", "Entered input is
		// "+labelInput, Status.PASS);

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "searchbuton");

		verifyLabelInArticle(labelInput);

	}

	public void verifyLabelInArticle(String labelInput) {

		// clicking on link
		String txtColTitle = driver.findElement(SearchPageDashboardComponents.titleLink).getText();
		clickElement(SearchPageDashboardComponents.titleLink, txtColTitle);
		if (presenceOfElement(SearchPageDashboardComponents.linkLockedByUser)) {
			clickElement(SearchPageDashboardComponents.linkLockedByUser, "User locked link");
		}

		// clicking setting tab and comparing
		String setting = driver.findElement(SearchPageDashboardComponents.settingTab).getText();
		clickElement(SearchPageDashboardComponents.settingTab, setting);
		String actualLabelValue = getInputValue(SearchPageDashboardComponents.labelInArticle);
		String actualLabelSecValue = getinputsFromInput(SearchPageDashboardComponents.labelSecoInArticle);
		System.out.println(actualLabelSecValue);
		if (labelInput.equalsIgnoreCase(actualLabelValue) || (actualLabelSecValue.contains(labelInput))) {
			report.updateTestLog("Label drop down value ", "Label drop down value is present as  " + labelInput,
					Status.PASS);
		} else {
			report.updateTestLog("Label drop down value ",
					"Label drop down value is NOT present as expected  ActualInput :" + labelInput
							+ "  Input in Artilce:" + actualLabelValue + "and secondary labelvalue in articel :"
							+ actualLabelSecValue,
					Status.FAIL);
		}

	}

	public void verifyTitleDate() {

		// clickElement(SearchPageDashboardComponents.titleName, "title input
		// dropdown");
		String titleInput = dataTable.getData("SearchPageDashboard", "TitleField");
		String sdateInput = dataTable.getData("SearchPageDashboard", "startDate");
		String edateInput = dataTable.getData("SearchPageDashboard", "endDate");
		System.out.println(titleInput + sdateInput + edateInput);
		enterText(SearchPageDashboardComponents.titleName, titleInput, "title input field");

		// clickElement(SearchPageDashboardComponents.startDate, "StartDate
		// dropdown");
		enterText(SearchPageDashboardComponents.startDate, sdateInput, "Input End Date is");
		enterText(SearchPageDashboardComponents.endDate, edateInput, "Input End Date is");

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		verifyTitleFieldInSearchRes(titleInput);
		verifyPublishedDateInSearchRes(sdateInput, edateInput);

		verifyTitleFieldInArticle(titleInput);
		navigateBack();
		verifyPublishedDateInArticle();

	}

	public void verifyShowsLabels() {

		// entering input and searching the results
		dbClick(SearchPageDashboardComponents.listShows, "Shows", SearchPageDashboardComponents.listShowsInput);

		String inputShow = dataTable.getData("SearchPageDashboard", "Shows");
		enterText(SearchPageDashboardComponents.listShowsInput, inputShow, "Shows dropdown");
		enterText(SearchPageDashboardComponents.listShowsInput, Keys.TAB, "Shows dropdown");

		dbClick(SearchPageDashboardComponents.listLabel, "Label_input", SearchPageDashboardComponents.listLabelInput);
		String inputLabel = dataTable.getData("SearchPageDashboard", "Label_input");
		enterText(SearchPageDashboardComponents.listLabelInput, inputLabel, "Label dropdown");
		enterText(SearchPageDashboardComponents.listLabelInput, Keys.TAB, "Label dropdown");

		// clicking on search button & verifying the following fields
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		verifyShowsInArticle(inputShow);
		navigateBack();
		verifyLabelInArticle(inputLabel);

	}

	public void verifyContentArticle() {

		// entering input and searching the results
		driver.findElement(SearchPageDashboardComponents.listContentType).click();
		clickElement(SearchPageDashboardComponents.listContentType, "contents dropdown");
		String inputContent = dataTable.getData("SearchPageDashboard", "contentAndType");
		enterText(SearchPageDashboardComponents.listContentTypeInput, inputContent, "contents dropdown");
		// System.out.println(inputShow);
		enterText(SearchPageDashboardComponents.listContentTypeInput, Keys.TAB, "contents dropdown");
		clickElement(SearchPageDashboardComponents.listArticleType, "Article dropdown");
		String inputArticle = dataTable.getData("SearchPageDashboard", "Article");
		// System.out.println(inputLabel);
		enterText(SearchPageDashboardComponents.listArticleTypeInput, inputArticle, "Article dropdown");
		enterText(SearchPageDashboardComponents.listArticleTypeInput, Keys.TAB, "Article dropdown");

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		// verifying the following fields
		verifyContentTypeInSearchResults(inputContent);
		verifyArticleTypeInReslts(inputArticle);
		goHome();

	}

	public void verifyContentSectionTopic() {

		// entering input and searching the results

		dbClick(SearchPageDashboardComponents.listContentType, "contentAndType",
				SearchPageDashboardComponents.listContentTypeInput);
		String inputContent = dataTable.getData("SearchPageDashboard", "contentAndType");
		enterText(SearchPageDashboardComponents.listContentTypeInput, inputContent, "contents dropdown");
		System.out.println(inputContent);
		enterText(SearchPageDashboardComponents.listContentTypeInput, Keys.TAB, "contents dropdown");

		dbClick(SearchPageDashboardComponents.listSectionsAndTopics, "SectionTopic dropdown",
				SearchPageDashboardComponents.listSectionsAndTopicsNameInput);
		String inputSectionTopics = dataTable.getData("SearchPageDashboard", "sectionsAndTopicsDropdown");
		System.out.println(inputSectionTopics);
		enterText(SearchPageDashboardComponents.listSectionsAndTopicsNameInput, inputSectionTopics,
				"SectionTopic dropdown");
		enterText(SearchPageDashboardComponents.listSectionsAndTopicsNameInput, Keys.TAB, "SectionTopic dropdown");
		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		// verifying the following fields
		verifyContentTypeInSearchResults(inputContent);
		verifySectionAndTopicsInArticle(inputSectionTopics);
		// navigateBack();
		goHome();

	}

	public String verifySourceSeries() {

		// entering input and searching the results

		dbClick(SearchPageDashboardComponents.listSource, "Source_input",
				SearchPageDashboardComponents.listSourceInput);
		String inputSource = dataTable.getData("SearchPageDashboard", "Source_input");
		enterText(SearchPageDashboardComponents.listSourceInput, inputSource, "Source Type dropdown");
		enterText(SearchPageDashboardComponents.listSourceInput, Keys.TAB, "Source Type dropdown");

		dbClick(SearchPageDashboardComponents.listSeries, "Series_Input",
				SearchPageDashboardComponents.listSeriesInput);
		String inputSeries = dataTable.getData("SearchPageDashboard", "Series_Input");
		enterText(SearchPageDashboardComponents.listSeriesInput, inputSeries, "Series dropdown");
		enterText(SearchPageDashboardComponents.listSeriesInput, Keys.TAB, "Series dropdown");

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		System.out.println(presenceOfElement(SearchPageDashboardComponents.noResltsFound));
		// If no results found
		if (presenceOfElement(SearchPageDashboardComponents.noResltsFound)) {
			String string = driver.findElement(SearchPageDashboardComponents.noResltsFound).getText();
			String status = "There are no results. Please try again with different search terms";
			System.out.println(string + status);
			if (string.contains(status)) {
				report.updateTestLog("Checking the text for No search results found", "NO results were found",
						Status.PASS);
				return "";
			} else {
				report.updateTestLog("Checking the text for No search results found",
						" <br>No Search results text mistatch </br>actualText :" + string + "Expected text" + status,
						Status.FAIL);
				return "";
			}
		} else {

			// verifying the following fields
			verifySourceInArticle(inputSource);
			navigateBack();
			verifySeriesInArticle(inputSeries);
			return "";
		}
	}

	public void verifyResetButton() {

		// entering input and searching the results
		dbClick(SearchPageDashboardComponents.listContentType, "contents dropdown",
				SearchPageDashboardComponents.listContentTypeInput);
		String inputContent = dataTable.getData("SearchPageDashboard", "contentAndType");
		enterText(SearchPageDashboardComponents.listContentTypeInput, inputContent, "contents dropdown");
		System.out.println(inputContent);
		enterText(SearchPageDashboardComponents.listContentTypeInput, Keys.TAB, "contents dropdown");

		dbClick(SearchPageDashboardComponents.listArticleType, "Article dropdown",
				SearchPageDashboardComponents.listArticleTypeInput);
		String inputArticle = dataTable.getData("SearchPageDashboard", "Article");
		System.out.println(inputArticle);
		enterText(SearchPageDashboardComponents.listArticleTypeInput, inputArticle, "Article dropdown");
		enterText(SearchPageDashboardComponents.listArticleTypeInput, Keys.TAB, "Article dropdown");

		clickElement(SearchPageDashboardComponents.publishStatusDropdown, "publishStatus dropdown");
		dbClick(SearchPageDashboardComponents.publishStatusDropdown, "PublishStatus",
				SearchPageDashboardComponents.dropdownInput);
		String inputPublished = dataTable.getData("SearchPageDashboard", "PublishStatus");
		enterText(SearchPageDashboardComponents.dropdownInput, inputPublished, "publishStatus dropdown");
		enterText(SearchPageDashboardComponents.dropdownInput, Keys.TAB, "publishStatus dropdown");
		System.out.println(inputPublished);

		// clicking on search button and first link in search results
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		// verifying the following fields
		verifyContentTypeInSearchResults(inputContent);
		verifyArticleTypeInReslts(inputArticle);
		navigateBack();
		verifyPublishStatusInArticle(inputPublished);
		navigateBack();
		navigateBack();
		clickElement(SearchPageDashboardComponents.btnReset, "Reset Button");
		Boolean boolean1 = true;

		// checking reset btn functionality

		verifyDefaultValue(SearchPageDashboardComponents.listContentType, "Any", "Content Type");

		verifyDefaultValue(SearchPageDashboardComponents.listArticleType, "Any", "Article Type");

		verifyDefaultValue(SearchPageDashboardComponents.publishStatusDropdown, "Any", "PublishStatus");

	}

	public void validateBulkOperations() throws InterruptedException {
		// waiting for the field clickable
		if (waitForObjectClickable(SearchPageDashboardComponents.dropdwnBulkOperations)) {
			report.updateTestLog("BulkOperations field", "BulkOperations field is displayed", Status.PASS);
		} else {
			report.updateTestLog("BulkOperations field", "BulkOperations field NOT is displayed", Status.FAIL);
		}

		// Checking whether dropdown is having default value of ANY
		String defValue = selectPresentInput(SearchPageDashboardComponents.dropdwnBulkOperations);
		// System.out.println(defValue);
		if (defValue.contains("- Choose an operation -")) {
			report.updateTestLog("BulkOperations field default value",
					"BulkOperations field default value was set to " + defValue, Status.PASS);
		} else {
			report.updateTestLog("BulkOperations field default value",
					"BulkOperations field default value was NOT set ", Status.FAIL);

		}

		String inputArticleTitle = dataTable.getData("SearchPageDashboard", "TitleField");

		addArticle(inputArticleTitle);
		goHome();
		enterText(SearchPageDashboardComponents.titleName, inputArticleTitle, "Title name field");
		System.out.println(inputArticleTitle);
		clickElement(SearchPageDashboardComponents.searchButton, "Search button");
		String nodeId1 = getInputValue(SearchPageDashboardComponents.nodeId1);
		System.out.println(nodeId1);
		String nodeId2 = getInputValue(SearchPageDashboardComponents.nodeId2);
		System.out.println(nodeId2);
		checkRequiredRows();
		selectInput(SearchPageDashboardComponents.dropdwnBulkOperations, "Delete item");
		clickElement(SearchPageDashboardComponents.btnExecute, "Execute Button");
		clickElement(SearchPageDashboardComponents.btnConfirm, "confirm Button");
		// verifyPresenceOfArticles(arrayList);
		Thread.sleep(60000);
		if (presenceOfElement(SearchPageDashboardComponents.msgSuccess)) {
			report.updateTestLog("Confirmation of article deletion ", "Articles got deleted as expected", Status.PASS);
		} else if (presenceOfElement(SearchPageDashboardComponents.msgError)) {
			String error = getInputValue(SearchPageDashboardComponents.msgError);
			report.updateTestLog("Confirmation of article deletion ",
					"Some error occured while deleting articles error message is" + error, Status.FAIL);
		}
		// verifyDeletedOperation(nodeId1);
		// verifyDeletedOperation(nodeId2);
		messageLogForAction(SearchPageDashboardComponents.messagelogValueActionDelete, nodeId1, "delete");
		messageLogForAction(SearchPageDashboardComponents.messagelogValueActionDelete, nodeId2, "delete");
	}

	public void verifyDeletedOperation(String value1) throws InterruptedException {
		navigateToSubMenu("Content", "NBC News Message Log");
		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.nodeIdsInMessageLog);
		System.out.println(elements.size());
		List<WebElement> elements2 = driver.findElements(SearchPageDashboardComponents.StatusInMessageLog);
		System.out.println(elements2.size());
		Boolean flag = true;
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			Iterator<WebElement> iterator2 = elements2.iterator();
			WebElement webElement2 = (WebElement) iterator2.next();
			WebElement webElement = (WebElement) iterator.next();
			// System.out.println("drhf");
			String string = webElement.getText().trim();
			System.out.println(string);
			if (string.contains(value1)) {
				String string2 = webElement2.getText().trim();
				System.out.println(string2);
				if (string2.contains("delete")) {
					flag = true;
					report.updateTestLog("Status validation In Message Log",
							"Actions field is set to " + string2 + " as expected", Status.PASS);

				} else {
					report.updateTestLog("Status validation In Message Log",
							"Mismatch of results operation performed : deleted  Actual status present" + string2,
							Status.FAIL);
					flag = true;

				}
			}

		}
		if (!flag) {
			report.updateTestLog("Status validation In Message Log",
					"NodeID :" + value1 + " not found in the message log page", Status.FAIL);
		}

	}

	public void checkRequiredRows() {

		clickElement(SearchPageDashboardComponents.nodeId1, "nodeID+");
		clickElement(SearchPageDashboardComponents.nodeId2, "nodeID");

	}

	public void verifyOrderSearchTable() {

		clickElement(SearchPageDashboardComponents.searchButton, "Search button");

		// spliting input
		String column = dataTable.getData("SearchPageDashboard", "ColumnFileds");
		String columnValues[] = column.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();

		// adding items into array
		arrayList.add(AddElements(SearchPageDashboardComponents.IDno));
		arrayList.add(AddElements(SearchPageDashboardComponents.titleHeaderName));
		arrayList.add(AddElements(SearchPageDashboardComponents.type));
		arrayList.add(AddElements(SearchPageDashboardComponents.createdBy));
		arrayList.add(AddElements(SearchPageDashboardComponents.pulishStatus));
		arrayList.add(AddElements(SearchPageDashboardComponents.updated));
		arrayList.add(AddElements(SearchPageDashboardComponents.publishDate));
		arrayList.add(AddElements(SearchPageDashboardComponents.operations));
		Boolean flag = false;

		// performing comparision
		for (int i = 0; i < columnValues.length; i++) {
			if (columnValues[i].contains(arrayList.get(i))) {
				flag = true;
				continue;
			} else {
				flag = false;
				report.updateTestLog("table header order   of Search Results ",
						"Order of Header is not same   Expected :" + column + " Actual :" + arrayList, Status.FAIL);
				break;

			}
		}
		if (flag) {
			report.updateTestLog("table header order   of Search Results ",
					"Table header order is " + column + "same as expected", Status.PASS);
		}
		// System.out.println(" Expected :"+column+" Actual :"+arrayList);

	}

	public void verifyManageFronts() throws InterruptedException {
		navigateToSubMenu("Content", "Manage Fronts");
		String string = getAllSelectoptions(SearchPageDashboardComponents.manageFronts);
		System.out.println(string);
		String s[] = string.split(",");
		for (int i = 0; i < s.length - 1; i++) {
			String statusString = s[i];
			verifyVocabType(statusString, SearchPageDashboardComponents.manageFronts);
		}
	}

	public void verifyVocabType(String string, By by) {
		selectInput(by, string);
		List<WebElement> elements = driver.findElements(SearchPageDashboardComponents.dropdownVocabType);
		System.out.println(elements.size());
		// Boolean flag = true ;
		if (presenceOfElement(SearchPageDashboardComponents.goToPage2)) {
			if (elements.size() == 50) {
				report.updateTestLog(" Vocab type number of  Search results validation",
						"Search results contain " + elements.size() + " samples", Status.PASS);

			}
		} else {
			report.updateTestLog("Vocab type number of Search resluts", "Search results contain " + elements.size()
					+ " samples but 50 samples should be present as per testcase", Status.PASS);
		}

		// elements.remove(0);
		Boolean flag = true;
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String actualValue = webElement.getText().trim();
			if ((actualValue.equals(string)) || ((actualValue.equals("Article Label")) && (string.equals("Labels")))
					|| ((actualValue.equals("Show")) && (string.equals("Shows")))) {
				flag = true;
				continue;
			} else {
				flag = false;
				report.updateTestLog("Vocab type Search Results text validation",
						" Mismatch of search values   Expected :" + string + " Actual :" + actualValue, Status.FAIL);
				break;

			}
		}
		if (flag) {
			report.updateTestLog("Vocab type Search Results text validation",
					"Search results are " + string + " same as expected", Status.PASS);
		}
	}

	public String AddElements(By by) {
		String string = driver.findElement(by).getText().trim();
		return string;
	}

	public void addArticle(String titleHeader) throws InterruptedException {

		int num = 2;
		for (int x = 1; x <= num; x++) {
			navigateToSubMenu("Content", "Add content", "Article");
			titleHeader = titleHeader + x;
			enterText(SearchPageDashboardComponents.resultText, titleHeader, "Input article headline");
			enterText(SearchPageDashboardComponents.sourceInArticle, "AARP", "source ");
			Thread.sleep(2000);
			clickElement(SearchPageDashboardComponents.settingTab, "Setting Tab");
			selectDropdownVal(SearchPageDashboardComponents.listPrimarySection, "About", "Primary section");
			Thread.sleep(3000);
			selectDropdownVal(SearchPageDashboardComponents.listPrimaryTopic, "TODAY", "Primary Topic");
			clickElement(SearchPageDashboardComponents.btnPublish, "Publish button");

			if (waitForObjectVisible(SearchPageDashboardComponents.msgSuccess)) {
				report.updateTestLog("Article success message",
						"Artilce has been successfully created with title" + titleHeader, Status.PASS);
			} else {
				String string = driver.findElement(SearchPageDashboardComponents.msgError).getText();
				report.updateTestLog("Article success message", "Artilce has  NOT been successfully created with title"
						+ titleHeader + " the message was " + string, Status.PASS);
			}
			// goHome();
		}
	}

}
