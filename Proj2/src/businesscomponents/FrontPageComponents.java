package businesscomponents;

import java.awt.AWTException;
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
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.hamcrest.core.StringContains;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;

import com.sun.glass.events.KeyEvent;

import supportlibraries.DriverScript;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
import uimap.ArticlePage;
import uimap.CMSArticlePage;
import uimap.FrontPage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class FrontPageComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public FrontPageComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

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

	// Navigate to Content--> Add content--> Article page

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

	public void navigateToNBCNewsMsgLOG() {

		try {
			navigateToSubMenu("Content", "NBC News Message Log");
		} catch (Exception e) {
			report.updateTestLog("Navigate To NBC News Message Log", "NOT Navigated to NBC News Message Log",
					Status.FAIL);
		}

	}

	public void navigateToArticlePage() {

		try {
			navigateToSubMenu("Content", "Add content", "Article");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Article Page",
					"NOT Navigated to Article Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void navigateToFrontPage() {

		try {
			navigateToSubMenu("Content", "Add content", "Front Page");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Front Page",
					"NOT Navigated to Front Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void addContentforArticlePage1() throws InterruptedException, AWTException {

		try {
			String title;
			waitForObjectVisible(CMSArticlePage.txtTitle);

			if (dataTable.getData("General_Data", "ArticleHeadline").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("GENERAL_DATA", "ArticleHeadline", title);
			} else {
				title = dataTable.getData("General_Data", "ArticleHeadline");
			}

			String primarySection = dataTable.getData("General_Data", "PrimarySection");
			String editEvent = dataTable.getData("General_Data", "EditEvent");
			String editNotes = dataTable.getData("General_Data", "EditorialNotes");
			String socMediaHeadline = dataTable.getData("General_Data", "SocialMediaHeadline");
			String coverHeadline = dataTable.getData("General_Data", "CoverHeadline");
			String txtSummary = dataTable.getData("General_Data", "Summary");
			String valSource = dataTable.getData("General_Data", "Source");
			String logMsg = dataTable.getData("General_Data", "Log Message");
			String seoHeadline = dataTable.getData("General_Data", "SEO Headline");
			String source = dataTable.getData("General_Data", "Source");
			String primaryTopic = dataTable.getData("General_Data", "PrimaryTopic");
			String byline = dataTable.getData("General_Data", "Byline");

			enterText(CMSArticlePage.editNotes, editNotes, "Editorial Notes");

			enterText(CMSArticlePage.txtTitle, title, "Article Headline");

			enterText(CMSArticlePage.txtSEOHeadline, seoHeadline, "SEO Headline");

			enterText(CMSArticlePage.socMediaHead, socMediaHeadline, "Social Media Headline");

			enterText(CMSArticlePage.coverHeadline, coverHeadline, "Cover Headline");

			enterText(CMSArticlePage.txtByline, byline, "Byline");

			enterText(CMSArticlePage.txtSource, source, "Source");

			enterText(CMSArticlePage.txtSummary, txtSummary, "Summary");

			// selectDropdownVal(CMSArticlePage.selSource, valSource, "Source");

			addMediaImage();

			addMediaVideo();

			// selectDropdownVal(CMSArticlePage.listboxEditEvent, editEvent,
			// "Edit Event");

			enterText(CMSArticlePage.logMsg, logMsg, "Log Message");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			wait(3000);

			clickElement(CMSArticlePage.btnSettings, "Settings");

			String addSecVal = dataTable.getData("General_Data", "Additional Sections");
			System.out.println(addSecVal);
			String addTopVal = dataTable.getData("General_Data", "Additional Topics");
			String addSubTopVal = dataTable.getData("General_Data", "Additional Sub Topics");
			String addArtLabVal = dataTable.getData("General_Data", "Additional Article Labels");
			String addShowVal = dataTable.getData("General_Data", "Additional Shows");
			String anchorsVal = dataTable.getData("General_Data", "Anchors");
			String primArtLabVal = dataTable.getData("General_Data", "Primary Article Label");
			String showVal = dataTable.getData("General_Data", "Show");
			String seriesVal = dataTable.getData("General_Data", "Series");

			selectDropdownVal(CMSArticlePage.listboxPrimarySection, primarySection, "Primary Section");

			selectDropdownVal(CMSArticlePage.listboxPrimaryTopic, primaryTopic, "Primary Topic");

			clickElement(CMSArticlePage.addSections, "Additional Sections");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSecVal + "')]"),
					addSecVal);

			clickElement(CMSArticlePage.addTopics, "Additional Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addTopVal + "')]"),
					addTopVal);

			clickElement(CMSArticlePage.addSubTopics, "Additional Sub Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSubTopVal + "')]"),
					addSubTopVal);

			clickElement(CMSArticlePage.addArtLabels, "Additional Article Labels");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addArtLabVal + "')]"),
					addArtLabVal);

			clickElement(CMSArticlePage.addShows, "Additional Shows");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addShowVal + "')]"),
					addShowVal);

			clickElement(CMSArticlePage.anchors, "Anchors");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + anchorsVal + "')]"),
					anchorsVal);

			clickElement(CMSArticlePage.primaryArticlelabe, "Primary Article Label");
			selectElement(By
					.xpath("//div[@id='edit_field_article_label_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ primArtLabVal + "')]"),
					primArtLabVal);

			clickElement(CMSArticlePage.show, "Show");
			selectElement(
					By.xpath("//div[@id='edit_field_show_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ showVal + "')]"),
					showVal);

			clickElement(CMSArticlePage.series, "Series");
			selectElement(By
					.xpath("//div[@id='edit_field_series_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ seriesVal + "')]"),
					seriesVal);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(CMSArticlePage.addSubTopics));
			Thread.sleep(3000);

			/*
			 * clickElement(CMSArticlePage.storyLine, "Story Line"); WebElement
			 * w4 = driver.findElement(By.xpath(
			 * "//ul[@class='chosen-results']/li[text()='#SharkWatch']"));
			 * w4.click(); report.updateTestLog("Select Dropdown Value",
			 * "Story Line is selected as #SharkWatch", Status.PASS);
			 * 
			 * 
			 * clickElement(CMSArticlePage.labels, "Labels"); WebElement w5 =
			 * driver.findElement(By.
			 * xpath("//ul[@class='chosen-results']/li[text()='Elections 2014 – Alabama']"
			 * )); w5.click(); report.updateTestLog("Select Dropdown Value",
			 * "Labels is selected as Elections 2014 – Alabama", Status.PASS);
			 */

			// clickElement(CMSArticlePage.btnSave, "Save");

			wait(2000);

			report.updateTestLog("Edit Event is visbile on Article Page",
					"Edit Event " + editEvent + " is visbile on Article Page", Status.PASS);

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Article page", "Exception occured : " + e.getMessage());
		}

	}

	public void addContentForExternalArticlePage() throws InterruptedException, AWTException {

		try {
			String title;
			waitForObjectVisible(CMSArticlePage.txtTitle);

			if (dataTable.getData("General_Data", "ArticleHeadline").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("GENERAL_DATA", "ArticleHeadline", title);
			} else {
				title = dataTable.getData("General_Data", "ArticleHeadline");
			}

			String primarySection = dataTable.getData("General_Data", "PrimarySection");
			String editEvent = dataTable.getData("General_Data", "EditEvent");
			String editNotes = dataTable.getData("General_Data", "EditorialNotes");
			String socMediaHeadline = dataTable.getData("General_Data", "SocialMediaHeadline");
			String coverHeadline = dataTable.getData("General_Data", "CoverHeadline");
			String txtSummary = dataTable.getData("General_Data", "Summary");
			String valSource = dataTable.getData("General_Data", "Source");
			String logMsg = dataTable.getData("General_Data", "Log Message");
			String seoHeadline = dataTable.getData("General_Data", "SEO Headline");
			String source = dataTable.getData("General_Data", "Source");
			String primaryTopic = dataTable.getData("General_Data", "PrimaryTopic");
			String articleType = dataTable.getData("General_Data", "Article Type");
			String urlExtLink = dataTable.getData("General_Data", "External Link URL");

			selectDropdownVal(CMSArticlePage.listArticleType, articleType, "Article Type");

			enterText(CMSArticlePage.editNotes, editNotes, "Editorial Notes");

			enterText(CMSArticlePage.txtTitle, title, "Article Headline");

			enterText(CMSArticlePage.txtSEOHeadline, seoHeadline, "SEO Headline");

			enterText(CMSArticlePage.socMediaHead, socMediaHeadline, "Social Media Headline");

			enterText(CMSArticlePage.coverHeadline, coverHeadline, "Cover Headline");

			enterText(CMSArticlePage.txtSourceExternal, source, "Source");

			enterText(CMSArticlePage.urlExternalLink, urlExtLink, "External Link URL");

			enterText(CMSArticlePage.txtSummary, txtSummary, "Summary");

			clickSelectPicture();

			addImage();

			WebElement addPic = driver.findElement(CMSArticlePage.btnPictureSelect);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addPic);
			report.updateTestLog("Add Image", "An Image is attached", Status.SCREENSHOT);

			enterText(CMSArticlePage.logMsg, logMsg, "Log Message");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			wait(3000);

			clickElement(CMSArticlePage.btnSettings, "Settings");

			String addSecVal = dataTable.getData("General_Data", "Additional Sections");
			System.out.println(addSecVal);
			String addTopVal = dataTable.getData("General_Data", "Additional Topics");
			String addSubTopVal = dataTable.getData("General_Data", "Additional Sub Topics");
			String addArtLabVal = dataTable.getData("General_Data", "Additional Article Labels");
			String addShowVal = dataTable.getData("General_Data", "Additional Shows");
			String anchorsVal = dataTable.getData("General_Data", "Anchors");
			String primArtLabVal = dataTable.getData("General_Data", "Primary Article Label");
			String showVal = dataTable.getData("General_Data", "Show");
			String seriesVal = dataTable.getData("General_Data", "Series");

			selectDropdownVal(CMSArticlePage.listboxPrimarySection, primarySection, "Primary Section");

			selectDropdownVal(CMSArticlePage.listboxPrimaryTopic, primaryTopic, "Primary Topic");

			clickElement(CMSArticlePage.addSections, "Additional Sections");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSecVal + "')]"),
					addSecVal);

			clickElement(CMSArticlePage.addTopics, "Additional Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addTopVal + "')]"),
					addTopVal);

			clickElement(CMSArticlePage.addSubTopics, "Additional Sub Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSubTopVal + "')]"),
					addSubTopVal);

			clickElement(CMSArticlePage.addArtLabels, "Additional Article Labels");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addArtLabVal + "')]"),
					addArtLabVal);

			clickElement(CMSArticlePage.addShows, "Additional Shows");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addShowVal + "')]"),
					addShowVal);

			clickElement(CMSArticlePage.anchors, "Anchors");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + anchorsVal + "')]"),
					anchorsVal);

			clickElement(CMSArticlePage.primaryArticlelabe, "Primary Article Label");
			selectElement(By
					.xpath("//div[@id='edit_field_article_label_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ primArtLabVal + "')]"),
					primArtLabVal);

			clickElement(CMSArticlePage.show, "Show");
			selectElement(
					By.xpath("//div[@id='edit_field_show_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ showVal + "')]"),
					showVal);

			clickElement(CMSArticlePage.series, "Series");
			selectElement(By
					.xpath("//div[@id='edit_field_series_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ seriesVal + "')]"),
					seriesVal);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(CMSArticlePage.addSubTopics));
			Thread.sleep(3000);

			/*
			 * clickElement(CMSArticlePage.storyLine, "Story Line"); WebElement
			 * w4 = driver.findElement(By.xpath(
			 * "//ul[@class='chosen-results']/li[text()='#SharkWatch']"));
			 * w4.click(); report.updateTestLog("Select Dropdown Value",
			 * "Story Line is selected as #SharkWatch", Status.PASS);
			 * 
			 * 
			 * clickElement(CMSArticlePage.labels, "Labels"); WebElement w5 =
			 * driver.findElement(By.
			 * xpath("//ul[@class='chosen-results']/li[text()='Elections 2014 – Alabama']"
			 * )); w5.click(); report.updateTestLog("Select Dropdown Value",
			 * "Labels is selected as Elections 2014 – Alabama", Status.PASS);
			 */

			// clickElement(CMSArticlePage.btnSave, "Save");

			wait(2000);

			report.updateTestLog("Edit Event is visbile on Article Page",
					"Edit Event " + editEvent + " is visbile on Article Page", Status.PASS);

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Article page", "Exception occured : " + e.getMessage());
		}

	}

	public void addContentForVideoArticlePage() throws InterruptedException, AWTException {

		try {
			String title;
			waitForObjectVisible(CMSArticlePage.txtTitle);

			if (dataTable.getData("General_Data", "ArticleHeadline").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("GENERAL_DATA", "ArticleHeadline", title);
			} else {
				title = dataTable.getData("General_Data", "ArticleHeadline");
			}

			String primarySection = dataTable.getData("General_Data", "PrimarySection");
			String editEvent = dataTable.getData("General_Data", "EditEvent");
			String editNotes = dataTable.getData("General_Data", "EditorialNotes");
			String socMediaHeadline = dataTable.getData("General_Data", "SocialMediaHeadline");
			String coverHeadline = dataTable.getData("General_Data", "CoverHeadline");
			String txtSummary = dataTable.getData("General_Data", "Summary");
			String valSource = dataTable.getData("General_Data", "Source");
			String logMsg = dataTable.getData("General_Data", "Log Message");
			String seoHeadline = dataTable.getData("General_Data", "SEO Headline");
			String source = dataTable.getData("General_Data", "Source");
			String primaryTopic = dataTable.getData("General_Data", "PrimaryTopic");
			String articleType = dataTable.getData("General_Data", "Article Type");
			String urlExtLink = dataTable.getData("General_Data", "External Link URL");
			String sourceOverride = dataTable.getData("General_Data", "Source Override");
			String byline = dataTable.getData("General_Data", "Byline");

			selectDropdownVal(CMSArticlePage.listArticleType, articleType, "Article Type");

			enterText(CMSArticlePage.editNotes, editNotes, "Editorial Notes");

			enterText(CMSArticlePage.txtTitle, title, "Article Headline");

			enterText(CMSArticlePage.txtSEOHeadline, seoHeadline, "SEO Headline");

			enterText(CMSArticlePage.socMediaHead, socMediaHeadline, "Social Media Headline");

			enterText(CMSArticlePage.coverHeadline, coverHeadline, "Cover Headline");

			enterText(CMSArticlePage.txtByline, byline, "Byline");

			enterText(CMSArticlePage.txtSource, source, "Source");

			clickSelectVideo();

			addVideo();

			WebElement addVid = driver.findElement(CMSArticlePage.btnVideoSelect);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addVid);
			report.updateTestLog("Add Video", "A Video is attached", Status.SCREENSHOT);

			enterText(CMSArticlePage.txtSummary, txtSummary, "Summary");

			selectElement(CMSArticlePage.rdChooseCvrImg, "Choose Cover Image");

			clickSelectPicture();

			addImage();

			WebElement addPic = driver.findElement(CMSArticlePage.btnPictureSelect);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addPic);
			report.updateTestLog("Add Image", "An Image is attached", Status.SCREENSHOT);

			enterText(CMSArticlePage.txtSourceOverride, sourceOverride, "Source Override");

			enterText(CMSArticlePage.logMsg, logMsg, "Log Message");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			wait(3000);

			clickElement(CMSArticlePage.btnSettings, "Settings");

			String addSecVal = dataTable.getData("General_Data", "Additional Sections");
			System.out.println(addSecVal);
			String addTopVal = dataTable.getData("General_Data", "Additional Topics");
			String addSubTopVal = dataTable.getData("General_Data", "Additional Sub Topics");
			String addArtLabVal = dataTable.getData("General_Data", "Additional Article Labels");
			String addShowVal = dataTable.getData("General_Data", "Additional Shows");
			String anchorsVal = dataTable.getData("General_Data", "Anchors");
			String primArtLabVal = dataTable.getData("General_Data", "Primary Article Label");
			String showVal = dataTable.getData("General_Data", "Show");
			String seriesVal = dataTable.getData("General_Data", "Series");

			selectDropdownVal(CMSArticlePage.listboxPrimarySection, primarySection, "Primary Section");

			selectDropdownVal(CMSArticlePage.listboxPrimaryTopic, primaryTopic, "Primary Topic");

			clickElement(CMSArticlePage.addSections, "Additional Sections");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSecVal + "')]"),
					addSecVal);

			clickElement(CMSArticlePage.addTopics, "Additional Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addTopVal + "')]"),
					addTopVal);

			clickElement(CMSArticlePage.addSubTopics, "Additional Sub Topics");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addSubTopVal + "')]"),
					addSubTopVal);

			clickElement(CMSArticlePage.addArtLabels, "Additional Article Labels");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addArtLabVal + "')]"),
					addArtLabVal);

			clickElement(CMSArticlePage.addShows, "Additional Shows");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + addShowVal + "')]"),
					addShowVal);

			clickElement(CMSArticlePage.anchors, "Anchors");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + anchorsVal + "')]"),
					anchorsVal);

			clickElement(CMSArticlePage.primaryArticlelabe, "Primary Article Label");
			selectElement(By
					.xpath("//div[@id='edit_field_article_label_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ primArtLabVal + "')]"),
					primArtLabVal);

			clickElement(CMSArticlePage.show, "Show");
			selectElement(
					By.xpath("//div[@id='edit_field_show_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ showVal + "')]"),
					showVal);

			clickElement(CMSArticlePage.series, "Series");
			selectElement(By
					.xpath("//div[@id='edit_field_series_und_chosen']//ul[@class='chosen-results']/li[contains(text(),'"
							+ seriesVal + "')]"),
					seriesVal);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(CMSArticlePage.addSubTopics));
			Thread.sleep(3000);

			/*
			 * clickElement(CMSArticlePage.storyLine, "Story Line"); WebElement
			 * w4 = driver.findElement(By.xpath(
			 * "//ul[@class='chosen-results']/li[text()='#SharkWatch']"));
			 * w4.click(); report.updateTestLog("Select Dropdown Value",
			 * "Story Line is selected as #SharkWatch", Status.PASS);
			 * 
			 * 
			 * clickElement(CMSArticlePage.labels, "Labels"); WebElement w5 =
			 * driver.findElement(By.
			 * xpath("//ul[@class='chosen-results']/li[text()='Elections 2014 – Alabama']"
			 * )); w5.click(); report.updateTestLog("Select Dropdown Value",
			 * "Labels is selected as Elections 2014 – Alabama", Status.PASS);
			 */

			// clickElement(CMSArticlePage.btnSave, "Save");

			wait(2000);

			report.updateTestLog("Edit Event is visbile on Article Page",
					"Edit Event " + editEvent + " is visbile on Article Page", Status.PASS);

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Article page", "Exception occured : " + e.getMessage());
		}

	}

	public void addContentForFrontPage() throws InterruptedException, AWTException {

		try {
			String title;
			int iFlag = 0;
			ArrayList<String> titleNo = new ArrayList<String>();

			String logMsg = dataTable.getData("Front_Page", "Log Message");

			/*
			 * waitForObjectVisible(FrontPage.rdNormalDay);
			 * 
			 * selectElement(FrontPage.rdNormalDay, "Normal Day");
			 * 
			 * clickElement(FrontPage.btnContinue, "Continue");
			 */

			waitForObjectVisible(FrontPage.txtFrontPageTitle);

			if (dataTable.getData("Front_Page", "Front Page Title").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("Front_Page", "Front Page Title", title);
			} else {
				title = dataTable.getData("Front_Page", "Front Page Title");
			}

			List<WebElement> listContSearch = driver
					.findElements(By.xpath("//a[@class='references-dialog-activate search-dialog']"));
			for (int i = 1; i <= 2; i++) {
				clickElement(By.xpath("(//a[@class='references-dialog-activate search-dialog'])[" + i + "]"),
						"Content Search");
				WebElement wf = driver.findElement(
						By.xpath("//iframe[@class='references-dialog-iframe ui-dialog-content ui-widget-content']"));
				driver.switchTo().frame(wf);
				String contentTitle = driver
						.findElement(By.xpath("//table[@class='views-table cols-5']/tbody/tr[" + i + "]/td[1]"))
						.getText();
				selectElement(By.xpath("//table[@class='views-table cols-5']/tbody/tr[" + i + "]"),
						"Title " + contentTitle);
				String txtTitle = driver.findElement(
						By.xpath("(//input[@class='fluid form-text form-autocomplete disable-input-enter-processed'])["
								+ i + "]"))
						.getAttribute("value");
				System.out.println("box title" + txtTitle);
				System.out.println(txtTitle.substring((txtTitle.indexOf("(") + 1), txtTitle.indexOf(")")));
				titleNo.add(txtTitle.substring((txtTitle.indexOf("(") + 1), txtTitle.indexOf(")")));

			}

			System.out.println("*******" + titleNo);

			clickSave();

			List<WebElement> w = driver.findElements(By.xpath("//div[@class='messages error']"));
			if (w.size() > 0) {
				String complErrText = w.get(0).getText().replaceAll("\\r\\n|\\r|\\n", " ");
				System.out.println(complErrText);
				Pattern p = Pattern.compile("Field [0-9]+ contains a news item that is not published");
				Matcher m = p.matcher(complErrText);
				// System.out.println(m.matches());
				while (m.find()) {
					System.out.println(m.group());
					String fieldNo = m.group().substring((m.group().indexOf("Field") + 6),
							m.group().indexOf(" contains"));
					String no = titleNo.get(Integer.parseInt(fieldNo) - 2);
					enterText(FrontPage.txtCMSSearch, no, "CMS Search");
					driver.findElement(FrontPage.txtCMSSearch).sendKeys(Keys.ENTER);
					clickElement(CMSArticlePage.btnPublish, "Publish");
					iFlag = 1;
				}

				if (iFlag > 0) {
					navigateToFrontPage();

					selectFrontPageNormalDay();

					enterText(FrontPage.txtFrontPageTitle, title, "Title");

					List<WebElement> listContSearch1 = driver
							.findElements(By.xpath("//a[@class='references-dialog-activate search-dialog']"));
					for (int i = 1; i <= 2; i++) {
						clickElement(By.xpath("(//a[@class='references-dialog-activate search-dialog'])[" + i + "]"),
								"Content Search");
						WebElement wf = driver.findElement(By.xpath(
								"//iframe[@class='references-dialog-iframe ui-dialog-content ui-widget-content']"));
						driver.switchTo().frame(wf);
						String contentTitle = driver
								.findElement(By.xpath("//table[@class='views-table cols-5']/tbody/tr[" + i + "]/td[1]"))
								.getText();
						selectElement(By.xpath("//table[@class='views-table cols-5']/tbody/tr[" + i + "]"),
								"Title " + contentTitle);
					}
				}
			}

			enterText(FrontPage.txtFrontPageTitle, title, "Title");

			enterText(FrontPage.txtLogMsg, logMsg, "Log Message For This State Change");

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Front page", "Exception occured : " + e.getMessage());
		}

	}

	public void selectFrontPageNormalDay() {
		waitForObjectVisible(FrontPage.rdNormalDay);

		selectElement(FrontPage.rdNormalDay, "Normal Day");

		clickElement(FrontPage.btnContinue, "Continue");
	}

	public void selectFrontPageBigStory() {
		waitForObjectVisible(FrontPage.rdBigStory);

		selectElement(FrontPage.rdBigStory, "Big Story");

		clickElement(FrontPage.btnContinue, "Continue");
	}

	public void noSourceVal() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		clickElement(CMSArticlePage.btnContentEntry, "Content Entry");
		enterText(CMSArticlePage.txtSource, "", "Source");

	}

	public void clickSave() {

		try {
			loadPage();
			clickElement(CMSArticlePage.btnSave, "Save");
		} catch (Exception e) {

		}
	}

	public void clickPublish() {

		try {
			loadPage();
			clickElement(CMSArticlePage.btnPublish, "Publish");
		} catch (Exception e) {

		}
	}

	public void clickDelete() {

		clickElement(CMSArticlePage.btnDelete, "Delete");
		clickElement(CMSArticlePage.btnConfirmDelete, "Confirm Delete");

	}

	public void clickHiddenPublish() {

		try {
			loadPage();
			clickElement(CMSArticlePage.btnHiddenPublish, "Hidden Publish");
		} catch (Exception e) {

		}
	}

	public void clickUnPublish() {

		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
			List<WebElement> lw = driver.findElements(CMSArticlePage.lnkUnpublish);
			lw.get(0).click();

			if (isAlertExist()) {
				Alert al = driver.switchTo().alert();
				al.accept();
			}
			loadPage();
			report.updateTestLog("Click Element", "Unpublish is clicked", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click Element", "Unpublish is NOT clicked " + e.getMessage(), Status.FAIL);

		}

	}

	public void verifyMsg() {
		// WebDriver driver1 = driver.getWebDriver();
		try {
			loadPage();
			String msg1 = "Article Headline field is required.";
			String msg2 = "Sections Topics and Sub-topics field is required.";
			if (driver.findElement(By.xpath("//li[text()='" + msg1 + "']")).isDisplayed()) {
				report.updateTestLog("Verify Message", msg1 + " is displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Message", msg1 + " is NOT displayed", Status.FAIL);
			}
			if (driver.findElement(By.xpath("//li[text()='" + msg2 + "']")).isDisplayed()) {
				report.updateTestLog("Verify Message", msg2 + " is displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Message", msg2 + " is NOT displayed", Status.FAIL);
			}

		} catch (Exception e) {

		}
	}

	public void verifyFrontPageCreatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Front_Page", "Front Page Title");
			String s = "Front Page " + title + " has been created.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Front Page Created Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyArticleUpdatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("General_Data", "ArticleHeadline");
			String s = "Article " + title + " has been updated.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Article Updated Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyFrontPageDeletedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Front_Page", "Front Page Title");
			String s = "Front Page " + title + " has been deleted.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Front Page Deleted Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyPromptMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages error']"));
			String actErrMsg = w.getText();
			String s = dataTable.getData("General_Data", "PromptMessage");
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Prompt Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyNoPromptMsg() {

		try {
			loadPage();
			List<WebElement> w = driver.findElements(By.xpath("//div[@class='messages error']"));

			if (w.size() == 0) {
				report.updateTestLog("Verify Message", "NO ERROR messages are dispalyed", Status.PASS);
			} else {
				report.updateTestLog("Verify Message", "ERROR messages are displayed", Status.FAIL);
				for (int i = 0; i < w.size(); i++) {
					report.updateTestLog("Verify Message", w.get(i).getText() + " is displayed", Status.WARNING);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Prompt Message", "Exception occured : " + e.getMessage());
		}
	}

	public void navigateToNbcMsgLog() throws InterruptedException {

	}

	public void validateFrontMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("Front_Page", "Front Page Title");
		driver.findElement(CMSArticlePage.txtMsgLogTitle).clear();
		enterText(CMSArticlePage.txtMsgLogTitle, title, "Title");
		// driver.findElement(CMSArticlePage.txtTitle).sendKeys(title);

		clickElement(CMSArticlePage.btnApply, "Apply");
		/*
		 * WebElement btnApply = driver.findElement(CMSArticlePage.btnApply);
		 * btnApply.click();
		 */

		// Get number of rows In table.
		// *[@id='views-form-nbcnews-msg-logs-nbcnews-msg-logs-admin-page']/div/table/tbody/tr
		int RowCount = driver.findElements(By.xpath("//table[@class='views-table cols-10']/tbody/tr")).size();
		System.out.println("Number Of Rows = " + RowCount);

		// Get number of columns In table.
		int ColCount = driver.findElements(By.xpath("//table[@class='views-table cols-10']/tbody/tr/td")).size();
		System.out.println("Number Of Columns = " + ColCount);

		// divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = "//*[@id='views-form-nbcnews-msg-logs-nbcnews-msg-logs-admin-page']/div/table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		String action = null;

		// Used for loop for number of rows.
		First: for (int i = 1; i <= RowCount; i++) {
			// Used for loop for number of columns.
			for (int j = 1; j <= ColCount; j++) {
				// Prepared final xpath of specific cell as per values of i and
				// j.
				String final_xpath = first_part + i + second_part + j + third_part;
				// Will retrieve value from located cell and print It.
				Thread.sleep(2000);

				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();

				if (j == 3) {
					action = Table_data;
				}

				if (j == 7) {
					if (Table_data.equals(title)) {
						report.updateTestLog("NBC News Message Log", "Title  " + title
								+ " is triggered in NBC News Message Log with Action : " + "<b>" + action + "</b>",
								Status.PASS);
						break First;
					} else {
						report.updateTestLog("NBC News Message Log",
								"Title " + title + " is NOT triggered in NBC News Message Log", Status.FAIL);
					}
				}
				if (j == 8) {

					String[] timeStampValues = Table_data.split("\\s");
					DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					Date msgtDate = dateFormatter.parse(timeStampValues[1]);
					Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

					String timeValue = timeStampValues[3];
					// if (todayDate == msgtDate) {

					// report.updateTestLog("Time stamp matched on Message
					// Log","Time stamp "+ msgtDate.toString() +" " + timeValue
					// + " visbile on Message log", Status.PASS);
					// }
					// else {
					// report.updateTestLog("Time stamp is NOT matched on
					// Message grid","Time stamp "+ msgtDate.toString() +" " +
					// timeValue + " NOT visbile on Message log", Status.FAIL);

					// }

				}

				System.out.print(Table_data + "  ");
			}
			System.out.println("");
			System.out.println("");
		}
	}

	public void validateFrontNoMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("Front_Page", "Front Page Title");
		String expAction = dataTable.getData("Front_Page", "Action");
		driver.findElement(CMSArticlePage.txtMsgLogTitle).clear();
		enterText(CMSArticlePage.txtMsgLogTitle, title, "Title");
		// driver.findElement(CMSArticlePage.txtTitle).sendKeys(title);

		clickElement(CMSArticlePage.btnApply, "Apply");
		/*
		 * WebElement btnApply = driver.findElement(CMSArticlePage.btnApply);
		 * btnApply.click();
		 */

		// Get number of rows In table.
		// *[@id='views-form-nbcnews-msg-logs-nbcnews-msg-logs-admin-page']/div/table/tbody/tr
		int RowCount = driver.findElements(By.xpath("//table[@class='views-table cols-10']/tbody/tr")).size();
		System.out.println("Number Of Rows = " + RowCount);

		// Get number of columns In table.
		int ColCount = driver.findElements(By.xpath("//table[@class='views-table cols-10']/tbody/tr/td")).size();
		System.out.println("Number Of Columns = " + ColCount);

		// divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = "//*[@id='views-form-nbcnews-msg-logs-nbcnews-msg-logs-admin-page']/div/table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		String action = null;

		// Used for loop for number of rows.
		First: for (int i = 1; i <= RowCount; i++) {
			// Used for loop for number of columns.
			for (int j = 1; j <= ColCount; j++) {
				// Prepared final xpath of specific cell as per values of i and
				// j.
				String final_xpath = first_part + i + second_part + j + third_part;
				// Will retrieve value from located cell and print It.
				Thread.sleep(2000);

				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();

				if (j == 3) {
					action = Table_data;
				}

				if (j == 7) {
					if (Table_data.equals(title) && !(action.equals(expAction))) {
						report.updateTestLog("NBC News Message Log",
								"Title  " + title + " is NOT triggered to NBC News Message Log with Action : " + "<b>"
										+ expAction + "</b>",
								Status.PASS);
						break First;
					} else if (Table_data.equals(title) && (action.equals(expAction))) {
						report.updateTestLog("NBC News Message Log", "Title " + title
								+ " is triggered to NBC News Message Log with Action : " + "<b>" + expAction + "</b>",
								Status.FAIL);
						break First;
					} else {

					}
				}
				if (j == 8) {

					String[] timeStampValues = Table_data.split("\\s");
					DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					Date msgtDate = dateFormatter.parse(timeStampValues[1]);
					Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

					String timeValue = timeStampValues[3];
					// if (todayDate == msgtDate) {

					// report.updateTestLog("Time stamp matched on Message
					// Log","Time stamp "+ msgtDate.toString() +" " + timeValue
					// + " visbile on Message log", Status.PASS);
					// }
					// else {
					// report.updateTestLog("Time stamp is NOT matched on
					// Message grid","Time stamp "+ msgtDate.toString() +" " +
					// timeValue + " NOT visbile on Message log", Status.FAIL);

					// }

				}

				System.out.print(Table_data + "  ");
			}
			System.out.println("");
			System.out.println("");
		}
	}

	public void enterArticleHeadline() {
		try {
			String title = generateDynamicNames();
			enterText(CMSArticlePage.txtTitle, title, "Article Headline");
		} catch (Exception e) {
			throw new FrameworkException("Enter Title in Article Headline", "Exception occured : " + e.getMessage());
		}
	}

	public void defaultPrimarySection() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		wait(2000);
		clickElement(CMSArticlePage.btnSettings, "Settings");

		String primarySection = "- None -";

		selectDropdownVal(CMSArticlePage.listboxPrimarySection, primarySection, "Primary Section");

		Alert a = driver.switchTo().alert();
		a.accept();

		clickElement(CMSArticlePage.btnSave, "Save");
	}

	public void selPrimarySection() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		wait(2000);
		clickElement(CMSArticlePage.btnSettings, "Settings");

		String primaryTopic = dataTable.getData("General_Data", "PrimaryTopic");
		String primarySection = dataTable.getData("General_Data", "PrimarySection");

		selectDropdownVal(CMSArticlePage.listboxPrimarySection, primarySection, "Primary Section");
		if (isAlertExist()) {
			Alert al = driver.switchTo().alert();
			al.accept();
		}
		selectDropdownVal(CMSArticlePage.listboxPrimaryTopic, primaryTopic, "Primary Topic");

	}

	public void verifyArticleTypeVal() {
		String artTypeVal = dataTable.getData("GENERAL_DATA", "Article Type");
		verifyDropdownSel(CMSArticlePage.listArticleType, artTypeVal, "Article Type");
	}

	public void clickAddMedia() {
		clickElement(CMSArticlePage.addMedia, "Add Media");
	}

	public void clickAddWidget() {
		clickElement(CMSArticlePage.addWidget, "Add Widget");
	}

	public void clickSelectPicture() {
		clickElement(CMSArticlePage.btnPictureSelect, "Picture Select");
	}

	public void clickSelectVideo() {
		clickElement(CMSArticlePage.btnVideoSelect, "Video Select");
	}

	public void clickEditDraft() {
		clickElement(FrontPage.tabEditDraft, "Edit Draft");
	}

	public void searchAndClickExistingFrontPage() {
		try {
			String contentType = dataTable.getData("Front_Page", "Content Type");

			navigateToSubMenu("My Dashboard", "All Recent Content");

			selectDropdownVal(FrontPage.lstContentType, contentType, "Content Type");

			clickElement(FrontPage.btnApply, "Apply");

			int rowCount = driver
					.findElements(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr"))
					.size();
			if (rowCount >= 1) {
				First: for (int i = 1; i <= rowCount; i++) {
					String statusPublish = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
									+ i + "]/td[6]"))
							.getText().trim();
					if (!statusPublish.equals("Not published")) {
						String title = driver.findElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"))
								.getText();
						dataTable.putData("Front_Page", "Front Page Title", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Front Page", "NO Existing Front Page. Need to create one.",
						Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Front Page", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickPublishedFrontPage() {
		try {
			String contentType = dataTable.getData("Front_Page", "Content Type");

			navigateToSubMenu("My Dashboard", "All Recent Content");

			selectDropdownVal(FrontPage.lstContentType, contentType, "Content Type");

			clickElement(FrontPage.btnApply, "Apply");

			int rowCount = driver
					.findElements(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr"))
					.size();
			if (rowCount >= 1) {
				First: for (int i = 1; i <= rowCount; i++) {
					String statusPublish = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
									+ i + "]/td[6]"))
							.getText().trim();
					if (statusPublish.equals("Published")) {
						String title = driver.findElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"))
								.getText();
						dataTable.putData("Front_Page", "Front Page Title", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Front Page",
						"NO Existing Published Front Page. Need to create one.", Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Front Page", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickUnPublishedFrontPage() {
		try {
			String contentType = dataTable.getData("Front_Page", "Content Type");

			navigateToSubMenu("My Dashboard", "All Recent Content");

			selectDropdownVal(FrontPage.lstContentType, contentType, "Content Type");

			clickElement(FrontPage.btnApply, "Apply");

			int rowCount = driver
					.findElements(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr"))
					.size();
			if (rowCount >= 1) {
				First: for (int i = 1; i <= rowCount; i++) {
					String statusPublish = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
									+ i + "]/td[6]"))
							.getText().trim();
					if (statusPublish.equals("Not published")) {
						String title = driver.findElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"))
								.getText();
						dataTable.putData("Front_Page", "Front Page Title", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Front Page",
						"NO Existing UnPublished Article. Need to have one.", Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Front Page", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickDraftFrontPage() {
		try {
			String contentType = dataTable.getData("Front_Page", "Content Type");

			navigateToSubMenu("My Dashboard", "All Recent Content");

			selectDropdownVal(FrontPage.lstContentType, contentType, "Content Type");

			clickElement(FrontPage.btnApply, "Apply");

			int rowCount = driver
					.findElements(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr"))
					.size();
			if (rowCount >= 1) {
				First: for (int i = 1; i <= rowCount; i++) {
					String statusPublish = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
									+ i + "]/td[6]"))
							.getText().trim();
					if (statusPublish.equals("Draft")) {
						String title = driver.findElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"))
								.getText();
						dataTable.putData("Front_Page", "Front Page Title", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Front Page", "NO Existing Draft Front Page. Need to have one.",
						Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Front Page", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void addMostPopularWidget() {

		try {

			String tabMenu = dataTable.getData("General_Data", "Widget Most Popular Menu");

			String[] menu = tabMenu.split(";");
			for (String s : menu) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
				clickElement(CMSArticlePage.addWidget, "Add Widget");
				waitForObjectVisible(CMSArticlePage.frameChooseWidget);
				selectWidgetTabAndMenu("Most Popular", s);

				switch (s) {

				case "Ellentube":
					enterText(CMSArticlePage.txtWidgetEllentubeCMSDisplayName, "Test CMS Display Name",
							"CMS Display Name");

					enterText(CMSArticlePage.txtWidgetEllentuveVideoID, "Test Video ID", "Video ID");

					clickElement(CMSArticlePage.btnInsertWidget, "Insert Widget");

					break;

				case "Live Video":
					enterText(CMSArticlePage.txtWidgetLivVidCMSDisplayName, "Test CMS Display Name",
							"CMS Display Name");

					enterText(CMSArticlePage.txtWidgetLivVidMPXID, "Test MPX ID", "MPX ID");

					clickElement(CMSArticlePage.btnInsertWidget, "Insert Widget");

					break;

				case "NBC Affiliates Video":
					enterText(CMSArticlePage.txtWidgetNBCAffVidCMSDisplayName, "Test CMS Display Name",
							"CMS Display Name");

					enterText(CMSArticlePage.txtWidgetNBCAffVidVideoSource, "http://testVideoSource.com",
							"Video Source");

					clickElement(CMSArticlePage.btnInsertWidget, "Insert Widget");

					break;

				case "NBC Video":
					enterText(CMSArticlePage.txtWidgetNBCVideoCMSDisplayName, "Test CMS Display Name",
							"CMS Display Name");

					enterText(CMSArticlePage.txtWidgetNBCVideoVideoID, "TestVideoID", "Video ID");

					clickElement(CMSArticlePage.btnInsertWidget, "Insert Widget");

					break;

				case "Video Bar":
					enterText(CMSArticlePage.txtWidgetVideoBarCMSDisplayName, "Test CMS Display Name",
							"CMS Display Name");

					enterText(CMSArticlePage.txtWidgetVideoBarPlaylistLab, "Test Playlist Label", "Playlist Label");

					enterText(CMSArticlePage.txtWidgetVideoBarMPXPlayID, "Test MPX Playlist ID", "MPX Playlist ID");

					clickElement(CMSArticlePage.btnInsertWidget, "Insert Widget");

					break;
				}
			}

			WebElement addWid = driver.findElement(CMSArticlePage.addWidget);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addWid);
			report.updateTestLog("Add Widget", Arrays.toString(menu) + " widgets attached", Status.SCREENSHOT);

		} catch (Exception e) {
			report.updateTestLog("Add Widget", "Widget could not be added - Error : " + e.getMessage(), Status.FAIL);
		}
	}

	public void addMediaImage() {

		try {
			clickElement(CMSArticlePage.addMedia, "Add Media");

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			selectElement(CMSArticlePage.selImage, "An Image");

			clickElement(CMSArticlePage.btnSubmitImg, "Submit");

			WebElement wf1 = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
			driver.switchTo().frame(wf1);

			wait(1000);
			clickElement(CMSArticlePage.btnSubmitImgConf, "Submit");

			wait(1000);
			driver.switchTo().parentFrame();

			WebElement element = driver.findElement(CMSArticlePage.addMedia);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			report.updateTestLog("Attach Image", "An Image is attached", Status.SCREENSHOT);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Media Image", "Media Image could not be added - Error : " + e.getMessage(),
					Status.FAIL);
		}

	}

	public void addMediaVideo() {

		try {
			clickElement(CMSArticlePage.addMedia, "Add Media");
			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			clickElement(CMSArticlePage.btnVids, "Videos");

			selectDropdownVal(CMSArticlePage.listMediaVideoSearchDate, "All", "Date");

			clickElement(CMSArticlePage.mediaVedioSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			waitForObjectVisible(CMSArticlePage.selVid);

			selectElement(CMSArticlePage.selVid, "A Video");

			clickElement(CMSArticlePage.btnSubmitVid, "Submit");

			WebElement wf1v = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
			driver.switchTo().frame(wf1v);
			wait(1000);

			clickElement(CMSArticlePage.btnSubmitImgConf, "Submit");

			WebElement element1 = driver.findElement(CMSArticlePage.addMedia);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			report.updateTestLog("Attach Video", "A Video is attached", Status.SCREENSHOT);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Media Video", "Media Video could not be added - Error : " + e.getMessage(),
					Status.FAIL);
		}

	}

	public void verifySettingsDefaulChkd() {
		isChecked(CMSArticlePage.chkAdsEnabled, "Ads Enabled");

		isChecked(CMSArticlePage.chkCommentsEnabled, "Comments Enabled");

		isChecked(CMSArticlePage.chkSearchable, "Searchable");
	}

	public void updateContentforArticlePage() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		clickElement(CMSArticlePage.editLink, "Edit Link");
		/*
		 * WebElement editLink = driver.findElement(CMSArticlePage.editLink);
		 * editLink.click();
		 */
		Thread.sleep(2000);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtTitle);
		waitForObjectVisible(CMSArticlePage.txtTitle);
		if (txtTitle.isDisplayed()) {
			System.out.println("Title field on Article Page");
			report.updateTestLog("Title is visbile on Article Page", "Title field is visbile on Article Page",
					Status.PASS);

			String title = dataTable.getData("General_Data", "ArticleHeadline");
			title = generateDynamicNames();
			dataTable.putData("General_Data", "ArticleHeadline", title);
			// String primarySection = dataTable.getData("General_Data",
			// "PrimarySection");
			// String editEvent = dataTable.getData("General_Data",
			// "EditEvent");

			driver.findElement(CMSArticlePage.txtTitle).clear();
			enterText(CMSArticlePage.txtTitle, title, "Article Headline");
			// driver.findElement(CMSArticlePage.txtTitle).sendKeys(title);

			// WebElement listEditEvent =
			// driver.findElement(CMSArticlePage.listboxEditEvent);
			// Select listOptions = new Select(listEditEvent);
			// listOptions.selectByVisibleText(editEvent);

			// WebElement saveButton =
			// driver.findElement(CMSArticlePage.btnSave);
			// saveButton.click();

			// Thread.sleep(3000);
			// WebElement listboxPrimarySection =
			// driver.findElement(CMSArticlePage.listboxPrimarySection);
			// Select PrimarySectionOptions = new Select(listboxPrimarySection);
			// PrimarySectionOptions.selectByVisibleText(primarySection);

			// report.updateTestLog("Edit Event is visbile on Article Page",
			// "Edit Event "+ editEvent +" is visbile on Article Page",
			// Status.PASS);

			clickElement(CMSArticlePage.btnSave, "Save");
			/*
			 * WebElement saveButton1 =
			 * driver.findElement(CMSArticlePage.btnSave); saveButton1.click();
			 */

			Thread.sleep(10000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

			// form.submit();

			// WebElement saveButton2 =
			// driver.findElement(CMSArticlePage.btnSave);
			// saveButton2.click();

		} else {
			throw new FrameworkException("Title field on Article Page", "Title field is NOT visbile on Article Page");
		}
	}

}

// driver.findElements(By.xpath("//*[@id='post-body-6522850981930750493']/div[1]/table/tbody/tr")).size();
// int Col_count =
// driver.findElements(By.xpath("//*[@id='post-body-6522850981930750493']/div[1]/table/tbody/tr[1]/td")).size();

// navigateToFrontPage
// wait function
// loginCMS verifyCMSLoginSuccessful creatingNewFrontPage
// WebElement btnSubmit = driver.findElement(CMSFrontPage.btnSubmit);
// WebDriverWait wait= new WebDriverWait((WebDriver) driver, 30);
// wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
// if(btnSubmit.isDisplayed()){
