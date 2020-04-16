package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        MORE_MENU_HEADER = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']";
        MORE_MENU_READING_LIST = "css:.mw-ui-icon-minerva-unStar";
        NAVIGATION_BUTTON = "id:Back";
        OPEN_NAVIGATION = "css:.header #mw-mf-main-menu-button";
        SEARCH_ICON = "css:button#searchIcon";
        GO_TO_START_PAGE_BUTTON = "id:W";
        SAVED_LIST = "id:Saved";
        CLOSE_BUTTON = "id:Close";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
