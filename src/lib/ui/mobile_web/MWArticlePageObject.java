package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:.minerva-footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions-watch .mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch .mw-ui-icon-wikimedia-unStar-progressive";

        NAME_LIST_FIELD = "xpath://android.widget.EditText[@text='Name of this list']";
        DESCRIPTION_LIST_FIELD = "xpath://android.widget.EditText[@text='Description (optional)']";
        LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']";
        FIND_ELEMENT_FOR_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']";
        CREATE_BUTTON = "xpath://android.widget.TextView[@text='Create new']";
        NAME_LIST_MENU_ADDED = "xpath://*[@resource-id='org.wikipedia:id/item_title']";
        TITLE_ARTICLE_TPL = "xpath://*[text()='{SUBSTRING}']";
        TITLE_ID_TPL = "css:#content h1";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
