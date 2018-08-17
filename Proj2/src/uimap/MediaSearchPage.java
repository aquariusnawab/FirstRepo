package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class MediaSearchPage {
	public static final By txtEmail = By.id("edit-name");
	public static final By txtPass = By.id("edit-pass");

	public static final By btnSubmit = By.id("edit-submit");
	public static final By mnuDashboard = By.xpath(".//*[@id='tabs']//a[@href='/admin/workbench']");
	
	
	public static final By mnuContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/admin/content']");
	public static final By subMnuAddContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add']");
	//public static final By subMnuFrontPage = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add/front-page']");
	public static final By editNotes = By.xpath("//textarea[@id='edit-field-editorial-notes-und-0-value']");
	
	public static final By txtMsgLogTitle = By.id("edit-title");
	
	
	
	
	
	//Radio Buttons
	public static final By rdChooseCvrImg = By.xpath("//input[@id='edit-field-article-cover-image-und-0-field-cover-image-type-und-normal']");
	
	//Frames
	public static final By frameChooseWidget  = By.xpath("//div[@id='widget-content-0']");
	
	//Links
	public static final By tabMostPopular  = By.linkText("Most Popular");
	
	
	//Edit Boxes
	public static final By txtPhotoFileName = By.xpath("//input[@id='edit-filename']");
	public static final By txtSearchKeyWord = By.xpath("//input[@id='edit-field-full-photo-info-value']");
	public static final By txtMPXIDGUID = By.xpath("//input[@name='field_mpx_id_value']");
	public static final By txtNBCVidBrand = By.xpath("//input[@name='field_brand_value']");
	
	
	//Buttons
	public static final By btnSearch = By.xpath("//input[@id='edit-submit-content-files']");
	public static final By btnReset = By.xpath("//input[@id='edit-reset']");
	public static final By btnPhotoSearch = By.xpath("//input[@id='edit-submit-content-files-images']");
	public static final By btnVideoSearch = By.xpath("//input[@id='edit-submit-content-files-videos']");
	
	//CMS Menu
	public static final By menuContent = By.linkText("Content");

	//CheckBox
	public static final By chkCreateAnotPinRef = By.xpath("//input[@id='edit-create-another']");
	
	
	//Clicks
	public static final By txtCategory = By.xpath("//div[@id='edit_field_category_und_chosen']");
	
	
	//DropDown
	public static final By lstContentType = By.xpath("//select[@id='edit-type']");
	public static final By listType = By.xpath("//select[@id='edit-type']");
	public static final By lstDate = By.xpath("//select[@id='edit-date']");
	
	//Tabs
	public static final By tabHeadlineSummary = By.xpath("//fieldset[@id='node_slideshow_form_group_headlines']//a[@class='fieldset-title']");
	
	//AppText
	public static final By txtNBCVidBrandKeyWord = By.xpath("//div[@class='description']");
}