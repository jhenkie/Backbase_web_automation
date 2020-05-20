package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends DefaultPage{
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign in')]")
    private WebElement signInLink;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Username']")
    private WebElement usernameInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//li[a[contains(text(),'Home')]]/following-sibling::li[1]/a")
    private WebElement profileLink;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Sign in')]")
    private WebElement signInText;

    @FindBy(how = How.XPATH, using = "//ul[@class='error-messages']/li")
    private WebElement errorText;

    public LoginPage(WebDriver driver, String backBaseLoginURL) {
        super(driver, backBaseLoginURL);
        PageFactory.initElements(this.driver, this);
    }

    public void goToSignInPage() {
        clickElement(signInLink);
    }

    public void loginAction(String email, String password) {
        fillElement(usernameInput, email);
        fillElement(passwordInput, password);
        clickElement(signInButton);
    }

    public void verifyLogin() throws InterruptedException {
        Thread.sleep(1000);
        assertElement(profileLink, readFromFile().substring(0,readFromFile().indexOf('@')));
    }

    public void verifySignIn() {
        assertElementVisible(signInText);
    }

    public void verifyFailedError(String text) {
        assertElement(errorText, text);
    }
}
