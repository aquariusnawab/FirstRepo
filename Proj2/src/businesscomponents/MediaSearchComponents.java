package businesscomponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.lang.reflect.Array;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.hamcrest.core.StringContains;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
import com.sun.media.jfxmedia.Media;

import supportlibraries.DriverScript;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
import uimap.CMSArticlePage;
import uimap.FrontPage;
import uimap.MediaSearchPage;
import uimap.PinnedRefDocPage;
import uimap.RecipePage;
import uimap.SlideshowPage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class MediaSearchComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public MediaSearchComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	private static final int MAX_TIMEOUT = 180;

	public void invokeCMSApplication() {
		report.updateTestLog("Invoke CMS Application",
				"Invoke the CMS application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));
	}

	public void navigateToFindMedia() {

		try {
			navigateToSubMenu("Content", "Find Media");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Find Media",
					"NOT Navigated to Find Media. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void validatePhotoFileName() {
		String valPhotoFileName = dataTable.getData("MediaSearch_Page", "Photo File Name");
		String valType = dataTable.getData("MediaSearch_Page", "Type");

		String actName = null;
		Boolean flag = false;

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		enterText(MediaSearchPage.txtPhotoFileName, valPhotoFileName, "Photo File Name");

		clickElement(MediaSearchPage.btnSearch, "Search");

		List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
		First: for (int i = 1; i <= lw.size(); i++) {
			actName = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[2]/a"))
					.getText();
			actName = actName.toLowerCase();
			valPhotoFileName = valPhotoFileName.toLowerCase();
			if (actName.contains(valPhotoFileName)) {
				flag = true;
			} else {
				flag = false;
				break First;
			}
		}

		if (flag) {
			report.updateTestLog("Validate Photo File Name",
					"All searched results have the NAME whcih includes entered Photo File Name keyword : "
							+ valPhotoFileName,
					Status.PASS);
		} else {
			report.updateTestLog("Validate Photo File Name", "Found a result with Name : " + actName
					+ " which DOES NOT include the entered keyword : " + valPhotoFileName, Status.FAIL);
		}
	}

	public void validateSearchKeyWords() {
		String valSearchKeyWord = dataTable.getData("MediaSearch_Page", "Search Key Words");
		String valType = dataTable.getData("MediaSearch_Page", "Type");

		String actName = null;
		String fullText = null;
		Boolean flag = false;

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		enterText(MediaSearchPage.txtSearchKeyWord, valSearchKeyWord, "Search Key Words");

		clickElement(MediaSearchPage.btnSearch, "Search");

		List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
		First: for (int i = 1; i <= lw.size(); i++) {
			actName = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[2]/a"))
					.getText();
			clickElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[2]/a"), actName);
			fullText = driver.findElement(By.xpath("//div[@class='block-content clearfix']")).getText();
			System.out.println("fullllllllllllllllll" + fullText);
			fullText = fullText.toLowerCase();
			valSearchKeyWord = valSearchKeyWord.toLowerCase();
			if (fullText.contains(valSearchKeyWord)) {
				flag = true;
			} else {
				flag = false;
				break First;
			}
			driver.navigate().back();
		}

		if (flag) {
			report.updateTestLog("Validate Search Key Words",
					"All searched results have the data whcih includes entered keyword : " + valSearchKeyWord,
					Status.PASS);
		} else {
			report.updateTestLog("Validate Search Key Words", "Found a result with Name : " + actName
					+ " whose data DOES NOT include the entered keyword : " + valSearchKeyWord, Status.FAIL);
		}

	}

	public void validateTypeVideo() {
		String valType = dataTable.getData("MediaSearch_Page", "Type");

		String txtType = null;
		String txtID = null;
		Boolean flag = false;
		int colType = 0;
		int colID = 0;
		int i = 0;

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		clickElement(MediaSearchPage.btnSearch, "Search");

		List<WebElement> tabHead = driver.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
		for (int c = 0; c < tabHead.size(); c++) {
			if (tabHead.get(c).getText().equals("Type")) {
				colType = c + 1;
			}
			if (tabHead.get(c).getText().equals("ID")) {
				colID = c + 1;
			}
		}

		List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
		First: for (i = 1; i <= lw.size(); i++) {
			txtType = driver
					.findElement(
							By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colType + "]"))
					.getText();
			txtType = txtType.toLowerCase();
			txtID = driver
					.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colID + "]"))
					.getText();
			txtID = txtID.toLowerCase();
			System.out.println(txtType);
			System.out.println(txtID);
			if (txtType.contains("video") && txtID.contains("id") && txtID.contains("guid")) {
				flag = true;
			} else {
				flag = false;
				break First;
			}
		}

		if (flag) {
			report.updateTestLog("Validate Type Video", "All searched results are as per the selection, Type : Video",
					Status.PASS);
		} else {
			report.updateTestLog("Validate Type Video", i + " Row is not a video", Status.FAIL);
		}
	}

	public void validateMPXIDGUID() {
		String valType = dataTable.getData("MediaSearch_Page", "Type");

		String txtType = null;
		String txtID = null;
		String valGUID = null;
		Boolean flag = false;
		int colType = 0;
		int colID = 0;
		int i = 0;

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		clickElement(MediaSearchPage.btnSearch, "Search");

		List<WebElement> tabHead = driver.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
		for (int c = 0; c < tabHead.size(); c++) {
			if (tabHead.get(c).getText().equals("Type")) {
				colType = c + 1;
			}
			if (tabHead.get(c).getText().equals("ID")) {
				colID = c + 1;
			}
		}

		List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
		First: for (i = 1; i <= lw.size(); i++) {
			txtType = driver
					.findElement(
							By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colType + "]"))
					.getText();
			txtType = txtType.toLowerCase();
			txtID = driver
					.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colID + "]"))
					.getText();
			txtID = txtID.toLowerCase();
			System.out.println(txtType);
			System.out.println(txtID);
			if (txtType.contains("video") && txtID.contains("id") && txtID.contains("guid")) {
				valGUID = txtID.split("guid: ")[1];
				flag = true;
				break First;
			} else {
				flag = false;
			}
		}

		enterText(MediaSearchPage.txtMPXIDGUID, valGUID, "MPX ID / MPX GUID");

		clickElement(MediaSearchPage.btnSearch, "Search");

		lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
		First: for (i = 1; i <= lw.size(); i++) {
			txtType = driver
					.findElement(
							By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colType + "]"))
					.getText();
			txtType = txtType.toLowerCase();
			txtID = driver
					.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colID + "]"))
					.getText();
			txtID = txtID.toLowerCase();
			System.out.println(txtType);
			System.out.println(txtID);
			if (txtType.contains("video") && txtID.contains(valGUID)) {
				flag = true;
			} else {
				flag = false;
				break First;
			}
		}

		if (flag) {
			report.updateTestLog("Validate MPX ID / MPX GUID",
					"All searched results include searched GUID : " + valGUID, Status.PASS);
		} else {
			report.updateTestLog("Validate MPX ID / MPX GUID", i + " Row does NOT have searched GUID : " + valGUID,
					Status.FAIL);
		}
	}

	public void validateNBCVidBrand() {
		String valType = dataTable.getData("MediaSearch_Page", "Type");

		String actBrand = null;
		Boolean flag = false;
		int colBrand = 0;
		int i = 0;

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		String valBrandKeyWords = driver.findElement(MediaSearchPage.txtNBCVidBrandKeyWord).getText();
		String[] keyWord = valBrandKeyWords.replaceAll("or ", "").split(", ");
		First: for (String k : keyWord) {
			enterText(MediaSearchPage.txtNBCVidBrand, k.trim(), "NBC Video Brand");

			clickElement(MediaSearchPage.btnSearch, "Search");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Brand")) {
					colBrand = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
			if (lw.size() == 0) {
				report.updateTestLog("NBC Video Brand", "No Results found for NBC Video Brand : " + k, Status.WARNING);
				continue First;
			}

			for (i = 1; i <= lw.size(); i++) {
				actBrand = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colBrand + "]"))
						.getText();
				actBrand = actBrand.toLowerCase();
				k = k.toLowerCase();
				System.out.println(actBrand);
				System.out.println(k);
				if (actBrand.contains(k)) {
					flag = true;
				} else {
					flag = false;
					break First;
				}
			}

			if (flag) {
				report.updateTestLog("Validate NBC Video Brand",
						"All searched results include NBC Video Brand searched keyword : " + k, Status.PASS);
			} else {
				report.updateTestLog("Validate NBC Video Brand",
						i + " Row does NOT have NBC Video Brand searched keyword : " + k, Status.FAIL);
			}
		}

	}

	public void validateDate() {
		String valType = dataTable.getData("MediaSearch_Page", "Type");
		String valDate = dataTable.getData("MediaSearch_Page", "Date");

		String actDate = null;
		Boolean flag = false;
		int colUpldDate = 0;
		int i = 0;

		Calendar cal = Calendar.getInstance();
		Date d;

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String lmtDate = null;

		for (String vD : valDate.split(";")) {
			if (vD.equals("Last Two Months")) {
				cal.add(Calendar.DATE, -60);
				d = cal.getTime();
				lmtDate = dateFormat.format(d);
			}

			if (vD.equals("Last Year")) {
				cal.add(Calendar.YEAR, -1);
				d = cal.getTime();
				lmtDate = dateFormat.format(d);
			}

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			selectDropdownVal(MediaSearchPage.lstDate, vD, "Date");

			clickElement(MediaSearchPage.btnSearch, "Search");

			List<WebElement> tabHead = driver
					.findElements(By.xpath("//table[@class='views-table cols-9']/thead/tr/th"));
			for (int c = 0; c < tabHead.size(); c++) {
				if (tabHead.get(c).getText().equals("Upload date")) {
					colUpldDate = c + 1;
				}
			}

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));
			First: for (i = 1; i <= lw.size(); i++) {
				actDate = driver
						.findElement(By.xpath(
								"//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colUpldDate + "]"))
						.getText();
				actDate = actDate.split("-")[0].trim();
				if (actDate.compareTo(lmtDate) >= 0) {
					flag = true;
				} else {
					flag = false;
					break First;
				}

			}

			if (flag) {
				report.updateTestLog("Validate Media Date", "All searched results have Date range as selected : " + vD,
						Status.PASS);
			} else {
				report.updateTestLog("Validate Media Date", i + " Row does NOT have Date range as selected : " + vD,
						Status.FAIL);
			}
		}

	}

	public void validateReset() {
		String valType = dataTable.getData("MediaSearch_Page", "Type");
		String valDate = dataTable.getData("MediaSearch_Page", "Date");

		String bfrRowText;
		String aftrRowText;
		String sRowText;
		String bfrType;
		String bfrBrand;
		String bfrDate;
		String aftrType;
		String aftrBrand;
		String aftrDate;
		String sType;
		String sBrand;
		String sDate;

		loadPage();
		report.updateTestLog("Validate Reset", "Before RESET", Status.SCREENSHOT);

		bfrType = getDropdownSelVal(MediaSearchPage.listType);

		bfrBrand = driver.findElement(MediaSearchPage.txtNBCVidBrand).getAttribute("value");

		bfrDate = getDropdownSelVal(MediaSearchPage.lstDate);

		List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

		bfrRowText = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[1]")).getText();

		Map<String, String> bl = new HashMap<String, String>();
		bl.put("Type", bfrType);
		bl.put("Brand", bfrBrand);
		bl.put("Date", bfrDate);
		bl.put("Row Text", bfrRowText);

		selectDropdownVal(MediaSearchPage.listType, valType, "Type");

		selectDropdownVal(MediaSearchPage.lstDate, valDate, "Date");

		String valBrandKeyWords = driver.findElement(MediaSearchPage.txtNBCVidBrandKeyWord).getText();
		String[] keyWord = valBrandKeyWords.replaceAll("or ", "").split(", ");
		String k = keyWord[0];

		enterText(MediaSearchPage.txtNBCVidBrand, k.trim(), "NBC Video Brand");

		clickElement(MediaSearchPage.btnSearch, "Search");

		sType = getDropdownSelVal(MediaSearchPage.listType);
		sBrand = driver.findElement(MediaSearchPage.txtNBCVidBrand).getAttribute("value");
		sDate = getDropdownSelVal(MediaSearchPage.lstDate);
		lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

		sRowText = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[1]")).getText();

		Map<String, String> sl = new HashMap<String, String>();
		sl.put("Type", sType);
		sl.put("Brand", sBrand);
		sl.put("Date", sDate);
		sl.put("Row Text", sRowText);

		clickElement(MediaSearchPage.btnReset, "Reset");

		aftrType = getDropdownSelVal(MediaSearchPage.listType);

		aftrBrand = driver.findElement(MediaSearchPage.txtNBCVidBrand).getAttribute("value");

		aftrDate = getDropdownSelVal(MediaSearchPage.lstDate);

		lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

		aftrRowText = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[1]")).getText();

		Map<String, String> al = new HashMap<String, String>();
		al.put("Type", aftrType);
		al.put("Brand", aftrBrand);
		al.put("Date", aftrDate);
		al.put("Row Text", aftrRowText);

		if (bfrType.equals(aftrType) && bfrBrand.equals(aftrBrand) && bfrDate.equals(aftrDate)
				&& bfrRowText.equals(aftrRowText)) {
			report.updateTestLog("Validate Reset Values", "Before RESET values : <b>" + bl
					+ "</b>. \n During Search values : <b>" + sl + "</b>. \n After RESET values : <b>" + al + "</b>",
					Status.PASS);
			report.updateTestLog("Validate Reset", "All Fields are RESET", Status.SCREENSHOT);
		} else {
			report.updateTestLog("Validate Reset Values", "Before RESET values : <b>" + bl
					+ "</b>. \n During Search values : <b>" + sl + "</b>. \n After RESET values : <b>" + al + "</b>",
					Status.FAIL);
		}

	}

	public void validateMediaFieldsOrder() {
		String p1 = positionFinder(MediaSearchPage.listType, MediaSearchPage.txtSearchKeyWord);
		if (p1.equalsIgnoreCase("x smaller")) {
			report.updateTestLog("Verify Position", "Search Key Words is Right To Type", Status.PASS);
		} else {
			report.updateTestLog("Verify Position", "Search Key Words is NOT Right To Type", Status.FAIL);
		}

		p1 = positionFinder(MediaSearchPage.txtSearchKeyWord, MediaSearchPage.txtPhotoFileName);
		if (p1.equalsIgnoreCase("x smaller")) {
			report.updateTestLog("Verify Position", "Photo File Name is Right To Search Key Words", Status.PASS);
		} else {
			report.updateTestLog("Verify Position", "Photo File Name is NOT Right To Search Key Words", Status.FAIL);
		}

		p1 = positionFinder(MediaSearchPage.txtPhotoFileName, MediaSearchPage.txtMPXIDGUID);
		if (p1.equalsIgnoreCase("x smaller")) {
			report.updateTestLog("Verify Position", "MPX ID / MPX GUID is Right To Photo File Name", Status.PASS);
		} else {
			report.updateTestLog("Verify Position", "MPX ID / MPX GUID is NOT Right To Photo File Name", Status.FAIL);
		}

		p1 = positionFinder(MediaSearchPage.txtMPXIDGUID, MediaSearchPage.txtNBCVidBrand);
		if (p1.equalsIgnoreCase("x smaller")) {
			report.updateTestLog("Verify Position", "NBC Video Brand is Right To MPX ID / MPX GUID", Status.PASS);
		} else {
			report.updateTestLog("Verify Position", "NBC Video Brand is NOT Right To MPX ID / MPX GUID", Status.FAIL);
		}

		p1 = positionFinder(MediaSearchPage.listType, MediaSearchPage.lstDate);
		if (p1.equalsIgnoreCase("y smaller")) {
			report.updateTestLog("Verify Position", "Date is below To Type", Status.PASS);
		} else {
			report.updateTestLog("Verify Position", "Date is NOT below To Type", Status.FAIL);
		}

	}

	public void validateFindPhotos() {
		try {
			String valSearchKeyWord = dataTable.getData("MediaSearch_Page", "Search Key Words");
			String valType = dataTable.getData("MediaSearch_Page", "Type");
			String valPhotoFileName = dataTable.getData("MediaSearch_Page", "Photo File Name");
			String valDate = dataTable.getData("MediaSearch_Page", "Date");

			String txt1 = null;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();

			// Search Key Words
			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			enterText(MediaSearchPage.txtSearchKeyWord, valSearchKeyWord, "Search Key Words");

			clickElement(MediaSearchPage.btnSearch, "Search");

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Photos");

			enterText(MediaSearchPage.txtSearchKeyWord, valSearchKeyWord, "Search Key Words");

			clickElement(MediaSearchPage.btnPhotoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Photos Functionality",
						"Results Displayed are the same for Find Media & Find Photos page", Status.PASS);
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Search Key Words'</b> field has the same functionality in Find Media & Find Photos page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Search Key Words'</b> field DOES NOT have the same functionality in Find Media & Find Photos page",
						Status.FAIL);
			}

			a1.clear();
			a2.clear();

			// Photo File Name
			navigateToFindMedia();

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			enterText(MediaSearchPage.txtPhotoFileName, valPhotoFileName, "Photo File Name");

			clickElement(MediaSearchPage.btnSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Photos");

			enterText(MediaSearchPage.txtPhotoFileName, valPhotoFileName, "Photo File Name");

			clickElement(MediaSearchPage.btnPhotoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Photos Functionality",
						"Results Displayed are the same for Find Media & Find Photos page", Status.PASS);
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Photo File Name'</b> field has the same functionality in Find Media & Find Photos page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Photo File Name'</b> field DOES NOT have the same functionality in Find Media & Find Photos page",
						Status.FAIL);
			}

			a1.clear();
			a2.clear();

			// Date
			navigateToFindMedia();

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			selectDropdownVal(MediaSearchPage.lstDate, valDate, "Date");

			clickElement(MediaSearchPage.btnSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Photos");

			selectDropdownVal(MediaSearchPage.lstDate, valDate, "Date");

			clickElement(MediaSearchPage.btnPhotoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Photos Functionality",
						"Results Displayed are the same for Find Media & Find Photos page", Status.PASS);
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Date'</b> field has the same functionality in Find Media & Find Photos page", Status.PASS);
			} else {
				report.updateTestLog("Verify Find Photos Functionality",
						"<b>'Date'</b> field DOES NOT have the same functionality in Find Media & Find Photos page",
						Status.FAIL);
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Find Photos Functionality", "Exception occured : " + e.getMessage());
		}

	}

	public void validateFindVideos() {
		try {
			String valSearchKeyWord = dataTable.getData("MediaSearch_Page", "Search Key Words");
			String valType = dataTable.getData("MediaSearch_Page", "Type");
			String valDate = dataTable.getData("MediaSearch_Page", "Date");

			String txt1 = null;
			int colType = 0;
			int colID = 0;
			String valGUID = null;
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();

			// Search Key Words
			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			enterText(MediaSearchPage.txtSearchKeyWord, valSearchKeyWord, "Search Key Words");

			clickElement(MediaSearchPage.btnSearch, "Search");

			List<WebElement> lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			enterText(MediaSearchPage.txtSearchKeyWord, valSearchKeyWord, "Search Key Words");

			clickElement(MediaSearchPage.btnVideoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Videos Functionality",
						"Results Displayed are the same for Find Media & Find Videos page", Status.PASS);
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'Search Key Words'</b> field has the same functionality in Find Media & Find Videos page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'Search Key Words'</b> field DOES NOT have the same functionality in Find Media & Find Videos page",
						Status.FAIL);
			}

			a1.clear();
			a2.clear();

			// MPX ID / MPX GUID
			navigateToFindMedia();

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			clickElement(MediaSearchPage.btnSearch, "Search");

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

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			First: for (int i = 1; i <= lw.size(); i++) {
				String txtType = driver
						.findElement(By
								.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colType + "]"))
						.getText();
				txtType = txtType.toLowerCase();
				String txtID = driver
						.findElement(
								By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]/td[" + colID + "]"))
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

			enterText(MediaSearchPage.txtMPXIDGUID, valGUID, "MPX ID / MPX GUID");

			clickElement(MediaSearchPage.btnSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			enterText(MediaSearchPage.txtMPXIDGUID, valGUID, "MPX ID / MPX GUID");

			clickElement(MediaSearchPage.btnVideoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Videos Functionality",
						"Results Displayed are the same for Find Media & Find Videos page", Status.PASS);
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'MPX ID / MPX GUID'</b> field has the same functionality in Find Media & Find Videos page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'MPX ID / MPX GUID'</b> field DOES NOT have the same functionality in Find Media & Find Videos page",
						Status.FAIL);
			}

			a1.clear();
			a2.clear();

			// NBC Video Brand
			navigateToFindMedia();

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			String valBrandKeyWords = driver.findElement(MediaSearchPage.txtNBCVidBrandKeyWord).getText();
			String[] keyWord = valBrandKeyWords.replaceAll("or ", "").split(", ");
			String k = keyWord[0];

			enterText(MediaSearchPage.txtNBCVidBrand, k.trim(), "NBC Video Brand");

			clickElement(MediaSearchPage.btnSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			enterText(MediaSearchPage.txtNBCVidBrand, k.trim(), "NBC Video Brand");

			clickElement(MediaSearchPage.btnVideoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Videos Functionality",
						"Results Displayed are the same for Find Media & Find Videos page", Status.PASS);
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'NBC Video Brand'</b> field has the same functionality in Find Media & Find Videos page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'NBC Video Brand'</b> field DOES NOT have the same functionality in Find Media & Find Videos page",
						Status.FAIL);
			}

			a1.clear();
			a2.clear();

			// Date
			navigateToFindMedia();

			selectDropdownVal(MediaSearchPage.listType, valType, "Type");

			selectDropdownVal(MediaSearchPage.lstDate, valDate, "Date");

			clickElement(MediaSearchPage.btnSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a1.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}

			navigateToSubMenu("Content", "Find Media", "Find Videos");

			selectDropdownVal(MediaSearchPage.lstDate, valDate, "Date");

			clickElement(MediaSearchPage.btnVideoSearch, "Search");

			lw = driver.findElements(By.xpath("//table[@class='views-table cols-9']/tbody/tr"));

			for (int i = 1; i <= lw.size(); i++) {
				txt1 = driver.findElement(By.xpath("//table[@class='views-table cols-9']/tbody/tr[" + i + "]"))
						.getText();
				a2.add(txt1.replaceAll("\\r\\n|\\r|\\n", ""));
			}
			System.out.println(a1);
			System.out.println(a2);
			if (a1.equals(a2)) {
				report.updateTestLog("Verify Find Videos Functionality",
						"Results Displayed are the same for Find Media & Find Videos page", Status.PASS);
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'Date'</b> field has the same functionality in Find Media & Find Videos page", Status.PASS);
			} else {
				report.updateTestLog("Verify Find Videos Functionality",
						"<b>'Date'</b> field DOES NOT have the same functionality in Find Media & Find Videos page",
						Status.FAIL);
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Find Videos Functionality", "Exception occured : " + e.getMessage());
		}

	}

	public void verifyMediaDateDef() {
		String valDate = dataTable.getData("MediaSearch_Page", "Date Default");

		verifyDropdownSel(MediaSearchPage.lstDate, valDate, "Date");
	}

	public void verifyTypeDefSel() {
		String valType = dataTable.getData("MediaSearch_Page", "Type Default");

		verifyDropdownSel(MediaSearchPage.listType, valType, "Media Type");
	}

}