package hw4;

import Base.TestBase;
import com.codeborne.selenide.WebDriverRunner;
import enums.DifferentElementsPageCheckboxes;
import enums.DifferentElementsPageRadioButtons;
import enums.ServiceSubsections;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DifferentElementsPageSelenide;
import pageObjects.HomePageSelenide;

import java.util.Date;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static enums.Users.PITER_CHAILOVSKII;
import static enums.DifferentElementsPageCheckboxes.WATER;
import static enums.DifferentElementsPageCheckboxes.WIND;
import static enums.DifferentElementsPageRadioButtons.SELEN;
import static enums.DifferentElementsPageDropDownOptions.YELLOW;

public class DifferentElementsPageTest extends TestBase {

    private HomePageSelenide homePage;
    private DifferentElementsPageSelenide differentElementsPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePageSelenide.class);
        differentElementsPage = page(DifferentElementsPageSelenide.class);

    }

    @AfterMethod
    public void afterMethod() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void differentElementsPageTest() {
        // 1 Open test site by URL
        homePage.openHomePage();

        // 2 Assert Browser title
        homePage.checkHomePageTitle();

        // 3 Perform login
        homePage.login(PITER_CHAILOVSKII);

        // 4 Assert User name in the left-top side of screen that user is loggined
        homePage.checkUserName();

        // 5 Check interface on Home page, it contains all needed elements:
        // 4 - pictures, 4 texts under them, 2 text above
        homePage.checkImagesAreDisplayed();
        homePage.checkTextBelowImages();
        homePage.checkMainTitleText();
        homePage.checkMainText();

        // 6 Click on "Service" subcategory in the header and check that drop down contains options
        homePage.checkServiceHeaderSubcategories();

        // 7 Click on Service subcategory in the left section and check that drop down contains options
        homePage.checkServiceLeftPanelSubcategories();

        // 8 Open through the header menu Service -> Different Elements Page
        homePage.selectSubsectionFromServiceHeaderDropDown(ServiceSubsections.DIFFERENT_ELEMENTS);

        // 9 Check interface on Different elements page, it contains all needed elements
        differentElementsPage.checkButtonsAreDisplayed();
        differentElementsPage.checkCheckBoxesAreDisplayed();
        differentElementsPage.checkColorDropDownIsDisplayed();
        differentElementsPage.checkRadioButtonsAreDisplayed();

        // 10 Assert that there is Right Section
        differentElementsPage.checkRightSectionIsDisplayed();

        // 11 Assert that there is Left Section
        differentElementsPage.checkLeftSectionIsDisplayed();

        // 12 Select checkboxes Water, Wind
        Map<DifferentElementsPageCheckboxes, Date> checkBoxToDateMap =
                differentElementsPage.selectCheckBoxesAndGetEventsDates(WATER, WIND);

        // 13 Assert that for each checkbox there is an individual log row and value is corresponded to the status.
        for (DifferentElementsPageCheckboxes checkbox : checkBoxToDateMap.keySet()) {
            differentElementsPage.checkEventFired(checkbox, checkBoxToDateMap.get(checkbox), true);
        }

        // 14 Select radio selen
        Date selenEventDate = differentElementsPage.selectRadioButtonAndGetEventsDate(SELEN);

        // 15 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
        differentElementsPage.checkEventFired(SELEN, selenEventDate);

        // 16 Select in dropdown Yellow
        Date yellowEventDate = differentElementsPage.selectColorDropDownValueAndGetEventDate(YELLOW);

        // 17 Assert that for dropdown there is a log row and value is corresponded to the selected value.
        differentElementsPage.checkEventFired(YELLOW, yellowEventDate);

        // 18 Unselect and assert checkboxes
        Map<DifferentElementsPageCheckboxes, Date> checkBoxToDateMapUncheck =
                differentElementsPage.selectCheckBoxesAndGetEventsDates(WATER, WIND);

        // 19 Assert that for each checkbox there is an individual log row and value is corresponded to
        for (DifferentElementsPageCheckboxes checkbox : checkBoxToDateMapUncheck.keySet()) {
            differentElementsPage.checkEventFired(checkbox, checkBoxToDateMapUncheck.get(checkbox), false);
        }
    }
}
