package Base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {
    private long initTime;

    @BeforeSuite
    public void beforeSuite(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

        Configuration.browser = "chrome";
        Configuration.screenshots = false;
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200;
        Configuration.collectionsPollingInterval = 350;

    }

    @AfterSuite
    public void afterSuite(){
        System.out.println(System.currentTimeMillis());

    }
}
