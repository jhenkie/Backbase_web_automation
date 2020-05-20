package stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import pages.DefaultPage;
import pages.LoginPage;
import utils.ConfigFileReader;

import java.io.IOException;

public class LoginStepDefinition implements En {
    Hooks hooks;
    LoginPage loginPage;

    public LoginStepDefinition(Hooks hook) throws IOException {
        hooks = hook;
        loginPage = hook.getPageObjectManager().getLoginPage();
    }

    @Given("^open browser and navigate to backbase login URL$")
    public void login() {
    }

    @When("^User goes to sign in page$")
    public void userGoesToSignInPage() {
        loginPage.goToSignInPage();
    }

    @And("^User logins with credential$")
    public void userLoginsWithCredential() throws IOException {
        loginPage.loginAction(DefaultPage.readFromFile(), new ConfigFileReader().getPassword());
    }

    @Then("^User verifies successful login$")
    public void userVerifiesSuccessfulLogin() throws InterruptedException {
        loginPage.verifyLogin();
    }

    @Then("^User logins with new credential \"([^\"]*)\"$")
    public void userLoginsWithNewCredential(String email) throws Throwable {
        loginPage.loginAction(email, new ConfigFileReader().getPassword());
    }

    @Then("^User verifies successful logout$")
    public void userVerifiesSuccessfulLogout() {
        loginPage.verifySignIn();
    }

    @And("^User login with invalid credential \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userLoginWithInvalidCredentialAnd(String email, String password) throws Throwable {
        loginPage.loginAction(email, password);
    }

    @Then("^User verifies failed \"([^\"]*)\"$")
    public void userVerifiesFailed(String text) {
        loginPage.verifyFailedError(text);
    }

    @And("^User only fills in email \"([^\"]*)\"$")
    public void userOnlyFillsInEmail(String email) {
        loginPage.loginAction(email,"");
    }

    @And("^User only fills in password \"([^\"]*)\"$")
    public void userOnlyFillsInPassword(String password) {
        loginPage.loginAction("", password);
    }
}
