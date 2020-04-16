package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyLIstPageObject;
import lib.ui.android.AndroidArticlPageObject;
import lib.ui.android.AndroidMyLIstPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyLIstPageObject;
import lib.ui.mobile_web.MWMyListsPageObject;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyLIstPageObjectFactory {

    public static MyLIstPageObject get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyLIstPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSMyLIstPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver) {
            };
        }
    }
}
