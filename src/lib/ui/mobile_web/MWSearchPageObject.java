package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form > input.search";
        SEARCH_CANCEL_BUTTON = "css:.header-action .cancel";

        SEARCH_RESULT_LIST = "xpath://*[@class='page-summary']";
        SEARCH_RESULT_LIST_IMAGE = "xpath://div[contains(@class,'list-thumb')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list > li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_RESULT_LIST_BY_SUBSTRING_TPL = "xpath://*[contains(text(),'{SUBSTRING}')]";
        SEARCH_BUTTON_CLEAR = "css:button.clear";

        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_LIST_TITLE_TPL = "xpath://li[@title='{TITLE}']//div[contains(@class,'list-thumb')]";
        SEARCH_RESULT_LIST_DESCRIPTION_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
