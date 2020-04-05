package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.security.PublicKey;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MORE_MENU_HEADER,
            MORE_MENU_READING_LIST,
            NAVIGATION_BUTTON,
            GO_TO_START_PAGE_BUTTON,
            SAVED_LIST,
            CLOSE_BUTTON;
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickNavigateUp() {
        this.waitForElementAndClick(NAVIGATION_BUTTON, "Cannot find button Navigate up", 5);
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
}
