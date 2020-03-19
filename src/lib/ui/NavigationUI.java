package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.security.PublicKey;

public class NavigationUI extends MainPageObject {

    private static final String
            MORE_MENU_HEADER = "//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']",
            MORE_MENU_READING_LIST = "//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']",
            NAVIGATION_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {

    }

    public void clickNavigateUp() {
        this.waitForElementAndClick(By.xpath(NAVIGATION_BUTTON), "Cannot find button Navigate up", 5);
    }

    public void openMoreMenuInHeader() {
        this.waitForElementAndClick(By.xpath(MORE_MENU_HEADER), "Cannot find button to open More menu", 5);
    }

    public void chooseReadingListInMoreMenu() {
        this.waitForElementAndClick(By.xpath(MORE_MENU_READING_LIST), "Cannot find button to open Reading lists", 5);
    }
}
