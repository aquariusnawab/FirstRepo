package uimap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageDashboardComponents {

	//TC1
	
	public static final By titleName = By.xpath("//input[@id='edit-title']");
	public static final By searchButton = By.xpath("//input[@id='edit-submit-content']");
	public static final By tableHeader = By.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr//td[4]");
	public static final By tableLink = By.xpath("(//a[text()='edit'])[1]");
	public static final By resultText = By.xpath("//input[@id='edit-title-field-und-0-value']");
	//tbody//tr[1]//td[@class='views-field views-field-title']//a[1]
	
	
	//TC2
	public static final By startDate = By.xpath("//input[@id='edit-published-date-min-datepicker-popup-0']");
	public static final By endDate = By.xpath("//*[@id='edit-published-date-max-datepicker-popup-0']");
	public static final By updatedDate = By.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr//td[9]");
	public static final By settingTab = By.xpath("//strong[text()='Settings' or text()='Professional']");
	public static final By updatedDateInArticle =By.xpath("//input[@id='edit-field-published-date-und-0-value-datepicker-popup-0']");
	public static final By firstRowDate = By.xpath("(//span[@class='date-display-single'])[1]");
	//span[@class='date-display-single']
	
	
	//TC3
	public static final By publishStatusDropdown = By.xpath("//div[@id='edit_state_chosen']//a//span");
	public static final By publicStatusresults = By.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr//td[7]");
	public static final By dropdownInput = By.xpath("//*[@id='edit_state_chosen']/div/div/input");
	public static final By rivisionLink = By.xpath("//a[text()='Revisions']");
	public static final By dropdown = By.xpath("//select[@id='edit-event']");
	public static final By titleLink1 =By.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr[2]//td[4]/a");
	public static final By titleLink = By.xpath("(//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr/td[contains(text(),'Article')]//preceding-sibling::td[contains(@class,'views-field views-field-title')]/a)[1]");
	public static final By homePageLink =By.xpath("//a[text()='Home']");
	public static final By linkLockedByUser = By.xpath("//div[@class='messages warning']//a[text()='here']");
	
	
	//TC4
	public static final By byLineDropdown = By.xpath("//div[@id='edit_field_attached_byline_target_id_entityreference_filter_1_chosen']//a//span");
	public static final By byLineDropdownInput = By.xpath("//div[@id='edit_field_attached_byline_target_id_entityreference_filter_1_chosen']//input");
	public static final By byLineDropdownSelect = By.xpath("//div[@id='edit_field_attached_byline_target_id_entityreference_filter_1_chosen']//ul[@class='chosen-results' and ..//text()='   Andy Dehnart  ']"); 
	public static final By byLineDropdownvalueInArticle1 = By.xpath("//input[@id='edit-field-attached-byline-und-0-target-id']");
	public static final By byLineDropdownSuggestions = By.xpath("//ul[@class='chosen-results']//li//em");
	//public static final By byLineDropdownSuggestions1 = By.xpath("//ul[@class='chosen-results']//li");
	public static final By byLineDropdownvalueInArticle = By.xpath("//input[contains(@name , 'field_attached_byline')]");

	
	//TC5
	
	//dropdown
	public static final By listSectionsAndTopics = By.xpath("//*[@id='edit_term_node_tid_depth_chosen']/a/span");
	public static final By listSectionsAndTopicsName = By.xpath("//label[@for='edit-term-node-tid-depth']");
	public static final By listSectionsAndTopicsNameInput = By.xpath("//div[@id='edit_term_node_tid_depth_chosen']//input");
	public static final By listPrimarySection = By.xpath("//Select[@id='edit-field-section-und-0-tid-select-1']");
	public static final By listPrimaryTopic = By.xpath("//Select[@id='edit-field-section-und-0-tid-select-2']");
	public static final By listAdditonalSections = By.xpath("//*[@id='edit_field_additional_sections_und_chosen']/ul/li[@class='search-choice']/span");
	public static final By listAdditionalTopic = By.xpath("//*[@id='edit_field_additional_topics_und_chosen']/ul/li[@class='search-choice']/span");
	public static final By listAdditionalSubTopics = By.xpath("//*[@id='edit_field_additional_sub_topics_und_chosen']/ul/li[@class='search-choice']/span");
	
	//TC6
	public static final By listContentType = By.xpath("//div[@id='edit_type_chosen']/a/span");
	public static final By listContentTypeInput = By.xpath("//div[@id='edit_type_chosen']//div[@class='chosen-search']/input");
	public static final By tableTypeInSearchRes = By.xpath("//table[@class='views-table sticky-enabled cols-10 tableheader-processed sticky-table']//tbody//tr//td[5]");
	
	//TC7
	public static final By listShows = By.xpath("//div[@id='edit_show_chosen']/a");
	public static final By listShowsInput = By.xpath("//div[@id='edit_show_chosen']//div[@class='chosen-search']/input");
	public static final By listShowsInArticle = By.xpath("//div[@id='edit_field_show_und_chosen']/a/span");
	
	//TC8
	public static final By listArticleType = By.xpath("//div[@id='edit_field_article_type_value_chosen']/a/span");
	public static final By listArticleType1 = By.xpath("//div[@id='edit_field_article_type_value_chosen']");
	public static final By listArticleTypeInput = By.xpath("//div[@id='edit_field_article_type_value_chosen']//input");
	public static final By listArticleValueInArticle = By.xpath("//*[@id='edit-field-article-type-und']");
	public static final By listArticleValueInArticle1 = By.xpath("//*[@id='edit-field-article-type-und']//option[@selected='selected']");

	//TC9
	public static final By listSeriesValue  =By.xpath("//div[@id='edit_serie_chosen']/a/span");
	public static final By listSeries  =By.xpath("//div[@id='edit_serie_chosen']");
	public static final By listSeriesInput = By.xpath("//div[@id='edit_serie_chosen']//input");
	public static final By listSeriesInArticle = By.xpath("//div[@id='edit_field_series_und_chosen']/a/span");
	
	//TC10
	public static final By listLabel = By.xpath("//div[@id='edit_label_chosen']//a//span");
	public static final By listLabelInput = By.xpath("//div[@id='edit_label_chosen']//input");
	public static final By labelInArticle = By.xpath("//div[@id='edit_field_article_label_und_chosen']/a/span");
	public static final By labelSuggestions = By.xpath("//div[@id='edit_label_chosen']//ul[@class='chosen-results']//li");
	public static final By labelSecoInArticle = By.xpath("//div[@id='edit_field_additional_article_labels_und_chosen']//li[@class='search-choice']");
	
	//TC11
	public static final By listSource = By.xpath("//*[@id='edit_field_source_reference_chosen']//a//span");
	public static final By listSourceInput = By.xpath("//*[@id='edit_field_source_reference_chosen']//input");
	public static final By sourceInArticle = By.xpath("//input[@id='edit-field-source-reference-und-0-target-id']");
	
	//TC12
	public static final By dropdwnBulkOperations = By.xpath("//select[@id='edit-operation']");
//  public static final By nodeIDAll = By.xpath("(//td[@class='views-field views-field-nid'])");
    public static final By ChkBox2 = By.xpath("//input[@id='edit-views-bulk-operations-0']");
    public static final By btnExecute = By.xpath("//*[@id='edit-submit--2']");
    public static final By btnConfirm = By.xpath("//input[@id='edit-submit']");
    public static final By titleNames = By.xpath("//tbody//tr//td[4]//a");
    public static final By NodeIDs = By.xpath("//tbody//tr//td[3]");
    public static final By progessBar = By.xpath("//*[@id='updateprogress']/div[@class='bar']");
//  public static final By titleNameOfAll = By.xpath("(//td[@class='views-field views-field-title'])//a");
    public static final By nodeId1 = By.xpath("(//td[@class='views-field views-field-nid'])[1]");
    public static final By nodeId2 = By.xpath("(//td[@class='views-field views-field-nid'])[2]");
    public static final By nodeIdsInMessageLog = By.xpath("//td[@class='views-field views-field-entity-id']");
    public static final By StatusInMessageLog = By.xpath("//td[@class='views-field views-field-action']");
    public static final By btnPublish = By.xpath("(//li[@class='to-published yes'])[1]");
    public static final By msgSuccess = By.xpath("//div[@class='messages status']");
    public static final By msgError = By.xpath("//div[@class='messages error']");
	public static final By messagelogValueActionDelete = By.xpath("//td[@class='views-field views-field-action' and contains(text() ,'delete')]/following-sibling::td[3]");


	//TC13
	public static final By inputCMS = By.xpath("//*[@id='node-id-input']");
	
	//TC14
	
	
	//TC15
	//TC16
	//TC17
	
	//TC18
	public static final By noResltsFound = By.xpath("//div[@class='view-empty']/p");
	
	//TC19
	public static final By IDno = By.xpath("(//th[@class='views-field views-field-nid'])[2]//a");
	public static final By titleHeaderName = By.xpath("(//th[@class='views-field views-field-title'])[2]//a");
	public static final By type = By.xpath("(//th[@class='views-field views-field-type'])[2]//a");
	public static final By createdBy = By.xpath("(//th[@class='views-field views-field-name'])[2]//a");
	public static final By pulishStatus = By.xpath("(//th[@class='views-field views-field-state'])[2]");
	public static final By updated = By.xpath("(//th[@class='views-field views-field-changed active'])[2]//a");
	public static final By publishDate = By.xpath("(//th[@class='views-field views-field-field-published-date'])[2]//a");
	public static final By operations = By.xpath("(//th[@class='views-field views-field-edit-node'])[2]");
	//TC20
	public static final By btnReset = By.xpath("//input[@id='edit-reset']");

	//TC21
	public static final By manageFronts = By.xpath("//*[@id='edit-vocab-type']");
	public static final By goToPage2 = By.xpath("//a[@title='Go to page 2']");
	public static final By dropdownVocabType = By.xpath("//td[@class='views-field views-field-field-section-type']");
	
	
	
	//Scrap
	public static final By imgUploadLink = By.xpath("(//img[@alt='Add media'])[2]"); 
	public static final By iframeUploadItems = By.xpath("//iframe[@id='mediaBrowser']");
	public static final By  linkImages = By.xpath("//a[@title='Images']");
	public static final By imgSlected   = By.xpath("//*[@id='media-item-1204350']/div");
	public static final By imgSel = By.xpath("//div[@class='media-item selected']");
	public static final By test = By.xpath("(//div[@class='media-item'])[1]");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
