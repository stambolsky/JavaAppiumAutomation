package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyLIstPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String
        login = "Borman666",
        password = "Borman12345678";

    @Test
    public void testSaveArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        }
        //logInWiKi("Borman666", "Borman12345678");
        String nameArticle = "Appium";
        String nameArticle_2 = "Java (programming language)";
        String nameList = "My articles";
        String descriptionList = "My list";
        SearchPageObject.clickBySeachField();
        SearchPageObject.typeSearchLine(nameArticle);
        SearchPageObject.clickByArticleWithSubstring(nameArticle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleAndCreateMyList(nameList,descriptionList);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    nameArticle, ArticlePageObject.getArticleTitle(nameArticle));

            ArticlePageObject.addArticlesToMySaved();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickNavigateUp();
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
            SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        } else {
            NavigationUI.waitAndClickSearchIcon();
        }
        SearchPageObject.typeSearchLine(nameArticle_2);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleAndCreateMyList(nameList,descriptionList);
            NavigationUI.openMoreMenuInHeader();
            NavigationUI.chooseReadingListInMoreMenu();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.goToStartPage();
            NavigationUI.clickSavedList();
        } else {
            NavigationUI.clickNavigateUp();
        }

        MyLIstPageObject MyLIstPageObject = MyLIstPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyLIstPageObject.openFolderByName("My articles");
        } else if ((Platform.getInstance().isIOS())){
            NavigationUI.clickCloseButton();
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }

        int articlesBefore = 0;
        int articlesAfter = 0;
        if (Platform.getInstance().isMw()) {
            articlesBefore = SearchPageObject.getAmountOFFoundArticles();
        }
        MyLIstPageObject.swipeArticleToDelete(nameArticle_2);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            MyLIstPageObject.openArticleFromMyList(nameArticle);
        }
        ArticlePageObject.waitForTitleElement(nameArticle);
        if (Platform.getInstance().isMw()) {
            articlesAfter = SearchPageObject.getAmountOFFoundArticles();
        }
        String article = ArticlePageObject.getArticleTitle(nameArticle);
        assertTrue("Заголовок статьи "+article+" не содержит - "+nameArticle,
                article.contains(nameArticle));
        assertTrue("The number of articles does not match", articlesBefore > articlesAfter);
    }
}
