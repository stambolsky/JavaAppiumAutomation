package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyLIstPageObject extends MainPageObject {

    private static final String
            FIND_ELEMENT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]";

    public MyLIstPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFindElementSubstring(String element, String substring) {
        return element.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String nameFolder) {
        String folderNameXpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, nameFolder);
        this.waitForElementAndClick(By.xpath(folderNameXpath), "Cannot find folder by name " + nameFolder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title);
        this.waitForElementPresent(By.xpath(article_title_xpath), "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title);
        this.waitForElementNotPresent(By.xpath(article_title_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(By.xpath(getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title)), "Cannot find the end of article");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void openArticleFromMyList(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        this.waitForElementAndClick(By.xpath(getFindElementSubstring(FIND_ELEMENT_BY_SUBSTRING_TPL, article_title)), "Cannot find article '"+article_title+"'", 15);
    }
}
