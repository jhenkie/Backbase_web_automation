package utils;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.PostPage;
import pages.RegistrationPage;
import pages.SettingsPage;
import java.io.IOException;

public class PageObjectManager {
    public WebDriver driver;
    private LoginPage loginPage;
    private SettingsPage settingsPage;
    private RegistrationPage registrationPage;
    private PostPage postPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() throws IOException {
        return (loginPage == null) ? loginPage = new LoginPage(driver, new ConfigFileReader().getBackBaseLoginURL()) : loginPage;
    }

    public SettingsPage getSettingsPage() throws IOException {
        return (settingsPage == null) ? settingsPage = new SettingsPage(driver, new ConfigFileReader().getBackBaseLoginURL()) : settingsPage;
    }

    public RegistrationPage getRegistrationPage() throws IOException {
        return (registrationPage == null) ? registrationPage = new RegistrationPage(driver, new ConfigFileReader().getBackBaseLoginURL()) : registrationPage;
    }

    public PostPage getPostPage() throws IOException {
        return (postPage == null) ? postPage = new PostPage(driver, new ConfigFileReader().getBackBaseLoginURL()) : postPage;
    }
}