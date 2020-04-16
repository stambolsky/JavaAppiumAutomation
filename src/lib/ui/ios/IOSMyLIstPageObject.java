package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyLIstPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyLIstPageObject extends MyLIstPageObject {

    static {
        FIND_ELEMENT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";
    }

    public IOSMyLIstPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
