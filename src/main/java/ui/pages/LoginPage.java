package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.ui_utils.RemoteDriverManager;



public class LoginPage extends BasePage{

    private String pageURL = JiraURL + "/login.jsp";
    @FindBy(id = "login-form-username") // не работает
    private WebElement usernameLocator;
    @FindBy(id = "login-form-password")
    private WebElement passwordLocator;
    @FindBy(id = "login-form-submit")
    private WebElement loginButtonLocator;

    public LoginPage() {

        PageFactory.initElements(driver, this);
        this.driver = RemoteDriverManager.getDriver();
    }

    public LoginPage open() {
        logger.info("OPENING URL: " + pageURL);
        driver.get(pageURL);
        return this;
    }

    public LoginPage enterUsername() {
        waitToBePresentAndSendKeys(usernameLocator, username);
        return this;
    }

    public LoginPage enterPassword() {
        waitToBePresentAndSendKeys(passwordLocator, password);
        return this;
    }

    public LoginPage clickLogin() {
        waitToBePresentAndSendSubmit(loginButtonLocator);
        return this;
    }

    public boolean isOnThePage() {
        return isOnThePage(pageURL);
    }

    public LoginPage login(WebElement user,String password){
        waitToBePresentAndSendKeys(usernameLocator, "user");
        waitToBePresentAndSendKeys(passwordLocator, "password");
        waitToBePresentAndSendSubmit(loginButtonLocator);
        return this;
    }

}

