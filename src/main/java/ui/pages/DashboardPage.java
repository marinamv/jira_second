package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.ui_utils.RemoteDriverManager;


public class DashboardPage extends BasePage{
    private String pageURL = JiraURL + "/secure/Dashboard.jspa";
    @FindBy (id = "create_link")
    private static WebElement createLocator;
    @FindBy (id = "find_link")
    private static WebElement issuesLocator;
    @FindBy (id = "quickSearchInput")
    private static WebElement searchFieldLocator;
    @FindBy (id = "filter_lnk_reported_lnk")
    private static WebElement filterReportedByMeLocator;
    @FindBy (xpath = "//*[@id='fieldreporter'][contains(text(), 'Current User')]")
    private static WebElement reporterFilterByMeLocator;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
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
        searchFieldLocator.sendKeys(searchWord);
        searchFieldLocator.submit();
        return this;
    }

   public boolean isReportedByMeFilter() {
        try {
            By by = null;
            reporterFilterByMeLocator.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
