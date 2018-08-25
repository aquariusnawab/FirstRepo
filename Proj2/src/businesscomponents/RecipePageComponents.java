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
import uimap.RecipePage;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class RecipePageComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";
	private static final String REGISTER_USER_DATA = "RegisterUser_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public RecipePageComponents(ScriptHelper scriptHelper) {
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

	public void navigateToRecipePage() {

		try {
			navigateToSubMenu("Content", "Add content", "Recipe");
		} catch (Exception e) {
			report.updateTestLog("Navigate To Recipe Page",
					"NOT Navigated to Recipe Page. Exception Occured : " + e.getMessage(), Status.FAIL);
		}

	}

	public void addContentForRecipePage() throws InterruptedException, AWTException {

		try {
			String title;
			waitForObjectVisible(CMSArticlePage.txtTitle);

			if (dataTable.getData("Recipe", "Recipe Headline").contains("AutoTitle")) {
				title = generateDynamicNames();
				dataTable.putData("Recipe", "Recipe Headline", title);
			} else {
				title = dataTable.getData("Recipe", "Recipe Headline");
			}

			String sourceURL = dataTable.getData("Recipe", "Source URL");
			String srcDisName = dataTable.getData("Recipe", "Source Display Name");
			String selCategory = dataTable.getData("Recipe", "Category");
			String selCuisine = dataTable.getData("Recipe", "Cuisine");
			String selCourseType = dataTable.getData("Recipe", "Course Type");
			String valServSize = dataTable.getData("Recipe", "Serving Size");
			String valCalories = dataTable.getData("Recipe", "Calories");
			String valYield = dataTable.getData("Recipe", "Yield");
			String valChefNotes = dataTable.getData("Recipe", "Chefs Notes");
			String valPrepAmount = dataTable.getData("Recipe", "Preparation Amount");
			String valCookAmount = dataTable.getData("Recipe", "Cooking Amount");
			String selPrepUnit = dataTable.getData("Recipe", "Preparation Unit");
			String selCookUnit = dataTable.getData("Recipe", "Cooking Unit");
			String valTitleForIngred = dataTable.getData("Recipe", "Title For Ingredients");
			String valIngredAmount = dataTable.getData("Recipe", "Ingredients Amount");
			String valIngredUnit = dataTable.getData("Recipe", "Ingredients Unit");
			String valIngredName = dataTable.getData("Recipe", "Ingredients Name");
			String valIngredDesc = dataTable.getData("Recipe", "Ingredients Description");
			String valSummary = dataTable.getData("Recipe", "Summary");
			String valLogMsg = dataTable.getData("Recipe", "Log Message");

			String socMediaHeadline = dataTable.getData("Recipe", "SocialMediaHeadline");
			String coverHeadline = dataTable.getData("Recipe", "CoverHeadline");
			String seoHeadline = dataTable.getData("Recipe", "SEO Headline");

			String primarySection = dataTable.getData("Recipe", "PrimarySection");
			String primaryTopic = dataTable.getData("Recipe", "PrimaryTopic");
			String byline = dataTable.getData("Recipe", "Byline");

			enterText(RecipePage.txtTitle, title, "Recipe Headline");

			enterText(RecipePage.txtByline, byline, "Byline");

			enterText(RecipePage.txtSEOHeadline, seoHeadline, "SEO Headline");

			enterText(RecipePage.socMediaHead, socMediaHeadline, "Social Media Headline");

			enterText(RecipePage.coverHeadline, coverHeadline, "Cover Headline");

			enterText(RecipePage.txtURL, sourceURL, "URL");

			enterText(RecipePage.txtSourceDispName, srcDisName, "Source Display Name");

			clickElement(RecipePage.txtCategory, "Category");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + selCategory + "')]"),
					selCategory);

			clickElement(RecipePage.txtCuisine, "Cuisine");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + selCuisine + "')]"),
					selCuisine);

			clickElement(RecipePage.txtCourseType, "Course Type");
			selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + selCourseType + "')]"),
					selCourseType);

			enterText(RecipePage.txtServingSize, valServSize, "Serving Size");

			enterText(RecipePage.txtCalories, valCalories, "Calories");

			enterText(RecipePage.txtYield, valYield, "Yield");

			WebElement wf = driver.findElement(By.xpath("//iframe[@id='edit-field-chefs-notes-und-0-value_ifr']"));
			driver.switchTo().frame(wf);

			enterText(RecipePage.txtChefNotes, valChefNotes, "Chefs Notes");

			driver.switchTo().parentFrame();

			enterText(RecipePage.txtPrepAmount, valPrepAmount, "Preparation Amount");

			enterText(RecipePage.txtCookAmount, valCookAmount, "Cooking Amount");

			selectDropdownVal(RecipePage.listPrepUnit, selPrepUnit, "Preparation Unit");

			selectDropdownVal(RecipePage.listCookUnit, selCookUnit, "Cooking Unit");

			enterText(RecipePage.txtTitleForIngrd, valTitleForIngred, "Title For Ingredients");

			enterText(RecipePage.txtIngredAmount, valIngredAmount, "Ingredients Amount");

			enterText(RecipePage.txtIngredUnit, valIngredUnit, "Ingredients Unit");

			enterText(RecipePage.txtIngredName, valIngredName, "Ingredients Name");

			enterText(RecipePage.txtIngredDescription, valIngredDesc, "Ingredients Description");

			addMediaImage();

			enterText(RecipePage.txtSummary, valSummary, "Summary");

			clickSelectPicture();

			addImage();

			clickSelectVideo();

			addVideo();

			enterText(RecipePage.txtLog, valLogMsg, "Log Message");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

			wait(3000);

			clickElement(CMSArticlePage.btnSettings, "Settings");

			String addSecVal = dataTable.getData("Recipe", "Additional Sections");
			String addTopVal = dataTable.getData("Recipe", "Additional Topics");
			String addSubTopVal = dataTable.getData("Recipe", "Additional Sub Topics");
			String addArtLabVal = dataTable.getData("Recipe", "Additional Article Labels");
			String addShowVal = dataTable.getData("Recipe", "Additional Shows");
			String anchorsVal = dataTable.getData("Recipe", "Anchors");
			String primArtLabVal = dataTable.getData("Recipe", "Primary Article Label");
			String showVal = dataTable.getData("Recipe", "Show");
			String seriesVal = dataTable.getData("Recipe", "Series");

			selectDropdownVal(RecipePage.listboxPrimarySection, primarySection, "Primary Section");

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

			clickElement(RecipePage.addArtLabels, "Additional Article Labels");
			addArtLabVal = driver.findElement(RecipePage.addArtLabelVal).getText();
			selectElement(RecipePage.addArtLabelVal, addArtLabVal);

			clickElement(RecipePage.addShows, "Additional Shows");
			addShowVal = driver.findElement(RecipePage.addShowVal).getText();
			selectElement(RecipePage.addShowVal, addShowVal);

			clickElement(RecipePage.anchors, "Anchors");
			anchorsVal = driver.findElement(RecipePage.anchorVal).getText();
			selectElement(RecipePage.anchorVal, anchorsVal);

			clickElement(RecipePage.primaryArticlelabe, "Primary Article Label");
			primArtLabVal = driver.findElement(RecipePage.primaryArtLabVal).getText();
			selectElement(RecipePage.primaryArtLabVal, primArtLabVal);

			clickElement(RecipePage.show, "Show");
			showVal = driver.findElement(RecipePage.showVal).getText();
			selectElement(RecipePage.showVal, showVal);

			clickElement(RecipePage.series, "Series");
			seriesVal = driver.findElement(RecipePage.seriesVal).getText();
			selectElement(RecipePage.seriesVal, seriesVal);

			wait(2000);

			// To handle chrome pop-up
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.onbeforeunload = function() {};");

		} catch (Exception e) {
			throw new FrameworkException("Add content for Recipe page", "Exception occured : " + e.getMessage());
		}

	}

	public void addAnotherIngredient() {
		String valIngredAmount = dataTable.getData("Recipe", "Ingredients Amount");
		String valIngredUnit = dataTable.getData("Recipe", "Ingredients Unit");
		String valIngredName = dataTable.getData("Recipe", "Ingredients Name");

		clickElement(RecipePage.btnAddAnotherItem, "Add Another Item");

		waitForObjectInVisible(By.xpath("//div[@class='throbber']"));
		wait(2000);

		int noAmount = driver.findElements(By.xpath("//input[contains(@id,'-field-ingredient-amount')]")).size();
		enterText(By.xpath("(//input[contains(@id,'-field-ingredient-amount')])[" + noAmount + "]"), valIngredAmount,
				"Ingredients Amount");

		int noUnit = driver.findElements(By.xpath("//input[contains(@name,'field_ingredient_unit')]")).size();
		enterText(By.xpath("(//input[contains(@name,'field_ingredient_unit')])[" + noUnit + "]"), valIngredUnit,
				"Ingredients Unit");

		int noName = driver.findElements(By.xpath("//input[contains(@id,'-field-ingredient-name')]")).size();
		enterText(By.xpath("(//input[contains(@id,'-field-ingredient-name')])[" + noName + "]"), valIngredName,
				"Ingredients Name");
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

	public void verifyRecipeCreatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Recipe", "Recipe Headline");
			String s = "Recipe " + title + " has been created.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Recipe Created Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyRecipeUpdatedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Recipe", "Recipe Headline");
			String s = "Recipe " + title + " has been updated.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Recipe Updated Message", "Exception occured : " + e.getMessage());
		}
	}

	public void verifyRecipeDeletedMsg() {

		try {
			loadPage();
			WebElement w = driver.findElement(By.xpath("//div[@class='messages status']"));
			String actErrMsg = w.getText().replaceAll("\\r\\n|\\r|\\n", " ");
			String title = dataTable.getData("Recipe", "Recipe Headline");
			String s = "Recipe " + title + " has been deleted.";
			String[] sp = s.split(";");
			for (String msg : sp) {

				if (actErrMsg.contains(msg)) {
					report.updateTestLog("Verify Message", msg + " is displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify Message", msg + " is NOT displayed", Status.FAIL);
				}
			}

		} catch (Exception e) {
			throw new FrameworkException("Verify Recipe Deleted Message", "Exception occured : " + e.getMessage());
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

	public void validateRecipeMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("Recipe", "Recipe Headline");
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

	public void validateNoMsgLog() throws InterruptedException, ParseException {
		navigateToNBCNewsMsgLOG();

		waitForObjectVisible(CMSArticlePage.txtMsgLogTitle);
		WebElement txtTitle = driver.findElement(CMSArticlePage.txtMsgLogTitle);

		String title = dataTable.getData("General_Data", "ArticleHeadline");
		String expAction = dataTable.getData("General_Data", "Action");
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

	public void searchAndClickExistingRecipe() {
		try {
			String contentType = dataTable.getData("Recipe", "Content Type");

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
						dataTable.putData("Recipe", "Recipe Headline", title);
						clickElement(By
								.xpath("//table[@class='views-table sticky-enabled cols-8 tableheader-processed sticky-table']/tbody/tr["
										+ i + "]/td[3]/a"),
								title);
						break First;
					}
				}
			} else {
				report.updateTestLog("Search And Click Recipe", "NO Existing Recipe. Need to create one.",
						Status.WARNING);
			}
		} catch (Exception e) {
			throw new FrameworkException("Search And Click Recipe", "Exception Occured : " + e.getMessage());
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickPublishedArticle() {
		String articleType = dataTable.getData("General_Data", "Article Type");

		clickElement(CMSArticlePage.menuContent, "Content");

		clickElement(CMSArticlePage.listArtTypeSearchCont, "Article Type");

		selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + articleType + "')]"),
				articleType);

		clickElement(CMSArticlePage.btnSearch, "Search");

		int rowCount = driver
				.findElements(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr"))
				.size();
		if (rowCount >= 2) {
			First: for (int i = 2; i <= rowCount; i++) {
				String statusPublish = driver.findElement(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
								+ i + "]/td[7]"))
						.getText().trim();
				if (statusPublish.equals("published")) {
					String title = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"))
							.getText();
					dataTable.putData("General_Data", "ArticleHeadline", title);
					clickElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"),
							title);
					break First;
				}
			}
		} else {
			report.updateTestLog("Search And Click Article", "NO Existing Article. Need to create one.",
					Status.WARNING);
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickUnPublishedArticle() {
		String articleType = dataTable.getData("General_Data", "Article Type");

		clickElement(CMSArticlePage.menuContent, "Content");

		clickElement(CMSArticlePage.listArtTypeSearchCont, "Article Type");

		selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + articleType + "')]"),
				articleType);

		clickElement(CMSArticlePage.btnSearch, "Search");

		int rowCount = driver
				.findElements(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr"))
				.size();
		if (rowCount >= 2) {
			First: for (int i = 2; i <= rowCount; i++) {
				String statusPublish = driver.findElement(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
								+ i + "]/td[7]"))
						.getText().trim();
				if (statusPublish.equals("unpublished")) {
					String title = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"))
							.getText();
					dataTable.putData("General_Data", "ArticleHeadline", title);
					clickElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"),
							title);
					break First;
				}
			}
		} else {
			report.updateTestLog("Search And Click Article", "NO Existing Article. Need to create one.",
					Status.WARNING);
		}
		// table[@class='views-table sticky-enabled cols-10
		// tableheader-processed sticky-table']//tr[2]/td[4]

	}

	public void searchAndClickDraftArticle() {
		String articleType = dataTable.getData("General_Data", "Article Type");

		clickElement(CMSArticlePage.menuContent, "Content");

		clickElement(CMSArticlePage.listArtTypeSearchCont, "Article Type");

		selectElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + articleType + "')]"),
				articleType);

		clickElement(CMSArticlePage.btnSearch, "Search");

		int rowCount = driver
				.findElements(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr"))
				.size();
		if (rowCount >= 2) {
			First: for (int i = 2; i <= rowCount; i++) {
				String statusPublish = driver.findElement(By
						.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
								+ i + "]/td[7]"))
						.getText().trim();
				if (statusPublish.equals("draft")) {
					String title = driver.findElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"))
							.getText();
					dataTable.putData("General_Data", "ArticleHeadline", title);
					clickElement(By
							.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tr["
									+ i + "]/td[4]/a"),
							title);
					break First;
				}
			}
		} else {
			report.updateTestLog("Search And Click Article", "NO Existing Article. Need to create one.",
					Status.WARNING);
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
			clickElement(RecipePage.addMedia, "Add Media");

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

			WebElement element = driver.findElement(RecipePage.addMedia);
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
			clickElement(RecipePage.addMedia, "Add Media");
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

			WebElement element1 = driver.findElement(RecipePage.addMedia);
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
