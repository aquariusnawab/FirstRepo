package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class PinnedRefDocPage {
	public static final By txtEmail = By.id("edit-name");
	public static final By txtPass = By.id("edit-pass");

	public static final By btnSubmit = By.id("edit-submit");
	public static final By mnuDashboard = By.xpath(".//*[@id='tabs']//a[@href='/admin/workbench']");
	
	
	public static final By mnuContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/admin/content']");
	public static final By subMnuAddContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add']");
	//public static final By subMnuFrontPage = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add/front-page']");
	public static final By editNotes = By.xpath("//textarea[@id='edit-field-editorial-notes-und-0-value']");
	
	public static final By txtMsgLogTitle = By.id("edit-title");
	
	
	
	
	public static final By txtSource = By.xpath("//input[@id='edit-field-source-reference-und-0-target-id']");
	public static final By txtSourceExternal = By.xpath("//input[@id='edit-field-source-site-name-und-0-value']");
	public static final By txtSourceOverride = By.xpath("//input[@id='edit-field-article-cover-image-und-0-field-source-override-und-0-value']");
	public static final By urlExternalLink = By.xpath("//input[@id='edit-field-external-link-und-0-value']");
	
	//public static final By txtAnnouncements = By.id("edit-field-announcements-und-0-value");
	public static final By listboxPrimarySection = By.xpath(".//*[@id='edit-field-section-und-0-tid-select-1']");
	public static final By listboxPrimaryTopic = By.xpath("//select[@id='edit-field-section-und-0-tid-select-2']");
	public static final By listboxEditEvent = By.id("edit-event");
	public static final By listArticleType = By.xpath("//select[@id='edit-field-article-type-und']");
	public static final By listMediaVideoSearchDate = By.xpath("(//select[@id='edit-date'])[2]");
	public static final By listVideoSearchDate = By.xpath("(//select[@id='edit-date'])[1]");
	public static final By btnSave = By.xpath("(//li[@class='to-draft'])[2]");
	public static final By btnPublish = By.xpath("(//li[@class='to-published yes'])[2]");
	public static final By btnHiddenPublish = By.xpath("(//li[@class='yes to-hidden'])[2]");
	public static final By btnDelete = By.xpath("(//li[@class='no float-right red'])[2]");
	public static final By btnConfirmDelete = By.xpath("//input[@id='edit-submit']");
	public static final By btnSettings = By.xpath("//strong[text()='Settings']");
	public static final By btnContentEntry = By.xpath("//strong[text()='Content Entry']");
	
	public static final By addSections = By.xpath("//div[@id='edit_field_additional_sections_und_chosen']");
	public static final By addSecVal = By.xpath("//div[@id='edit_field_additional_sections_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By addTopics = By.xpath("//div[@id='edit_field_additional_topics_und_chosen']");
	public static final By addTopVal = By.xpath("//div[@id='edit_field_additional_topics_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By addSubTopics = By.xpath("//div[@id='edit_field_additional_sub_topics_und_chosen']");
	public static final By addSubTopVal = By.xpath("//div[@id='edit_field_additional_sub_topics_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By addArtLabels = By.xpath("//div[@id='edit_field_additional_article_labels_und_chosen']");
	public static final By addArtLabelVal = By.xpath("//div[@id='edit_field_additional_article_labels_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By addShows = By.xpath("//div[@id='edit_field_additional_shows_und_chosen']");
	public static final By addShowVal  = By.xpath("//div[@id='edit_field_additional_shows_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By anchors = By.xpath("//div[@id='edit_field_anchors_und_chosen']");
	public static final By anchorVal = By.xpath("//div[@id='edit_field_anchors_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By primaryArticlelabe = By.xpath("//div[@id='edit_field_article_label_und_chosen']");
	public static final By primaryArtLabVal = By.xpath("//div[@id='edit_field_article_label_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By show = By.xpath("//div[@id='edit_field_show_und_chosen']");
	public static final By showVal = By.xpath("//div[@id='edit_field_show_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By series = By.xpath("//div[@id='edit_field_series_und_chosen']");
	public static final By seriesVal = By.xpath("//div[@id='edit_field_series_und_chosen']//ul[@class='chosen-results']/li[2]");
	
	public static final By labels = By.xpath("(//li[@class='search-field'])[4]");
	public static final By storyLine = By.xpath("//div[@id='edit_field_storyline_und_chosen']");
	
	public static final By list = By.xpath("//select[@id='edit-field-section-und-0-tid']");
	
	
	//public static final By subMnuNbcMsgLog = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/admin/content/nbcnews_msg_logs']");
	public static final By subMnuNbcMsgLog = By.xpath(".//*[@id='admin-menu-menu']/li[1]/ul/li[7]/a");
	public static final By btnApply = By.id("edit-submit-nbcnews-msg-logs");
	public static final By editLink = By.xpath(".//*[@id='block-system-main']/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[5]/a");
	public static final By selSource = By.xpath("//select[@id='edit-field-source-reference-und']");
	public static final By addMedia = By.xpath("//a[@id='edit-field-body-recipe-und-0-value_media']");
	public static final By addWidget = By.xpath("//a[@id='edit-field-content-und-0-value_news_widgets']");
	public static final By btnPictureSelect =By.xpath("//a[@id='edit-field-article-cover-image-und-0-field-picture-und-0-select']");
	public static final By btnVideoSelect = By.xpath("//a[@id='edit-field-mpx-video-und-0-field-video-und-0-select']");
	public static final By btnImags = By.xpath("(//li[@class='ui-state-default ui-corner-top']/a)[1]");
	public static final By btnVids = By.xpath("(//li[@class='ui-state-default ui-corner-top']/a)[2]");
	public static final By mediaVedioSearch = By.xpath("(//input[@id='edit-submit-nbc-news-mpx-media-browser'])[2]");
	public static final By videoSearch = By.xpath("(//input[@id='edit-submit-nbc-news-mpx-media-browser'])[1]");
	//public static final By selImage = By.xpath("//img[@title='The Masters - Round One']");
	public static final By selImage = By.xpath("(//img[@typeof='foaf:Image'])[1]");
	//public static final By selVid = By.xpath("//img[@src='http://sys-cms.nbcnews.com/sites/newscms/files/styles/media_thumbnail/public/media-mpx/2017-05/n_maddow_mcfaul_170202.jpg?itok=VUv6M_MT']");
	public static final By selVid = By.xpath("(//img[@typeof='foaf:Image'])[14]");
	public static final By selDirVid = By.xpath("(//img[@typeof='foaf:Image'])[2]");
	public static final By btnSubmitImg = By.xpath("//a[@class='button button-yes fake-submit nbc_news_mpx_media_browser--media_browser_1 jquery-once-2-processed']");
	public static final By btnSubmitVid = By.xpath("//a[@class='button button-yes fake-submit nbc_news_mpx_media_browser--media_browser_2 jquery-once-2-processed']");
	
	public static final By btnSubmitImgConf = By.xpath("//a[@class='button fake-ok']");
	public static final By logMsg = By.xpath("//textarea[@id='edit-event-comment']");
	public static final By menuHome = By.xpath("//span[text()='Home']");
	
	//Radio Buttons
	public static final By rdChooseCvrImg = By.xpath("//input[@id='edit-field-article-cover-image-und-0-field-cover-image-type-und-normal']");
	
	//Frames
	public static final By frameChooseWidget  = By.xpath("//div[@id='widget-content-0']");
	
	//Links
	public static final By tabMostPopular  = By.linkText("Most Popular");
	public static final By leftMenuLiveVideo  = By.linkText("Live Video");
	public static final By lnkUnpublish = By.linkText("Unpublish");
	
	//Edit Boxes
	public static final By txtTaxonRef = By.xpath("//input[@id='edit-field-taxonomy-reference-und-0-target-id']");
	public static final By txtContent = By.xpath("//input[@id='edit-field-pinned-articles-und-0-target-id']");
	public static final By txtMenuObjRef = By.xpath("//input[@id='edit-field-menu-reference-und-0-target-id']");
	public static final By txtVideoRailTitle = By.xpath("//input[@id='edit-field-video-rail-element-und-0-field-video-rail-title-und-0-value']");
	public static final By txtMPXVideoFeedId = By.xpath("//input[@id='edit-field-video-rail-element-und-0-field-video-rail-id-und-0-value']");
	
	
	public static final By txtPinnedRefName = By.xpath("//input[@id='edit-name']");
	
	//Buttons
	public static final By btnSavePinRef = By.xpath("//input[@value='Save Pinning Reference']");
	public static final By btnDeletePinRef = By.xpath("//input[@value='Delete Pinning Reference']");
	public static final By btnInsertWidget = By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span[contains(text(),'Insert Widget')]");
	public static final By btnSearch = By.xpath("//input[@id='edit-submit-content']");
	public static final By btnAddAnotherItem = By.xpath("//input[@name='field_ingredients_line_und_0_field_ingredients_add_more']");
	public static final By btnAddNewSlide = By.xpath("//input[@id='edit-field-slide-und-actions-ief-add']");
	public static final By btnSelectSlideImage = By.xpath("//a[@id='edit-field-slide-und-form-field-picture-und-0-select']");
	public static final By btnCreateSlide = By.xpath(" //input[@id='edit-field-slide-und-form-actions-ief-add-save']");
	public static final By btnRemoveCoverImage = By.xpath("//a[@class='button remove']");
	
	//CMS Menu
	public static final By menuContent = By.linkText("Content");
	
	//Lists
	public static final By listArtTypeSearchCont = By.xpath("//div[@id='edit_field_article_type_value_chosen']");
	public static final By listPrepUnit = By.xpath("//select[@id='edit-field-prep-time-unit-und']");
	public static final By listCookUnit = By.xpath("//select[@id='edit-field-cooking-time-unit-und']");
	
	//CheckBox
	public static final By chkCreateAnotPinRef = By.xpath("//input[@id='edit-create-another']");
	public static final By chkAdsEnabled = By.xpath("//input[@id='edit-field-ads-enabled-und']");
	public static final By chkCommentsEnabled = By.xpath("//input[@id='edit-field-comments-enabled-und']");
	public static final By chkSearchable = By.xpath("//input[@id='edit-field-searchable-und']");
	public static final By chkGraphicContent = By.xpath("//input[@id='edit-field-slide-und-form-field-is-graphic-und']");
	
	//Clicks
	public static final By txtCategory = By.xpath("//div[@id='edit_field_category_und_chosen']");
	public static final By txtCuisine = By.xpath("//div[@id='edit_field_cuisine_und_chosen']");
	public static final By txtCourseType = By.xpath("//div[@id='edit_field_course_type_und_chosen']");
	
	//DropDown
	public static final By lstContentType = By.xpath("//select[@id='edit-type']");
	
	//Tabs
	public static final By tabHeadlineSummary = By.xpath("//fieldset[@id='node_slideshow_form_group_headlines']//a[@class='fieldset-title']");
}