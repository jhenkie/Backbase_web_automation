package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import pages.DefaultPage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.ConfigFileReader;

import java.io.IOException;

import static pages.DefaultPage.increaseEmailNumber;
import static pages.DefaultPage.readFromFile;

public class RegistrationStepDefinition implements En {
    Hooks hooks;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    public RegistrationStepDefinition(Hooks hook) throws IOException {
        hooks = hook;
        registrationPage = hook.getPageObjectManager().getRegistrationPage();
    }

    @When("^User goes to sign up page$")
    public void userGoesToSignUpPage() {
        registrationPage.goToSignUpPage();
    }

    @And("^User register with details$")
    public void userRegisterWithDetails() throws IOException {
        registrationPage.registerAction(increaseEmailNumber(),readFromFile().substring(0,readFromFile().indexOf('@')), new ConfigFileReader().getPassword());
    }

    @Then("^User verifies successful register$")
    public void userVerifiesSuccessfulRegister() throws InterruptedException {
        loginPage.verifyLogin();
    }

    @And("^User register with empty details$")
    public void userRegisterWithEmptyDetails() throws IOException {
        registrationPage.registerAction("","","");
    }

    @Then("^User verifies error register$")
    public void userVerifiesErrorRegister() {
        registrationPage.verifyErrorRegister();
    }
}
