package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.security.PublicKey;

public class NavigationUI extends MainPageObject {

    private static final String
            MORE_MENU_HEADER = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']",
            MORE_MENU_READING_LIST = "xpath://*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']",
            NAVIGATION_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {

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
}
