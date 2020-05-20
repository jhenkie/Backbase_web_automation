package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends DefaultPage{
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Settings')]")
    private WebElement settingLink;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Your Name']")
    private WebElement nameInput;

    @FindBy(how = How.XPATH, using = "//textarea[@placeholder='Short bio about you']")
    private WebElement bioInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Email']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='New Password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Update Settings')]")
    private WebElement updateSettingsButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'logout')]")
    private WebElement logoutButton;

    public SettingsPage(WebDriver driver, String backBaseLoginURL) {
        super(driver, backBaseLoginURL);
        PageFactory.initElements(this.driver, this);
    }

    public void goToSettingsPage() {
        clickElement(settingLink);
    }

    public void updateSettingsDetail(String name, String bio) {
        fillElement(nameInput,name);
        fillElement(bioInput,bio);
        clickElement(updateSettingsButton);
    }

    public void updateSettingsMain(String email) {
        fillElement(emailInput,email);
        clickElement(updateSettingsButton);
    }

    public void clickSettingLink() {
        clickElement(settingLink);
    }

    public void verifyAction(String name, String bio) {
        assertElementAttribute(nameInput,name);
        assertElementAttribute(bioInput,bio);
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }
}
