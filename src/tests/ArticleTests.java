package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testSwipeArticle() {
        String article = "Appium";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();
        SearchPageObject.typeSearchLine(article);
        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(article, 15);
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckTitleArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        String searchLine = "Selenium";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForNotEmptyResultsLabel();
        SearchPageObject.assertThereIsResultOfSearch();
    }
}
