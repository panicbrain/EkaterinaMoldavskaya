package Base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    private long initTime;

    @BeforeSuite
    public void beforeSuite(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

    }

    @AfterSuite
    public void afterSuite(){
        System.out.println(System.currentTimeMillis());

    }
}
