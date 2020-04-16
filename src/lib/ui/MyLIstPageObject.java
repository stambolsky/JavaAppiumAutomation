package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyLIstPageObject extends MainPageObject {

    protected static String
            FIND_ELEMENT_BY_SUBSTRING_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public MyLIstPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFindElementSubstring(String element, String substring) {
        return element.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{SUBSTRING}", article_title);
    }

    public void openFolderByName(String nameFolder) {
        String folderNameXpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, nameFolder);
        this.waitForElementAndClick(folderNameXpath, "Cannot find folder by name " + nameFolder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title);
        this.waitForElementPresent(article_title_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title);
        this.waitForElementNotPresent(article_title_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title), "Cannot find the end of article");
            if (Platform.getInstance().isIOS()) {
                this.clickElementToTheRightUpperCorner(article_title, "Cannot find saved article");
            }
            this.waitForArticleToDisappearByTitle(article_title);
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click to remove article from saved", 15);
        }

        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }
    }

    public void openArticleFromMyList(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        this.waitForElementAndClick(getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title), "Cannot find article '"+article_title+"'", 15);
    }
}
