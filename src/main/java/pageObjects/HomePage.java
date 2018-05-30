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

    @FindBys({
            @FindBy(css = ".nav > li > a")
    })
    private List<WebElement> headerSections;

    @FindBys ({
            @FindBy(css = ".benefit-icon")
    })
    private List<WebElement> images;

    @FindBys({
            @FindBy(css = ".benefit-txt")
    })
    private List<WebElement> textBelowImages;

    @FindBy(css = ".main-title")
    private WebElement mainTitle;

    @FindBy(css = ".main-txt")
    private WebElement mainText;

    @FindBy(css = "h3 a")
    private WebElement subHeader;

    @FindBy(linkText = "JDI GITHUB")
    private WebElement jdiGithubUrl;

    @FindBy(css = ".uui-side-bar")
    private WebElement leftSection;

    @FindBy(css = ".footer-content")
    private WebElement footer;

    public void open(WebDriver driver) {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public void checkHomePageTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkUserNameText(String name) {
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), name);
    }

    public void checkHomePageTitleAfterLogin(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkHeaderSectionsText() {
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), exepectedHeaderSections.get(i));
        }
    }

    public void checkImagesAreDisplayed() {
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
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

    public void checkMainTitleText(String mainTitleText){
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), mainTitleText);
    }

    public void checkMainText(String checkedMainText){
        assertTrue(mainText.isDisplayed());
        assertEquals(mainText.getText(), checkedMainText);
    }

    public void checkSubHeaderText(String subHeaderText){
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(), subHeaderText);
    }

    public void checkJdiGithubUrl(String jdiUrl){
        assertTrue(jdiGithubUrl.isDisplayed());
        assertTrue(jdiGithubUrl.isEnabled());
        assertEquals(jdiGithubUrl.getAttribute("href"), jdiUrl);
    }

    public void checkLeftSectionIsDisplayed(){
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooterIsDisplayed(){
        assertTrue(footer.isDisplayed());
    }
}
