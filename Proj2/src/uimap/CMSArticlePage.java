package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class CMSArticlePage {
	public static final By txtEmail = By.id("edit-name");
	public static final By txtPass = By.id("edit-pass");

	public static final By btnSubmit = By.id("edit-submit");
	public static final By mnuDashboard = By.xpath(".//*[@id='tabs']//a[@href='/admin/workbench']");
	
	
	public static final By mnuContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/admin/content']");
	public static final By subMnuAddContent = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add']");
	//public static final By subMnuFrontPage = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/node/add/front-page']");
	public static final By editNotes = By.xpath("//textarea[@id='edit-field-editorial-notes-und-0-value']");
	public static final By txtTitle = By.id("edit-title-field-und-0-value");
	public static final By txtMsgLogTitle = By.id("edit-title");
	public static final By txtSEOHeadline = By.id("edit-field-seo-headline-und-0-value");
	public static final By socMediaHead = By.xpath("//input[@id='edit-field-social-media-headline-und-0-value']");
	public static final By coverHeadline = By.xpath("//input[@id='edit-field-article-cover-headline-und-0-value']");
	
	public static final By txtSource = By.xpath("//input[@id='edit-field-source-reference-und-0-target-id']");
	public static final By txtSourceExternal = By.xpath("//input[@id='edit-field-source-site-name-und-0-value']");
	public static final By txtSourceOverride = By.xpath("//input[@id='edit-field-article-cover-image-und-0-field-source-override-und-0-value']");
	public static final By urlExternalLink = By.xpath("//input[@id='edit-field-external-link-und-0-value']");
	public static final By txtSummary = By.xpath("//textarea[@id='edit-field-summary-und-0-value']");
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
	public static final By addSections = By.xpath("(//li[@class='search-field'])[1]");
	public static final By addTopics = By.xpath("(//li[@class='search-field'])[2]");
	public static final By addSubTopics = By.xpath("(//li[@class='search-field'])[3]");
	public static final By addArtLabels = By.xpath("(//li[@class='search-field'])[4]");
	public static final By addShows = By.xpath("(//li[@class='search-field'])[5]");
	public static final By anchors = By.xpath("(//li[@class='search-field'])[6]");
	public static final By primaryArticlelabe = By.xpath("//div[@id='edit_field_article_label_und_chosen']");
	public static final By show = By.xpath("//div[@id='edit_field_show_und_chosen']");
	public static final By series = By.xpath("//div[@id='edit_field_series_und_chosen']");
	public static final By labels = By.xpath("(//li[@class='search-field'])[4]");
	public static final By storyLine = By.xpath("//div[@id='edit_field_storyline_und_chosen']");
	
	public static final By list = By.xpath("//select[@id='edit-field-section-und-0-tid']");
	
	
	//public static final By subMnuNbcMsgLog = By.xpath(".//*[@id='admin-menu-menu']//a[@href='/admin/content/nbcnews_msg_logs']");
	public static final By subMnuNbcMsgLog = By.xpath(".//*[@id='admin-menu-menu']/li[1]/ul/li[7]/a");
	public static final By btnApply = By.id("edit-submit-nbcnews-msg-logs");
	public static final By editLink = By.xpath(".//*[@id='block-system-main']/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[5]/a");
	public static final By selSource = By.xpath("//select[@id='edit-field-source-reference-und']");
	public static final By addMedia = By.xpath("//a[@id='edit-field-content-und-0-value_media']");
	public static final By addWidget = By.xpath("//a[@id='edit-field-content-und-0-value_news_widgets']");
	public static final By btnPictureSelect =By.xpath("//a[@id='edit-field-article-cover-image-und-0-field-picture-und-0-select']");
	public static final By btnVideoSelect = By.xpath("//a[@id='edit-field-mpx-video-und-0-field-video-und-0-select']");
	public static final By btnImags = By.xpath("(//li[@class='ui-state-default ui-corner-top']/a)[1]");
	public static final By btnVids = By.xpath("(//li[@class='ui-state-default ui-corner-top']/a)[2]");
	public static final By mediaVedioSearch = By.xpath("(//input[@id='edit-submit-nbc-news-mpx-media-browser'])[2]");
	public static final By videoSearch = By.xpath("(//input[@id='edit-submit-nbc-news-mpx-media-browser'])[1]");
	//public static final By selImage = By.xpath("//img[@title='The Masters - Round One']");
	public static final By selImage = By.xpath("(//img[@typeof='foaf:Image'])[1]");
	public static final By selImage1 = By.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_1']//div[@class='media-thumbnail'])[1]");
	public static final By testImage = By.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_1']//div[@class='media-thumbnail'])[2]");
	public static final By selectedImg = By.xpath("//div[@class='media-item selected']");
	//public static final By selVid = By.xpath("//img[@src='http://sys-cms.nbcnews.com/sites/newscms/files/styles/media_thumbnail/public/media-mpx/2017-05/n_maddow_mcfaul_170202.jpg?itok=VUv6M_MT']");
	public static final By selVid = By.xpath("(//img[@typeof='foaf:Image'])[14]");
	public static final By selDirVid = By.xpath("(//img[@typeof='foaf:Image'])[2]");
	public static final By selVid1 = By.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail'])[1]");
	public static final By testVideo = By.xpath("(//div[@id='media-tab-nbc_news_mpx_media_browser--media_browser_2']//div[@class='media-thumbnail'])[2]");
	public static final By selectedVid = By.xpath("//div[@class='media-item selected']");
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
	public static final By txtWidgetLivVidCMSDisplayName  = By.xpath("//div[@id='widget-live-video-0']//input[@id='widget_display_name']");
	public static final By txtByline = By.xpath("//input[@id='edit-field-attached-byline-und-0-target-id']");
	public static final By txtWidgetLivVidMPXID = By.xpath("//div[@id='widget-live-video-0']//input[@id='widget_mpx-id']");
	public static final By txtWidgetEllentubeCMSDisplayName  = By.xpath("//div[@id='widget-ellentube']//input[@id='widget_display_name']");
	public static final By txtWidgetEllentuveVideoID = By.xpath("//div[@id='widget-ellentube']//input[@id='widget_video-id']");
	public static final By txtWidgetNBCAffVidCMSDisplayName  = By.xpath("//div[@id='widget-nbc-affiliates-video']//input[@id='widget_display_name']");
	public static final By txtWidgetNBCAffVidVideoSource  = By.xpath("//div[@id='widget-nbc-affiliates-video']//input[@id='widget_video-source']");
	public static final By txtWidgetNBCVideoCMSDisplayName  = By.xpath("//div[@id='widget-nbc-video']//input[@id='widget_display_name']");
	public static final By txtWidgetNBCVideoVideoID = By.xpath("//div[@id='widget-nbc-video']//input[@id='widget_video-id']");
	public static final By txtWidgetVideoBarCMSDisplayName  = By.xpath("//div[@id='widget-video-bar']//input[@id='widget_display_name']");
	public static final By txtWidgetVideoBarPlaylistLab  = By.xpath("//div[@id='widget-video-bar']//input[@id='widget_playlist-label']");
	public static final By txtWidgetVideoBarMPXPlayID = By.xpath("//div[@id='widget-video-bar']//input[@id='widget_mpx-playlist-id']");
	
	//Buttons
	public static final By btnInsertWidget = By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span[contains(text(),'Insert Widget')]");
	public static final By btnSearch = By.xpath("//input[@id='edit-submit-content']");
	
	//CMS Menu
	public static final By menuContent = By.linkText("Content");
	
	//Lists
	public static final By listArtTypeSearchCont = By.xpath("//div[@id='edit_field_article_type_value_chosen']");
	
	//CheckBox
	public static final By chkAdsEnabled = By.xpath("//input[@id='edit-field-ads-enabled-und']");
	public static final By chkCommentsEnabled = By.xpath("//input[@id='edit-field-comments-enabled-und']");
	public static final By chkSearchable = By.xpath("//input[@id='edit-field-searchable-und']");
}