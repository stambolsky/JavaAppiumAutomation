package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends  MainPageObject {

    private static final String
            TITLE = "//*[@resource-id='org.wikipedia:id/page_contents_container']",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            ARTICLE_MENU_BOOKMARK = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
            ONBOARDING_BUTTON = "//*[@resource-id='org.wikipedia:id/onboarding_button']",
            NAME_LIST_FIELD = "//android.widget.EditText[@text='Name of this list']",
            DESCRIPTION_LIST_FIELD = "//android.widget.EditText[@text='Description (optional)']",
            LIST_OK_BUTTON = "//*[@resource-id='android:id/button1']",
            FIND_ELEMENT_FOR_TEXT_TPL = "//*[@text='{SUBSTRING}']",
            CREATE_BUTTON = "//android.widget.TextView[@text='Create new']",
            NAME_LIST_MENU_ADDED = "//*[@resource-id='org.wikipedia:id/item_title']",
            TITLE_ARTICLE_TPL = "//*[@content-desc='{SUBSTRING}']";
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFindElement(String element, String substring) {
        return element.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page!", 15);
    }

    public WebElement waitForTitleElement(String nameArticle) {
        return this.waitForElementPresent(By.xpath(getFindElement(TITLE_ARTICLE_TPL, nameArticle)), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle(String nameArticle) {
        WebElement title_element = waitForTitleElement(nameArticle);
        return title_element.getTagName();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public void addArticleAndCreateMyList(String nameList, String descriptionList) {
        this.waitForTitleElement();
        this.waitForElementAndClick(By.xpath(ARTICLE_MENU_BOOKMARK), "Cannot find button to open Bookmark", 5);
        this.clickGotItButton();
        this.waitForElementAndClick(By.xpath(CREATE_BUTTON), "Cannot find button Create new", 5);
        this.waitForElementAndSendKeys(By.xpath(NAME_LIST_FIELD), nameList, "Cannot find 'Name of this list' input", 5);
        this.waitForElementAndSendKeys(By.xpath(DESCRIPTION_LIST_FIELD), descriptionList, "Cannot find 'Description' input", 5);
        this.waitForElementAndClick(By.xpath(LIST_OK_BUTTON), "Cannot find button OK", 5);
    }

    public void addArticleInMyList(String nameList) {
        this.waitForElementAndClick(By.xpath(ARTICLE_MENU_BOOKMARK), "Cannot find button to open Bookmark", 5);
        this.waitForElementAndClick(By.xpath(NAME_LIST_MENU_ADDED), "Cannot find list " + nameList, 15);
    }

    public void clickGotItButton() {
        this.waitForElementAndClick(By.xpath(ONBOARDING_BUTTON), "Cannot find button Got It", 5);

    }



}
