package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyLIstPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyLIstPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        //logInWiKi("Borman666", "Borman12345678");
        String nameArticle = "Appium";
        String nameArticle_2 = "Java (programming language)";
        String nameList = "My articles";
        String descriptionList = "My list";
        SearchPageObject.typeSearchLine(nameArticle);
        SearchPageObject.clickByArticleWithSubstring(nameArticle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleAndCreateMyList(nameList,descriptionList);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickNavigateUp();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine(nameArticle_2);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleAndCreateMyList(nameList,descriptionList);
            NavigationUI.openMoreMenuInHeader();
            NavigationUI.chooseReadingListInMoreMenu();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            NavigationUI.goToStartPage();
            NavigationUI.clickSavedList();
        }

        MyLIstPageObject MyLIstPageObject = MyLIstPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyLIstPageObject.openFolderByName("My articles");
        } else {
            NavigationUI.clickCloseButton();
        }
        MyLIstPageObject.swipeArticleToDelete(nameArticle_2);

        MyLIstPageObject.openArticleFromMyList(nameArticle);
        ArticlePageObject.waitForTitleElement(nameArticle);
        String article = ArticlePageObject.getArticleTitle(nameArticle);
        assertTrue("Заголовок статьи "+article+" не содержит - "+nameArticle,
                article.contains(nameArticle));
    }
}
