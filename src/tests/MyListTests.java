package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyLIstPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveArticlesToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        //logInWiKi("Borman666", "Borman12345678");
        String nameArticle = "Appium";
        String nameArticle_2 = "Java (programming language)";
        String nameList = "My articles";
        String descriptionList = "My list";
        SearchPageObject.typeSearchLine(nameArticle);
        SearchPageObject.clickByArticleWithSubstring(nameArticle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addArticleAndCreateMyList(nameList,descriptionList);
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickNavigateUp();
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine(nameArticle_2);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.addArticleInMyList(nameList);
        NavigationUI.openMoreMenuInHeader();
        NavigationUI.chooseReadingListInMoreMenu();
        MyLIstPageObject MyLIstPageObject = new MyLIstPageObject(driver);
        MyLIstPageObject.openFolderByName("My articles");
        MyLIstPageObject.swipeArticleToDelete(nameArticle);
        MyLIstPageObject.openArticleFromMyList(nameArticle_2);
        ArticlePageObject.waitForTitleElement(nameArticle_2);
        String article = ArticlePageObject.getArticleTitle(nameArticle_2);
        assertTrue("Заголовок статьи "+article+" не содержит - "+nameArticle_2,
                article.contains(nameArticle_2));
    }
}
