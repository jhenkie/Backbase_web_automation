package stepdefinition;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.*;

import java.io.IOException;

public class Hooks {
    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;
    Scenario scenario;

    public Hooks() {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver(DriverType.CHROME));
        scenarioContext = new ScenarioContext();
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    @Before
    public void getScenarioName(Scenario scenario) throws IOException {
        System.setProperty("scenarioName",scenario.getName());
        Reporter.assignAuthor("Back base - "+new ConfigFileReader().getBackBaseLoginURL());
    }

    @After
    public void tearDown() {
        getWebDriverManager().quitDriver();
    }

}