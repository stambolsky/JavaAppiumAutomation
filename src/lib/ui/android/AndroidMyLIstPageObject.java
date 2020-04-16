package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyLIstPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyLIstPageObject extends MyLIstPageObject {

    static {
        FIND_ELEMENT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
    }

    public AndroidMyLIstPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
