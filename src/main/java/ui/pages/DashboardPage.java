package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ui.ui_utils.RemoteDriverManager;


public class DashboardPage extends BasePage{
    private String pageURL = baseURL + "/secure/Dashboard.jspa";

    private By createLocator = By.id("create_link");
    private By issuesLocator = By.id("find_link");
    private By searchFieldLocator = By.id("quickSearchInput");
    private By filterReportedByMeLocator = By.id("filter_lnk_reported_lnk");
    private By reporterFilterByMeLocator = By.xpath("//*[@id='fieldreporter'][contains(text(), 'Current User')]");

    public DashboardPage() {

        this.driver = RemoteDriverManager.getDriver();
    }

    public boolean isOnThePage() {
        return isOnThePage(pageURL);
    }

    public DashboardPage clickCreateButton(){
        waitToBePresentAndClick(createLocator);
        return this;
    }

    public DashboardPage issuesFilterReportedByMe() {
        SelectDropDownItem(issuesLocator,filterReportedByMeLocator);
        return this;
    }
    public DashboardPage search(String searchWord) {

        WebElement element = driver.findElement(searchFieldLocator);
        element.sendKeys(searchWord);
        element.submit();
        return this;
    }

    public boolean isReportedByMeFilter() {
        By buttonSelector = reporterFilterByMeLocator;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
