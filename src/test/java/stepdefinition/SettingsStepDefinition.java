package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.DefaultPage;
import pages.SettingsPage;

import java.io.IOException;

public class SettingsStepDefinition {
    Hooks hooks;
    SettingsPage settingsPage;

    public SettingsStepDefinition(Hooks hook) throws IOException {
        hooks = hook;
        settingsPage = hook.getPageObjectManager().getSettingsPage();
    }

    @When("^User goes to settings page$")
    public void userGoesToSettingsPage() {
        settingsPage.goToSettingsPage();
    }

    @And("^User updates settings detail\"([^\"]*)\" \"([^\"]*)\"$")
    public void userUpdatesSettingsDetail(String name, String bio) {
        settingsPage.updateSettingsDetail(name, bio);
    }

    @And("^User updates settings main\"([^\"]*)\"$")
    public void userUpdatesSettingsMain(String email) {
        settingsPage.updateSettingsMain(email);
    }

    @Then("^User verifies updated settings \"([^\"]*)\" \"([^\"]*)\"$")
    public void userVerifiesUpdatedSettings(String name, String bio) {
        settingsPage.clickSettingLink();
        settingsPage.verifyAction(name, bio);
    }

    @Then("^User verifies updated settings oldName \"([^\"]*)\"$")
    public void userVerifiesUpdatedSettingsOldName(String bio) {
        settingsPage.clickSettingLink();
        settingsPage.verifyAction(DefaultPage.readFromFile().substring(0,DefaultPage.readFromFile().indexOf('@')), bio);
    }

    @And("^User reverts data back oldName \"([^\"]*)\"$")
    public void userRevertsDataBackOldName(String bio) {
        settingsPage.updateSettingsDetail(DefaultPage.readFromFile().substring(0,DefaultPage.readFromFile().indexOf('@')), bio);
    }

    @And("^User clicks logout button$")
    public void userClicksLogoutButton() {
        settingsPage.clickLogoutButton();
    }

    @And("^User reverts data back$")
    public void userRevertsDataBack() {
        settingsPage.updateSettingsMain(DefaultPage.readFromFile());
    }
}
