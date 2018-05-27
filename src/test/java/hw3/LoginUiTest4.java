package hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginUiTest4 {
    private WebDriver driver;
    private HomePage homepage;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        homepage = PageFactory.initElements(driver,HomePage.class);
        driver.manage().window().maximize();

    }


    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }


    @Test
    public void loginUiTest() {

        // 1 Open test site by URL
        homepage.open(driver);

        // 2 Assert Browser title
        homepage.checkHomePageTitle(driver);

        // 3 Perform login
        homepage.login("epam","1234");

        // 4 Assert User name in the left-top side of screen that user is loggined
        homepage.checkUserNameText(driver);

        // 5 Assert Browser title
        homepage.checkHomePageTitleAfterLogin(driver);


        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        homepage.checkHeaderSectionsText(driver);

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement image : images) {
            assertTrue(image.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        homepage.checkTextBelowImages(driver);


        // 9 Assert a text of the main header
        WebElement mainTitle = driver.findElement(By.cssSelector(".main-title"));
        assertTrue(mainTitle.isDisplayed());
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");
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
