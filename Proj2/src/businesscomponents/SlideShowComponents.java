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
import uimap.RecipePage;
import uimap.SlideshowPage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class SlideShowComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public SlideShowComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	private static final int MAX_TIMEOUT = 180;

	public void invokeCMSApplication() {
		report.updateTestLog("Invoke CMS Application",
				"Invoke the CMS application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));
	}

	public void navigateToSlideshowPage() {

		try {
			navigateToSubMenu("Content", "Add content", "Slideshow");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Slideshow Page",
					"NOT Navigated to Slideshow Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

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

			/*
			 * WebElement element2 = driver.findElement(RecipePage.addShows);
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);",
			 * element2); wait(5000);
			 */
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

	public void verifySlideshowCreatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Slideshow_Page", "Slideshow Headline");
			String s = "Slideshow " + title + " has been created.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Slideshow Created Message", "Exception occured : " + e.getMessage());
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

	public void validateSlideshowMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("Slideshow_Page", "Slideshow Headline");
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
			report.updateTestLog("Navigate To NBC News Message Log", "NOT Navigated to NBC News Message Log",
					Status.FAIL);
		}

	}

	public void searchAndClickExistingSlideshow() {
		try {
			String contentType = dataTable.getData("Slideshow_Page", "Content Type");

			navigateToSubMenu("My Dashboard", "All Recent Content");

			selectDropdownVal(RecipePage.lstContentType, contentType, "Content Type");

			clickElement(FrontPage.btnApply, "Apply");

			int rowCount = driver
					.findElements(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr"))
					.size();
			if (rowCount >= 2) {
				First: for (int i = 2; i <= rowCount; i++) {
					String statusPublish = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
									+ i + "]/td[6]"))
							.getText().trim();
					if (!statusPublish.equals("Not published")) {
						String title = driver.findElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"))
								.getText();
						dataTable.putData("Slideshow_Page", "Slideshow Headline", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Slideshow", "NO Existing Slideshow. Need to create one.",
						Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Slideshow", "Exception Occured : " + e.getMessage());
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

	public void verifySlideshowDeletedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Slideshow_Page", "Slideshow Headline");
			String s = "Slideshow " + title + " has been deleted.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Slideshow Deleted Message", "Exception occured : " + e.getMessage());
		}
	}

	public void uploadBulkImgs() {
		try {
			clickElement(SlideshowPage.btnAddFile, "Add File");

			clickElement(SlideshowPage.btnAddFiles, "Add files");

			String projDir = System.getProperty("user.dir");

			Runtime.getRuntime().exec(projDir + "\\Upload.exe " + "Image1");

			Thread.sleep(6000);
			clickElement(SlideshowPage.btnStrtUpld, "Start Upload");

			waitForObjectVisible(SlideshowPage.signUploaded);

			wait(5000);
		} catch (Exception e) {
			throw new FrameworkException("Upload Bulk Images", "Exception occured : " + e.getMessage());
		}

	}

	public void bulkImgUploadToSlideshow() {
		try {
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();
			int colSlideTit = 0;
			String txtSlideTitle = null;

			clickElement(SlideshowPage.btnAddFile, "Add File");

			String[] imgs = { "Image1", "Image2", "Image3" };
			for (String img : imgs) {
				clickElement(SlideshowPage.btnAddFiles, "Add files");

				String projDir = System.getProperty("user.dir");

				Runtime.getRuntime().exec(projDir + "\\Upload.exe " + img);
				Thread.sleep(2000);

			}

			Thread.sleep(6000);
			clickElement(SlideshowPage.btnStrtUpld, "Start Upload");

			for (int i = 1; i <= imgs.length; i++) {
				waitForObjectVisible(By.xpath("(//div[@class='plupload_file_action']/a[@href='#'])[" + i + "]"));
				wait(2000);
			}

			String width = driver.findElement(By.xpath("//div[@class='plupload_progress_bar']")).getAttribute("style");
			while (!width.contains("100%")) {
				width = driver.findElement(By.xpath("//div[@class='plupload_progress_bar']")).getAttribute("style");
			}

			waitForObjectInVisible(By.xpath("//div[@class='plupload_progress_bar']"));

			chkUnchkCheckBox("Check", SlideshowPage.chkUseImgsToCrtSldShow,
					"Use these images to create (or add to) a slideshow.");

			clickElement(SlideshowPage.btnNext, "Next");

			if (waitForObjectVisible(SlideshowPage.titleEditMultipleFiles)) {
				report.updateTestLog("Verify Page", "'Edit Multiple Files' Page is displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Page", "'Edit Multiple Files' Page is NOT displayed", Status.FAIL);
			}

			int i = 0;
			for (String img : imgs) {
				img = img.toLowerCase();

				if (waitForObjectVisible(By.xpath("//em[text()='Edit image' and contains(../text(),'" + img + "')]"))) {
					// String imgName =
					// driver.findElement(By.xpath("//em[text()='Edit image' and
					// contains(../text(),'"+img+"')]")).getText();

					String imgName = driver.findElement(By.xpath("//h2[contains(text(),'" + img + "')]")).getText();
					WebElement imgHead = driver
							.findElement(By.xpath("//em[text()='Edit image' and contains(../text(),'" + img + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", imgHead);

					wait(2000);

					report.updateTestLog("Verify Image", imgName + " is displayed", Status.SCREENSHOT);
					i = i + 1;
					String tt = "ImageTitle" + System.currentTimeMillis();
					enterText(By.xpath("(//input[contains(@id,'edit-field-file-image-title-text')])[" + i + "]"), tt,
							"TITLE TEXT");
					a1.add(tt);
				} else {
					report.updateTestLog("Verify Image", img + " is NOT displayed", Status.FAIL);
					break;
				}
			}

			clickElement(SlideshowPage.btnBulkImgSave, "Save");

			waitForObjectVisible(SlideshowPage.titleCreateSlide);

			List<WebElement> tabHead = driver.findElements(
					By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/thead/tr/th"));
			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Slide title")) {
					colSlideTit = c + 1;
				}
			}

			List<WebElement> lw = driver
					.findElements(By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr"));

			for (i = 1; i <= lw.size(); i++) {
				txtSlideTitle = driver
						.findElement(By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr["
								+ i + "]/td[" + colSlideTit + "]"))
						.getText();
				txtSlideTitle = txtSlideTitle.replace(":", "");
				a2.add(txtSlideTitle);
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a2.containsAll(a1)) {
				WebElement tblSlide = driver.findElement(
						By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']//tr/td[text()='"
								+ a1.get(0) + ": ']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tblSlide);

				report.updateTestLog("Uploaded Images added to slides", "All Uploaded Images are added to Slides",
						Status.SCREENSHOT);
			} else {
				report.updateTestLog("Uploaded Images added to slides", "All Uploaded Images are NOT added to Slides",
						Status.FAIL);
			}

		} catch (Exception e) {
			throw new FrameworkException("Upload Bulk Images", "Exception occured : " + e.getMessage());
		}

	}

	public void bulkImgUploadToExistSlideshow() {
		try {
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();
			int colSlideTit = 0;
			String txtSlideTitle = null;

			clickElement(SlideshowPage.btnAddFile, "Add File");

			String[] imgs = { "Image1", "Image2", "Image3" };
			for (String img : imgs) {
				clickElement(SlideshowPage.btnAddFiles, "Add files");

				String projDir = System.getProperty("user.dir");

				Runtime.getRuntime().exec(projDir + "\\Upload.exe " + img);
				Thread.sleep(2000);

			}

			Thread.sleep(6000);
			clickElement(SlideshowPage.btnStrtUpld, "Start Upload");

			for (int i = 1; i <= imgs.length; i++) {
				waitForObjectVisible(By.xpath("(//div[@class='plupload_file_action']/a[@href='#'])[" + i + "]"));
				wait(2000);
			}

			String width = driver.findElement(By.xpath("//div[@class='plupload_progress_bar']")).getAttribute("style");
			while (!width.contains("100%")) {
				width = driver.findElement(By.xpath("//div[@class='plupload_progress_bar']")).getAttribute("style");
			}

			waitForObjectInVisible(By.xpath("//div[@class='plupload_progress_bar']"));

			chkUnchkCheckBox("Check", SlideshowPage.chkUseImgsToCrtSldShow,
					"Use these images to create (or add to) a slideshow.");

			enterText(SlideshowPage.txtAddImgsToSlidShw, "title", "ADD IMAGES TO A SLIDESHOW");

			String title = selectAuto();
			title = (title.split("\\(")[0]).trim();
			dataTable.putData("Slideshow_Page", "Slideshow Headline", title);

			clickElement(SlideshowPage.btnNext, "Next");

			if (waitForObjectVisible(SlideshowPage.titleEditMultipleFiles)) {
				report.updateTestLog("Verify Page", "'Edit Multiple Files' Page is displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Page", "'Edit Multiple Files' Page is NOT displayed", Status.FAIL);
			}
			int i = 0;
			for (String img : imgs) {
				img = img.toLowerCase();

				if (waitForObjectVisible(By.xpath("//em[text()='Edit image' and contains(../text(),'" + img + "')]"))) {
					// String imgName =
					// driver.findElement(By.xpath("//em[text()='Edit image' and
					// contains(../text(),'"+img+"')]")).getText();

					String imgName = driver.findElement(By.xpath("//h2[contains(text(),'" + img + "')]")).getText();
					WebElement imgHead = driver
							.findElement(By.xpath("//em[text()='Edit image' and contains(../text(),'" + img + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", imgHead);

					wait(2000);

					report.updateTestLog("Verify Image", imgName + " is displayed", Status.SCREENSHOT);
					i = i + 1;
					String tt = "ImageTitle" + System.currentTimeMillis();
					enterText(By.xpath("(//input[contains(@id,'edit-field-file-image-title-text')])[" + i + "]"), tt,
							"TITLE TEXT");
					a1.add(tt);
				} else {
					report.updateTestLog("Verify Image", img + " is NOT displayed", Status.FAIL);
					break;
				}
			}

			clickElement(SlideshowPage.btnBulkImgSave, "Save");

			waitForObjectVisible(SlideshowPage.titleEditSlide);

			List<WebElement> tabHead = driver.findElements(
					By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/thead/tr/th"));
			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Slide title")) {
					colSlideTit = c + 1;
				}
			}

			List<WebElement> lw = driver
					.findElements(By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr"));

			for (i = 1; i <= lw.size(); i++) {
				txtSlideTitle = driver
						.findElement(By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']/tbody/tr["
								+ i + "]/td[" + colSlideTit + "]"))
						.getText();
				txtSlideTitle = txtSlideTitle.replace(":", "");
				a2.add(txtSlideTitle);
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a2.containsAll(a1)) {
				WebElement tblSlide = driver.findElement(
						By.xpath("//table[@id='ief-entity-table-edit-field-slide-und-entities']//tr/td[text()='"
								+ a1.get(0) + ": ']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tblSlide);

				report.updateTestLog("Uploaded Images added to slides", "All Uploaded Images are added to Slides",
						Status.SCREENSHOT);
			} else {
				report.updateTestLog("Uploaded Images added to slides", "All Uploaded Images are NOT added to Slides",
						Status.FAIL);
			}
		} catch (Exception e) {
			throw new FrameworkException("Upload Bulk Images", "Exception occured : " + e.getMessage());
		}

	}

}
