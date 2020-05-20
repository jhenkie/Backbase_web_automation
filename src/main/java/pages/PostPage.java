package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PostPage extends DefaultPage{
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New Post')]")
    private WebElement newPostLink;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Article Title']")
    private WebElement titleInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder=\"What's this article about?\"]")
    private WebElement aboutInput;

    @FindBy(how = How.XPATH, using = "//textarea[@placeholder='Write your article (in markdown)']")
    private WebElement articleInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter Tags']")
    private WebElement tagsInput;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Publish Article')]")
    private WebElement publishButton;

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'This functionality has been automated by UI automation')]")
    private WebElement articleText;

    @FindBy(how = How.XPATH, using = "//li[a[contains(text(),'Home')]]/following-sibling::li[1]/a")
    private WebElement profileLink;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'QA Test')]")
    private WebElement titleText;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete Article')]")
    private WebElement deleteButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'No articles are here')]")
    private WebElement emptyArticleText;

    public PostPage(WebDriver driver, String backBaseLoginURL) {
        super(driver, backBaseLoginURL);
        PageFactory.initElements(this.driver, this);
    }

    public void goToNewPostPage() {
        clickElement(newPostLink);
    }

    public void createPostDetails() {
        fillElement(titleInput, "QA Test");
        fillElement(aboutInput, "QA Automation");
        fillElement(articleInput,"This functionality has been automated by UI automation");
        fillElement(tagsInput, "Automation");
        clickElement(publishButton);
    }

    public void verifyCreatedPost() {
        assertElementVisible(articleText);
    }

    public void goToProfilePage() throws InterruptedException {
        Thread.sleep(2000);
        clickElement(profileLink);
    }

    public void goToArticle() {
        clickElement(titleText);
    }

    public void clickDeleteButton() {
        clickElement(deleteButton);
    }

    public void verifyDeletedPost() {
        assertElementVisible(emptyArticleText);
    }

}
