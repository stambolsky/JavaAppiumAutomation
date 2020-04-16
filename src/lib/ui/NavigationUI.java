package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.security.PublicKey;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MORE_MENU_HEADER,
            MORE_MENU_READING_LIST,
            NAVIGATION_BUTTON,
            GO_TO_START_PAGE_BUTTON,
            SAVED_LIST,
            OPEN_NAVIGATION,
            SEARCH_ICON,
            CLOSE_BUTTON;
    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickNavigateUp() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.openNavigation();
        } else {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find button Navigate up", 5);
            this.tryClickElementWithFewAttempts(MORE_MENU_READING_LIST, "Cannot find navigation button to My List", 15);
        }
    }

    public void waitAndClickSearchIcon() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(SEARCH_ICON, "Cannot find icon search", 15);
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void openMoreMenuInHeader() {
        this.waitForElementAndClick(MORE_MENU_HEADER, "Cannot find button to open More menu", 5);
    }

    public void chooseReadingListInMoreMenu() {
        this.waitForElementAndClick(MORE_MENU_READING_LIST, "Cannot find button to open Reading lists", 5);
    }

    public void goToStartPage() {
        this.waitForElementAndClick(GO_TO_START_PAGE_BUTTON, "Cannot find button to open Start PAge", 5);
    }

    public void clickSavedList() {
        this.waitForElementAndClick(SAVED_LIST, "Cannot find button open Saved List", 5);
    }

    public void clickCloseButton() {
        this.waitForElementAndClick(CLOSE_BUTTON, "Cannot find button Close", 15);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
