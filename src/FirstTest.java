import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find search input",
                5);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndCheckText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Search Wikipedia",
                "Cannot find Search Wikipedia input",
                5);
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_image']"));
        List<WebElement> listArticle = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_image']"));
        Assert.assertTrue("Нет статей на странице", listArticle.size()>0);

        waitForAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Cannot find search field",
                5
        );

        listArticle = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_image']"));
        Assert.assertTrue("Список статей не пустой", listArticle.size()==0);
    }

    @Test
    public void testWordInSearch() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Selenium",
                "Cannot find search input",
                5
        );

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        List<WebElement> listArticle = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));

        for (int i = 0; listArticle.size()>i; i++) {
            Assert.assertTrue("Заголовок статьи "+listArticle.get(i).getText()+" не содержит слово - Selenium",
                    listArticle.get(i).getText().contains("Selenium"));
        }

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Cannot find X to cancel search",
                15);

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "X is still present on the page",
                5);
    }

    @Test
    public void testSwipeArticle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Appium']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_contents_container']"),
                "Cannot find article img",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the and of the article",
                20
        );
    }

    @Test
    public void saveArticlesToMyList() {

        //logInWiKi("Borman666", "Borman12345678");
        String nameArticle = "Appium";
        String nameArticle_2 = "Java (programming language)";
        String nameList = "My articles";
        String descriptionList = "My list";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                nameArticle,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='"+nameArticle+"']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_contents_container']"),
                "Cannot find article img",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot find button to open Bookmark",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button']"),
                "Cannot find button Got It",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Create new']"),
                "Cannot find button Create new",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Name of this list']"),
                nameList,
                "Cannot find 'Name of this list' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Description (optional)']"),
                descriptionList,
                "Cannot find 'Description' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot find button OK",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button Navigate up",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article 'Java'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot find button to open Bookmark",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+nameList+"')]"),
                "Cannot find list "+nameList,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
                "Cannot find button to open More menu",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']"),
                "Cannot find button to open Reading lists",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'My articles')]"),
                "Cannot find button to open '"+nameList+"' lists",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='"+nameArticle+"']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='"+nameArticle+"']"),
                "Cannot delete saved article '"+nameArticle+"'",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='"+nameArticle_2+"']"),
                "Cannot find article '"+nameArticle_2+"'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+nameArticle_2+"']"),
                "Cannot find article '"+nameArticle_2+"'",
                15
        );

        waitForElementPresent(By.xpath("//*[@content-desc='"+nameArticle_2+"']"));
        WebElement article = driver.findElement(By.xpath("//android.view.View[@content-desc='"+nameArticle_2+"']"));

        Assert.assertTrue("Заголовок статьи "+article.getTagName()+" не содержит - "+nameArticle_2,
                article.getTagName().contains(nameArticle_2));
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        String serchLine = "Linkin Park Diskography";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                serchLine,
                "Cannot find search input",
                5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find anything by the request " + serchLine,
                15
        );

        int amountOfSearchResult = getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue("We found too few results!",
                amountOfSearchResult > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {

        String serchLine = "fhdkj";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                serchLine,
                "Cannot find search input",
                5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String emptyResultLabel = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Cannot find empty result label by the request " + serchLine,
                15
        );

        assertElementNotPresent(
                By.xpath(searchResultLocator),
                "We've found some results by request " + serchLine
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String serchLine = "Java";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                serchLine,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article 'Object-oriented programming language' topic searching by " + serchLine,
                15
        );

        String titleBeforRotation = waitForElementAndGetNameTag(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "content-desc",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetNameTag(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "content-desc",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been changed after screen rotation",
                titleBeforRotation,
                titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetNameTag(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "content-desc",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been changed after screen rotation",
                titleBeforRotation,
                titleAfterSecondRotation);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by, "Cannot find search input", 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    private void waitForElementAndCheckText(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        Assert.assertEquals(element.getText(), value);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForAndClear(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
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

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int maxSwipes) {
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

    protected void swipeElementToLeft(By by, String errror_message) {
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

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String errorMessage) {
        int amoutOfElement = getAmountOfElements(by);
        if (amoutOfElement > 0) {
            String defaultMessage = "An Element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMessage, tomeOutInSecond);
        return element.getAttribute(attribute);
    }

    private String waitForElementAndGetNameTag(By by, String attribute, String errorMessage, long tomeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMessage, tomeOutInSecond);
        return element.getTagName();
    }

    private void logInWiKi(String login, String password) {
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

    private void expandMenu() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/drawer_icon_menu']"),
                "Cannot find icon menu",
                5);
    }

    private void logOut() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/main_drawer_login_button'][@text='Log out']"),
                "Cannot find icon menu",
                5);
    }


}
