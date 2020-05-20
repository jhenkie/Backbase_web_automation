package pages;

import com.cucumber.listener.Reporter;
import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public abstract class DefaultPage {
    protected WebDriver driver;

    public DefaultPage(WebDriver driver, String url) {
        this.driver = driver;
        if (isNotBlank(url)) {
            driver.navigate().to(url);
        }
        PageFactory.initElements(new DefaultElementLocatorFactory(driver), this);
    }

    public <T> T waitUntil(ExpectedCondition<T> expectedCondition) {
        return waitUntil(expectedCondition, 3);
    }

    public <T> T waitUntil(ExpectedCondition<T> expectedCondition, int timeoutSeconds) {
        return new WebDriverWait(driver, timeoutSeconds).pollingEvery(500, TimeUnit.MILLISECONDS).until(expectedCondition);
    }

    public void clickElement(WebElement elem) {
        try {
            waitUntil(ExpectedConditions.elementToBeClickable(elem), 3);
            elem.click();
        } catch (TimeoutException | AssertionError e) {
            capturePage();
            Assert.fail("element not found:" + elem);
        }
    }

    public void fillElement(WebElement elem, String text) {
        try {
            Thread.sleep(500);
            waitUntil(ExpectedConditions.elementToBeClickable(elem), 3);
            elem.clear();
            elem.sendKeys(text);
        }catch(TimeoutException | AssertionError | InterruptedException e) {
            capturePage();
            Assert.fail("element not found:" + elem);
        }
    }

    public String dateGenerator(String format) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public void capturePage(String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String relativePath = "screenshots/" + testName + ".png";
            File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/" + relativePath);
            FileUtils.copyFile(screenshot, destinationPath);
            Reporter.addScreenCaptureFromPath(relativePath);
        } catch (IOException e) {
        }
    }

    public boolean assertElementVisible(WebElement elem) {
        try {
            waitUntil(ExpectedConditions.elementToBeClickable(elem));
            return elem.isDisplayed();
        }catch(TimeoutException | AssertionError e) {
            capturePage();
            Assert.fail("element not found:" + elem);
        }
        return false;
    }

    public void assertElementAttribute(final WebElement elem, String text) {
        try{
            Thread.sleep(500);
            waitUntil(ExpectedConditions.elementToBeClickable(elem));
            Assert.assertTrue(elem.getAttribute("value").contains(text));
            capturePage();
        }catch(TimeoutException | AssertionError | InterruptedException e) {
            capturePage();
            Assert.fail("element not found:" + elem);
        }
    }

    public void assertElement(WebElement elem, String text) {
        try{
            Thread.sleep(500);
            waitUntil(ExpectedConditions.elementToBeClickable(elem));
            System.out.println(elem.getText());
            System.out.println(text);
            Assert.assertTrue(elem.getText().contains(text));
            capturePage();
        }catch(TimeoutException | AssertionError | InterruptedException e) {
            capturePage();
            Assert.fail("element not found:" + elem);
        }
    }

    public void capturePage() {
        String testName = System.getProperty("scenarioName").replace("\"","_")+"_"+dateGenerator("hh:mm:ss").replace(":","_");
        capturePage(testName);
    }

    public static void writeToFile(String email) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/emailGenerator.txt");
            writer.write(email);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile() {
        String getEmail="";
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/emailGenerator.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getEmail;
    }

    public static String increaseEmailNumber() {
        String oldEmail,newEmail = "";
        int number,addNumber = 0;

        oldEmail = readFromFile();
        number = Integer.parseInt(oldEmail.substring(oldEmail.indexOf("@")-1,oldEmail.indexOf("@")));
        addNumber =number+1;
        newEmail = oldEmail.replace(String.valueOf(number),String.valueOf(addNumber));
        writeToFile(newEmail);
        return newEmail;
    }
}