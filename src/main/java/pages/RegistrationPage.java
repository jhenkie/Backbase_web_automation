package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigFileReader;

import java.io.IOException;

public class RegistrationPage extends DefaultPage{
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign up')]")
    private WebElement signUpLink;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Username']")
    private WebElement usernameInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Email']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Sign up')]")
    private WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//li[a[contains(text(),'Home')]]/following-sibling::li[1]/a")
    private WebElement profileLink;

    @FindBy(how = How.XPATH, using = "//ul[@class='error-messages']/li")
    private WebElement errorText;

    public RegistrationPage(WebDriver driver, String backBaseLoginURL) {
        super(driver, backBaseLoginURL);
        PageFactory.initElements(this.driver, this);
    }

    public void goToSignUpPage() {
        clickElement(signUpLink);
    }

    public void registerAction(String email, String username, String password) throws IOException {
        fillElement(emailInput, email);
        fillElement(usernameInput, username);
        fillElement(passwordInput, password);
        clickElement(signUpButton);
    }

    public void verifyErrorRegister() {
        assertElementVisible(errorText);
    }
}
