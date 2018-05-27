package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    private List<String> exepectedHeaderSections = Arrays.asList(
            "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
    );

    private List<String> expectedBenefitTextList = Arrays.asList(
            "To include good practices\n" +
                    "and ideas from successful\n" +
                    "EPAM project",
            "To be flexible and\n" +
                    "customizable",
            "To be multiplatform",
            "Already have good base\n" +
                    "(about 20 internal and\n" +
                    "some external projects),\n" +
                    "wish to get more…"
    );


    @FindBy(css = "#user-icon")
    private WebElement userIcon;

    @FindBy(css = "#Name")
    private WebElement loginInput;

    @FindBy(css = "#Password")
    private WebElement passwordInput;

    @FindBy(css = "#login-form [type=submit]")
    private WebElement submitButton;

    @FindBy(css = ".profile-photo span")
    private WebElement userName;

    @FindBys ({
        @FindBy(css = ".nav > li > a")
    })
    private List<WebElement> headerSections;

    @FindBys ({
            @FindBy(css = ".benefit-txt")
    })
    private List<WebElement> textBelowImages;

    public void open(WebDriver driver){
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public void checkHomePageTitle(WebDriver driver){
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkUserNameText(WebDriver driver){
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), "PITER CHAILOVSKII");
    }

    public void checkHomePageTitleAfterLogin(WebDriver driver){
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkHeaderSectionsText(WebDriver driver) {
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), exepectedHeaderSections.get(i));
        }
    }

    public void checkTextBelowImages(WebDriver driver) {
        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            assertTrue(textBelowImage.isDisplayed());
            imageTexts.add(textBelowImage.getText());
        }
        assertEquals(imageTexts, expectedBenefitTextList);
    }
}
