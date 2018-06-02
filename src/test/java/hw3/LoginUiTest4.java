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
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homepage = PageFactory.initElements(driver, HomePage.class);
    }


    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }


    @Test
    public void loginUiTest() {

        // 1 Open test site by URL
        homepage.open(driver);

        // 2 Assert Browser title
        homepage.checkHomePageTitle(driver);

        // 3 Perform login
        homepage.login("epam", "1234");

        // 4 Assert User name in the left-top side of screen that user is loggined
        homepage.checkUserNameText();

        // 5 Assert Browser title
        homepage.checkHomePageTitleAfterLogin(driver);

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        homepage.checkHeaderSectionsText();

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        homepage.checkImagesAreDisplayed();

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        homepage.checkTextBelowImages(driver);

        // 9 Assert a text of the main header
        homepage.checkMainTitleText();
        homepage.checkMainText();

        // 10 Assert a text of the sub header
        homepage.checkSubHeaderText();

        // 11 Assert that JDI GITHUB is a link and has a proper URL
        homepage.checkJdiGithubUrl();

        // 12 Assert that there is Left Section
        homepage.checkLeftSectionIsDisplayed();

        // 13 Assert that there is Footer
        homepage.checkFooterIsDisplayed();
    }
}
