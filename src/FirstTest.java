import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/sergejtambolskij/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Skip')]",
                "Cannot find search input",
                5);
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find Search Wikipedia input",
                5);

        waitForElementByXpathAndCheckText(
                "//*[@resource-id='org.wikipedia:id/search_src_text']",
                "Search Wikipedia",
                "Cannot find Search Wikipedia input",
                5);


        //element_to_enter_search_line.sendKeys("Java");

    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath) {
        return waitForElementPresentByXpath(xpath, "Cannot find search input", 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    private void waitForElementByXpathAndCheckText(String xpath, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeOutInSecond);
        Assert.assertEquals(element.getText(), value);
    }
}
