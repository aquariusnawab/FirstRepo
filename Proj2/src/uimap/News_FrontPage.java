package uimap;

import org.openqa.selenium.By;

/**
 * UI Map for UserRegistrationPage 
 */
public class News_FrontPage {
	
	//Dropdown
	public static final By listCuratedRefType = By.xpath("(//select[contains(@id,'curated-reference-type')])[1]");
	
	public static final By txtCoverHeadlineOvrrd = By.xpath("(//div[contains(@id,'article-cover-headline')]//input)[1]");
	
	public static final By txtArticleListSlideshow = By.xpath("(//div[contains(@id,'article-or-list-add-more-wrapper')]//input[1])[1]");
	
	
}