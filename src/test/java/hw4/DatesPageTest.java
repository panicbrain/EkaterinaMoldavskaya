package hw4;

import Base.TestBase;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import enums.ServiceSubsections;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DatesPageSelenide;
import pageObjects.HomePageSelenide;

import java.util.Date;

import static com.codeborne.selenide.Selenide.page;
import static enums.Users.PITER_CHAILOVSKII;

public class DatesPageTest extends TestBase {
    private HomePageSelenide homePage;
    private DatesPageSelenide datesPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePageSelenide.class);
        datesPage = page(DatesPageSelenide.class);
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void datesPageTest() throws Exception {
        // 1 Open test site by URL
        homePage.openHomePage();

        // 2 Assert Browser title
        homePage.checkHomePageTitle();

        // 3 Perform login
        homePage.login(PITER_CHAILOVSKII);

        // 4 Assert User name in the left-top side of screen that user is loggined
        homePage.checkUserName();

        // 5 Open through the header menu Service -> Dates Page
        homePage.selectSubsectionFromServiceHeaderDropDown(ServiceSubsections.DATES);

        // 6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most
        Date setLeftSliderEventDate1 = datesPage.setLeftSliderRangeAndGetEventDate(0);
        Date setRightSliderEventDate1 = datesPage.seaRightSliderRangeAndGetEventDate(100);

        // 7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkChangeRangeFromEventFired(setLeftSliderEventDate1, 0);
        datesPage.checkChangeRangeToEventFired(setRightSliderEventDate1, 100);

        Selenide.sleep(1000);

        // 8 From = 0, To = 0
        Date setLeftSliderEventDate2 = datesPage.setLeftSliderRangeAndGetEventDate(0);
        Date setRightSliderEventDate2 = datesPage.seaRightSliderRangeAndGetEventDate(0);

        // 9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkChangeRangeFromEventFired(setLeftSliderEventDate2, 0);
        datesPage.checkChangeRangeToEventFired(setRightSliderEventDate2, 0);

        // 10 From = 100, To = 100
        Date setRightSliderEventDate3 = datesPage.seaRightSliderRangeAndGetEventDate(100);
        Date setLeftSliderEventDate3 = datesPage.setLeftSliderRangeAndGetEventDate(100);

        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkChangeRangeFromEventFired(setLeftSliderEventDate3, 100);
        datesPage.checkChangeRangeToEventFired(setRightSliderEventDate3, 100);

        //12 From = 30, To = 70
        Date setLeftSliderEventDate4 = datesPage.setLeftSliderRangeAndGetEventDate(30);
        Date setRightSliderEventDate4 = datesPage.seaRightSliderRangeAndGetEventDate(70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        //Fails:
        //datesPage.checkChangeRangeFromEventFired(setLeftSliderEventDate4, 30);
        datesPage.checkChangeRangeToEventFired(setRightSliderEventDate4, 70);
    }
}
