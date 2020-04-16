package lib.ui.mobile_web;

import lib.ui.MyLIstPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyLIstPageObject {

    static {
        FIND_ELEMENT_BY_SUBSTRING_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{SUBSTRING}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{SUBSTRING}')]/../../a[contains(@class,'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
