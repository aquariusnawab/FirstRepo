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
import java.util.Calendar;
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
import uimap.MediaModelPage;
import uimap.MediaSearchPage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class MediaModelComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public MediaModelComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void validateMediaSearchKeyWord() {
		String valSearchKeyWord = dataTable.getData("Media_Model", "Search Key Words");
		boolean flag = true;
		String imgTitle = null;
		String imgCaption = null;
		String imgSource = null;
		String imgFullPhotoInfo = null;
		String imgLabel = null;
		int s;
		int i = 1;
		// String bolMedia = dataTable.getData("Media_Model", "Media");

		do {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			clickElement(CMSArticlePage.addMedia, "Add Media");

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			enterText(MediaModelPage.txtSearchKeyWords, valSearchKeyWord, "Search Key Words");

			clickElement(MediaModelPage.btnMediaImgSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver.findElements(By.xpath("//img[@typeof='foaf:Image']")).size();
			System.out.println(s);
			if (s > 12) {
				s = 12;
			}

			wait(1000);
			First: while (true) {
				imgLabel = driver.findElement(By.xpath("(//label[@class='media-filename'])[" + i + "]")).getText();
				imgLabel = imgLabel.toLowerCase();
				valSearchKeyWord = valSearchKeyWord.toLowerCase();
				if (imgLabel.contains(valSearchKeyWord)) {
					i = i + 1;
					flag = true;
					continue First;
				} else {
					selectElement(By.xpath("(//img[@typeof='foaf:Image'])[" + i + "]"), "An Image");
					i = i + 1;

					clickElement(CMSArticlePage.btnSubmitImg, "Submit");

					WebElement wf1 = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
					driver.switchTo().frame(wf1);

					wait(1000);

					imgTitle = driver.findElement(MediaModelPage.txtImgTitleText).getAttribute("value");

					imgCaption = driver.findElement(MediaModelPage.txtImgCaption).getAttribute("value");

					imgSource = driver.findElement(MediaModelPage.txtImgSource).getAttribute("value");

					imgFullPhotoInfo = driver.findElement(MediaModelPage.txtFullPhotoInfo).getAttribute("value");

					imgTitle = imgTitle.toLowerCase();
					imgCaption = imgCaption.toLowerCase();
					imgSource = imgSource.toLowerCase();
					imgFullPhotoInfo = imgFullPhotoInfo.toLowerCase();

					if (imgTitle.contains(valSearchKeyWord) || imgCaption.contains(valSearchKeyWord)
							|| imgSource.contains(valSearchKeyWord) || imgFullPhotoInfo.contains(valSearchKeyWord)) {
						flag = true;
						clickElement(MediaModelPage.btnImgCancel, "Cancel");
						break First;
					} else {
						flag = false;
						break;
					}
				}
			}
		} while (i <= s);

		if (flag) {
			report.updateTestLog("Validate Search Key Words",
					"All Images displayed includes Search Key Word : <b>" + valSearchKeyWord + "</b>", Status.PASS);
		} else {
			report.updateTestLog("Validate Search Key Words",
					"All Images displayed DO NOT include Search Key Word : " + valSearchKeyWord, Status.FAIL);
		}
	}

	public void validateMediaFileName() {
		String valFileName = dataTable.getData("Media_Model", "File Name");
		boolean flag = true;
		String imgLabel = null;
		int s;
		int i = 1;
		String bolMedia = dataTable.getData("Media_Model", "Media");

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		if (bolMedia.equals("No")) {
			clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"), "Picture - Select");
		} else {
			clickElement(CMSArticlePage.addMedia, "Add Media");
		}

		wait(3000);

		WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
		driver.switchTo().frame(wf);

		clickElement(CMSArticlePage.btnImags, "Images");

		enterText(MediaModelPage.txtImgFileName, valFileName, "File Name");

		clickElement(MediaModelPage.btnMediaImgSearch, "Search");

		waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

		s = driver.findElements(By.xpath("//img[@typeof='foaf:Image']")).size();
		System.out.println(s);
		if (s > 12) {
			s = 12;
		}

		wait(1000);
		while (i <= s) {
			imgLabel = driver.findElement(By.xpath("(//label[@class='media-filename'])[" + i + "]")).getText();
			imgLabel = imgLabel.toLowerCase();
			valFileName = valFileName.toLowerCase();
			if (imgLabel.contains(valFileName)) {
				i = i + 1;
				flag = true;
			} else {
				flag = false;
				break;
			}

		}

		if (flag) {
			clickElement(MediaModelPage.btnImgCancel1, "Cancel");
			report.updateTestLog("Validate File Name",
					"All Images displayed includes File Name : <b>" + valFileName + "</b>", Status.PASS);
		} else {
			report.updateTestLog("Validate File Name", "All Images displayed DO NOT include File Name : " + valFileName,
					Status.FAIL);
		}
	}

	public void validateSortBy() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		clickElement(CMSArticlePage.addMedia, "Add Media");

		wait(3000);

		WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
		driver.switchTo().frame(wf);

		clickElement(CMSArticlePage.btnImags, "Images");

		waitForObjectVisible(MediaModelPage.txtImgFileName);

		try {
			Select a = new Select(driver.findElement(MediaModelPage.lstImgSortBy));
			a.selectByVisibleText("Upload date");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (isClickable(By.xpath("(//select[@id='edit-sort-by'])[1]/option"))) {
			report.updateTestLog("Validate Sort By", "Sort By is Enabled", Status.FAIL);
		} else {
			report.updateTestLog("Validate Sort By", "Sort By is Disabled", Status.PASS);
		}
	}

	public void validateImgOrder() {
		try {
			boolean flag = true;
			String imgLabel1 = null;
			String imgLabel2 = null;
			String actDate = null;
			String lmtDate = null;
			int colUpldDate = 0;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();
			String bolMedia = dataTable.getData("Media_Model", "Media");

			String[] orders = { "Asc", "Desc" };
			for (String order : orders) {
				int s;
				int i = 1;
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

				if (bolMedia.equals("No")) {
					clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"), "Picture - Select");
				} else {
					clickElement(CMSArticlePage.addMedia, "Add Media");
				}

				wait(3000);

				WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
				driver.switchTo().frame(wf);

				clickElement(CMSArticlePage.btnImags, "Images");

				waitForObjectVisible(MediaModelPage.txtImgFileName);

				selectDropdownVal(MediaModelPage.lstImgOrder, order, "Order");

				clickElement(MediaModelPage.btnMediaImgSearch, "Search");

				waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

				s = driver.findElements(By.xpath("//img[@typeof='foaf:Image']")).size();
				System.out.println(s);
				if (s > 12) {
					s = 12;
				}

				wait(1000);
				while (i <= s) {
					imgLabel1 = driver.findElement(By.xpath("(//label[@class='media-filename'])[" + i + "]")).getText();
					i = i + 1;
					a1.add(imgLabel1);
				}
				System.out.println(a1);

				clickElement(MediaModelPage.btnImgCancel1, "Cancel");

				navigateToSubMenu("Content", "Find Media", "Find Photos");

				for (String fn : a1) {
					enterText(MediaSearchPage.txtPhotoFileName, fn, "Photo File Name");

					clickElement(MediaSearchPage.btnPhotoSearch, "Search");

					List<WebElement> tabHead = driver
							.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
					for (int c = 0; c < tabHead.size(); c++) {
						if (tabHead.get(c).getText().equals("Upload date")) {
							colUpldDate = c + 1;
						}
					}
					int rowCnt = 1;
					if (order.equals("Asc")) 
						rowCnt = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr")).size();
					
					actDate = driver
							.findElement(By
									.xpath("//table[@class='views-table cols-9']/tbody/tr["+rowCnt+"]/td[" + colUpldDate + "]"))
							.getText();
					actDate = actDate.split("-")[0].trim();
					a2.add(actDate);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date d1 = null;
				Date d2 = null;
				if (order.equals("Asc")) {
					First: for (int z = 0; z < (a2.size() - 1); z++) {
						d1 = sdf.parse(a2.get(z));
						d2 = sdf.parse(a2.get(z + 1));
						if (d1.compareTo(d2) <= 0) {
							flag = true;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
						} else {
							flag = false;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
							break First;
						}
					}
				} else {
					First: for (int z = 0; z < (a2.size() - 1); z++) {
						d1 = sdf.parse(a2.get(z));
						d2 = sdf.parse(a2.get(z + 1));
						if (d1.compareTo(d2) >= 0) {
							flag = true;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
						} else {
							flag = false;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
							break First;
						}
					}
				}

				if (flag) {
					report.updateTestLog("Validate " + order + " Order",
							"All Images Displayed are in <b>" + order + " order</b>", Status.PASS);
				} else {
					report.updateTestLog("Validate Order", "All Images Displayed are NOT in <b>" + order + " order"
							+ " Found the order of Dates to be : " + sdf.format(d1) + " " + sdf.format(d2) + "</b>",
							Status.FAIL);
				}
				a1.clear();
				a2.clear();
				String page = dataTable.getData("Media_Model", "Sub Menu");
				navigateToSubMenu("Content", "Add content", page);
				goToImageOption();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateMediaDate() {
		try {
			boolean flag = true;
			String imgLabel1 = null;
			String imgLabel2 = null;
			String actDate = null;
			String lmtDate = null;
			int colUpldDate = 0;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();
			String bolMedia = dataTable.getData("Media_Model", "Media");
			Calendar cal = Calendar.getInstance();
			Date d;

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

			String[] dates = { "Last Two Months", "Last Year" };
			for (String date : dates) {
				int s;
				int i = 1;
				int z;
				if (date.equals("Last Two Months")) {
					cal.add(Calendar.DATE, -60);
					d = cal.getTime();
					lmtDate = dateFormat.format(d);
				}

				if (date.equals("Last Year")) {
					cal.add(Calendar.YEAR, -1);
					d = cal.getTime();
					lmtDate = dateFormat.format(d);
				}
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

				if (bolMedia.equals("No")) {
					clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"), "Picture - Select");
				} else {
					clickElement(CMSArticlePage.addMedia, "Add Media");
				}

				wait(3000);

				WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
				driver.switchTo().frame(wf);

				clickElement(CMSArticlePage.btnImags, "Images");

				waitForObjectVisible(MediaModelPage.txtImgFileName);

				selectDropdownVal(MediaModelPage.lstImgDate, date, "Order");

				clickElement(MediaModelPage.btnMediaImgSearch, "Search");

				waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

				s = driver.findElements(By.xpath("//img[@typeof='foaf:Image']")).size();
				System.out.println(s);
				if (s > 12) {
					s = 12;
				}

				wait(1000);
				while (i <= s) {
					imgLabel1 = driver.findElement(By.xpath("(//label[@class='media-filename'])[" + i + "]")).getText();
					i = i + 1;
					a1.add(imgLabel1);
				}
				System.out.println(a1);

				clickElement(MediaModelPage.btnImgCancel1, "Cancel");

				navigateToSubMenu("Content", "Find Media", "Find Photos");

				for (String fn : a1) {
					enterText(MediaSearchPage.txtPhotoFileName, fn, "Photo File Name");

					clickElement(MediaSearchPage.btnPhotoSearch, "Search");

					List<WebElement> tabHead = driver
							.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
					for (int c = 0; c < tabHead.size(); c++) {
						if (tabHead.get(c).getText().equals("Upload date")) {
							colUpldDate = c + 1;
						}
					}

					actDate = driver
							.findElement(By
									.xpath("//table[@class='views-table cols-9']/tbody/tr[1]/td[" + colUpldDate + "]"))
							.getText();
					actDate = actDate.split("-")[0].trim();
					a2.add(actDate);
				}

				First: for (z = 0; z < (a2.size()); z++) {
					if (a2.get(z).compareTo(lmtDate) >= 0) {
						flag = true;
					} else {
						flag = false;
						break First;
					}
				}

				if (flag) {
					report.updateTestLog("Validate Media Date",
							"All Images displayed have Upload date range as selected : <b>" + date + "</b>",
							Status.PASS);
				} else {
					report.updateTestLog("Validate Media Date",
							"An Image with Upload date " + a2.get(z)
									+ " DOES NOT have Upload date range as selected : <b>" + date + "</b>",
							Status.FAIL);
				}
				a1.clear();
				a2.clear();
				String page = dataTable.getData("Media_Model", "Sub Menu");
				navigateToSubMenu("Content", "Add content", page);
				goToImageOption();
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	public void goToImageOption() {
		String page = dataTable.getData("Media_Model", "Sub Menu");

		switch (page) {

		/*
		 * case "Common List": String valContType =
		 * dataTable.getData("Media_Model", "Content Type");
		 * selectDropdownVal(MediaModelPage.lstComonListContentType,
		 * valContType, "Content Type");
		 */

		case "Cover":
			String valCoverBlocks = dataTable.getData("Media_Model", "Content Type");
			selectDropdownVal(MediaModelPage.lstCoverBlocks, valCoverBlocks, "Content Type");
			clickElement(MediaModelPage.btnAddNewCoverBlock, "Add New Cover Block");
			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
			break;

		}
	}

	public void goToVideoOption() {
		String page = dataTable.getData("Media_Model", "Sub Menu");

		switch (page) {

		case "Common List":
			String valContType = dataTable.getData("Media_Model", "Content Type");
			selectDropdownVal(MediaModelPage.lstComonListContentType, valContType, "Content Type");
			break;

		case "Cover":
			String valCoverBlocks = dataTable.getData("Media_Model", "Content Type");
			selectDropdownVal(MediaModelPage.lstCoverBlocks, valCoverBlocks, "Content Type");
			clickElement(MediaModelPage.btnAddNewCoverBlock, "Add New Cover Block");
			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
			break;

		}
	}

	public void validateVideoTitleKeywrds() {
		try {
			String valTitlKeywrds = dataTable.getData("Media_Model", "Title Keywords");
			boolean flag = false;
			String vidLabel = null;
			int s;
			int i = 1;

			String page = dataTable.getData("Media_Model", "Sub Menu");
			navigateToSubMenu("Content", "Add content", page);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			String bolMedia = dataTable.getData("Media_Model", "Media");
			if (bolMedia.equals("No")) {
				goToVideoOption();
				waitForObjectVisible(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"));
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.btnVids, "Videos");

			enterText(MediaModelPage.txtVidTitleKeywrds, valTitlKeywrds, "Title Keywords");

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.mediaVedioSearch, "Search");
			else
				clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * System.out.println(s); if(s>24) { s = 24; }
			 */

			wait(1000);
			while (i <= s) {
				vidLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				vidLabel = vidLabel.toLowerCase();
				System.out.println(vidLabel);
				valTitlKeywrds = valTitlKeywrds.toLowerCase();
				if (vidLabel.contains(valTitlKeywrds)) {
					i = i + 1;
					flag = true;
				} else {
					flag = false;
					break;
				}

			}

			if (flag) {
				clickElement(MediaModelPage.btnVidCancel, "Cancel");
				report.updateTestLog("Validate Title Keywords",
						"All Vidoes displayed includes Title Keywords : <b>" + valTitlKeywrds + "</b>", Status.PASS);
			} else {
				report.updateTestLog("Validate File Name",
						"All Vidoes displayed DO NOT include Title Keywords : <b>" + valTitlKeywrds + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVideoMPXGUID() {
		try {
			boolean flag = false;
			String vidLabel = null;
			int s;
			int i = 1;
			int colType = 0;
			int colID = 0;
			String valGUID = null;

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));

			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Type")) {
					colType = c + 1;
				}
				if (tabHead.get(c).getText().equals("ID")) {
					colID = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			First: for (int j = 1; j <= lw.size(); j++) {
				String txtType = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colType + "]"))
						.getText();
				txtType = txtType.toLowerCase();
				String txtID = driver
						.findElement(
								By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colID + "]"))
						.getText();
				txtID = txtID.toLowerCase();
				System.out.println(txtType);
				System.out.println(txtID);
				if (txtType.contains("video") && txtID.contains("id") && txtID.contains("guid")) {
					valGUID = txtID.split("guid: ")[1];
					break First;
				} else {

				}
			}

			String page = dataTable.getData("Media_Model", "Sub Menu");
			navigateToSubMenu("Content", "Add content", page);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			String bolMedia = dataTable.getData("Media_Model", "Media");
			if (bolMedia.equals("No")) {
				goToVideoOption();
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.btnVids, "Videos");

			enterText(MediaModelPage.txtVidMPXGUID, valGUID, "MPX GUID");

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.mediaVedioSearch, "Search");
			else
				clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * if(s>24) { s = 24; }
			 */

			wait(1000);
			while (i <= s) {
				vidLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				selectElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail'])["
								+ i + "]"),
						"A Video : " + vidLabel);
				clickElement(CMSArticlePage.btnSubmitVid, "Submit");

				if (bolMedia.equals("No")) {
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

					clickElement(MediaModelPage.btnVideoEdit, "Video - Edit");

					waitForObjectVisible(By.xpath("//div[@id='modalContent']"));

					// waitForObjectVisible(MediaModelPage.txtRetrVidMPXGUID);
				} else {
					WebElement wf1v = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
					driver.switchTo().frame(wf1v);
					wait(1000);
				}

				waitForObjectVisible(MediaModelPage.txtRetrVidMPXGUID);
				String actGUID = driver.findElement(MediaModelPage.txtRetrVidMPXGUID).getAttribute("value");

				if (actGUID.equals(valGUID)) {
					i = i + 1;
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				if (!bolMedia.equals("No"))
					clickElement(MediaModelPage.btnImgCancel, "Cancel");
				else {
					clickElement(MediaModelPage.btnPictureEdit_Cancel, "Cancel");
					clickElement(MediaModelPage.btnVideoRemove, "Remove");
					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
				}
				report.updateTestLog("Validate MPX GUID", "All Videos displayed have MPX GUID : <b>" + valGUID + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Validate MPX GUID",
						"All Videos displayed DO NOT have MPX GUID : <b>" + valGUID + "</b>", Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVideoMPXID() {
		try {
			boolean flag = false;
			String vidLabel = null;
			int s;
			int i = 1;
			int colType = 0;
			int colID = 0;
			String valID = null;

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));

			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Type")) {
					colType = c + 1;
				}
				if (tabHead.get(c).getText().equals("ID")) {
					colID = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			First: for (int j = 1; j <= lw.size(); j++) {
				String txtType = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colType + "]"))
						.getText();
				txtType = txtType.toLowerCase();
				String txtID = driver
						.findElement(
								By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colID + "]"))
						.getText();
				txtID = txtID.toLowerCase();
				System.out.println(txtType);
				System.out.println(txtID);
				if (txtType.contains("video") && txtID.contains("id") && txtID.contains("guid")) {
					valID = txtID.split("guid: ")[0];
					valID = (valID.split("id: ")[1]).trim();
					System.out.println("id " + valID);
					break First;
				} else {

				}
			}

			String page = dataTable.getData("Media_Model", "Sub Menu");
			navigateToSubMenu("Content", "Add content", page);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			String bolMedia = dataTable.getData("Media_Model", "Media");
			if (bolMedia.equals("No")) {
				goToVideoOption();
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.btnVids, "Videos");

			enterText(MediaModelPage.txtVidMPXID, valID, "MPX ID");

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.mediaVedioSearch, "Search");
			else
				clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * if(s>24) { s = 24; }
			 */

			wait(1000);
			while (i <= s) {
				vidLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				selectElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail'])["
								+ i + "]"),
						"A Video : " + vidLabel);
				clickElement(CMSArticlePage.btnSubmitVid, "Submit");

				if (bolMedia.equals("No")) {
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

					clickElement(MediaModelPage.btnVideoEdit, "Video - Edit");

					waitForObjectVisible(By.xpath("//div[@id='modalContent']"));

					// waitForObjectVisible(MediaModelPage.txtRetrVidMPXGUID);
				} else {
					WebElement wf1v = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
					driver.switchTo().frame(wf1v);
					wait(1000);
				}

				waitForObjectVisible(MediaModelPage.txtRetrVidMPXID);
				String actID = driver.findElement(MediaModelPage.txtRetrVidMPXID).getAttribute("value");

				if (actID.equals(valID)) {
					i = i + 1;
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				if (!bolMedia.equals("No"))
					clickElement(MediaModelPage.btnImgCancel, "Cancel");
				else {
					clickElement(MediaModelPage.btnPictureEdit_Cancel, "Cancel");
					clickElement(MediaModelPage.btnVideoRemove, "Remove");
					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
				}
				report.updateTestLog("Validate MPX ID", "All Videos displayed have MPX ID : <b>" + valID + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Validate MPX ID",
						"All Videos displayed DO NOT have MPX ID : <b>" + valID + "</b>", Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVideoBrand() {
		try {
			boolean flag = false;
			String vidLabel = null;
			int s;
			int i = 1;
			int colType = 0;
			int colID = 0;
			String txtBrand = null;
			ArrayList<String> a1 = new ArrayList<String>();

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));

			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Brand")) {
					colType = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			First: for (int j = 1; j <= lw.size(); j++) {
				txtBrand = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colType + "]"))
						.getText().trim();
				if (txtBrand.isEmpty()) {
					continue First;
				} else {
					break First;
				}
			}

			String page = dataTable.getData("Media_Model", "Sub Menu");
			navigateToSubMenu("Content", "Add content", page);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			String bolMedia = dataTable.getData("Media_Model", "Media");
			if (bolMedia.equals("No")) {
				goToVideoOption();
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.btnVids, "Videos");

			enterText(MediaModelPage.txtVidBrand, txtBrand, "Brands/Shows");

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.mediaVedioSearch, "Search");
			else
				clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * if(s>24) { s = 24; }
			 */

			wait(1000);
			while (i <= s) {
				vidLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				vidLabel = vidLabel.split("\\(")[0].trim();
				vidLabel = vidLabel.replaceAll("‘", "'");
				vidLabel = vidLabel.replaceAll("’", "'");

				if (vidLabel.contains("…")) {
					vidLabel = vidLabel.split("…")[0].trim();
				}

				System.out.println(vidLabel);
				a1.add(vidLabel);
				i = i + 1;

			}

			clickElement(MediaModelPage.btnVidCancel, "Cancel");

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			for (String v : a1) {
				enterText(MediaSearchPage.txtSearchKeyWord, v, "Search Key Words");

				clickElement(MediaSearchPage.btnVideoSearch, "Search");

				String actBrand = driver
						.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[1]/td[" + colType + "]"))
						.getText().trim();

				if (actBrand.equals(txtBrand)) {
					i = i + 1;
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				// clickElement(MediaModelPage.btnImgCancel, "Cancel");
				report.updateTestLog("Validate Brand", "All Videos displayed have Brand : <b>" + txtBrand + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Validate Brand",
						"All Videos displayed DO NOT have Brand : <b>" + txtBrand + "</b>", Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVideoMPXDescrp() {
		try {
			boolean flag = false;
			String vidLabel = null;
			int s;
			int i = 1;
			int colType = 0;
			int colID = 0;
			String valID = null;
			String txtMPXDesc = null;

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));

			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Name")) {
					colType = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			First: for (int j = 1; j <= lw.size(); j++) {
				txtMPXDesc = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + j + "]/td[" + colType + "]"))
						.getText().trim();
				txtMPXDesc = txtMPXDesc.split("Description: ")[1];
				if (txtMPXDesc.endsWith("...")) {
					txtMPXDesc = txtMPXDesc.substring(0, txtMPXDesc.length() - 3);
				}
				txtMPXDesc = txtMPXDesc.trim();
				System.out.println(txtMPXDesc);
				if (txtMPXDesc.isEmpty()) {
					continue First;
				} else {
					break First;
				}
			}

			String page = dataTable.getData("Media_Model", "Sub Menu");
			navigateToSubMenu("Content", "Add content", page);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			String bolMedia = dataTable.getData("Media_Model", "Media");
			if (bolMedia.equals("No")) {
				goToVideoOption();
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.btnVids, "Videos");

			enterText(MediaModelPage.txtVidMPXDecrp, txtMPXDesc, "MPX Description");

			if (!bolMedia.equals("No"))
				clickElement(CMSArticlePage.mediaVedioSearch, "Search");
			else
				clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * if(s>24) { s = 24; }
			 */

			wait(1000);
			while (i <= s) {
				vidLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				selectElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail'])["
								+ i + "]"),
						"A Video : " + vidLabel);
				clickElement(CMSArticlePage.btnSubmitVid, "Submit");

				if (bolMedia.equals("No")) {
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

					clickElement(MediaModelPage.btnVideoEdit, "Video - Edit");

					waitForObjectVisible(By.xpath("//div[@id='modalContent']"));

					// waitForObjectVisible(MediaModelPage.txtRetrVidMPXGUID);
				} else {
					WebElement wf1v = driver.findElement(By.xpath("//iframe[@id='mediaStyleSelector']"));
					driver.switchTo().frame(wf1v);
					wait(1000);
				}

				waitForObjectVisible(MediaModelPage.txtRetrVidMPXDecrp);
				String actMPXDesc = driver.findElement(MediaModelPage.txtRetrVidMPXDecrp).getAttribute("value");

				if (actMPXDesc.contains(txtMPXDesc)) {
					i = i + 1;
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				if (!bolMedia.equals("No"))
					clickElement(MediaModelPage.btnImgCancel, "Cancel");
				else {
					clickElement(MediaModelPage.btnPictureEdit_Cancel, "Cancel");
					clickElement(MediaModelPage.btnVideoRemove, "Remove");
					waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
				}
				report.updateTestLog("Validate MPX Description",
						"All Videos displayed include MPX Description : <b>" + txtMPXDesc + "</b>", Status.PASS);
			} else {
				report.updateTestLog("Validate MPX ID",
						"All Videos displayed DO NOT have MPX Description : <b>" + txtMPXDesc + "</b>", Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVidOrder() {
		try {
			boolean flag = true;
			String imgLabel1 = null;
			String imgLabel2 = null;
			String actDate = null;
			String lmtDate = null;
			String vidLabel;
			int colUpldDate = 0;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();

			String[] orders = { "Asc", "Desc" };
			for (String order : orders) {
				int s;
				int i = 1;
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

				String bolMedia = dataTable.getData("Media_Model", "Media");
				if (bolMedia.equals("No")) {
					// String valContType = dataTable.getData("Media_Model",
					// "Content Type");
					// selectDropdownVal(MediaModelPage.lstComonListContentType,
					// valContType, "Content Type");
					clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
				} else {
					clickElement(CMSArticlePage.addMedia, "Add Media");
				}

				wait(3000);

				WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
				driver.switchTo().frame(wfv);

				if (!bolMedia.equals("No"))
					clickElement(CMSArticlePage.btnVids, "Videos");

				waitForObjectVisible(MediaModelPage.txtVidMPXGUID);

				if (!bolMedia.equals("No"))
					selectDropdownVal(MediaModelPage.lstVidOrder, order, "Order");
				else
					selectDropdownVal(MediaModelPage.lstImgOrder, order, "Order");

				if (!bolMedia.equals("No"))
					clickElement(CMSArticlePage.mediaVedioSearch, "Search");
				else
					clickElement(CMSArticlePage.videoSearch, "Search");

				waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

				s = driver
						.findElements(By
								.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
						.size();
				System.out.println(s);

				wait(1000);
				while (i <= s) {
					vidLabel = driver.findElement(By
							.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
									+ i + "]"))
							.getText();
					vidLabel = vidLabel.split("\\(")[0].trim();
					vidLabel = vidLabel.replaceAll("‘", "'");
					vidLabel = vidLabel.replaceAll("’", "'");
					if (vidLabel.contains("…")) {
						vidLabel = vidLabel.split("…")[0].trim();
					}

					System.out.println(vidLabel);
					a1.add(vidLabel);
					i = i + 1;

				}
				System.out.println(a1);

				clickElement(MediaModelPage.btnVidCancel, "Cancel");

				navigateToSubMenu("Content", "Find Media", "Find Videos");

				for (String fn : a1) {
					enterText(MediaSearchPage.txtSearchKeyWord, fn, "Search Key Word");

					clickElement(MediaSearchPage.btnVideoSearch, "Search");

					List<WebElement> tabHead = driver
							.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
					for (int c = 0; c < tabHead.size(); c++) {
						if (tabHead.get(c).getText().equals("Upload date")) {
							colUpldDate = c + 1;
						}
					}

					actDate = driver
							.findElement(By
									.xpath("//table[@class='views-table cols-9']/tbody/tr[1]/td[" + colUpldDate + "]"))
							.getText();
					actDate = actDate.split("-")[0].trim();
					a2.add(actDate);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date d1 = null;
				Date d2 = null;
				if (order.equals("Asc")) {
					First: for (int z = 0; z < (a2.size() - 1); z++) {
						d1 = sdf.parse(a2.get(z));
						d2 = sdf.parse(a2.get(z + 1));
						if (d1.compareTo(d2) <= 0) {
							flag = true;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
						} else {
							flag = false;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
							break First;
						}
					}
				} else {
					First: for (int z = 0; z < (a2.size() - 1); z++) {
						d1 = sdf.parse(a2.get(z));
						d2 = sdf.parse(a2.get(z + 1));
						if (d1.compareTo(d2) >= 0) {
							flag = true;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
						} else {
							flag = false;
							System.out.println(sdf.format(d1) + " " + sdf.format(d2));
							break First;
						}
					}
				}

				if (flag) {
					report.updateTestLog("Validate " + order + " Order",
							"All Videos Displayed are in <b>" + order + " order</b>", Status.PASS);
				} else {
					report.updateTestLog("Validate Order", "All Videos Displayed are NOT in <b>" + order + " order"
							+ " Found the order of Dates to be : " + sdf.format(d1) + " " + sdf.format(d2) + "</b>",
							Status.FAIL);
				}
				a1.clear();
				a2.clear();
				String page = dataTable.getData("Media_Model", "Sub Menu");
				navigateToSubMenu("Content", "Add content", page);
				goToVideoOption();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateVidDate() {
		try {
			boolean flag = true;
			String imgLabel1 = null;
			String imgLabel2 = null;
			String actDate = null;
			String lmtDate = null;
			int colUpldDate = 0;
			String vidLabel;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();

			Calendar cal = Calendar.getInstance();
			Date d;

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

			String[] dates = { "Last Two Months", "Last Year" };
			for (String date : dates) {
				int s;
				int i = 1;
				int z;
				if (date.equals("Last Two Months")) {
					cal.add(Calendar.DATE, -60);
					d = cal.getTime();
					lmtDate = dateFormat.format(d);
				}

				if (date.equals("Last Year")) {
					cal.add(Calendar.YEAR, -1);
					d = cal.getTime();
					lmtDate = dateFormat.format(d);
				}
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

				String bolMedia = dataTable.getData("Media_Model", "Media");
				if (bolMedia.equals("No")) {
					// String valContType = dataTable.getData("Media_Model",
					// "Content Type");
					// selectDropdownVal(MediaModelPage.lstComonListContentType,
					// valContType, "Content Type");
					clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
				} else {
					clickElement(CMSArticlePage.addMedia, "Add Media");
				}

				wait(3000);

				WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
				driver.switchTo().frame(wfv);

				if (!bolMedia.equals("No"))
					clickElement(CMSArticlePage.btnVids, "Videos");

				waitForObjectVisible(MediaModelPage.txtVidMPXGUID);

				if (!bolMedia.equals("No"))
					selectDropdownVal(MediaModelPage.lstVidDate, date, "DATE");
				else
					selectDropdownVal(MediaModelPage.lstImgDate, date, "DATE");

				if (!bolMedia.equals("No"))
					clickElement(CMSArticlePage.mediaVedioSearch, "Search");
				else
					clickElement(CMSArticlePage.videoSearch, "Search");

				waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

				// waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

				s = driver
						.findElements(By
								.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']"))
						.size();
				System.out.println(s);

				wait(1000);
				while (i <= s) {
					vidLabel = driver.findElement(By
							.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail']//label[@class='media-filename'])["
									+ i + "]"))
							.getText();
					vidLabel = vidLabel.split("\\(")[0].trim();
					vidLabel = vidLabel.replaceAll("‘", "'");
					vidLabel = vidLabel.replaceAll("’", "'");
					if (vidLabel.contains("…")) {
						vidLabel = vidLabel.split("…")[0].trim();
					}

					System.out.println(vidLabel);
					a1.add(vidLabel);
					i = i + 1;

				}
				System.out.println(a1);

				clickElement(MediaModelPage.btnVidCancel, "Cancel");

				navigateToSubMenu("Content", "Find Media", "Find Videos");

				for (String fn : a1) {
					enterText(MediaSearchPage.txtSearchKeyWord, fn, "Search Key Word");

					clickElement(MediaSearchPage.btnVideoSearch, "Search");

					List<WebElement> tabHead = driver
							.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
					for (int c = 0; c < tabHead.size(); c++) {
						if (tabHead.get(c).getText().equals("Upload date")) {
							colUpldDate = c + 1;
						}
					}

					actDate = driver
							.findElement(By
									.xpath("//table[@class='views-table cols-9']/tbody/tr[1]/td[" + colUpldDate + "]"))
							.getText();
					actDate = actDate.split("-")[0].trim();
					a2.add(actDate);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date d1 = null;
				Date d2 = null;

				First: for (z = 0; z < (a2.size()); z++) {
					d1 = sdf.parse(a2.get(z));
					d2 = sdf.parse(lmtDate);
					System.out.println(sdf.format(d1) + " " + sdf.format(d2));
					if (d1.compareTo(d2) >= 0) {
						flag = true;
					} else {
						flag = false;
						break First;
					}
				}

				if (flag) {
					report.updateTestLog("Validate Date",
							"All Videos displayed have Upload date range as selected : <b>" + date + "</b>",
							Status.PASS);
				} else {
					report.updateTestLog("Validate Media Date",
							"A Video with Upload date " + a2.get(z)
									+ " DOES NOT have Upload date range as selected : <b>" + date + "</b>",
							Status.FAIL);
				}
				a1.clear();
				a2.clear();
				String page = dataTable.getData("Media_Model", "Sub Menu");
				navigateToSubMenu("Content", "Add content", page);
				goToVideoOption();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void verifySelImageColor() {

		try {
			String color;
			clickElement(CMSArticlePage.addMedia, "Add Media");

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			selectElement(CMSArticlePage.selImage1, "An Image");

			String selImgColor = driver.findElement(CMSArticlePage.selectedImg).getCssValue("background-color");
			System.out.println(selImgColor);

			String testImgColor = driver.findElement(CMSArticlePage.testImage).getCssValue("background-color");
			System.out.println(testImgColor);

			if (selImgColor.equals("rgba(176, 196, 222, 1)")) {
				color = "Blue";
			} else {
				color = "Color Changed";
			}

			if (selImgColor.equals(testImgColor)) {
				report.updateTestLog("Verify Selected Image Color",
						"The selected Image DOES NOT have background color : <b>" + color + "</b>", Status.FAIL);
			} else {
				report.updateTestLog("Verify Selected Image Color",
						"The selected Image has background color : <b>" + color + "</b>", Status.PASS);
			}

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

	public void verifySelVideoColor() {

		try {
			String color;

			clickElement(CMSArticlePage.addMedia, "Add Media");
			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			clickElement(CMSArticlePage.btnVids, "Videos");

			selectDropdownVal(CMSArticlePage.listMediaVideoSearchDate, "All", "Date");

			clickElement(CMSArticlePage.mediaVedioSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			waitForObjectVisible(CMSArticlePage.selVid);

			selectElement(CMSArticlePage.selVid1, "A Video");

			String selVidColor = driver.findElement(CMSArticlePage.selectedVid).getCssValue("background-color");
			System.out.println(selVidColor);

			String testVidColor = driver.findElement(CMSArticlePage.testVideo).getCssValue("background-color");
			System.out.println(testVidColor);

			if (selVidColor.equals("rgba(176, 196, 222, 1)")) {
				color = "Blue";
			} else {
				color = "Color Changed";
			}

			if (selVidColor.equals(testVidColor)) {
				report.updateTestLog("Verify Selected Video Background Color",
						"The selected Video DOES NOT have background color : <b>" + color + "</b>", Status.FAIL);
			} else {
				report.updateTestLog("Verify Selected Video Background Color",
						"The selected Video has background color : <b>" + color + "</b>", Status.PASS);
			}

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

	public void verifySelPictureColor() {

		try {

			String color;

			// String bolMedia = dataTable.getData("Media_Model", "Media");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
			waitForObjectVisible(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"));
			// String valContType = dataTable.getData("Media_Model", "Content
			// Type");
			// selectDropdownVal(MediaModelPage.lstComonListContentType,
			// valContType, "Content Type");
			clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"), "Picture - Select");

			// clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"),
			// "Picture - Select");

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			selectElement(CMSArticlePage.selImage1, "An Image");

			String selImgColor = driver.findElement(CMSArticlePage.selectedImg).getCssValue("background-color");
			System.out.println(selImgColor);

			String testImgColor = driver.findElement(CMSArticlePage.testImage).getCssValue("background-color");
			System.out.println(testImgColor);

			if (selImgColor.equals("rgba(176, 196, 222, 1)")) {
				color = "Blue";
			} else {
				color = "Color Changed";
			}

			if (selImgColor.equals(testImgColor)) {
				report.updateTestLog("Verify Selected Image Color",
						"The selected Image DOES NOT have background color : <b>" + color + "</b>", Status.FAIL);
			} else {
				report.updateTestLog("Verify Selected Image Color",
						"The selected Image has background color : <b>" + color + "</b>", Status.PASS);
			}

			clickElement(CMSArticlePage.btnSubmitImg, "Submit");

			wait(1000);
			driver.switchTo().parentFrame();

			WebElement element = driver.findElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			report.updateTestLog("Attach Image", "An Image is attached", Status.SCREENSHOT);

			// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
			// 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Image", "Image could not be added - Error : " + e.getMessage(), Status.FAIL);
		}

	}

	public void verifySelOnlyVideoColor() {

		try {
			String color;

			// String valContType = dataTable.getData("Media_Model", "Content
			// Type");
			// selectDropdownVal(MediaModelPage.lstComonListContentType,
			// valContType, "Content Type");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
			waitForObjectVisible(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"));
			clickElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"), "Video - Select");
			wait(3000);

			WebElement wfv = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wfv);

			// clickElement(CMSArticlePage.btnVids, "Videos");

			selectDropdownVal(CMSArticlePage.listVideoSearchDate, "All", "Date");

			clickElement(CMSArticlePage.videoSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			waitForObjectVisible(CMSArticlePage.selDirVid);

			selectElement(CMSArticlePage.selDirVid, "A Video");

			String selVidColor = driver.findElement(CMSArticlePage.selectedVid).getCssValue("background-color");
			System.out.println(selVidColor);

			String testVidColor = driver.findElement(CMSArticlePage.testVideo).getCssValue("background-color");
			System.out.println(testVidColor);

			if (selVidColor.equals("rgba(176, 196, 222, 1)")) {
				color = "Blue";
			} else {
				color = "Color Changed";
			}

			if (selVidColor.equals(testVidColor)) {
				report.updateTestLog("Verify Selected Video Background Color",
						"The selected Video DOES NOT have background color : <b>" + color + "</b>", Status.FAIL);
			} else {
				report.updateTestLog("Verify Selected Video Background Color",
						"The selected Video has background color : <b>" + color + "</b>", Status.PASS);
			}

			clickElement(CMSArticlePage.btnSubmitVid, "Submit");

			WebElement element1 = driver.findElement(By.xpath("//a[contains(@id,'-field-video-und-0-select')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			report.updateTestLog("Attach Video", "A Video is attached", Status.SCREENSHOT);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		} catch (Exception e) {
			report.updateTestLog("Add Video", "Video could not be added - Error : " + e.getMessage(), Status.FAIL);
		}

	}

	public void navigateToStaticPage() {

		try {
			navigateToSubMenu("Content", "Add content", "Static Page");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Static Page",
					"NOT Navigated to Static Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void validatePictureSearchKeyWord() {
		String valSearchKeyWord = dataTable.getData("Media_Model", "Search Key Words");
		boolean flag = true;
		String imgTitle = null;
		String imgCaption = null;
		String imgSource = null;
		String imgFullPhotoInfo = null;
		String imgLabel = null;
		int s;
		int i = 1;
		String bolMedia = dataTable.getData("Media_Model", "Media");

		goToImageOption();
		do {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			if (bolMedia.equals("No")) {
				waitForObjectVisible(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"));
				// String valContType = dataTable.getData("Media_Model",
				// "Content Type");
				// selectDropdownVal(MediaModelPage.lstComonListContentType,
				// valContType, "Content Type");
				clickElement(By.xpath("//a[contains(@id,'-field-picture-und-0-select')]"), "Picture - Select");
			} else {
				clickElement(CMSArticlePage.addMedia, "Add Media");
			}

			wait(3000);

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='mediaBrowser']"));
			driver.switchTo().frame(wf);

			clickElement(CMSArticlePage.btnImags, "Images");

			enterText(MediaModelPage.txtSearchKeyWords, valSearchKeyWord, "Search Key Words");

			clickElement(MediaModelPage.btnMediaImgSearch, "Search");

			waitForObjectInVisible(By.xpath("//div[@class='throbber']"));

			s = driver
					.findElements(By
							.xpath("//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_1']//div[@class='media-thumbnail']"))
					.size();
			System.out.println(s);
			/*
			 * if(s>12) { s = 12; }
			 */

			wait(1000);
			First: while (true) {
				imgLabel = driver.findElement(By
						.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_1']//div[@class='media-thumbnail']//label[@class='media-filename'])["
								+ i + "]"))
						.getText();
				imgLabel = imgLabel.toLowerCase();
				valSearchKeyWord = valSearchKeyWord.toLowerCase();
				if (imgLabel.contains(valSearchKeyWord)) {
					i = i + 1;
					flag = true;
					continue First;
				} else {
					selectElement(By.xpath("(//img[@typeof='foaf:Image'])[" + i + "]"), "An Image");
					i = i + 1;

					clickElement(CMSArticlePage.btnSubmitImg, "Submit");

					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

					clickElement(MediaModelPage.btnPictureEdit, "Picture - Edit");

					waitForObjectVisible(By.xpath("//div[@id='modalContent']"));

					/*
					 * WebElement wf1 = driver.findElement(By.xpath(
					 * "//iframe[@id='mediaStyleSelector']"));
					 * driver.switchTo().frame(wf1);
					 * 
					 * wait(1000);
					 */
					waitForObjectVisible(MediaModelPage.txtImgTitleText);
					imgTitle = driver.findElement(MediaModelPage.txtImgTitleText).getAttribute("value");

					imgCaption = driver.findElement(MediaModelPage.txtImgCaption).getAttribute("value");

					imgSource = driver.findElement(MediaModelPage.txtImgSource).getAttribute("value");

					// imgFullPhotoInfo =
					// driver.findElement(MediaModelPage.txtFullPhotoInfo).getAttribute("value");

					imgTitle = imgTitle.toLowerCase();
					imgCaption = imgCaption.toLowerCase();
					imgSource = imgSource.toLowerCase();
					// imgFullPhotoInfo = imgFullPhotoInfo.toLowerCase();

					if (imgTitle.contains(valSearchKeyWord) || imgCaption.contains(valSearchKeyWord) || imgSource
							.contains(valSearchKeyWord) /*
														 * || imgFullPhotoInfo.
														 * contains(
														 * valSearchKeyWord)
														 */) {
						flag = true;
						clickElement(MediaModelPage.btnPictureEdit_Cancel, "Cancel");
						clickElement(MediaModelPage.btnPictureRemove, "Remove");
						break First;
					} else {
						flag = false;
						break;
					}
				}
			}
		} while (i <= s);

		if (flag) {
			report.updateTestLog("Validate Search Key Words",
					"All Images displayed includes Search Key Word : <b>" + valSearchKeyWord + "</b>", Status.PASS);
		} else {
			report.updateTestLog("Validate Search Key Words",
					"All Images displayed DO NOT include Search Key Word : " + valSearchKeyWord, Status.FAIL);
		}
	}

	public void navigateToAppBlock() {

		try {
			navigateToSubMenu("Content", "Add content", "App Block");
		} catch (Exception e) {
			report.updateTestLog("Navigate To App Block",
					"NOT Navigated to App Block. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void navigateToCommonList() {

		try {
			navigateToSubMenu("Content", "Add content", "Common List");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Common List",
					"NOT Navigated to Common List. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void navigateToCover() {

		try {
			navigateToSubMenu("Content", "Add content", "Cover");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Cover Page",
					"NOT Navigated to Cover Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}
}
