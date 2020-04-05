package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyLIstPageObject;

public class IOSMyLIstPageObject extends MyLIstPageObject {

    static {
        FIND_ELEMENT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";
    }

    public IOSMyLIstPageObject(AppiumDriver driver) {
        super(driver);
    }
}
