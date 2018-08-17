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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

//import org.hamcrest.core.StringContains;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import uimap.CMSArticlePage;
import uimap.FrontPage;
import uimap.PinnedRefDocPage;
import uimap.RecipePage;
import uimap.SlideshowPage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class PinnedRefDocComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public PinnedRefDocComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	private static final int MAX_TIMEOUT = 180;

	public void invokeCMSApplication() {
		report.updateTestLog("Invoke CMS Application",
				"Invoke the CMS application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));
	}

	public void navigateToCreateNewFront() {

		try {
			navigateToSubMenu("Content", "Manage Fronts", "Create New Front");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Create New Front",
					"NOT Navigated to Create New Front Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void navigateToManageFronts() {

		try {
			navigateToSubMenu("Content", "Manage Fronts");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Manage Fronts",
					"NOT Navigated to Manage Fronts. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void addContentForPinnedRefDoc() throws InterruptedException, AWTException {

		try {
			String title;
			String valVideoRailTitle = dataTable.getData("PinnedRefDoc_Page", "Video Rail Title");
			String valMPXVideoFeedId = dataTable.getData("PinnedRefDoc_Page", "MPX Feed Id");
			waitForObjectVisible(PinnedRefDocPage.txtPinnedRefName);

			if (dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("PinnedRefDoc_Page", "Pinned Reference Name", title);
			} else {
				title = dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name");
			}

			enterText(PinnedRefDocPage.txtPinnedRefName, title, "Pinned Reference Name");

			enterText(PinnedRefDocPage.txtTaxonRef, "a", "Taxonomy Reference");

			selectAuto();

			enterText(PinnedRefDocPage.txtContent, "a", "Content");

			selectAuto();

			enterText(PinnedRefDocPage.txtMenuObjRef, "a", "Menu Reference");

			selectAuto();

			enterText(PinnedRefDocPage.txtVideoRailTitle, valVideoRailTitle, "Video Rail Title");

			enterText(PinnedRefDocPage.txtMPXVideoFeedId, valMPXVideoFeedId, "MPX Video Feed Id");

			isChecked(PinnedRefDocPage.chkCreateAnotPinRef, "Create another pinned ref");

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Pinned Ref Doc page",
					"Exception occured : " + e.getMessage());
		}

	}

	public void updatePinnedRefName() {
		try {
			String title;
			if (dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name").contains("AutoTitle")) {
				title = generateDynamicNames() + " UPDATED";
				dataTable.putData("PinnedRefDoc_Page", "Pinned Reference Name", title);
			} else {
				title = dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name");
			}

			enterText(PinnedRefDocPage.txtPinnedRefName, title, "Pinned Reference Name");
		} catch (Exception e) {
			throw new FrameworkException("Update Pinned Reference Name", "Exception occured : " + e.getMessage());
		}
	}

	public void clickSavePinRef() {
		clickElement(PinnedRefDocPage.btnSavePinRef, "Save Pinning Reference");
	}

	public void clickDeletePinRef() {
		String valPinRefName = driver.findElement(PinnedRefDocPage.txtPinnedRefName).getAttribute("value");
		dataTable.putData("PinnedRefDoc_Page", "Pinned Reference Name", valPinRefName);

		clickElement(PinnedRefDocPage.btnDeletePinRef, "Delete Pinning Reference");

		clickElement(CMSArticlePage.btnConfirmDelete, "Confirm Delete");
	}

	public void addContentForSlideshowPage() throws InterruptedException, AWTException {

		try {
			String title;
			waitForObjectVisible(SlideshowPage.txtTitle);

			if (dataTable.getData("Slideshow_Page", "Slideshow Headline").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("Slideshow_Page", "Slideshow Headline", title);
			} else {
				title = dataTable.getData("Slideshow_Page", "Slideshow Headline");
			}

			String valSummary = dataTable.getData("Slideshow_Page", "Summary");
			String valLogMsg = dataTable.getData("Slideshow_Page", "Log Message");

			String socMediaHeadline = dataTable.getData("Slideshow_Page", "SocialMediaHeadline");
			String coverHeadline = dataTable.getData("Slideshow_Page", "CoverHeadline");
			String seoHeadline = dataTable.getData("Slideshow_Page", "SEO Headline");
			String valCaptionBody = dataTable.getData("Slideshow_Page", "Caption Body");

			String primarySection = dataTable.getData("Slideshow_Page", "PrimarySection");
			String primaryTopic = dataTable.getData("Slideshow_Page", "PrimaryTopic");
			String byline = dataTable.getData("Slideshow_Page", "Byline");
			String valSlideTitle = dataTable.getData("Slideshow_Page", "Slide Title");
			String valCredit = dataTable.getData("Slideshow_Page", "Credit");

			enterText(SlideshowPage.txtTitle, title, "Slideshow Headline");

			clickElement(SlideshowPage.tabHeadlineSummary, "Headlines & Summary");

			enterText(SlideshowPage.txtSEOHeadline, seoHeadline, "SEO Headline");

			enterText(SlideshowPage.coverHeadline, coverHeadline, "Cover Headline");

			enterText(SlideshowPage.socMediaHead, socMediaHeadline, "Social Media Headline");

			WebElement wf = driver
					.findElement(By.xpath("//iframe[@id='edit-field-slideshow-caption-und-0-value_ifr']"));
			driver.switchTo().frame(wf);

			enterText(SlideshowPage.txtCaptionBody, valCaptionBody, "Caption Body");

			driver.switchTo().parentFrame();

			enterText(SlideshowPage.txtSummary, valSummary, "Summary");

			clickElement(SlideshowPage.btnAddNewSlide, "Add New Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			enterText(SlideshowPage.txtSlideTitle, valSlideTitle, "Slide Title");

			if (!driver.findElement(SlideshowPage.chkGraphicContent).isSelected()) {
				selectElement(SlideshowPage.chkGraphicContent, "Graphic Content");
			}

			clickElement(SlideshowPage.btnSelectSlideImage, "Select Slide Image");

			addImage();

			enterText(SlideshowPage.txtCredit, valCredit, "Credit");

			clickElement(SlideshowPage.btnCreateSlide, "Create Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			WebElement element1 = driver.findElement(SlideshowPage.txtSummary);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			report.updateTestLog("Add New Slide", "A New Slide is attached", Status.SCREENSHOT);

			clickSelectPicture();

			addImage();

			enterText(SlideshowPage.txtLog, valLogMsg, "Log Message For This State Change");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			wait(3000);

			clickElement(CMSArticlePage.btnSettings, "Settings");

			String primArtLabVal = dataTable.getData("Slideshow_Page", "Primary Article Label");
			String addArtLabVal = dataTable.getData("Slideshow_Page", "Additional Article Labels");
			String addShowVal = dataTable.getData("Slideshow_Page", "Additional Shows");
			String showVal = dataTable.getData("Slideshow_Page", "Show");
			String seriesVal = dataTable.getData("Slideshow_Page", "Series");
			String anchorsVal = dataTable.getData("Slideshow_Page", "Anchors");
			String addSecVal = dataTable.getData("Slideshow_Page", "Additional Sections");
			String addTopVal = dataTable.getData("Slideshow_Page", "Additional Topics");
			String addSubTopVal = dataTable.getData("Slideshow_Page", "Additional Sub Topics");
			String source = dataTable.getData("Slideshow_Page", "Source");

			clickElement(SlideshowPage.primaryArticlelabe, "Primary Article Label");
			primArtLabVal = driver.findElement(RecipePage.primaryArtLabVal).getText();
			selectElement(RecipePage.primaryArtLabVal, primArtLabVal);

			clickElement(RecipePage.addArtLabels, "Additional Article Labels");
			addArtLabVal = driver.findElement(RecipePage.addArtLabelVal).getText();
			selectElement(RecipePage.addArtLabelVal, addArtLabVal);

			clickElement(RecipePage.show, "Show");
			showVal = driver.findElement(RecipePage.showVal).getText();
			selectElement(RecipePage.showVal, showVal);

			clickElement(RecipePage.addShows, "Additional Shows");
			addShowVal = driver.findElement(RecipePage.addShowVal).getText();
			selectElement(RecipePage.addShowVal, addShowVal);

			clickElement(RecipePage.series, "Series");
			seriesVal = driver.findElement(RecipePage.seriesVal).getText();
			selectElement(RecipePage.seriesVal, seriesVal);

			clickElement(RecipePage.anchors, "Anchors");
			anchorsVal = driver.findElement(RecipePage.anchorVal).getText();
			selectElement(RecipePage.anchorVal, anchorsVal);

			selectDropdownVal(SlideshowPage.listboxPrimarySection, primarySection, "Primary Section");

			selectDropdownVal(RecipePage.listboxPrimaryTopic, primaryTopic, "Primary Topic");

			clickElement(RecipePage.addSections, "Additional Sections");
			addSecVal = driver.findElement(RecipePage.addSecVal).getText();
			selectElement(RecipePage.addSecVal, addSecVal);

			clickElement(RecipePage.addTopics, "Additional Topics");
			addTopVal = driver.findElement(RecipePage.addTopVal).getText();
			selectElement(RecipePage.addTopVal, addTopVal);

			clickElement(RecipePage.addSubTopics, "Additional Sub Topics");
			addSubTopVal = driver.findElement(RecipePage.addSubTopVal).getText();
			selectElement(RecipePage.addSubTopVal, addSubTopVal);

			enterText(CMSArticlePage.txtSource, source, "Source");

			enterText(SlideshowPage.txtByline, byline, "Byline");

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Recipe page", "Exception occured : " + e.getMessage());
		}

	}

	public void clickSelectPicture() {
		clickElement(CMSArticlePage.btnPictureSelect, "Picture Select");
	}

	public void enterSlideshowHeadline() {
		try {
			String title = generateDynamicNames();
			enterText(SlideshowPage.txtTitle, title, "Slideshow Headline");
		} catch (Exception e) {
			throw new FrameworkException("Enter Title in Slideshow Headline", "Exception occured : " + e.getMessage());
		}
	}

	public void removeCoverImage() {
		clickElement(SlideshowPage.btnRemoveCoverImage, "Remove Cover Image");
		wait(2000);
	}

	public void addCoverImage() {
		clickSelectPicture();

		addImage();
	}

	public void noSourceSlideshow() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		clickElement(SlideshowPage.btnSettings, "Settings");
		enterText(SlideshowPage.txtSource, "", "Source");

	}

	public void verifyPinnedRefCreatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name");
			String s = "Created Pinned Ref, adding another.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Pinned Ref Created Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifySlideshowUpdatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Slideshow_Page", "Slideshow Headline");
			String s = "Slideshow " + title + " has been updated.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Slideshow Updated Message", "Exception occured : " + e.getMessage());
		}
	}

	public void validatePinnedRefMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name");
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

	public void navigateToNBCNewsMsgLOG() {

		try {
			navigateToSubMenu("Content", "NBC News Message Log");
		} catch (Exception e) {
			report.updateTestLog("Navigate To NBC News Message Log",
					"NOT Navigated to NBC News Message Log " + e.getMessage(), Status.FAIL);
		}

	}

	public void searchAndClickExistingPinnedRef() {
		try {

			navigateToManageFronts();

			int rowCount = driver.findElements(By.xpath("//table[@class='views-table cols-3']/tbody/tr")).size();
			if (rowCount >= 2) {
				clickElement(By.xpath("(//table[@class='views-table cols-3']/tbody/tr//a)[1]"), "Edit");
			} else {
				report.updateTestLog("Search And Click Pinned Ref", "NO Existing Pinned Ref. Need to create one.",
						Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Pinned Ref", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void removeSlide() {
		String valSlideTitle = dataTable.getData("Slideshow_Page", "Slide Title");
		String valCredit = dataTable.getData("Slideshow_Page", "Credit");
		List<WebElement> lstSlides = driver
				.findElements(By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr"));

		if (lstSlides.size() > 0) {
			By btnRemove = By
					.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr/td[7]/div/input");
			clickElement(btnRemove, "Remove Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			By btnConfirmRemove = By.xpath(
					"//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr[2]//input[contains(@name,'remove-confirm')]");
			clickElement(btnConfirmRemove, "Confirm Remove Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
		} else {
			clickElement(SlideshowPage.btnAddNewSlide, "Add New Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			enterText(SlideshowPage.txtSlideTitle, valSlideTitle, "Slide Title");

			if (!driver.findElement(SlideshowPage.chkGraphicContent).isSelected()) {
				selectElement(SlideshowPage.chkGraphicContent, "Graphic Content");
			}

			clickElement(SlideshowPage.btnSelectSlideImage, "Select Slide Image");

			addImage();

			enterText(SlideshowPage.txtCredit, valCredit, "Credit");

			clickElement(SlideshowPage.btnCreateSlide, "Create Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			WebElement element1 = driver.findElement(SlideshowPage.txtSummary);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			report.updateTestLog("Add New Slide", "A New Slide is attached", Status.SCREENSHOT);

			By btnRemove = By
					.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr/td[7]/div/input");
			clickElement(btnRemove, "Remove Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			By btnConfirmRemove = By.xpath(
					"//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr[2]//input[contains(@name,'remove-confirm')]");
			clickElement(btnConfirmRemove, "Confirm Remove Slide");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
		}

	}

	public void addSlide() {
		String valSlideTitle = dataTable.getData("Slideshow_Page", "Slide Title");
		String valCredit = dataTable.getData("Slideshow_Page", "Credit");

		clickElement(SlideshowPage.btnAddNewSlide, "Add New Slide");

		waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

		enterText(SlideshowPage.txtSlideTitle, valSlideTitle, "Slide Title");

		if (!driver.findElement(SlideshowPage.chkGraphicContent).isSelected()) {
			selectElement(SlideshowPage.chkGraphicContent, "Graphic Content");
		}

		clickElement(SlideshowPage.btnSelectSlideImage, "Select Slide Image");

		addImage();

		enterText(SlideshowPage.txtCredit, valCredit, "Credit");

		clickElement(SlideshowPage.btnCreateSlide, "Create Slide");

		waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

		WebElement element1 = driver.findElement(SlideshowPage.tabHeadlineSummary);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		report.updateTestLog("Add New Slide", "A New Slide is attached", Status.SCREENSHOT);
	}

	public void verifyPinnedRefDeletedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("PinnedRefDoc_Page", "Pinned Reference Name");
			String s = "The nbcnews_pinned_ref " + title + " has been deleted.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Pinned Ref Deleted Message", "Exception occured : " + e.getMessage());
		}
	}

}
