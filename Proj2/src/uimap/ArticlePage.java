package uimap;

import org.openqa.selenium.By;

public class ArticlePage {

	/*
	 * public static final By featurearticlehierarchyLink =
	 * By.xpath("//div[@class='breadcrumb_container']"); public static final By
	 * featureLink = By.xpath("//div[@class='breadcrumb_container']//a"); public
	 * static final By sectionFeatureLinks =
	 * By.xpath("//div[@class='article-body']//div[@class='media-body']//h3");
	 * public static final By sectionArticleTimeStamp =
	 * By.xpath("(.//*[@class='article-flags']//time)[1]"); //
	 * (//div[@class='article-body']//div[@class='media-body']//div[contains(@
	 * class,'flag flag_default')]/a)[3] //
	 * (//div[@class='article-body']//div[contains(@class,'story-link')]//img[1]
	 * )[3]
	 */
	public static final By ArtAdv = By.xpath(".//div[@class='ad-content ad-sm ad-md ad-lg boxflex']");
	public static final By ArtFFAdv = By.xpath(".//div[@class='ad-content ad-sm ad-md ad-lg topmulti']");
	public static final By lnkArtheirarchy = By.xpath("//div[@class='breadcrumb_container']");
	public static final By lnkArtheirarchy1 = By.xpath("//div[@class='breadcrumb_container']/a[1]");
	public static final By lnkArtheirarchy2 = By.xpath("//div[@class='breadcrumb_container']/a[2]");
	public static final By lnkArtFFheirarchy1 = By
			.xpath("//div[@class='breadcrumb_container']//a[@class='breadcrumb']");
	public static final By lnkArtFFheirarchyarrw = By.xpath("//i[@class='fa fa-angle-right']");
	public static final By lnkArtFFheirarchy2 = By
			.xpath("//h1[@class='item-heading item-heading_group item-heading_group-lg']");

	public static final By lnkArtShare = By
			.xpath("//span[@class='article_meta-title article_meta-title-sm social-list_article-title']");
	public static final By lnkArtfbShare = By.xpath("//a[@class='js-sharearticle facebook']");
	public static final By lnkArttwitterShare = By.xpath("//a[@class='js-sharearticle twitter']");
	public static final By lnkArtgoogleplusShare = By.xpath("//a[@class='js-sharearticle google-plus']");
	public static final By lnkArtmailShare = By.xpath("//a[@class='js-sharearticle']");
	public static final By lnkArtprint = By.xpath("//button[@class='js-sharearticle print']");
	public static final By lnkArtcommenticon = By.xpath("//i[@class='fa fa-comment-o']");
	public static final By lnkArtcomment = By.xpath("//span[contains(text(),'Comment')]");

	// Footer Links
	public static final By contArtMore = By
			.xpath("//iframe[@class='ad-revpanel_iframe ad-revpanel_taboola-below-article']");
	public static final By frameFTW = By.xpath("//iframe[@class='ad-revpanel_iframe ad-revpanel_taboola-rail']");
	public static final By lnkArtspons = By
			.xpath("(//div[@class='trc_rbox_container']//span[contains(text(),'Sponsored Links')])[2]");
	public static final By lnkArttaboola = By
			.xpath("(//div[@class='trc_rbox_container']//span[contains(text(),'by Taboola')])[2]");
	public static final By lnkArtMoretoexplore = By
			.xpath("(//div[@class='trc_rbox_container']//span[@class='trc_rbox_header_span'])[1]");
	public static final By lnkArtSponseredcnt = By.xpath("//span[@class='trc_inner_header trc_header_left_column']");
	public static final By lnkArtMorefrmweb = By.xpath("//span[@class='trc_inner_header trc_header_right_column']");
	// Changed by sravani
	public static final By lnkArtpromotedlnks = By
			.xpath("(//div[@class='trc-widget-footer']//span[contains(text(),'Promoted Links')])[4]");
	// Previous xpath
	// By.xpath("(//div[@class='trc-widget-footer']//span[contains(text(),'Promoted
	// Links')])[1]");
	// Changed by sravani
	public static final By lnkArtbyTaboola = By
			.xpath("(//div[@class='trc-widget-footer']//span[contains(text(),'by Taboola')])[2]");
	// Previous xpath
	// By.xpath("(//div[@class='trc-widget-footer']//span[contains(text(),'by
	// Taboola')])[1]");

	public static final By imgArtmoretoExplore = By
			.xpath("(//div[@id='taboola-below-article-thumbnails']//div[@class='thumbBlock_holder']//span)[1]");
	// Changed by sravani
	public static final By lnkArtmoretoExplore = By.xpath("(//span[@class='video-label video-title'])[1]");
	// Previous xpath
	// By.xpath("(//div[@id='taboola-below-article-thumbnails']//span[@class='video-label
	// video-title'])[1]");

	public static final By hdrArtFromtheWeb = By
			.xpath("(//div[@class='trc_rbox_container']//span[contains(text(),'FROM THE WEB')])");
	public static final By ImgArtFromtheweb = By
			.xpath("(//div[@class='trc_rbox_container']//span[@class='thumbBlock'])[1]");
	public static final By fromthewebsection = By.xpath("//*[@id='rbox-t2m']");
	public static final By hdArtMorefromnbc = By.xpath("//span[contains(text(),'MORE FROM NBC NEWS')]");
	public static final By morefromnbcsection = By
			.xpath("//div[@class='trc_rbox organic-thumbnails-a trc-content-hybrid ']");

	// Left side Article section Name and Time
	public static final By hdrsecArt = By.xpath("//div[@class='article-flags_container']");
	public static final By hdrsecArt1 = By.xpath("(//div[@class='article-flags_container']//a)[1]");
	public static final By hdrsectimeArt = By.xpath("(//div[@class='article-flags_container']//time)[1]");
	public static final By Artbyline = By.xpath("(//span[@class='byline'])[1]");
	public static final By ArtTopic = By.xpath("(//span[contains(text(),'Topics')])[1]");
	public static final By ArtTopicname = By.xpath("(//a[@class='flag flag_article-topic'])[1]");
	public static final By ArtFirstPublished = By.xpath("(//span[contains(text(),'First Published')])[1]");
	public static final By ArtFirstPublishedon = By
			.xpath("(//div[@class='timestamp_meta']//time[@class='timestamp_article'])[1]");
	public static final By Artdownarrow = By.xpath("(//i[@class='fa fa-arrow-down'])[1]");
	public static final By ArtNextStory = By.xpath("(//span[@class='article_meta-title'])[1]");
	public static final By ArtNextStoryName = By.xpath("(//div[@class='article-nav_container']//a)[1]");

	public static final By ArtAd = By.xpath("(//div[@class='ad-label'])[2]");
	public static final By ArtAdbox = By.xpath("(//div[@class='ad-content ad-sm ad-md ad-lg boxflex'])[1]");
	public static final By ArtAdchoices = By.xpath("(//div[@id='BAP-holder']//img)[1]");
	// Previous Xpath By.xpath(" (//div[@id='BAP-holder']//img)[1]");

	// video Feature Front
	public static final By videoArtsharevideo = By.xpath("//span[contains(text(),'Share Video')]");
	public static final By videoArtfbShare = By.xpath("//i[@class='fa fa-lg fa-facebook']");
	public static final By videoArttwitterShare = By.xpath("//i[@class='fa fa-lg fa-twitter']");
	public static final By videoArtgoogleplusShare = By.xpath("//i[@class='fa fa-lg fa-google-plus']");
	public static final By videoArtmailshare = By.xpath("//i[@class='fa fa-lg fa-envelope']");
	public static final By videoArtembedshare = By.xpath("//i[@class='js-toggle-embed fa fa-lg fa-code']");

	// Article structure
	public static final By numOfArticles = By.xpath("//article");
	public static final By articleMainCont = By.xpath("//div[@class='article_capsule']/div[2]/div");
	public static final By articleSubCont = By
			.xpath("//div[@class='article_capsule']//div[@class='row_article article_main-container']/div");

	// Article Body
	public static final By articlePara = By.xpath("//div[@class='article-body']/p");
	public static final By articleImg = By.xpath("//div[@class='article-body']/figure");
	public static final By articleVideo = By.xpath("//div[@class='article-body']/div[@data-playertype='Inline']");
	public static final By articleWidgets = By.xpath("//div[@id='taboola-below-article-text-links']");
	public static final By articleIframe = By
			.xpath("//iframe[contains(@class,'ad-revpanel_iframe ad-revpanel_taboola-below-article')]");
	public static final By articleLinks = By.xpath(".//div[@class='article-body']/p//*[contains(@href,'')]");

}
