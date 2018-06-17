package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.ServiceSubsections;
import enums.Users;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static enums.Users.PITER_CHAILOVSKII;

public abstract class EpamJDIPageSelenide {
    private List<String> expectedHeaderSections = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");

    @FindBy(css = ".profile-photo")
    private SelenideElement userIcon;

    @FindBy(css = "#Name")
    private SelenideElement loginInput;

    @FindBy(css = "#Password")
    private SelenideElement passwordInput;

    @FindBy(css = ".form-horizontal button[type = 'submit']")
    private SelenideElement submitButton;

    @FindBy(css = ".profile-photo span")
    public SelenideElement userName;

    @FindBy(css = ".nav .dropdown-toggle")
    private SelenideElement serviceHeaderToggle;

    @FindBy(css = ".nav .dropdown-menu a")
    private ElementsCollection serviceHeaderSubcategories;

    @FindBy(css = ".sidebar-menu")
    private SelenideElement leftSection;

    @FindBy(css = ".sub a")
    private ElementsCollection serviceLeftPanelSubcategories;

    public void login(Users user) {
        userIcon.click();
        loginInput.val(user.login);
        passwordInput.val(user.password);
        submitButton.click();
    }

    public void checkUserName() {
        userName.shouldBe(Condition.visible);
        userName.shouldHave(Condition.text(PITER_CHAILOVSKII.name));
    }

    public void checkServiceHeaderSubcategories() {
        serviceHeaderToggle.click();
        for (int i = 0; i < serviceHeaderSubcategories.size(); i++) {
            serviceHeaderSubcategories.get(i).should(Condition.appear);
            serviceHeaderSubcategories.get(i).shouldHave(Condition.text(ServiceSubsections.values()[i].getText()));
        }
        //closes drop down
        serviceHeaderToggle.click();
    }

    public void checkServiceLeftPanelSubcategories() {
        for (int i = 0; i < serviceLeftPanelSubcategories.size(); i++) {
            serviceLeftPanelSubcategories.get(i).shouldBe(Condition.visible);
            serviceLeftPanelSubcategories.get(i).shouldHave(Condition.text(ServiceSubsections.values()[i].getText()));
        }
    }

    public void selectSubsectionFromServiceHeaderDropDown(ServiceSubsections subsection) {
        serviceHeaderToggle.click();
        serviceLeftPanelSubcategories.findBy(Condition.text(subsection.getText())).click();
    }

    public void checkLeftSectionIsDisplayed() {
        leftSection.shouldBe(Condition.visible);
    }

    String formatDate(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }
}
