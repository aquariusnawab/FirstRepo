package uimap;
import org.openqa.selenium.By;

public class coverPage {
	
	public static final By topStoryImg = By
			.xpath(".//*[@id='top-stories-section']//div[contains(@class,'img-container img-container_')]/img");
	public static final By topStoryHeadline = By
			.xpath(".//*[@id='top-stories-section']//div[contains(@class,'col-md-8')]//h3");
	public static final By topStorySection = By.xpath(".//*[@id='top-stories-section']//div[contains(@class,'flag_section-name')]/a");
	public static final By topVideoHeadline1 = By.xpath(".//*[@id='top-stories-section']//div[contains(@class,'col-md-4')]//h3");
	public static final By topStoryAd1 = By.xpath("//div[@class='ad-container align-center']//div[contains(@id,'google_ads_iframe')]");

	public static final By topStoryAd = By.linkText("advertisement");
	
	public static final By topVideoHeadline = By.
			xpath(".//*[@id='video-section']/div/div[2]/div[1]/div/div[1]/div/div[1]/a/div[2]/h3");
	
	public static final By hdrHomeLink = By.xpath("//a[contains(text(),'Home')]");
	public static final By hdrTopVideos = By.xpath("//a[contains(text(),'Top Videos')]");
	/*public static final By hdrDecisionLink = By.xpath("//a[contains(text(),'Decision 2016')]");
	public static final By hdrLogo = By.id("js-brand-logo");*/
	public static final By hdrDecisionLink = By.xpath("//a[contains(text(),'Decision 2016')]");
	//Code added to absorb the header changes - Anand
	public static final By hdrLogo = By.xpath("//a[@class='slim-white-site-header__site-logo inline-block']");
	public static final By hdrLinks = By.xpath("//ul[@class='nav-bar hidden-xs inline-block']//a");
	
	public static final By hdrOnGoing = By.cssSelector("li.nav-sub-title.nav-sub-title_storyline");
	public static final By hdrStoryLine1 = By.xpath("//body[@id='top']/header/div/div/div/div/div/div/div[2]/ul/li[2]/a");
	public static final By hdrStoryLine2 = By.xpath("//body[@id='top']/header/div/div/div/div/div/div/div[2]/ul/li[3]/a");
	public static final By homeLink = By.cssSelector("svg");
	
	public static final By articleFacebook = By.cssSelector("span.social-list_txt");
	public static final By featuredArticle = By.xpath("//div[@id='adSpotlight1']/div/div[2]/a/h3");
	public static final By videoPage = By.cssSelector("span.playlist_thumb-title. ");
	
	//Breaking News Section 
	public static final By headingBreakingNews 		= By.xpath(".//*[@id='breakingnews-section']//div[@class='item-heading_group-wrapper']//a[@href='http://www.breakingnews.com']");//.//*[@id='breakingnews-section']//div[@class='item-heading_group-wrapper']//a[@href='http://www.breakingnews.com']");
	public static final By headingAroundWeb    		= By.xpath(".//*[@id='breakingnews-section']//span[@class='uppercase font-small']");
	public static final By lnkfirst 				= By.xpath(".//*[@id='breakingnews-section']//div[@data-dy-article='1']/a/h3");
	public static  String allLinksinBreakingNews	= ".//*[@id='breakingnews-section']//div[@class='row row_panel']//a//h3";
	public static final By lnkTimeAgo 				= By.xpath(".//*[@id='breakingnews-section']//div[@data-dy-article='1']//time[@class='timeago']");
	public static final By lnkNewsReportedBy		= By.xpath(".//*[@id='breakingnews-section']//div[@data-dy-article='1']//div[@class='flag flag_default flag_external']/a");
	public static final By lnkPoweredBy				= By.xpath(".//*[@id='breakingnews-section']//div[@class='col-lg-12']//span[@class='uppercase']");	
	public static final By headerBreakingNewsPage	= By.xpath(".//*[@id='site-header']//h4[@class='logo-text']");
			
	//Featured Section
	public static final By headingFeaturedSection			= By.xpath(".//*[@id='featured-section']//h2[contains(@class,'item-heading')]");
	public static final By imageFeaturedSection				= By.xpath(".//*[@id='adSpotlight1']/div[contains(@class,'story-link story-link_sm placeholder')]//a/img");
	//Previous Xpath = By.xpath(".//*[@id='adSpotlight1']/div[@class='story-link story-link_sm placeholder']//a/img");
	public static final By linkFeaturedArticle				= By.xpath(".//*[@id='adSpotlight1']/div[@class='story-link story-link_sm placeholder']//a/h3");
	//Changed by sravani
	public static final By linkFeaturedReported				= By.xpath(".//*[@id='adSpotlight1']//div[contains(@class,'flag flag_default')]/a");
			//Previous Xapth By.xpath(".//*[@id='adSpotlight1']//div[@class='flag flag_default flag_section-name']/a");
	//public static final By cpmore1							= By.xpath("//div[@class='js-scroll-navbar']/ul/li[12]/button");	
	
	public static final By advNews				= By.xpath(".//*[@id='top-stories-section']//div[contains(@class,'ad-container-topmulti')]");
	public static final By advInvestigations	= By.xpath(".//*[@id='news-section']//div[contains(@class,'ad-container-boxfront2')]");
	public static final By advWeirdNews			= By.xpath(".//*[@id='news-section']//div[@data-placement='boxfront3']");
	public static final By advAsianAmerica		= By.xpath(".//*[@id='news-section']//div[@data-placement='boxfront4']");
	public static final By advUSNews			= By.xpath(".//*[@id='news-section']//div[contains(@class,'ad-container-boxfront1')]");

	//Code done by Akhila
	
	public static final By TopAd=By.xpath("(//div[@class='ad-label'])[1]");
	public static final By TopBannerAd=By.xpath("//div[@class='ad-content ad-sm ad-md ad-lg topbanner ad-content_row']");
	//Search
			public static final By sSearchicon = By.xpath(".//*[@id='top']/header//i[@class='fa fa-search']");
					//previous Xpath = By.xpath("//*[@id='top']/header/div/div[1]/div/div/div/div[2]/div/ul/li/a/i[1]");
			public static final By sSearchtextbox = By.xpath("//div[contains(@class,'col-lg-1')]//*[@id='q']");
					//Previous xpath = By.xpath("//div[@class='col-lg-1']//*[@id='q']");
		//	public static final By sSearchclclick =By.xpath("//div[@class='col-lg-1']//input[@class='screen-reader-txt']");
			
			//Banner Ad
			public static final By breakingnewssection =By.xpath(".//*[@id='breakingnews-section']//h2[@class='item-heading item-heading_group item-heading_group-lg']");
			public static final By TopAdbanner =By.xpath("//div[@class='google_ads_iframe_/2620/nbcnews/cover_7__container__']");
			public static final By Adbanner1 =By.xpath("//div[@class='ad-content ad-sm ad-md ad-lg boxfront1']");
			public static final By Adbanner =By.xpath("//div[@class='ad-content ad-sm ad-md ad-lg topmulti']");
			public static final By cpAdvertisment = By.xpath("(//div[@class='ad-label'])[2]");
			
			//Sign up
			public static final By signupmail =By.xpath("//input[@class='c-input-text j-email']");
			public static final By signupbtn =By.xpath("//input[@class='j-submit c-button c-submit']");
			public static final By signuptext =	By.xpath("//a/h2[@class='item-heading item-heading_sm item-heading_title']");
			
			//Social Sharing Icons
			public static final By follownbc =By.xpath("(//h2[@class='item-heading item-heading_sm item-heading_title'])[2]");
			public static final By sfacebook =By.xpath("//i[@class='fa fa-lg fa-facebook']");
			public static final By sTwitter =By.xpath("//i[@class='fa fa-lg fa-twitter']");
			public static final By sgoogleplus =By.xpath("//i[@class='fa fa-lg fa-google-plus']");
			public static final By sRssFeeds =By.xpath("//i[@class='fa fa-lg fa-rss']");
			
			//Featured Section
			public static final By featuredheading = By.xpath("//h2[contains(text(),'Featured')]");
			public static final By featuredLink =By.xpath(".//*[@id='adSpotlight1']/div[1]/div[2]/a/h3");
			public static final By featuredImgLink =By.xpath(".//*[@id='adSpotlight1']/div[1]/div[1]/a/img");
			public static final By featuredsmallLink =By.xpath(".//*[@id='adSpotlight1']/div[1]/div[2]/div/div/a");
			
			
			//More Related Links
			//public static final By cpmore = By.xpath("//ul[@class='nav-bar nav-bar_sm dyother dyMonitor']//button[@class='js-toggle btn btn_alt btn_md btn_site-menu']");
			//public static final By cpmoreclose =By.xpath("//button[@class='js-toggle btn btn_alt btn_md btn_site-menu is-active']");
			
			//Code added to accomodate the header changes for More sections
			public static final By cpmore = By.xpath("//button[@class='js-toggle btn btn_alt btn_md btn_site-menu inline-block']");
			public static final By cpmoreclose =By.xpath("//button[@class='js-toggle btn btn_alt btn_md btn_site-menu inline-block is-active']");
			
			
			public static final By cpMoreExp =By.xpath("//nav[@class='dropdown dropdown_site-nav is-open']");
			public static final By moreExpSections =By.xpath("//h4[contains(text(),'Sections')]");
			public static final By moreExpTopOngoing =By.xpath("//h4[contains(text(),'Top Ongoing')]");
			public static final By moreExpTv =By.xpath("//h4[contains(text(),'TV')]");
			public static final By moreExpFeatured =By.xpath("//h4[contains(text(),'Featured')]");
			public static final By moreExpmultimedia =By.xpath("//h4[contains(text(),'Multimedia')]");
			public static final By moreExpMorefromNbc =By.xpath("//h4[contains(text(),'More From NBC')]");
			public static final By moreParent1 =By.xpath("//ul[@class='dyother dyMonitor']");
			/*public static final By moreTopOngoing1 =By.xpath("//div[@class='site-header js-scroll-header']//div[@class='col-xs-6 col-sm-4 col']/div/ul[1]/li[1]/a");
			public static final By moreTopOngoing2 =By.xpath("//div[@class='site-header js-scroll-header']//div[@class='col-xs-6 col-sm-4 col']/div/ul[1]/li[2]/a");
			*/
			//Changes done by Akhila for ongoing sections in VerifyMoreLinks4
			public static final By moreTopOngoing1 =By.xpath("//a[@data-target='#ongoing-01-section']");
			public static final By moreTopOngoing2 =By.xpath("//a[@data-target='#ongoing-02-section']"); 
			//Advertisements by Akhila
			public static final By advSports = By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3 col-sans-gutters']");
			public static final By advPolitics = By.xpath("//*[@id='politics-section']//div[@class='col-sm-6 col-md-4 col-lg-3']");
			public static final By advPopCulture = By.xpath("//*[@id='pop-culture-section']//div[@class='col-sm-6 col-md-4 col-lg-3']");
			public static final By advLifestyle = By.xpath("//*[@id='lifestyle-section']//div[@class='col-sm-6 col-md-4 col-lg-3']");
			
			
			// Changes done by sravani for Advertisement
			public static final By advSportsBanner 		= By.xpath("//*[@id='sports-section']//a[text()='advertisement']");
			public static final By advPoliticsBanner 	= By.xpath("//*[@id='politics-section']//a[text()='advertisement']");
			public static final By advPopCultureBanner	= By.xpath("//*[@id='pop-culture-section']//a[text()='advertisement']");
			public static final By advLifestyleBanner	= By.xpath("//*[@id='lifestyle-section']//a[text()='advertisement']");
			
			//SectionName
		
			public static final By hdrUS = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'U.S.')]");
			public static final By hdrWorld = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'World')]");
			public static final By hdrLocal = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Local')]");
			public static final By hdrPolitics = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Politics')]");
			public static final By hdrHealth = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Health')]");
			public static final By hdrTech = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Tech')]");
			public static final By hdrScience = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Science')]");
			public static final By hdrPopCulture = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Pop Culture')]");
			public static final By hdrBusiness = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Business')]");
			public static final By hdrInvetigations = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Investigations')]");
			public static final By hdrSports = By.xpath("//li[@class='dyother dyMonitor']//a[contains(text(),'Sports')]");
			
		// New Changes - Social Sharing icons
			
			public static final By cpfb = By.xpath("//a[@title='Share on Facebook']");
			public static final By cptweet = By.xpath("//a[@title='Tweet']");
			public static final By cpgoogleplus = By.xpath("//a[@title='Share on Google+']");
			public static final By cpEmail = By.xpath("//a[@title='Email']");
			
			// Footer Links - Sravani
			public static final By footerLinks = By.xpath(".//*[@id='top']/footer//a");
			
			
}
