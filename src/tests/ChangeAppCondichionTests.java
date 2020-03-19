package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppCondichionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        String serchLine = "Java";
        SearchPageObject.typeSearchLine(serchLine);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String nameArticle = "Java (programming language)";
        String title_before_rotation = ArticlePageObject.getArticleTitle(nameArticle);

        this.rotateScreenLandscape();
        String titleAfterRotation =  ArticlePageObject.getArticleTitle(nameArticle);

        assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                titleAfterRotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle(nameArticle);

        assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String searchLine = "Appium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(searchLine);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        this.backGroundApp(2);
        ArticlePageObject.waitForTitleElement();
    }
}
