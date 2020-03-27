package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeOutInSecond) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    public void waitForElementAndCheckText(String locator, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        Assert.assertEquals(element.getText(), value);
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSecond) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwipes = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipes > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    public void swipeElementToLeft(String locator, String errror_message) {
        WebElement element = waitForElementPresent(locator, errror_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amoutOfElement = getAmountOfElements(locator);
        if (amoutOfElement > 0) {
            String defaultMessage = "An Element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(String locator, String errorMessage) {
        int amoutOfElement = getAmountOfElements(locator);
        if (amoutOfElement < 1) {
            String defaultMessage = "An Element '" + locator.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMessage, tomeOutInSecond);
        return element.getAttribute(attribute);
    }

    public String waitForElementAndGetNameTag(String locator, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMessage, tomeOutInSecond);
        return element.getTagName();
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    /*public void logInWiKi(String login, String password) {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_announcement_action_positive']"),
                "Cannot find 'Log in' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Username']"),
                login,
                "Cannot find 'UserName' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Password']"),
                password,
                "Cannot find 'Password' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/login_button']"),
                "Cannot find button LogIn",
                5);

        */
    /*waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Turn on reading list sync?']"),
                "Cannot find modal window",
                20
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot find 'No Thanks' button",
                5);*/
    /*
    }

    public void expandMenu() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/drawer_icon_menu']"),
                "Cannot find icon menu",
                5);
    }

    public void logOut() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/main_drawer_login_button'][@text='Log out']"),
                "Cannot find icon menu",
                5);
    }*/
}
