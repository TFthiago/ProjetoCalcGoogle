package Mobile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalcGoogleTest {

    private AndroidDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "emulator-5554")
                .amend("appium:automationName", "uiautomator2")
                .amend("appium:appPackage", "com.google.android.calculator")
                .amend("appium:appActivity", "com.android.calculator2.Calculator")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL privateUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(privateUrl, options);
        wait = new WebDriverWait(driver, Duration.ofMillis(20000));

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void sampleTest() {
        var el1 = driver.findElement(AppiumBy.accessibilityId("2"));
        el1.click();
        var el2 = driver.findElement(AppiumBy.accessibilityId("0"));
        el2.click();
        var el3 = driver.findElement(AppiumBy.accessibilityId("multiply"));
        el3.click();
        var el4 = driver.findElement(AppiumBy.accessibilityId("1"));
        el4.click();
        var el5 = driver.findElement(AppiumBy.accessibilityId("0"));
        el5.click();
        var el6 = driver.findElement(AppiumBy.accessibilityId("equals"));
        el6.click();
    }
}

