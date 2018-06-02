package hw2.ex1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;


public class Assert4TextsBelow4Images {

    @DataProvider(parallel = true)
    public Object[][] expectedBenefitTexts() {
        return new Object[][]{
                {0, "To include good practices\n" +
                        "and ideas from successful\n" +
                        "EPAM project"},
                {1, "To be flexible and\n" +
                        "customizable"},
                {2, "To be multiplatform"},
                {3, "Already have good base\n" +
                        "(about 20 internal and\n" +
                        "some external projects),\n" +
                        "wish to get more…"}
        };
    }

    @Test(dataProvider = "expectedBenefitTexts", threadPoolSize = 4, invocationCount = 1)
    public void assert4TextsBelow4ImagesTest(int textBelowImageIndex, String benefitText) {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://epam.github.io/JDI/");

        List<WebElement> textBelowImages = driver.findElements(By.cssSelector(".benefit-txt"));
        assertEquals(textBelowImages.get(textBelowImageIndex).getText(), benefitText);

        driver.close();
    }
}
