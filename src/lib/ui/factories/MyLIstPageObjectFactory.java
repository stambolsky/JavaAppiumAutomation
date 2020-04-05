package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyLIstPageObject;
import lib.ui.android.AndroidArticlPageObject;
import lib.ui.android.AndroidMyLIstPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyLIstPageObject;

public class MyLIstPageObjectFactory {

    public static MyLIstPageObject get(AppiumDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyLIstPageObject(driver);
        } else {
            return new IOSMyLIstPageObject(driver);
        }
    }
}
