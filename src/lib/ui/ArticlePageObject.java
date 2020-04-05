package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends  MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ONBOARDING_BUTTON,
            NAME_LIST_FIELD,
            DESCRIPTION_LIST_FIELD,
            LIST_OK_BUTTON,
            FIND_ELEMENT_FOR_TEXT_TPL,
            CREATE_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            NAME_LIST_MENU_ADDED,
            TITLE_ARTICLE_TPL,
            TITLE_ID_TPL;
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFindElement(String element, String substring) {
        return element.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement waitForTitleElement(String article, int time) {
        return this.waitForElementPresent(getFindElement(TITLE_ID_TPL, article), "Cannot find article title on page!", time);
    }

    public WebElement waitForTitleElement(String nameArticle) {
        return this.waitForElementPresent(getFindElement(TITLE_ARTICLE_TPL, nameArticle), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle(String nameArticle) {
        if (Platform.getInstance().isAndroid()) {
            WebElement title_element = waitForTitleElement(nameArticle);
            return title_element.getTagName();
        } else {
            WebElement title_element = waitForTitleElement(nameArticle);
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);

        } else {
            this.swipeUpFillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        }
    }

    public void addArticleAndCreateMyList(String nameList, String descriptionList) {
        this.waitForTitleElement();
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to open Bookmark", 5);
        this.clickGotItButton();
        this.waitForElementAndClick(CREATE_BUTTON, "Cannot find button Create new", 5);
        this.waitForElementAndSendKeys(NAME_LIST_FIELD, nameList, "Cannot find 'Name of this list' input", 5);
        this.waitForElementAndSendKeys(DESCRIPTION_LIST_FIELD, descriptionList, "Cannot find 'Description' input", 5);
        this.waitForElementAndClick(LIST_OK_BUTTON, "Cannot find button OK", 5);
    }

    public void addArticleInMyList(String nameList) {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to open Bookmark", 5);
        this.waitForElementAndClick(NAME_LIST_MENU_ADDED, "Cannot find list " + nameList, 15);
    }

    public void clickGotItButton() {
        this.waitForElementAndClick(ONBOARDING_BUTTON, "Cannot find button Got It", 5);
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find options to add article to reading list", 5);
    }



}
