package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    public void waitForElementAndCheckText(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        Assert.assertEquals(element.getText(), value);
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
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
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int maxSwipes) {
        int alreadySwipes = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipes > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    public void swipeElementToLeft(By by, String errror_message) {
        WebElement element = waitForElementPresent(by, errror_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String errorMessage) {
        int amoutOfElement = getAmountOfElements(by);
        if (amoutOfElement > 0) {
            String defaultMessage = "An Element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(By by, String errorMessage) {
        int amoutOfElement = getAmountOfElements(by);
        if (amoutOfElement < 1) {
            String defaultMessage = "An Element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMessage, tomeOutInSecond);
        return element.getAttribute(attribute);
    }

    public String waitForElementAndGetNameTag(By by, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMessage, tomeOutInSecond);
        return element.getTagName();
    }

    public void logInWiKi(String login, String password) {
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

        /*waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Turn on reading list sync?']"),
                "Cannot find modal window",
                20
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot find 'No Thanks' button",
                5);*/
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
    }
}
