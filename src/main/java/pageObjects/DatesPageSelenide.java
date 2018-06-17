package pageObjects;

import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Date;

public class DatesPageSelenide extends HomePageSelenide {

    @FindBy(css = ".ui-slider a:nth-child(1)")
    private SelenideElement leftSliderHandle;

    @FindBy(css = ".ui-slider a:nth-child(3)")
    private SelenideElement rightSliderHandle;

    @FindBy(css = ".logs li")
    private ElementsCollection eventLogRecords;

    public Date setLeftSliderRangeAndGetEventDate(int value) throws Exception {
        moveSliderTo(value, leftSliderHandle, 0, getSliderValue(rightSliderHandle));
        return new Date();
    }

    public Date seaRightSliderRangeAndGetEventDate(int value) throws Exception {
        moveSliderTo(value, rightSliderHandle, getSliderValue(leftSliderHandle), 100);
        return new Date();
    }

    public void checkChangeRangeFromEventFired(Date eventTime, int value) {
        String expectedEvent = formatDate(eventTime) + " Range 2(From):" + value + " link clicked";
        eventLogRecords.filterBy(Condition.text(expectedEvent)).shouldHave(CollectionCondition.size(1));
    }

    public void checkChangeRangeToEventFired(Date eventTime, int value) {
        String expectedEvent = formatDate(eventTime) + " Range 2(To):" + value + " link clicked";
        eventLogRecords.filterBy(Condition.text(expectedEvent)).shouldHave(CollectionCondition.size(1));
    }

    private int getSliderValue(SelenideElement sliderHandle) {
        return Integer.parseInt(sliderHandle.getText());
    }

    private void moveSliderTo(int value, SelenideElement sliderHandle, int minValue, int maxValue) throws Exception {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        int moveOffset = 3;
        if (value >= minValue || value <= maxValue) {
            actions.clickAndHold(sliderHandle).perform();
            while (getSliderValue(sliderHandle) != value) {
                if (getSliderValue(sliderHandle) < value) {
                    actions.moveByOffset(moveOffset, 0).perform();
                }
                if (getSliderValue(sliderHandle) > value) {
                    actions.moveByOffset(-moveOffset, 0).perform();
                }
            }
            actions.release().perform();
        } else {
            throw new Exception("Slider value is out of range. Please fix the test.");
        }
    }
}
