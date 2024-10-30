package Mobile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    public void calcTest() {

        //Operação: 20 x 10 = 200

        var algarismo2 = driver.findElement(AppiumBy.accessibilityId("2"));
        algarismo2.click();

        var algarismo0 = driver.findElement(AppiumBy.accessibilityId("0"));
        algarismo0.click();

        var funcaoMultiplicar = driver.findElement(AppiumBy.accessibilityId("multiply"));
        funcaoMultiplicar.click();

        var algarismo1 = driver.findElement(AppiumBy.accessibilityId("1"));
        algarismo1.click();

        algarismo0.click();

        var funcaoResultado = driver.findElement(AppiumBy.accessibilityId("equals"));
        funcaoResultado.click();

        //Validação
        var infoResultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals("200", infoResultado.getText());

        //Limpar
        var funcaoClear = driver.findElement(AppiumBy.accessibilityId("clear"));
        funcaoClear.click();

        //Operação: 108 x 349 = 37.692

        algarismo1.click();

        algarismo0.click();

        var algarismo8 = driver.findElement(AppiumBy.accessibilityId("8"));
        algarismo8.click();

        funcaoMultiplicar.click();

        var algarismo3 = driver.findElement(AppiumBy.accessibilityId("3"));
        algarismo3.click();

        var algarismo4 = driver.findElement(AppiumBy.accessibilityId("4"));
        algarismo4.click();

        var algarismo9 = driver.findElement(AppiumBy.accessibilityId("9"));
        algarismo9.click();

        funcaoResultado.click();

        //Validação
        assertEquals("37692", infoResultado.getText());

        funcaoClear.click();

        //Operação: 50 ^ 27 = 7.45058059692E45

        var algarismo5 = driver.findElement(AppiumBy.accessibilityId("5"));
        algarismo5.click();

        algarismo0.click();

        var funcaoPotencia = driver.findElement(AppiumBy.accessibilityId("power"));
        funcaoPotencia.click();

        algarismo2.click();

        var algarismo7 = driver.findElement(AppiumBy.accessibilityId("7"));
        algarismo7.click();

        funcaoResultado.click();

        //Validação
        assertEquals("7.45058059692E45", infoResultado.getText());

    }
}

