package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MORE_MENU_HEADER = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']";
        MORE_MENU_READING_LIST = "xpath://*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']";
        NAVIGATION_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
