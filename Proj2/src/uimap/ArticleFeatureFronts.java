package uimap;
import org.openqa.selenium.By;

public class ArticleFeatureFronts {

	public static final By 	featurearticlehierarchyLink				= By.xpath("//div[@class='breadcrumb_container']");
	public static final By 	featureLink								= By.xpath("//div[@class='breadcrumb_container']//a");
	public static final By sectionFeatureLinks						= By.xpath("//div[@class='article-body']//div[@class='media-body']//h3");
	public static final By sectionArticleTimeStamp					= By.xpath("(.//*[@class='article-flags']//time)[1]");
			//	(//div[@class='article-body']//div[@class='media-body']//div[contains(@class,'flag flag_default')]/a)[3]
				//	(//div[@class='article-body']//div[contains(@class,'story-link')]//img[1])[3]
}
