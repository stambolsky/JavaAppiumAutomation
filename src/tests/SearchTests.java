package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();
        SearchPageObject.initSearchInputAndCheckText("Search Wikipedia");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitAndCheckArticlesMoreZero();
        SearchPageObject.waitAndCheckArticlesEquallyZero();
    }

    @Test
    public void testWordInSearch() {
        String word = "Selenium";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();
        SearchPageObject.typeSearchLine(word);
        SearchPageObject.waitAndCheckWordTitleArticle(word);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisapper();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();

        String serchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(serchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOFFoundArticles();

        assertTrue("We found too few results!",
                amountOfSearchResult > 0);
    }

    @Test
    public void testSearchArticleByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();
        String searchLine = "Belarus";
        String description = "Country in Eastern Europe";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOFFoundArticles();
        assertTrue("We found too few results!",
                amountOfSearchResult > 3);
        SearchPageObject.waitForElementByTitleAndDescription(searchLine, description);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.clickBySeachField();

        String serchLine = "fhdkj";
        SearchPageObject.typeSearchLine(serchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
