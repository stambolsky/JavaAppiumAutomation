package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        MORE_MENU_HEADER = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']";
        MORE_MENU_READING_LIST = "id://*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']";
        NAVIGATION_BUTTON = "id:Back";
        GO_TO_START_PAGE_BUTTON = "id:W";
        SAVED_LIST = "id:Saved";
        CLOSE_BUTTON = "id:Close";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}

