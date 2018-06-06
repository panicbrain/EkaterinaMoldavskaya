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

    private List<String> expectedHeaderSections = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");

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

    @FindBy(css = ".nav > li > a")
    private List<WebElement> headerSections;

    @FindBy(css = ".benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
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

    public void checkUserNameText() {
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), "PITER CHAILOVSKII");
    }

    public void checkHomePageTitleAfterLogin(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkHeaderSectionsText() {
        assertEquals(headerSections.size(), 4);
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), expectedHeaderSections.get(i));
        }
    }

    public void checkImagesAreDisplayed() {
        assertEquals(images.size(), 4);
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
        }
    }

    public void checkTextBelowImages(WebDriver driver) {
        assertEquals(textBelowImages.size(), 4);
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            assertTrue(textBelowImage.isDisplayed());
            imageTexts.add(textBelowImage.getText());
        }
        assertEquals(imageTexts, expectedBenefitTextList);
    }

    public void checkMainTitleText() {
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");
    }

    public void checkMainText() {
        assertTrue(mainText.isDisplayed());
        assertEquals(mainText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    public void checkSubHeaderText() {
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(), "JDI GITHUB");
    }

    public void checkJdiGithubUrl() {
        assertTrue(jdiGithubUrl.isDisplayed());
        assertTrue(jdiGithubUrl.isEnabled());
        assertEquals(jdiGithubUrl.getAttribute("href"), "https://github.com/epam/JDI");
    }

    public void checkLeftSectionIsDisplayed() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooterIsDisplayed() {
        assertTrue(footer.isDisplayed());
    }
}
