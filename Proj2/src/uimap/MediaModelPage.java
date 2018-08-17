package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class MediaModelPage {
	
	
	//Radio Buttons
	public static final By rdNormalDay = By.xpath("//input[@id='edit-nlid-1']");
	public static final By rdBigStory = By.xpath("//input[@id='edit-nlid-2']");
	
	//Buttons
	public static final By btnImgCancel = By.xpath("//a[@class='button fake-cancel']");
	public static final By btnImgCancel1 = By.xpath("//a[@class='button button-no fake-cancel nbc_news_mpx_media_browser--media_browser_1 jquery-once-2-processed']");
	public static final By btnVidCancel = By.xpath("//a[@class='button button-no fake-cancel nbc_news_mpx_media_browser--media_browser_2 jquery-once-2-processed']");
	public static final By btnMediaImgSearch = By.xpath("(//input[@id='edit-submit-nbc-news-mpx-media-browser'])[1]");
	public static final By btnContinue = By.xpath("//input[@id='edit-submit']");
	public static final By btnSave = By.xpath("(//li[@class='to-draft'])[2]");
	public static final By btnApply = By.xpath("//input[@id='edit-submit-publisher-workbench-recent-content-overridden']");
	public static final By btnPictureEdit = By.xpath("//a[contains(@id,'-field-picture-und-0-edit')]");
	public static final By btnVideoEdit = By.xpath("//a[contains(@id,'-field-video-und-0-edit')]");
	public static final By btnPictureRemove = By.xpath("//a[contains(@id,'-field-picture-und-0-remove')]");
	public static final By btnVideoRemove = By.xpath("//a[contains(@id,'-field-video-und-0-remove')]");
	public static final By btnPictureEdit_Cancel = By.xpath("//a[contains(@id,'edit-cancel')]");
	public static final By btnAddNewCoverBlock = By.xpath("//input[@id='edit-field-article-blocks-und-actions-ief-add']");
	
	//Edit Box
	public static final By txtFullPhotoInfo = By.xpath("//textarea[contains(@id,'edit-field-full-photo-info-')]");
	public static final By txtImgSource = By.xpath("//input[contains(@name,'field_source[')]");
	public static final By txtImgCaption = By.xpath("//textarea[contains(@id,'edit-field-caption')]");
	public static final By txtImgTitleText = By.xpath("//input[contains(@id,'edit-field-file-image-title-text')]");
	public static final By txtSearchKeyWords = By.xpath("//input[@name='field_full_photo_info_value']");
	public static final By txtImgFileName = By.xpath("(//input[@name='filename'])[1]");
	public static final By txtVidTitleKeywrds = By.xpath("//input[@id='edit-field-mpx-title-value']");
	public static final By txtVidMPXGUID = By.xpath("//input[@id='edit-field-mpx-guid-value']");
	public static final By txtVidMPXID = By.xpath("//input[@name='field_mpx_id_value']");
	public static final By txtVidMPXDecrp = By.xpath("//input[@id='edit-field-mpx-description-value']");
	public static final By txtVidBrand = By.xpath("//input[@id='edit-field-brand-value']");
	public static final By txtRetrVidMPXGUID = By.xpath("//input[contains(@id,'edit-field-mpx-guid-')]");
	public static final By txtRetrVidMPXID = By.xpath("//input[contains(@id,'edit-field-mpx-id-')]");
	public static final By txtRetrVidBrand = By.xpath("//input[contains(@id,'edit-field-brand-')]");
	public static final By txtRetrVidMPXDecrp = By.xpath("//textarea[contains(@id,'edit-field-mpx-description-')]");
		
	
	public static final By txtFrontPageTitle = By.xpath("//input[@id='edit-title']");
	public static final By txtLogMsg = By.xpath("//textarea[@id='edit-event-comment']");
	public static final By txtCMSSearch = By.xpath("//input[@id='node-id-input']");
	
	//Links
	public static final By lnkContentSearch = By.xpath("//a[@class='references-dialog-activate search-dialog']");
	
	//DropDown
	public static final By lstContentType = By.xpath("//select[@id='edit-type']");
	public static final By lstImgSortBy = By.xpath("(//select[@id='edit-sort-by'])[1]");
	public static final By lstImgOrder = By.xpath("(//select[@id='edit-sort-order'])[1]");
	public static final By  lstVidOrder = By.xpath("(//select[@id='edit-sort-order'])[2]");
	public static final By lstImgDate = By.xpath("(//select[@id='edit-date'])[1]");
	public static final By lstVidDate = By.xpath("(//select[@id='edit-date'])[2]");
	public static final By lstComonListContentType = By.xpath("//select[contains(@id,'-curated-list-content-und-0-field-curated-list-instance-type-und')]");
	public static final By lstCoverBlocks = By.xpath("//select[contains(@id,'-article-blocks-und-actions-bundle')]");
	
	//Tab
	public static final By tabEditDraft = By.xpath("//a[text()='Edit Draft']");
	
	
	
}