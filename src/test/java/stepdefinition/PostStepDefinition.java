package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PostPage;

import java.io.IOException;

public class PostStepDefinition {
    Hooks hooks;
    PostPage postPage;

    public PostStepDefinition(Hooks hook) throws IOException {
        hooks = hook;
        postPage = hook.getPageObjectManager().getPostPage();
    }

    @When("^User goes to new post page$")
    public void userGoesToNewPostPage() {
        postPage.goToNewPostPage();
    }

    @And("^User creates post with details$")
    public void userCreatesPostWithDetails() {
        postPage.createPostDetails();
    }

    @Then("^User verifies successful created post$")
    public void userVerifiesSuccessfulCreatedPost() {
        postPage.verifyCreatedPost();
    }

    @When("^User goes to profile page$")
    public void userGoesToProfilePage() throws InterruptedException {
        postPage.goToProfilePage();
    }

    @And("^User goes to article$")
    public void userGoesToArticle() {
        postPage.goToArticle();
    }

    @And("^User click delete article button$")
    public void userClickDeleteArticleButton() {
        postPage.clickDeleteButton();
    }

    @Then("^User verifies successful deleted article$")
    public void userVerifiesSuccessfulDeletedArticle() {
        postPage.verifyDeletedPost();
    }
}
