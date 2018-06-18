package pageObjects;

import com.codeborne.selenide.*;
import org.openqa.selenium.support.FindBy;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class HomePageSelenide extends EpamJDIPageSelenide {

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

    @FindBy(css = ".benefit-icon")
    private ElementsCollection images;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection textBelowImages;

    @FindBy(css = ".main-title")
    private SelenideElement mainTitle;

    @FindBy(css = ".main-txt")
    private SelenideElement mainText;

    @FindBy(css = "h3 a")
    private SelenideElement subHeader;

    public void openHomePage() {
        open("https://epam.github.io/JDI/index.html");
    }

    public void checkHomePageTitle() {
        assertEquals(Selenide.title(), "Home Page");
    }

    public void checkImagesAreDisplayed() {
        for (SelenideElement image : images) {
            image.shouldBe(Condition.visible);
        }
        images.shouldBe(CollectionCondition.size(4));
    }

    public void checkTextBelowImages() {
        textBelowImages.shouldHave(CollectionCondition.exactTexts(expectedBenefitTextList));
    }

    public void checkMainTitleText() {
        mainTitle.shouldBe(Condition.visible);
        mainTitle.shouldHave(Condition.text("EPAM FRAMEWORK WISHES…"));
    }

    public void checkMainText() {
        mainText.shouldBe(Condition.visible);
        mainText.shouldHave(Condition.text("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
                " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA." +
                " UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI" +
                " UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."));
    }
}
