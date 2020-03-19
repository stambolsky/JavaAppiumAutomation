package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckTitleArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        String searchLine = "Selenium";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForNotEmptyResultsLabel();
        SearchPageObject.assertThereIsResultOfSearch();
    }
}
