package hw2.ex2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MixedLoginUITests {
    private WebDriver driver;
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
                    "wish to get more\u2026"
    );

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }

    @Test(groups = {"Regression"})
    public void loginUiTest7() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        WebElement userIcon = driver.findElement(By.cssSelector("#user-icon"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector("#login-form [type=submit]")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".nav > li > a"));
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), expectedHeaderSections.get(i));
        }

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            assertTrue(textBelowImage.isDisplayed());
            imageTexts.add(textBelowImage.getText());
        }
        assertEquals(imageTexts, expectedBenefitTextList);

        // 9 Assert a text of the main header
        WebElement mainTitle = driver.findElement(By.cssSelector(".main-title"));
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES\u2026");
        WebElement mainText = driver.findElement(By.cssSelector(".main-txt"));
        assertTrue(mainText.isDisplayed());
        assertEquals(mainText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        // 10 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector("h3 a"));
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(), "JDI GITHUB");

        // 11 Assert that JDI GITHUB is a link and has a proper URL
        WebElement jdiGithubUrl = driver.findElement(By.linkText("JDI GITHUB"));
        assertTrue(jdiGithubUrl.isDisplayed());
        assertTrue(jdiGithubUrl.isEnabled());
        assertEquals(jdiGithubUrl.getAttribute("href"), "https://github.com/epam/JDI");

        // 12 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector(".uui-side-bar"));
        assertTrue(leftSection.isDisplayed());

        // 13 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector(".footer-content"));
        assertTrue(footer.isDisplayed());
    }

    @Test(groups = {"Regression"})
    public void loginUiTest8() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        WebElement userIcon = driver.findElement(By.cssSelector("#user-icon"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector("#login-form [type=submit]")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".nav > li > a"));
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), expectedHeaderSections.get(i));
        }

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            assertTrue(textBelowImage.isDisplayed());
            imageTexts.add(textBelowImage.getText());
        }
        assertEquals(imageTexts, expectedBenefitTextList);

        // 9 Assert a text of the main header
        WebElement mainTitle = driver.findElement(By.cssSelector(".main-title"));
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES\u2026");
        WebElement mainText = driver.findElement(By.cssSelector(".main-txt"));
        assertTrue(mainText.isDisplayed());
        assertEquals(mainText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        // 10 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector("h3 a"));
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(), "JDI GITHUB");

        // 11 Assert that JDI GITHUB is a link and has a proper URL
        WebElement jdiGithubUrl = driver.findElement(By.linkText("JDI GITHUB"));
        assertTrue(jdiGithubUrl.isDisplayed());
        assertTrue(jdiGithubUrl.isEnabled());
        assertEquals(jdiGithubUrl.getAttribute("href"), "https://github.com/epam/JDI");

        // 12 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector(".uui-side-bar"));
        assertTrue(leftSection.isDisplayed());

        // 13 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector(".footer-content"));
        assertTrue(footer.isDisplayed());
    }

    @Test(groups = {"Smoke"})
    public void loginUiTest9() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        WebElement userIcon = driver.findElement(By.cssSelector("#user-icon"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector("#login-form [type=submit]")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        assertTrue(userName.isDisplayed());
        assertEquals(userName.getText(), "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".nav > li > a"));
        for (int i = 0; i < headerSections.size(); i++) {
            assertEquals(headerSections.get(i).getText(), expectedHeaderSections.get(i));
        }

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            assertTrue(textBelowImage.isDisplayed());
            imageTexts.add(textBelowImage.getText());
        }
        assertEquals(imageTexts, expectedBenefitTextList);

        // 9 Assert a text of the main header
        WebElement mainTitle = driver.findElement(By.cssSelector(".main-title"));
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES\u2026");
        WebElement mainText = driver.findElement(By.cssSelector(".main-txt"));
        assertTrue(mainText.isDisplayed());
        assertEquals(mainText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        // 10 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector("h3 a"));
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(), "JDI GITHUB");

        // 11 Assert that JDI GITHUB is a link and has a proper URL
        WebElement jdiGithubUrl = driver.findElement(By.linkText("JDI GITHUB"));
        assertTrue(jdiGithubUrl.isDisplayed());
        assertTrue(jdiGithubUrl.isEnabled());
        assertEquals(jdiGithubUrl.getAttribute("href"), "https://github.com/epam/JDI");

        // 12 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector(".uui-side-bar"));
        assertTrue(leftSection.isDisplayed());

        // 13 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector(".footer-content"));
        assertTrue(footer.isDisplayed());
    }
}
