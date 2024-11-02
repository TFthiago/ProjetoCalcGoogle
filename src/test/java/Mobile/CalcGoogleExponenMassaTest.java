package Mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalcGoogleExponenMassaTest {

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

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/massaExpo.csv", numLinesToSkip = 1)
        void testeExponenciacaoMassa(String numero1, String numero2, String resultadoEsperado) throws Exception {

        // Elementos para autmação

        WebElement botaoPotencia = driver.findElement(AppiumBy.accessibilityId("power"));
        WebElement botaoResultado = driver.findElement(AppiumBy.accessibilityId("equals"));
        WebElement resultadoPreview = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_preview"));
        WebElement botaoClear = driver.findElement(AppiumBy.accessibilityId("clear"));

    // Sequência de ações

        //Digitar algarismo
        for (char digit : numero1.toCharArray()) {
            WebElement elementoNumero = driver.findElement(AppiumBy.accessibilityId(String.valueOf(digit)));
            elementoNumero.click();
        }

        botaoPotencia.click();

        //Digitar algarismo
        for (char digit : numero2.toCharArray()) {
            WebElement elementoNumero = driver.findElement(AppiumBy.accessibilityId(String.valueOf(digit)));
            elementoNumero.click();
        }

        //Esperar pelo resultado
        wait.until(ExpectedConditions.elementToBeClickable(resultadoPreview));

        botaoResultado.click();

        //Validar resultado
        WebElement elementoResultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals(resultadoEsperado, elementoResultado.getText());
        System.out.println("A operação de " + numero1 + " x " + numero2 + " é " + elementoResultado.getText());

        //Limpar calculadora
        botaoClear.click();

        }
}

