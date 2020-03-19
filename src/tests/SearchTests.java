package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.initSearchInputAndCheckText("Search Wikipedia");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitAndCheckArticlesMoreZero();
        SearchPageObject.waitAndCheckArticlesEquallyZero();
    }

    @Test
    public void testWordInSearch() {
        String word = "Selenium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        SearchPageObject.typeSearchLine(word);
        SearchPageObject.waitAndCheckWordTitleArticle(word);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisapper();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");

        String serchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(serchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOFFoundArticles();

        assertTrue("We found too few results!",
                amountOfSearchResult > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");

        String serchLine = "fhdkj";
        SearchPageObject.typeSearchLine(serchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchArticleByTitleAndDescription() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.waitSearchResultAndClick("Skip");
        SearchPageObject.waitSearchResultAndClick("Search Wikipedia");
        String searchLine = "Belarus";
        String description = "Country in Eastern Europe";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOFFoundArticles();
        assertTrue("We found too few results!",
                amountOfSearchResult > 3);
        SearchPageObject.waitForElementByTitleAndDescription(searchLine, description);

    }
}
