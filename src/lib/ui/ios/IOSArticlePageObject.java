package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON = "id:Back";

        NAME_LIST_FIELD = "xpath://android.widget.EditText[@text='Name of this list']";
        DESCRIPTION_LIST_FIELD = "xpath://android.widget.EditText[@text='Description (optional)']";
        LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']";
        FIND_ELEMENT_FOR_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']";
        CREATE_BUTTON = "xpath://android.widget.TextView[@text='Create new']";
        NAME_LIST_MENU_ADDED = "xpath://*[@resource-id='org.wikipedia:id/item_title']";
        TITLE_ARTICLE_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
        TITLE_ID_TPL = "id:{SUBSTRING}";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
