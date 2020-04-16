package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlPageObject extends ArticlePageObject {

    static {
                TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_contents_container']";
                FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
                OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']";
                ONBOARDING_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']";
                NAME_LIST_FIELD = "xpath://android.widget.EditText[@text='Name of this list']";
                DESCRIPTION_LIST_FIELD = "xpath://android.widget.EditText[@text='Description (optional)']";
                LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']";
                FIND_ELEMENT_FOR_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']";
                CREATE_BUTTON = "xpath://android.widget.TextView[@text='Create new']";
                NAME_LIST_MENU_ADDED = "xpath://*[@resource-id='org.wikipedia:id/item_title']";
                TITLE_ARTICLE_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
    }

    public AndroidArticlPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
