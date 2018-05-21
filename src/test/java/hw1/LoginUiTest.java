package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.*;

public class LoginUiTest {
    WebDriver driver;

    List<String> expectedBenefitTextList = Arrays.asList(
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

    //1 Create a new test
    @Test
    public void loginUiTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\EkaterinaMoldavskaya\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 3 Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        // 4 Perform login
        WebElement userIcon = driver.findElement(By.cssSelector("#user-icon"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector("#login-form [type=submit]")).click();

        // 5 Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(userName.isDisplayed());
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");

        // 6 Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        // 7 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".nav > li > a"));
        Set<String> sectionNames = new HashSet<String>();
        for (WebElement headerSection : headerSections) {
            sectionNames.add(headerSection.getText());
        }
        Assert.assertEquals(sectionNames, new HashSet<String>(Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")));

        // 8 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement image : images) {
            Assert.assertTrue(image.isDisplayed());
        }

        // 9 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> imageTexts = new ArrayList<String>();
        for (WebElement textBelowImage : textBelowImages) {
            imageTexts.add(textBelowImage.getText());
            Assert.assertTrue(textBelowImage.isDisplayed());
        }
        Assert.assertEquals(imageTexts, expectedBenefitTextList);

        // 10 Assert a text of the main header
        WebElement mainTitle = driver.findElement(By.cssSelector(".main-title"));
        Assert.assertTrue(mainTitle.isDisplayed());
        Assert.assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");
        WebElement mainText = driver.findElement(By.cssSelector(".main-txt"));
        Assert.assertTrue(mainText.isDisplayed());
        Assert.assertEquals(mainText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        // 11 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector("h3 a"));
        Assert.assertTrue(subHeader.isDisplayed());
        Assert.assertEquals(subHeader.getText(), "JDI GITHUB");

        // 12 Assert that JDI GITHUB is a link and has a proper URL
        WebElement jdiGithubUrl = driver.findElement(By.linkText("JDI GITHUB"));
        Assert.assertTrue(jdiGithubUrl.isDisplayed());
        Assert.assertTrue(jdiGithubUrl.isEnabled());
        jdiGithubUrl.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/epam/JDI");
        driver.navigate().back();

        // 13 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector(".uui-side-bar"));
        Assert.assertTrue(leftSection.isDisplayed());

        // 14 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector(".footer-content"));
        Assert.assertTrue(footer.isDisplayed());
    }

    // 15 Close Browser
    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
