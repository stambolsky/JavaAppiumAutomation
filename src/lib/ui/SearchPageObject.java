package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_CANCEL_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']",
            SEARCH_RESULT_LIST = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_RESULT_LIST_IMAGE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_image']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_RESULT_LIST_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_LIST_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']",
            SEARCH_RESULT_LIST_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";

    public SearchPageObject(AppiumDriver driver) {

        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String element, String substring) {
        return element.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_LIST_TITLE_TPL.replace("{TITLE}", title)
                + "/.." + SEARCH_RESULT_LIST_DESCRIPTION_TPL.replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresent(getResultSearchByTitleAndDescription(title, description),
                "Cannot find article in search result", 10);
    }

    public void initSearchInputAndCheckText(String search_line) {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find Search Wikipedia input", 20);
        this.waitForElementAndCheckText(SEARCH_INPUT, search_line, "Cannot find Search Wikipedia input",  20);
    }

    public void waitAndCheckWordTitleArticle(String wordInTitle) {
        waitForElementPresent(SEARCH_RESULT_LIST, "Cannot find search list");
        List<WebElement> listArticle = driver.findElements(By.xpath(SEARCH_RESULT_LIST));

        for (int i = 0; listArticle.size()>i; i++) {
            Assert.assertTrue("Заголовок статьи "+listArticle.get(i).getText()+" не содержит слово - " + wordInTitle,
                    listArticle.get(i).getText().contains(wordInTitle));
        }
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisapper() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find search input", 5);
    }

    public void clickByArticleWithSubstring(String subString) {
        String search_result_xpath = getResultSearchElement(SEARCH_RESULT_LIST_BY_SUBSTRING_TPL, subString);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + subString, 10);
    }

    public void waitSearchResultAndClick(String subString) {
        String search_result_xpath = getResultSearchElement(SEARCH_RESULT_BY_SUBSTRING_TPL, subString);
        this.waitForElementAndClick(search_result_xpath, "Cannot find search result with substring " + subString, 5);
    }

    public void waitAndCheckArticlesMoreZero() {
        waitForElementPresent(SEARCH_RESULT_LIST_IMAGE,"");
        List listArticle = driver.findElements(By.xpath(SEARCH_RESULT_LIST_IMAGE));
        Assert.assertTrue("Нет статей на странице", listArticle.size()>0);
    }

    public void waitAndCheckArticlesEquallyZero() {
        waitForElementAndClear(SEARCH_INPUT, "Cannot find search field", 5);
        List listArticle = driver.findElements(By.xpath(SEARCH_RESULT_LIST_IMAGE));
        Assert.assertEquals("Список статей не пустой", 0, listArticle.size());
    }

    public int getAmountOFFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element.", 15);
    }

    public void waitForNotEmptyResultsLabel() {
        this.waitForElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find not empty result.", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
    }

    public void assertThereIsResultOfSearch() {
        this.assertElementPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
    }
}
