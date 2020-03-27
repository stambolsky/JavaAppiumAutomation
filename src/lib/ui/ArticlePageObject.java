package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends  MainPageObject {

    private static final String
            TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_contents_container']",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            ARTICLE_MENU_BOOKMARK = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
            ONBOARDING_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']",
            NAME_LIST_FIELD = "xpath://android.widget.EditText[@text='Name of this list']",
            DESCRIPTION_LIST_FIELD = "xpath://android.widget.EditText[@text='Description (optional)']",
            LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']",
            FIND_ELEMENT_FOR_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']",
            CREATE_BUTTON = "xpath://android.widget.TextView[@text='Create new']",
            NAME_LIST_MENU_ADDED = "xpath://*[@resource-id='org.wikipedia:id/item_title']",
            TITLE_ARTICLE_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
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

    public WebElement waitForTitleElement(String nameArticle) {
        return this.waitForElementPresent(getFindElement(TITLE_ARTICLE_TPL, nameArticle), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle(String nameArticle) {
        WebElement title_element = waitForTitleElement(nameArticle);
        return title_element.getTagName();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
    }

    public void addArticleAndCreateMyList(String nameList, String descriptionList) {
        this.waitForTitleElement();
        this.waitForElementAndClick(ARTICLE_MENU_BOOKMARK, "Cannot find button to open Bookmark", 5);
        this.clickGotItButton();
        this.waitForElementAndClick(CREATE_BUTTON, "Cannot find button Create new", 5);
        this.waitForElementAndSendKeys(NAME_LIST_FIELD, nameList, "Cannot find 'Name of this list' input", 5);
        this.waitForElementAndSendKeys(DESCRIPTION_LIST_FIELD, descriptionList, "Cannot find 'Description' input", 5);
        this.waitForElementAndClick(LIST_OK_BUTTON, "Cannot find button OK", 5);
    }

    public void addArticleInMyList(String nameList) {
        this.waitForElementAndClick(ARTICLE_MENU_BOOKMARK, "Cannot find button to open Bookmark", 5);
        this.waitForElementAndClick(NAME_LIST_MENU_ADDED, "Cannot find list " + nameList, 15);
    }

    public void clickGotItButton() {
        this.waitForElementAndClick(ONBOARDING_BUTTON, "Cannot find button Got It", 5);

    }



}
