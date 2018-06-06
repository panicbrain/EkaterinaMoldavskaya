package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.DifferentElementsPageCheckboxes;
import enums.DifferentElementsPageDropDownOptions;
import enums.DifferentElementsPageRadioButtons;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DifferentElementsPageSelenide extends EpamJDIPageSelenide{
    @FindBy(css = ".label-checkbox")
    private ElementsCollection checkboxes;
    @FindBy(css = ".label-radio")
    private ElementsCollection radioButtons;
    @FindBy(css = ".colors select")
    private SelenideElement colorsDropDown;
    @FindBy(css = "[name='Default Button']")
    private SelenideElement defaultButton;
    @FindBy(css = "[type=button]")
    private SelenideElement button;
    @FindBy(css = "[name='log-sidebar']")
    private SelenideElement rightSection;
    @FindBy(css = ".logs li")
    private ElementsCollection eventLogRecords;

    public void checkCheckBoxesAreDisplayed() {
        checkboxes.shouldBe(CollectionCondition.size(4));
        for(SelenideElement checkbox : checkboxes) {
            checkbox.shouldBe(Condition.visible);
        }
    }

    public void checkRadioButtonsAreDisplayed() {
        radioButtons.shouldBe(CollectionCondition.size(4));
        for(SelenideElement radioButton : radioButtons) {
            radioButton.shouldBe(Condition.visible);
        }
    }

    public void checkColorDropDownIsDisplayed() {
        colorsDropDown.shouldBe(Condition.visible);
    }

    public void checkButtonsAreDisplayed() {
        defaultButton.shouldBe(Condition.visible);
        button.shouldBe(Condition.visible);
    }

    public void checkRightSectionIsDisplayed() {
        rightSection.shouldBe(Condition.visible);
    }

    public Map<DifferentElementsPageCheckboxes, Date> selectCheckBoxesAndGetEventsDates(
            DifferentElementsPageCheckboxes... checkboxNames
    ) {
        HashMap<DifferentElementsPageCheckboxes, Date> checkboxToDateMap =
                new HashMap<DifferentElementsPageCheckboxes, Date>();
        for (DifferentElementsPageCheckboxes checkboxName : checkboxNames) {
            checkboxes.findBy(Condition.text(checkboxName.getText())).shouldBe(Condition.enabled).click();
            checkboxToDateMap.put(checkboxName, new Date());

        }
        return checkboxToDateMap;
    }

    public Date selectRadioButtonAndGetEventsDate(DifferentElementsPageRadioButtons radioButton) {
        radioButtons.findBy(Condition.text(radioButton.getText())).shouldBe(Condition.enabled).click();
        return new Date();
    }

    public Date selectColorDropDownValueAndGetEventDate(DifferentElementsPageDropDownOptions option) {
        colorsDropDown.shouldBe(Condition.enabled).selectOption(option.getText());
        return new Date();
    }

    public void checkEventFired(DifferentElementsPageCheckboxes checkbox, Date eventTime, boolean isChecked) {
        String expectedEvent = formatDate(eventTime) + " "+ checkbox.getText() + ": condition changed to " + isChecked;
        checkEventFired(expectedEvent);
    }

    public void checkEventFired(DifferentElementsPageRadioButtons radioButton, Date eventTime) {
        String expectedEvent = formatDate(eventTime) + " metal: value changed to " + radioButton.getText();
        checkEventFired(expectedEvent);
    }

    public void checkEventFired(DifferentElementsPageDropDownOptions option, Date eventTime) {
        String expectedEvent = formatDate(eventTime) + " Colors: value changed to " + option.getText();
        checkEventFired(expectedEvent);
    }

    private void checkEventFired(String expectedEvent) {
        eventLogRecords.filterBy(Condition.text(expectedEvent)).shouldHave(CollectionCondition.size(1));
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }
}
