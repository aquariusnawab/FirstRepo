package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class FrontPage {
	
	
	//Radio Buttons
	public static final By rdNormalDay = By.xpath("//input[@id='edit-nlid-1']");
	public static final By rdBigStory = By.xpath("//input[@id='edit-nlid-2']");
	
	//Buttons
	public static final By btnContinue = By.xpath("//input[@id='edit-submit']");
	public static final By btnSave = By.xpath("(//li[@class='to-draft'])[2]");
	public static final By btnApply = By.xpath("//input[@id='edit-submit-publisher-workbench-recent-content-overridden']");
	
	//Edit Box
	public static final By txtFrontPageTitle = By.xpath("//input[@id='edit-title']");
	public static final By txtLogMsg = By.xpath("//textarea[@id='edit-event-comment']");
	public static final By txtCMSSearch = By.xpath("//input[@id='node-id-input']");
	
	//Links
	public static final By lnkContentSearch = By.xpath("//a[@class='references-dialog-activate search-dialog']");
	
	//DropDown
	public static final By lstContentType = By.xpath("//select[@id='edit-type']");
	
	//Tab
	public static final By tabEditDraft = By.xpath("//a[text()='Edit Draft']");
	
	
	
}